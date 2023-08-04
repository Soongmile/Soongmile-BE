package soongmile.soongmileback.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import soongmile.soongmileback.domain.Field;
import soongmile.soongmileback.domain.request.TagCreateRequest;
import soongmile.soongmileback.domain.request.TagUpdateRequest;
import soongmile.soongmileback.service.TagService;

@Tag(name = "tag", description = "Tag API")
@RestController
@RequestMapping("/tag")
@RequiredArgsConstructor
public class TagController {
    private final TagService tagService;

    @Operation(summary = "태그 생성", description = "태그 생성 API")
    @PostMapping
    public ResponseEntity create(@RequestBody TagCreateRequest request) {
        tagService.createTag(request);
        return ResponseEntity.ok("ok");
    }

    @Operation(summary = "태그 전체 조회", description = "태그 전체 조회 API")
    @GetMapping
    public ResponseEntity getAllTags() {
        return ResponseEntity.ok(tagService.getAllTags());
    }

    @Operation(summary = "필드별 태그 조회", description = "필드별 태그 조회 API")
    @GetMapping("/{field}")
    public ResponseEntity getTagsByField(@PathVariable Field field) {
        return ResponseEntity.ok(tagService.getTagsByField(field));
    }

    @Operation(summary = "태그 삭제", description = "태그 삭제 API")
    @DeleteMapping("/{id}")
    public ResponseEntity deleteTag(@PathVariable Long id) {
        tagService.deleteTag(id);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "태그 수정", description = "태그 수정 API")
    @PutMapping("/{id}")
    public ResponseEntity updateTag(@PathVariable Long id, @RequestBody TagUpdateRequest request) {
        tagService.updateTag(id, request);
        return ResponseEntity.ok().build();
    }
}