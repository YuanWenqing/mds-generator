package xyz.codemeans.mybatis.generator;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import xyz.codemeans.mybatis.generator.config.GenerationDef;
import xyz.codemeans.mybatis.generator.config.TypeConfig;

/**
 * @author yuanwq
 */
@Data
@ConfigurationProperties("codemeans.mybatis.generator")
public class GeneratorProperties {

  private List<GenerationDef> generations = Lists.newArrayList();
  @NestedConfigurationProperty
  private TypeConfig types = new TypeConfig();

}
