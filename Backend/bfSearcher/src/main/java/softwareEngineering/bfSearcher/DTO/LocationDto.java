package softwareEngineering.bfSearcher.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationDto {
    private Long id;
    private String locationName;
    private String hobbyCategory;
    private String locationCategory;
    private Double latitude;
    private Double longitude;
    private String address;
    private String phoneNumber;
    private String homepage;
    private String closedDay;
    private String runtime;
    private Integer freePark;
    private Integer paidPark;
    private Integer doorForDisabled;
    private Integer wheelchairRental;
    private Integer toiletForDisabled;
    private Integer parkForDisabled;
    private Integer bigPark;
    private Integer guideDogPermitted;
    private Integer brailleGuide;
    private Integer audioGuide;
    private Double starRating;
}