package softwareEngineering.bfSearcher.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationForMapDto {
    private Long locationId;
    private String locationName;
    private String hobbyCategory;
    private Double latitude;
    private Double longitude;
}