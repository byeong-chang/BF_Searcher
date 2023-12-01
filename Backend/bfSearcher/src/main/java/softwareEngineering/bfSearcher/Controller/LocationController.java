package softwareEngineering.bfSearcher.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import softwareEngineering.bfSearcher.DTO.*;
import softwareEngineering.bfSearcher.Entity.Review;
import softwareEngineering.bfSearcher.Service.LocationService;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    @PostMapping("Location")
    public List<LocationDto> Location(@RequestBody LocationRecommendDto locationRecommendDto) {
        return locationService.getRecommendLocation(locationRecommendDto);
    }

    @PostMapping("LocationSearch")
    public List<LocationDto> LocationSearch(@RequestBody LocationSearchDto locationSearchDto){
        return locationService.getSearchLocation(locationSearchDto);
    }

    @GetMapping("LocationReview/{locationId}")
    public List<LocationReviewDto> LocationReview(@PathVariable Long locationId){
        return locationService.getLocationReviews(locationId);
    }
    @GetMapping("LocationDetail/{locationId}")
    public LocationDetail LocationDetail(@PathVariable Long locationId){
        return locationService.getDetailLocation(locationId);
    }
}
