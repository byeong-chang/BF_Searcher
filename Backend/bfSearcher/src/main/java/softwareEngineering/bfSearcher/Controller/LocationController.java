package softwareEngineering.bfSearcher.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import softwareEngineering.bfSearcher.DTO.LocationDto;
import softwareEngineering.bfSearcher.Service.LocationService;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class LocationController {

    private final LocationService locationService;
    @GetMapping("Location/{token}")
    public List<LocationDto> Location(@PathVariable String token,
                                      @RequestParam double latitude,
                                      @RequestParam double longitude) {
        return locationService.getRecommendLocation(token, latitude, longitude);
    }

}
