package softwareEngineering.bfSearcher.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import softwareEngineering.bfSearcher.DTO.LocationDto;
import softwareEngineering.bfSearcher.DTO.LocationRecommendDto;
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

}
