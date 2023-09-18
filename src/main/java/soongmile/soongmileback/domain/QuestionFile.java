package soongmile.soongmileback.domain;


import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "question_file")
public class QuestionFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY -> AUTO
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Question question;

    @ManyToOne(fetch = FetchType.LAZY)
    private FileEntity fileEntity;
}
