package softwareEngineering.bfSearcher.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
