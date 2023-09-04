package soongmile.soongmileback.response;

import lombok.Builder;
import lombok.Data;
import soongmile.soongmileback.domain.Question;

import java.util.List;

@Data
public class MyPageQuestionsResponse {

    private List<Question> questions;

    @Builder
    public MyPageQuestionsResponse(List<Question> questions) {
        this.questions = questions;
    }
}
