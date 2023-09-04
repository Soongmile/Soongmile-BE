package soongmile.soongmileback.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import soongmile.soongmileback.domain.Field;
import soongmile.soongmileback.domain.Tag;
import soongmile.soongmileback.request.TagCreateRequest;
import soongmile.soongmileback.request.TagUpdateRequest;
import soongmile.soongmileback.response.TagView;
import soongmile.soongmileback.repository.TagRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    }

    public Map<Field, List<String>> getAllTagMap() {
        List<Tag> all = tagRepository.findAll();

        Map<Field, List<String>> map = new HashMap<>();



        for (Tag tag : all) {
            if (map.containsKey(tag.getField())) {
                List<String> tags = map.get(tag.getField());
                tags.add(tag.getTagName());
                map.put(tag.getField(), tags);
                continue;
            }

            List<String> tags = new ArrayList<>();
            tags.add(tag.getTagName());
            map.put(tag.getField(), tags);
        }

        return map;
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
