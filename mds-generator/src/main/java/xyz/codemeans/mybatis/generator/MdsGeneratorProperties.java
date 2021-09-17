package xyz.codemeans.mybatis.generator;

import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import xyz.codemeans.mybatis.generator.impl.GenerationDef;

/**
 * @author yuanwq
 */
@Data
@ConfigurationProperties("mds-generator")
public class MdsGeneratorProperties {

  @NestedConfigurationProperty
  private List<GenerationDef> generations;


}
