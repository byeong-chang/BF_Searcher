package softwareEngineering.bfSearcher.Controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import softwareEngineering.bfSearcher.DTO.ChatLogDto;
import softwareEngineering.bfSearcher.DTO.DetailRecruitmentDto;
import softwareEngineering.bfSearcher.DTO.MatchingLogDto;
import softwareEngineering.bfSearcher.DTO.RecruitmentDto;
import softwareEngineering.bfSearcher.Entity.ChatLog;
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
    @PatchMapping("removeRecruitment/{recruitmentId}")
    public Recruitment removeRecruitment(@PathVariable Long recruitmentId){
        return matchingService.removeRecruitmet(recruitmentId);
    }
    @GetMapping("showFiveRecruitment")
    public List<Recruitment> showRecentFiveRecruitment(){
        return matchingService.showRecentFiveRecruitment();
    }
    @GetMapping("showAllRecruitment")
    public List<Recruitment> showAllRecruitment(){
        return matchingService.showAllRecruitment();
    }
    @GetMapping("showDetailRecruitment/{recruitmentId}")
    public DetailRecruitmentDto showDetailRecruitment(@PathVariable Long recruitmentId){
        return matchingService.showDetailRecruitment(recruitmentId);
    }
    @GetMapping("removeRecruitmentAutoByDate")
    // 이건 여행가는 날짜가 되면 등록글의 flag를 실패(1)로 변환 매일 같은시간 하루 한번 작동하게끔 하면 좋을듯
    public List<Recruitment> removeRecruitmentAutoByDate(){
        return matchingService.removeRecruitmentAutoByDate();
    }
    @PostMapping("writeChatting")
    public ChatLog writeChatting(@RequestBody ChatLogDto chatLogDto){
        return matchingService.writeChatting(chatLogDto);
    }
    @PostMapping("saveMatchingResult")
    public MatchingLog saveMatchingResult(@RequestBody MatchingLogDto matchingLogDto){
        return matchingService.saveMatchingResult(matchingLogDto.getFlag(),matchingLogDto.getRecruitmentId(),matchingLogDto.getVolunteerUserId());
    }
}
