package xyz.codemeans.mybatis.generator;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import xyz.codemeans.mybatis.generator.config.GenerationDef;
import xyz.codemeans.mybatis.generator.core.MdsGenerator;
import xyz.codemeans.mybatis.generator.core.TypeGeneration;
import xyz.codemeans.mybatis.generator.core.TypeMapping;
import xyz.codemeans.mybatis.generator.core.TypeProcessor;

/**
 * an example
 *
 * @author yuanwq
 */
@Slf4j
public class GeneratorRunner {

  private final GeneratorProperties properties;

  public GeneratorRunner(GeneratorProperties properties) {
    this.properties = properties;
  }

  public List<TypeGeneration> run() throws IOException {
    TypeMapping typeMapping = new TypeMapping(properties.getTypes());
    TypeProcessor typeProcessor = new TypeProcessor(typeMapping);
    MdsGenerator generator = new MdsGenerator(typeProcessor);
    List<TypeGeneration> allGenerations = Lists.newArrayList();
    for (GenerationDef def : properties.getGenerations()) {
      List<TypeGeneration> generations = generator.generate(def);
      log.info("models in {} generated: {}", def.getInputPackage(),
          Lists.transform(generations, TypeGeneration::getOutfile));
      allGenerations.addAll(generations);
    }
    return allGenerations;
  }
}
