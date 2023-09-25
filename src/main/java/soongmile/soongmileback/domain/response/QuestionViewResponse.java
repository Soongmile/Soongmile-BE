package soongmile.soongmileback.domain.response;

import lombok.Data;
import soongmile.soongmileback.domain.Field;

import java.util.List;

@Data
public class QuestionViewResponse {

    private String title;
    private String content;
    private List<String> tags;
    private List<Field> fields;
    private String time;
    private int hits;
    private int answerCount;

    public QuestionViewResponse(String title, String content, List<String> tags, List<Field> fields, String time, int hits, int answerCount) {
        this.title = title;
        this.content = content;
        this.tags = tags;
        this.fields = fields;
        this.time = time;
        this.hits = hits;
        this.answerCount = answerCount;
    }
}
