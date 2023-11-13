package softwareEngineering.bfSearcher.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import softwareEngineering.bfSearcher.DTO.MatchingLogDto;
import softwareEngineering.bfSearcher.DTO.RecruitmentDto;
import softwareEngineering.bfSearcher.Entity.MatchingLog;
import softwareEngineering.bfSearcher.Entity.Recruitment;
import softwareEngineering.bfSearcher.Service.MatchingService;

import java.util.List;

@RestController
@RequestMapping("")
@RequiredArgsConstructor
public class MatchingController {
    private final MatchingService matchingService;

    @PostMapping("writeRecruitment")
    public Recruitment writeRecruitment(@RequestBody RecruitmentDto recruitmentDto){
        return matchingService.writeRecruitment(recruitmentDto);
    }

    @GetMapping("showFiveRecruitment")
    public List<Recruitment> showRecentFiveRecruitment(){
        return matchingService.showRecentFiveRecruitment();
    }

    @PostMapping("saveMatchingResult")
    public MatchingLog saveMatchingResult(@RequestBody MatchingLogDto matchingLogDto){
        return matchingService.saveMatchingResult(matchingLogDto.getFlag(),matchingLogDto.getRecruitmentId(),matchingLogDto.getVolunteerUserId());
    }

}
