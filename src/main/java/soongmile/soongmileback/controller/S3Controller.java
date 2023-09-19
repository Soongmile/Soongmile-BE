package soongmile.soongmileback.controller;

import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import soongmile.soongmileback.dto.FileDto;
import soongmile.soongmileback.service.AwsS3UploadService;
import soongmile.soongmileback.service.FileService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Api(tags = "file", value = "File API")
@RestController
@RequestMapping("/test/file")
@RequiredArgsConstructor
public class S3Controller {

    private final FileService fileService;

//    @Operation(summary = "파일 업로드", description = "파일 업로드 API")
//    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, value = "/list")
//    public ResponseEntity<Map<String, String>> uploadFiles(@RequestPart List<MultipartFile> fileList) throws IOException {
//        return ResponseEntity.ok(fileService.save(fileList));
//    }

    @Operation(summary = "파일 업로드", description = "파일 업로드 API")
    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FileDto> uploadFile(@RequestPart MultipartFile file) throws IOException {
        return ResponseEntity.ok(fileService.save(file));
    }

    @Operation(summary = "파일 리스트 업로드", description = "파일 리스트 업로드 API")
    @PostMapping(value = "/list",consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<FileDto>> uploadFiles(@RequestPart List<MultipartFile> files) throws IOException {
        return ResponseEntity.ok(fileService.save(files));
    }

}
