package softwareEngineering.bfSearcher.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RecruitmentDto {


    private Long id;
    private Long locationId;
    private Long userId;
    private LocalDate reservationDate;
    private String content;
    private Integer flag;

}
