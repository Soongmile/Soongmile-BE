package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.request.QuestionCreateRequest;
import soongmile.soongmileback.jwt.JwtTokenProvider;
import soongmile.soongmileback.repository.MemberRepository;
import soongmile.soongmileback.service.QuestionService;

import java.security.Principal;

@Api(tags = "question", value = "Question API")
@Controller
@RequestMapping("/user/question")
@RequiredArgsConstructor
public class QuestionController {

    private final MemberRepository memberRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final QuestionService questionService;

    @GetMapping("/test/write") //기본 주소 요청
    public String qWrite(Model model, Principal principal) {  //이 메서드 호출
        System.out.println("여기까지는 들어옴.");
        System.out.println(principal.getName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            //로그인하지 않은 사용자는 로그인 페이지로
            return "/user/login";
        }
        System.out.println("auth는 null이 아닌가봐.");
        model.addAttribute("questionNew", new QuestionCreateRequest());
        return "questionWrite";     //호출 후 이걸 찾아감
    }

    @ResponseBody
    @Operation(summary = "질문 생성", description = "질문 생성 API")
    @PostMapping
    public ResponseEntity create(@RequestHeader(value = "Authorization", required = false) String token, @RequestBody QuestionCreateRequest request) {
        String email = jwtTokenProvider.getUserPk(token);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalStateException();
        }
        questionService.createQuestion(request);
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "질문 조회", description = "질문 조회 API")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.findById(id));
    }

    @Operation(summary = "질문 좋아요", description = "질문 좋아요 API")
    @PutMapping("/like/{id}")
    public ResponseEntity like(@RequestHeader(value = "Authorization", required = false) String token, @PathVariable Long id) {
        String email = jwtTokenProvider.getUserPk(token);
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            throw new IllegalStateException();
        }
        return ResponseEntity.ok(questionService.likeById(id));
    }

}
