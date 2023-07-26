package soongmile.soongmileback.domain;

import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@Entity
@Getter
public class Point {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "point_id")
    private Long id;

    @OneToOne(mappedBy = "point", fetch = LAZY)
    private User user;
}
