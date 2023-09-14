package soongmile.soongmileback.response;

import lombok.Builder;
import lombok.Data;
import soongmile.soongmileback.domain.Answer;

import java.util.List;

@Data
public class MyPageAnswersResponse {

    private List<Answer> answers;

    @Builder
    public MyPageAnswersResponse(List<Answer> answers) {
        this.answers = answers;
    }
}
