package softwareEngineering.bfSearcher.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Table(name = "matching_log")
public class MatchingLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "volunteer_user_id")
    private User volunteerUser;
    @ManyToOne
    @JoinColumn(name = "matching_user_id")
    private User matchingUser;
    @Column(name = "matching_result", length = 255)
    private String matchingResult;

}