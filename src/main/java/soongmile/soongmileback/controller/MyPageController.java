package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.response.MyPageAnswersResponse;
import soongmile.soongmileback.response.MyPageProfileResponse;
import soongmile.soongmileback.response.MyPageQuestionsResponse;
import soongmile.soongmileback.jwt.JwtTokenProvider;
import soongmile.soongmileback.repository.MemberRepository;

@Api(tags = "MyPage", value = "마이페이지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/mypage")
public class MyPageController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;

    @Operation(summary = "프로필 조회", description = "프로필 API")
    @GetMapping("/profile")
    public MyPageProfileResponse myPageProfile(@RequestHeader(value = "Authorization", required = false) String token) {
        String email = jwtTokenProvider.getUserPk(token);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalStateException();
        }
        return new MyPageProfileResponse(member.getMemberName(), member.getDepartment(), member.getMajor(), member.getField());
    }

    @Operation(summary = "내가 쓴 글 조회", description = "내가 쓴 글 API")
    @GetMapping("/questions")
    public MyPageQuestionsResponse myPageQuestions(@RequestHeader(value = "Authorization", required = false) String token) {
        String email = jwtTokenProvider.getUserPk(token);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalStateException();
        }
        return new MyPageQuestionsResponse(member.getQuestions());
    }

    @Operation(summary = "내가 쓴 답변 조회", description = "내가 쓴 답변 API")
    @GetMapping("/answers")
    public MyPageAnswersResponse myPageAnswers(@RequestHeader(value = "Authorization", required = false) String token) {
        String email = jwtTokenProvider.getUserPk(token);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalStateException();
        }
        return new MyPageAnswersResponse(member.getAnswers());
    }
}
