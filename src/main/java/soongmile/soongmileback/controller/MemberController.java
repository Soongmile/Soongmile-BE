package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.request.SignInRequest;
import soongmile.soongmileback.domain.request.SignUpRequest;
import soongmile.soongmileback.domain.response.ResponseDto;
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
    public ResponseDto join(@RequestBody @Valid SignUpRequest signUpRequest) {
        try {
            memberService.create(signUpRequest);
            return ResponseDto.success("redirect:/");
        } catch (RuntimeException e) {
            return ResponseDto.fail(HttpStatus.BAD_REQUEST, "회원가입에 실패했습니다.");
        }
    }

    @Operation(summary = "회원가입 이메일 검증 - 이메일 인증번호 전송", description = "학교 이메일 인증번호 전송 API")
    @PostMapping("/emailCode")
    public ResponseDto sendEmailCode(@RequestParam String email) throws Exception {
        return ResponseDto.success("인증 번호를 성공적으로 전송했습니다.", emailService.sendSimpleMessage(email));
    }

    @Operation(summary = "회원가입 이메일 검증 - 전송된 인증번호와 매칭", description = "인증번호 매칭 API")
    @PostMapping("/emailConfirm")
    public ResponseDto emailConfirm(@RequestParam String code) throws ChangeSetPersister.NotFoundException {
        try {
            return ResponseDto.success("인증 번호가 일치합니다.", emailService.verifyEmail(code));
        } catch (ChangeSetPersister.NotFoundException e) {
            return ResponseDto.fail(HttpStatus.BAD_REQUEST, "유효하지 않은 인증번호입니다.");
        }
    }

    @Operation(summary = "로그인", description = "로그인 API")
    @PostMapping("/login")
    public ResponseDto login(@RequestBody SignInRequest signInRequest) {
        try {
            return ResponseDto.success("로그인에 성공했습니다.", memberService.login(signInRequest));
        } catch (UsernameNotFoundException e) {
            return ResponseDto.fail(HttpStatus.BAD_REQUEST, "사용자가 존재하지 않습니다.");
        } catch (IllegalStateException e) {
            return ResponseDto.fail(HttpStatus.BAD_REQUEST, "비밀번호가 틀립니다.");
        } catch (Exception e) {
            return ResponseDto.fail(HttpStatus.INTERNAL_SERVER_ERROR, "오류가 발생했습니다.");
        }
    }
}
