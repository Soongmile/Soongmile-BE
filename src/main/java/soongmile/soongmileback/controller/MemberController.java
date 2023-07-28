package soongmile.soongmileback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.MemberCreateForm;
import soongmile.soongmileback.service.MemberService;

import javax.validation.Valid;
import java.sql.SQLIntegrityConstraintViolationException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberCreateForm", new MemberCreateForm());
        return "join_form";
    }

    @PostMapping("/join")
    public String join(@RequestBody @Valid MemberCreateForm memberCreateForm, BindingResult bindingResult) {
        System.out.println(memberCreateForm.getEmail());
        System.out.println(memberCreateForm.getPassword());
        System.out.println(memberCreateForm.getPasswordchecker());
        System.out.println(memberCreateForm.getMembername());

        if (bindingResult.hasErrors()) {
            System.out.println("회원가입 실패???");
            return "join_form";
        }

        if (!memberCreateForm.getPassword().equals(memberCreateForm.getPasswordchecker())) {
            System.out.println("비밀번호가 일치하지 않습니다.");
            bindingResult.rejectValue("passwordchecker", "passwordIncorrect", "비밀번호가 일치하지 않습니다.");
            return "join_form";
        }

        /*try {
            memberService.create(memberCreateForm.getEmail(), memberCreateForm.getPassword(), memberCreateForm.getMembername());
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", "이미 등록된 사용자입니다.");
            return "join_form";
        } catch(Exception e) {
            e.printStackTrace();
            bindingResult.reject("signupFailed", e.getMessage());
            return "join_form";
        }*/
        memberService.create(memberCreateForm.getEmail(), memberCreateForm.getPassword(), memberCreateForm.getMembername());
        System.out.println("회원가입 성공!!!");
        return "redirect:/";
    }

    // 실제 로그인을 진행하는 @PostMapping 방식의 메서드는 스프링 시큐리티가 대신 처리하므로 직접 구현할 필요가 없다.
    @GetMapping("/login")
    public String login() {
        System.out.println("여기까지는 들어옴 ㅋ");
        return "login_form";
    }
}
