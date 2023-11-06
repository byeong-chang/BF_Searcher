package softwareEngineering.bfSearcher.Entity;


import jakarta.persistence.*;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String hobby;
//    왜 HobbyCategory로 안받고 String 으로 받았느냐? -> DB는 TEXT형임
//    hobby는 중복선택 가능하게끔 설계되어있는데, 이걸 또 정규화로 분할하려니 복잡했음. MySQL의 경우 데이터를 Json으로 저장해야 할텐데 이럴거면 NOSQL을 쓰는게 나은 느낌
//    그래서 문자열로 받아서 DB의 부하를 줄이고 Java 코드에서 연산을 통해 HobbyCategory와 대응해주자 하는 취지에서 String으로 받음.

    @Column(name = "Like_location")
    private String likeLocation;

    @ManyToOne
    @JoinColumn(name = "location_category_id")
    private LocationCategory locationCategory;

    @ManyToOne
    @JoinColumn(name = "disabled_category_id")
    private DisabledCategory disabledCategory;

    @Column
    private String username;

    @Column
    private String email;

    @Column(name = "userId")
    private String userId;

    @Column
    private String passwd;

    @Column(name = "disabled")
    private Long disabledValidate;
}