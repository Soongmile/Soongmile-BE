package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.parameters.P;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.request.SignInRequest;
import soongmile.soongmileback.domain.request.SignUpRequest;
import soongmile.soongmileback.service.EmailService;
import soongmile.soongmileback.service.MemberService;

import javax.validation.Valid;

@Api(tags = "members", value = "회원 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final MemberService memberService;
    private final EmailService emailService;

    @Operation(summary = "회원가입", description = "회원가입 API")
    @PostMapping("/join")
    public String join(@RequestBody @Valid SignUpRequest signUpRequest) {
        try {
            memberService.create(signUpRequest);
            return "redirect:/";
        } catch (RuntimeException e) {
            return "/join";
        }
    }

    // 회원가입 - 이메일 인증번호 전송
    @Operation(summary = "회원가입 중 학교 이메일 인증번호 전송", description = "학교 이메일 인증번호 전송 API")
    @PostMapping("/emailCode")
    public String sendEmailCode(@RequestParam String email) throws Exception {
        return emailService.sendSimpleMessage(email);
    }

    // 회원가입 - 이메일 인증번호 매칭
    @Operation(summary = "전송된 인증번호와 매칭", description = "인증번호 매칭 API")
    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String code) throws ChangeSetPersister.NotFoundException {
        try {
            return emailService.verifyEmail(code);
        } catch (ChangeSetPersister.NotFoundException e) {
            return "유효하지 않은 인증번호입니다.";
        }
    }


    // 로그인
    @Operation(summary = "로그인", description = "로그인 API")
    @PostMapping("/login")
    public String login(@RequestBody SignInRequest signInRequest) {
        try {
            return memberService.login(signInRequest);
        } catch (UsernameNotFoundException e) {
            return "사용자가 존재하지 않습니다.";
            // return ResponseDto.fail(HttpStatus.N, "사용자가 존재하지 않습니다.");
        } catch (IllegalStateException e) {
            return "비밀번호 틀림 ㅋ";
            // return new BaseResponse<>(INVALID_PASSWORD);
        } catch (Exception e) {
            return "걍 안돼 ㅠ";
            // return new BaseResponse<>(LOGIN_ERROR);
        }
    }
}
