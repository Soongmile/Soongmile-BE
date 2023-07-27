package soongmile.soongmileback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import soongmile.soongmileback.repository.QuestionRepository;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;

    // 질문 페이지 보기
    @GetMapping("/user/question")
    public String list(Model model) {
        model.addAttribute("questionList", questionRepository.findAll());
        return "question_list";
    }
}
