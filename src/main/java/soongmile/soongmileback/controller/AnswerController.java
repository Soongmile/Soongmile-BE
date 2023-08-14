package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.request.AnswerCreateRequest;
import soongmile.soongmileback.service.AnswerService;


import javax.validation.Valid;
import java.security.Principal;

@Api(tags = "answer", value = "Answer API")
@Controller
@RequestMapping("/user/answer")
@RequiredArgsConstructor
public class AnswerController {

    private final AnswerService answerService;

    @GetMapping("/test/answer") //기본 주소 요청
    public String answerWrite(Model model, Principal principal) {  //이 메서드 호출
        System.out.println(principal.getName());
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            //로그인하지 않은 사용자는 로그인 페이지로
            return "/user/login";
        }
        model.addAttribute("answerNew", new AnswerCreateRequest());
        return "questionWrite";     //호출 후 이걸 찾아감
    }

    @ResponseBody
    @Operation(summary = "답변 생성", description = "답변 생성 API")
    @PostMapping
    public ResponseEntity create(@RequestBody @Valid AnswerCreateRequest request) {
        answerService.createAnswer(request);
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "답변 조회", description = "답변 조회 API")
    @GetMapping("/{id}")
    public ResponseEntity findById(@PathVariable Long id) {
        return ResponseEntity.ok(answerService.findById(id));
    }
}
