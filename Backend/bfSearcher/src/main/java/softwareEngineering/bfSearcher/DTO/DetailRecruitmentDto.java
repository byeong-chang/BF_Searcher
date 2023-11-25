package softwareEngineering.bfSearcher.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import softwareEngineering.bfSearcher.Entity.ChatLog;
import softwareEngineering.bfSearcher.Entity.Location;
import softwareEngineering.bfSearcher.Entity.User;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailRecruitmentDto {

    private Location location;
    private User user;
    private LocalDate reservationDate;
    private String title;
    private String content;
    private Integer flag;
    private List<ChatLogDto> chatLogList;
}

