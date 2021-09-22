package xyz.codemeans.mybatis.generator.core;

import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import xyz.codemeans.mybatis.generator.annotation.MdsExclude;
import xyz.codemeans.mybatis.generator.annotation.MdsGenerated;
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
    Collection<Class> types = ClassPath.from(ClassLoader.getSystemClassLoader())
        .getAllClasses()
        .stream()
        .filter(clazz -> clazz.getPackageName()
            .equalsIgnoreCase(def.getInputPackage()))
        .map(clazz -> clazz.load())
        .collect(Collectors.toSet());
    for (Class<?> type : types) {
      MdsExclude mdsExclude = type.getAnnotation(MdsExclude.class);
      MdsGenerated mdsGenerated = type.getAnnotation(MdsGenerated.class);
      if (mdsExclude != null || mdsGenerated != null) {
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
