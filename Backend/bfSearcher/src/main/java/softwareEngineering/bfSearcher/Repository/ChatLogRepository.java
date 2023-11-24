package softwareEngineering.bfSearcher.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import softwareEngineering.bfSearcher.Entity.ChatLog;
import softwareEngineering.bfSearcher.Entity.Recruitment;

import java.util.List;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {
    // Recruitment 기준 Log 가져오기 + Chat 대댓글 가져오기
    List<ChatLog> findByRecruitmentId(Long recruitmentId);
    ChatLog save(ChatLog chatLog);
}

