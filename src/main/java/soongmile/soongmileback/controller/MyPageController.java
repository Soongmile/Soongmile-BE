package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.response.MyPageAnswersResponse;
import soongmile.soongmileback.domain.response.MyPageProfileResponse;
import soongmile.soongmileback.domain.response.MyPageQuestionsResponse;
import springfox.documentation.annotations.ApiIgnore;

@Api(tags = "MyPage", value = "마이페이지 API")
@RestController
@RequiredArgsConstructor
@RequestMapping("/user/mypage")
public class MyPageController {

    @Operation(summary = "프로필 조회", description = "프로필 API")
    @GetMapping("/profile")
    public MyPageProfileResponse myPageProfile(@ApiIgnore @AuthenticationPrincipal Member member) {
        if (member == null) {
            throw new IllegalStateException();
        }
        return new MyPageProfileResponse(member.getMemberName(), member.getDepartment(), member.getMajor(), member.getField());
    }

    @Operation(summary = "내가 쓴 글 조회", description = "내가 쓴 글 API")
    @GetMapping("/questions")
    public MyPageQuestionsResponse myPageQuestions(@ApiIgnore @AuthenticationPrincipal Member member) {
        if (member == null) {
            throw new IllegalStateException();
        }
        return new MyPageQuestionsResponse(member.getQuestions());
    }

    @Operation(summary = "내가 쓴 답변 조회", description = "내가 쓴 답변 API")
    @GetMapping("/answers")
    public MyPageAnswersResponse myPageAnswers(@ApiIgnore @AuthenticationPrincipal Member member) {
        if (member == null) {
            throw new IllegalStateException();
        }
        return new MyPageAnswersResponse(member.getAnswers());
    }
}
