package mds.example.model.user;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;

/**
 * @author yuanwq
 */
@Data
public class UserProfile {

  private long id;
  private String nickname;
  private boolean active;
  private LocalDateTime createAt;
  private List<String> tags;
  private UserGender gender;
}
