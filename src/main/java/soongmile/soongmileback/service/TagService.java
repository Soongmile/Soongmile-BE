package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Field;
import soongmile.soongmileback.domain.Tag;
import soongmile.soongmileback.domain.request.TagCreateRequest;
import soongmile.soongmileback.domain.request.TagUpdateRequest;
import soongmile.soongmileback.domain.response.TagView;
import soongmile.soongmileback.repository.TagRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TagService {

    private final TagRepository tagRepository;

    @Transactional
    public void createTag(TagCreateRequest request) {
        Tag tag = Tag.create(request.getField(), request.getTagName());
        tagRepository.save(tag);
    }

    public List<TagView> getAllTags() {
        List<Tag> all = tagRepository.findAll();

        List<TagView> response = new ArrayList<>();

        for (Tag tag : all) {
            TagView tagView = TagView.builder()
                    .id(tag.getId())
                    .field(tag.getField())
                    .tagName(tag.getTagName())
                    .build();

            response.add(tagView);
        }
        return response;

//        return all.stream().map(
//                tag -> TagView.builder()
//                        .id(tag.getId())
//                        .field(tag.getField())
//                        .tagName(tag.getTagName())
//                        .build())
//                .collect(Collectors.toList());
    }

    public List<TagView> getTagsByField(Field field) {
        List<Tag> all = tagRepository.findByField(field);

        List<TagView> response = new ArrayList<>();
        for (Tag tag : all) {
            TagView tagView = TagView.builder()
                    .id(tag.getId())
                    .field(tag.getField())
                    .tagName(tag.getTagName())
                    .build();

            response.add(tagView);
        }
        return response;
    }

    @Transactional
    public void deleteTag(Long id) {
        tagRepository.deleteById(id);
    }

    @Transactional
    public void updateTag(Long id, TagUpdateRequest request) {
        Tag tag = tagRepository.findById(id).orElse(null);
        if (tag == null) {
            return;
        }

        tag.setTagName(request.getTagName());
    }
}
