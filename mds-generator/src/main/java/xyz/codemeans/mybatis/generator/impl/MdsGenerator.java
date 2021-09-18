package xyz.codemeans.mybatis.generator.impl;

import com.google.common.collect.Lists;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import xyz.codemeans.mybatis.generator.config.GenerationDef;

/**
 * @author yuanwq
 */
@Slf4j
public class MdsGenerator {

  private final TypeProcessor typeProcessor = new TypeProcessor();

  public List<File> generate(GenerationDef def) throws IOException {
    defaultOutputPackage(def);
    List<File> outfiles = Lists.newArrayList();
    Reflections reflections = new Reflections(def.getInputPackage(),
        new SubTypesScanner(false));
    File outdir = composeDir(def);
    for (Class<?> type : reflections.getSubTypesOf(Object.class)) {
      TypeGeneration generation = typeProcessor.process(type, def);
      File outfile = new File(outdir, generation.getSqlSupportTypeName() + ".java");
      Files.write(outfile.toPath(), generation.getContent().getBytes(def.getOutputCharset()));
    }
    System.out.println(reflections.getAllTypes());
    reflections.getSubTypesOf(Object.class)
        .forEach(System.out::println);

    return outfiles;
  }

  private void defaultOutputPackage(GenerationDef def) {
    if (def.getOutputPackage() == null || def.getOutputPackage().length() == 0) {
      def.setOutputPackage(def.getInputPackage() + ".sql");
    }
  }

  private File composeDir(GenerationDef def) {
    String subpath = def.getOutputPackage().replace("\\.", File.separator);
    return new File(def.getOutputDir(), subpath);
  }


}
