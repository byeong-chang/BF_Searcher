package softwareEngineering.bfSearcher.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String hobby;
    private String likeLocation;
    private String locationCategory;
    private String disabledCategory;
    private String username;
    private String email;
    private String userId;
    private String passwd;
    private Long disabledValidate;
    private String token;
}