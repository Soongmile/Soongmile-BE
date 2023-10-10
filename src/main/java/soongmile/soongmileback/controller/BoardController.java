package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import soongmile.soongmileback.domain.response.ResponseDto;
import soongmile.soongmileback.service.QuestionService;

@Api(tags = "board", value = "메인 화면 API")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final QuestionService questionService;

    @Operation(summary = "메인 화면", description = "메인 화면 API")
    @PostMapping
    public ResponseDto showMainPage() {
        return ResponseDto.success("페이지와 사이즈에 맞는 질문글들을 반환합니다.", questionService.getMainPage());
    }

    @Operation(summary = "제목 검색 기능", description = "제목 검색 기능 API")
    @PostMapping("/search/title")
    public ResponseDto searchTitle(@RequestParam String keyword) {
        return ResponseDto.success("제목에서 키워드를 포함하는 질문글을 반환합니다.", questionService.searchTitle(keyword));
    }

    @Operation(summary = "내용 검색 기능", description = "내용 검색 기능 API")
    @PostMapping("/search/content")
    public ResponseDto searchContent(@RequestParam String keyword) {
        return ResponseDto.success("내용에서 키워드를 포함하는 질문글을 반환합니다.", questionService.searchContent(keyword));
    }

    @Operation(summary = "제목 & 내용 검색 기능", description = "제목 & 내용 검색 기능 API")
    @PostMapping("/search/both")
    public ResponseDto searchTitleAndContent(@RequestParam String keyword) {
        return ResponseDto.success("제목 & 내용에서 키워드를 포함하는 질문글을 반환합니다.", questionService.searchTitleAndContent(keyword));
    }
}
