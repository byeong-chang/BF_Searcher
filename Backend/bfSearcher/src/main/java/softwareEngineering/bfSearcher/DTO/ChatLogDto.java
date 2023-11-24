package softwareEngineering.bfSearcher.DTO;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softwareEngineering.bfSearcher.Entity.Recruitment;
import softwareEngineering.bfSearcher.Entity.User;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ChatLogDto {

    private Long currentChatId;
    private String currentUserToken;
    private Long recruitmentId;
    private String data;
    private Long chatLink;
    private String userName;

}
