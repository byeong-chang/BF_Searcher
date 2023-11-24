package softwareEngineering.bfSearcher.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import softwareEngineering.bfSearcher.DTO.ChatLogDto;
import softwareEngineering.bfSearcher.DTO.DetailRecruitmentDto;
import softwareEngineering.bfSearcher.DTO.RecruitmentDto;
import softwareEngineering.bfSearcher.Entity.*;
import softwareEngineering.bfSearcher.Repository.*;

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
    private final ChatLogRepository chatLogRepository;

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
        List<Recruitment> recruitmentList= recruitmentRepository.findTop5ByFlagOrderByIdDesc(0L);
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

    public DetailRecruitmentDto showDetailRecruitment(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseGet(Recruitment::new);
        List<ChatLog> chatLogList = chatLogRepository.findByRecruitmentId(recruitment.getId());
        List<ChatLogDto> chatLogDtoList = new ArrayList<>();
        for (ChatLog chat : chatLogList) {
            ChatLogDto chatLogDto = ChatLogDto.builder()
                    .currentChatId(chat.getId())
                    .recruitmentId(chat.getRecruitment().getId())
                    .currentUserToken(chat.getVolunteerUser().getToken())
                    .data(chat.getData())
                    .userName(chat.getVolunteerUser().getUsername())
                    .chatLink(chat.getChatLink())
                    .build();
            chatLogDtoList.add(chatLogDto);
        }

        DetailRecruitmentDto detailRecruitmentDto = DetailRecruitmentDto.builder()
                .chatLogList(chatLogDtoList)
                .content(recruitment.getContent())
                .flag(recruitment.getFlag())
                .reservationDate(recruitment.getReservationDate())
                .location(recruitment.getLocation())
                .user(recruitment.getUser())
                .build();
        return detailRecruitmentDto;
    }

    public ChatLog writeChatting(ChatLogDto chatLogDto) {
        Recruitment recruitment = recruitmentRepository.findById(chatLogDto.getRecruitmentId()).orElseGet(Recruitment::new);
        ChatLog chatLog = ChatLog.builder()
                .chatLink(chatLogDto.getChatLink())
                .data(chatLogDto.getData())
                .volunteerUser(userRepository.findByToken(chatLogDto.getCurrentUserToken()).orElseGet(User::new))
                .matchingUser(recruitment.getUser())
                .recruitment(recruitment)
                .build();
        chatLogRepository.save(chatLog);
        return  chatLog;
    }
}
