package softwareEngineering.bfSearcher.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softwareEngineering.bfSearcher.Entity.Location;
import softwareEngineering.bfSearcher.Entity.Review;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDetailDto {
    private Location location;
    private List<Review> reviews;
}
