package softwareEngineering.bfSearcher.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softwareEngineering.bfSearcher.DTO.RecruitmentDto;
import softwareEngineering.bfSearcher.Entity.Location;
import softwareEngineering.bfSearcher.Entity.MatchingLog;
import softwareEngineering.bfSearcher.Entity.Recruitment;
import softwareEngineering.bfSearcher.Entity.User;
import softwareEngineering.bfSearcher.Repository.LocationRepository;
import softwareEngineering.bfSearcher.Repository.MatchingLogRepository;
import softwareEngineering.bfSearcher.Repository.RecruitmentRepository;
import softwareEngineering.bfSearcher.Repository.UserRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchingService {

    private final RecruitmentRepository recruitmentRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final MatchingLogRepository matchingLogRepository;

    public Recruitment writeRecruitment(RecruitmentDto recruitmentDto){
        Recruitment recruitment = Recruitment.builder()
                .location(locationRepository.findById(recruitmentDto.getLocationId()).orElseGet(Location::new))
                .user(userRepository.findById(recruitmentDto.getUserId()).orElseGet(User::new))
                .flag(0)
                .content(recruitmentDto.getContent())
                .reservationDate(recruitmentDto.getReservationDate())
                .build();
        recruitmentRepository.save(recruitment);

        return recruitment;

    }
    public List<Recruitment> showRecentFiveRecruitment(){
        List<Recruitment> recruitmentList= recruitmentRepository.findTop5ByOrderByIdDesc();
        return recruitmentList;
    }

//    public makeChatting() - 채팅기능 어떻게 할지 자료조사가 필요함
    public MatchingLog saveMatchingResult(Boolean flag, Long recruitmentId, Long volunteerUserId){
        // 이거도 코드가 좀 이상함 입력값이라던가 매칭 기록 어떻게 남길건지고민이 필요
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseGet(Recruitment::new);
        String result = "";
        if (flag){
            result = "매칭 성공";
            recruitment.setFlag(1);
            recruitmentRepository.save(recruitment);
        }
        else{
            result = "매칭 실패";
        }
        MatchingLog matchingLog = MatchingLog.builder()
                .matchingResult(result)
                .matchingUser(recruitment.getUser())
                .volunteerUser(userRepository.findById(volunteerUserId).orElseGet(User::new))
                .build();
        matchingLogRepository.save(matchingLog);
        return matchingLog;
    }

    public Recruitment removeRecruitmet(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseGet(Recruitment::new);
        recruitment.setFlag(1);
        recruitmentRepository.save(recruitment);
        return recruitment;
    }

    public List<Recruitment> showAllRecruitment() {
        List<Recruitment> recruitmentList = recruitmentRepository.findByFlag(0L);
        return recruitmentList;
    }

    public List<Recruitment> removeRecruitmentAutoByDate() {
        List<Recruitment> recruitmentList = recruitmentRepository.findByFlag(0L);
        LocalDate today = LocalDate.now();
        List<Recruitment> removedRecruitment = new ArrayList<>();
        for (Recruitment re : recruitmentList){
            if (today.compareTo(re.getReservationDate()) >= 0 ){
                removeRecruitmet(re.getId());
                removedRecruitment.add(re);
            }
        }
        return  removedRecruitment;
    }
}
