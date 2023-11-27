package softwareEngineering.bfSearcher.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationReviewDto {
    String userName;
    Double starRating;
    String content;
}
