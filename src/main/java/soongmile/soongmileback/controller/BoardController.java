package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soongmile.soongmileback.domain.response.ResponseDto;
import soongmile.soongmileback.service.QuestionService;

@Api(tags = "board", value = "메인 화면 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final QuestionService questionService;

    @Operation(summary = "메인 화면", description = "메인 화면 API")
    @PostMapping("/main")
    public ResponseDto showMainPage(@RequestParam int page, @RequestParam int size) {
        return ResponseDto.success("페이지와 사이즈에 맞는 질문글들을 반환합니다.", questionService.getMainPage(page, size));
    }
}
