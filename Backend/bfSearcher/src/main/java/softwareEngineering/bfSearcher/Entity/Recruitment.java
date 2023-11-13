package softwareEngineering.bfSearcher.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "recruitment")
public class Recruitment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "reservation_date")
    private LocalDate reservationDate;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "flag")
    private Integer flag;


    // 추가적인 필드, 생성자, getter, setter 등이 있을 수 있습니다.

    // 생성자, Getter 및 Setter는 필요에 따라 자유롭게 추가할 수 있습니다.
}