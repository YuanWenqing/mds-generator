package mds.example.model.user;

import java.time.LocalDateTime;
import java.util.List;
import lombok.Data;
import mds.example.model.ModelParent;
import xyz.codemeans.mybatis.generator.annotation.MdsExclude;

/**
 * @author yuanwq
 */
@Data
public class UserProfile extends ModelParent {

  private long id;
  private String nickname;
  private boolean active;
  private LocalDateTime createAt;
  private List<String> tags;
  private UserGender gender;

  @MdsExclude
  private String excludeField;
}
