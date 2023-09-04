package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.request.SignInRequest;
import soongmile.soongmileback.request.SignUpRequest;
import soongmile.soongmileback.jwt.JwtTokenProvider;
import soongmile.soongmileback.repository.MemberRepository;
import soongmile.soongmileback.service.EmailService;
import soongmile.soongmileback.service.MemberService;

import javax.validation.Valid;

@Api(tags = "members", value = "회원 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final MemberService memberService;
    private final EmailService emailService;
    private final PasswordEncoder passwordEncoder;

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

    // 여기서 인증코드 매칭하고 인증됐는지 안됐는지 여부를 알려줘야 함!
    // 회원가입 - 이메일 인증
    @Operation(summary = "회원가입 중 학교 이메일 인증", description = "학교 이메일 인증 API")
    @PostMapping("/emailConfirm")
    public String emailConfirm(@RequestParam String email) throws Exception {
        return emailService.sendSimpleMessage(email);
    }

    // 로그인
    @Operation(summary = "로그인", description = "로그인 API")
    @PostMapping("/login")
    public String login(@RequestBody SignInRequest signInRequest) {
        try {
            Member member = memberRepository.findByEmail(signInRequest.getEmail());

            if (member == null) {
                throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
            }

            if (!passwordEncoder.matches(signInRequest.getPassword(), member.getPassword())) {
                throw new IllegalStateException("비밀번호가 틀립니다.");
            }

            String token = jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
            return token;
        } catch (UsernameNotFoundException e) {
            return "없는 사람";
            // return new BaseResponse<>(INVALID_USER);
        } catch (IllegalStateException e) {
            return "비밀번호 틀림 ㅋ";
            // return new BaseResponse<>(INVALID_PASSWORD);
        } catch (Exception e) {
            return "걍 안돼 ㅠ";
            // return new BaseResponse<>(LOGIN_ERROR);
        }
    }
}
