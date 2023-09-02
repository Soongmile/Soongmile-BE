package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.MemberCreateForm;
import soongmile.soongmileback.domain.request.LoginRequest;
import soongmile.soongmileback.jwt.JwtTokenProvider;
import soongmile.soongmileback.repository.MemberRepository;
import soongmile.soongmileback.service.MemberService;

import javax.validation.Valid;
import java.util.Map;

@Api(tags = "members", value = "회원 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class MemberController {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final MemberService memberService;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("memberCreateForm", new MemberCreateForm());
        return "join_form";
    }

    @Operation(summary = "회원가입", description = "회원가입 API")
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

        memberService.create(memberCreateForm.getEmail(), memberCreateForm.getPassword(), memberCreateForm.getMembername());
        System.out.println("회원가입 성공!!!");
        return "redirect:/";
    }

    // 실제 로그인을 진행하는 @PostMapping 방식의 메서드는 스프링 시큐리티가 대신 처리하므로 직접 구현할 필요가 없다.
    @GetMapping("/login")
    public String login() {
        return "login_form";
    }

    // 로그인
    @Operation(summary = "로그인", description = "로그인 API")
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        try {
            Member member = memberRepository.findByEmail(loginRequest.getEmail());

            if (member == null) {
                throw new UsernameNotFoundException("사용자를 찾을수 없습니다.");
            }

            if (!member.getPassword().equals(loginRequest.getPassword())) {
                throw new IllegalStateException("비밀번호가 틀립니다.");
            }

            return jwtTokenProvider.createToken(member.getUsername(), member.getRoles());
        } catch (UsernameNotFoundException e) {
            // return new BaseResponse<>(INVALID_USER);
        } catch (IllegalStateException e) {
            // return new BaseResponse<>(INVALID_PASSWORD);
        } catch (Exception e) {
            // return new BaseResponse<>(LOGIN_ERROR);
        }
        return "";
    }
}
