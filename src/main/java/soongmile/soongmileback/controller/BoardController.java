package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;
import soongmile.soongmileback.domain.response.ResponseDto;
import soongmile.soongmileback.service.QuestionService;

@Api(tags = "members", value = "회원 API")
@Slf4j
@RestController
@RequiredArgsConstructor
public class BoardController {

    private final QuestionService questionService;

    /*public ResponseDto showMainPage() {

    }*/
}
