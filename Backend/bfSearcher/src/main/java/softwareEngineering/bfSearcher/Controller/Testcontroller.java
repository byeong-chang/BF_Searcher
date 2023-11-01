package softwareEngineering.bfSearcher.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import softwareEngineering.bfSearcher.DTO.LocationDto;
import softwareEngineering.bfSearcher.Service.LocationService;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class Testcontroller {

    private final LocationService locationService;

    @GetMapping(value = "test")
    public List<LocationDto> getAllLocation(){
        return locationService.getAllLocations();
    }
}
