package softwareEngineering.bfSearcher.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import softwareEngineering.bfSearcher.DTO.LocationDetailDto;
import softwareEngineering.bfSearcher.DTO.LocationDto;
import softwareEngineering.bfSearcher.DTO.LocationRecommendDto;
import softwareEngineering.bfSearcher.DTO.LocationSearchDto;
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

    @GetMapping("LocationDetail/{locationId}")
    public LocationDetailDto LocationDetail(@PathVariable int locationId){
        return locationService.getLocationDetails(locationId);
    }
}
