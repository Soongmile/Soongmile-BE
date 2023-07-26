package soongmile.soongmileback.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import soongmile.soongmileback.repository.QuestionRepository;

@Controller
@RequiredArgsConstructor
public class QuestionController {

    private final QuestionRepository questionRepository;

    @GetMapping("/question/list")
    public String list(Model model) {
        model.addAttribute("questionList", questionRepository.findAll());
        return "question_list";
    }
}
