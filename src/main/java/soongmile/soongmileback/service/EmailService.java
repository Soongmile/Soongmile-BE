package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import soongmile.soongmileback.util.RedisUtil;

import javax.mail.Message;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender emailSender;
    private final RedisUtil redisUtil;

    private static final String EMAIL_FORM = "@soongsil.ac.kr";
    private static final int EMAIL_LIMIT = 16;
    private static final int EMAIL_FORM_LENGTH = 15;

    public String verifyValidEmail(String email) {
        int length = email.length();
        if (length <= EMAIL_LIMIT || !email.substring(length - EMAIL_FORM_LENGTH, length).equals(EMAIL_FORM)) {
            throw new IllegalStateException();
        }
        return email;
    }

    private MimeMessage createMessage(String email, String verificationCode) throws Exception {
        System.out.println("보내는 대상 : " + email);
        System.out.println("인증 번호 : " + verificationCode);
        MimeMessage message = emailSender.createMimeMessage();

        message.addRecipients(Message.RecipientType.TO, email); //보내는 대상
        message.setSubject("이메일 인증 테스트"); // 제목

        String msgg = createMessage(verificationCode);

        message.setText(msgg, "utf-8", "html");//내용
        message.setFrom(new InternetAddress("soongmile@gmail.com","soongmile")); // 보내는 사람

        return message;
    }

    private String createMessage(String verificationCode) {
        String msgg="";
        msgg+= "<div style='margin:20px;'>";
        msgg+= "<h1> 안녕하세요 숭차로입니다. </h1>";
        msgg+= "<br>";
        msgg+= "<p>아래 코드를 복사해 입력해주세요<p>";
        msgg+= "<br>";
        msgg+= "<p>감사합니다.<p>";
        msgg+= "<br>";
        msgg+= "<div align='center' style='border:1px solid black; font-family:verdana';>";
        msgg+= "<h3 style='color:blue;'>회원가입 인증 코드입니다.</h3>";
        msgg+= "<div style='font-size:130%'>";
        msgg+= "CODE : <strong>";
        msgg+= verificationCode+"</strong><div><br/> ";
        msgg+= "</div>";
        return msgg;
    }

    public static String createKey() {
        Random rand = new Random();
        return String.valueOf((rand.nextInt(9000) + 1000));
    }

    public String sendSimpleMessage(String email) throws Exception {
        String verificationCode = createKey();
        MimeMessage message = createMessage(email, verificationCode);
        try {
            redisUtil.setDataExpire(verificationCode, email, 60 * 5L); // 유효시간 5분
            emailSender.send(message);
        } catch (MailException es){
            es.printStackTrace();
            throw new IllegalArgumentException();
        }
        return verificationCode;
    }

    public void verifyEmail(String code) throws ChangeSetPersister.NotFoundException {
        String memberEmail = redisUtil.getData(code);
        if (memberEmail == null) {
            throw new ChangeSetPersister.NotFoundException();
        }
        redisUtil.deleteData(code);
    }
}