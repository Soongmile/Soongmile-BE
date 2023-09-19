package soongmile.soongmileback.controller;


import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soongmile.soongmileback.domain.Member;
import soongmile.soongmileback.domain.request.QuestionCreateRequest;
import soongmile.soongmileback.service.FileService;

import java.io.IOException;
import java.util.List;

//@Api(tags = "file", value = "File API")
//@Controller
//@RequestMapping("/user/file")
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;

    @ResponseBody
    @Operation(summary = "파일 업로드", description = "파일 업로드 API")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity create(
            @RequestPart List<MultipartFile> fileList
    ) throws IOException {
        fileService.saveFile(fileList.get(0));
        return ResponseEntity.ok("ok");
    }

}
