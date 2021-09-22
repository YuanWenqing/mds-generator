package xyz.codemeans.mybatis.generator.core;

import com.google.common.collect.Lists;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import xyz.codemeans.mybatis.generator.annotation.MdsExclude;
import xyz.codemeans.mybatis.generator.config.GenerationDef;

/**
 * @author yuanwq
 */
@Slf4j
public class MdsGenerator {

  private final TypeProcessor typeProcessor;

  public MdsGenerator(TypeProcessor typeProcessor) {
    this.typeProcessor = typeProcessor;
  }

  public List<TypeGeneration> generate(GenerationDef def) throws IOException {
    List<TypeGeneration> generations = Lists.newArrayList();
    Reflections reflections = new Reflections(def.getInputPackage(),
        new SubTypesScanner(false));
    for (Class<?> type : reflections.getSubTypesOf(Object.class)) {
      MdsExclude mdsExclude = type.getAnnotation(MdsExclude.class);
      if (mdsExclude == null) {
        continue;
      }
      TypeGeneration generation = typeProcessor.process(type, def);
      generation.getOutfile().getParentFile().mkdirs();
      Files.write(generation.getOutfile().toPath(),
          generation.getContent().getBytes(def.getOutputCharset()));
      generations.add(generation);
    }
    return generations;
  }

}
