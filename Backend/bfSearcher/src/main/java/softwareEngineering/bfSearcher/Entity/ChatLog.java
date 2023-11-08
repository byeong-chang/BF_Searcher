package softwareEngineering.bfSearcher.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "chat_log")
public class ChatLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "volunteer_user_id")
    private User volunteerUser;

    @ManyToOne
    @JoinColumn(name = "matching_user_id")
    private User matchingUser;

    @ManyToOne
    @JoinColumn(name = "recruitment_id")
    private Recruitment recruitment;
    @Column(name = "data", columnDefinition = "TEXT")
    private String data;

    @Column(name = "flag")
    private Integer flag;


    // 추가적인 필드, 생성자, getter, setter 등이 있을 수 있습니다.

    // 생성자, Getter 및 Setter는 필요에 따라 자유롭게 추가할 수 있습니다.
}