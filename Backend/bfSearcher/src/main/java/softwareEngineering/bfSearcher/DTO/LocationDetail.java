package softwareEngineering.bfSearcher.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softwareEngineering.bfSearcher.Entity.Location;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDetail {
    private List<LocationReviewDto> locationReviewDtos;
    private Location location;
}
