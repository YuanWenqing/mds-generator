package mds.example;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import xyz.codemeans.mybatis.generator.GeneratorProperties;
import xyz.codemeans.mybatis.generator.GeneratorRunner;
import xyz.codemeans.mybatis.generator.config.GenerationDef;
import xyz.codemeans.mybatis.generator.core.TypeGeneration;

/**
 * @author yuanwq
 */
public class ExampleMain {

  public static void main(String[] args) throws IOException {
    GeneratorProperties properties = new GeneratorProperties();
    properties.getGenerations().add(new GenerationDef()
        .setInputPackage("mds.example.model")
        .setOutputDir(new File("mds-example/src/main/gen/"))
    );
    GeneratorRunner runner = new GeneratorRunner(properties);
    List<TypeGeneration> generations = runner.run();
    System.out.println(generations.stream()
        .map(TypeGeneration::getOutfile)
        .collect(Collectors.toList()));
  }
}
