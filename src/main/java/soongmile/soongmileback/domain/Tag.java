package soongmile.soongmileback.domain;


import lombok.*;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tag")
public class Tag {

    @Id
    @GeneratedValue
    private Long id;

    @Enumerated(STRING)
    @Column(name = "field", nullable = false)
    private Field field;

    @Column(name = "tag", nullable = false)
    private String tagName;

    public static Tag create(Field field, String tagName) {
        return Tag.builder()
                .field(field)
                .tagName(tagName)
                .build();
    }
}