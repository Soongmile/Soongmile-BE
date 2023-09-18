package soongmile.soongmileback.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "answer_file")
public class AnswerFile {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO) //IDENTITY -> AUTO
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Answer answer;

    @ManyToOne(fetch = FetchType.LAZY)
    private FileEntity fileEntity;
}
