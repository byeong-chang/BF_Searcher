package softwareEngineering.bfSearcher.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softwareEngineering.bfSearcher.Entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatchingLogDto {
    Boolean flag;
    Long recruitmentId;
    Long volunteerUserId;
}
