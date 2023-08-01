package soongmile.soongmileback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.request.QuestionCreateRequest;
import soongmile.soongmileback.repository.QuestionRepository;
import soongmile.soongmileback.service.QuestionService;

import javax.validation.Valid;

@Tag(name = "questions", description = "Question API")
@RestController
@RequestMapping("/v1/question")
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping("/user/question/write") //기본 주소 요청
    public String qWrite(Model model) {  //이 메서드 호출

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            //로그인하지 않은 사용자는 로그인 페이지로
            return "/user/login";
        }

        model.addAttribute("questionNew", new QuestionCreateRequest());
        return "questionWrite";     //호출 후 이걸 찾아감
    }

    @Operation(summary = "질문 생성", description = "질문 생성 API")
    @PostMapping
    public ResponseEntity create(@RequestBody QuestionCreateRequest request) {
        questionService.createQuestion(request);
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "질문 조회", description = "질문 조회 API")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(questionService.findById(id));
    }
}
