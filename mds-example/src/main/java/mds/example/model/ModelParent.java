package mds.example.model;

import java.time.LocalDateTime;
import xyz.codemeans.mybatis.generator.annotation.MdsExclude;

/**
 * @author yuanwq
 */
@MdsExclude
public abstract class ModelParent {

  private LocalDateTime updateAt;
}
