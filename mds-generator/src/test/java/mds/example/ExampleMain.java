package mds.example;

import java.io.File;
import java.io.IOException;
import xyz.codemeans.mybatis.generator.GeneratorProperties;
import xyz.codemeans.mybatis.generator.GeneratorRunner;
import xyz.codemeans.mybatis.generator.config.GenerationDef;

/**
 * @author yuanwq
 */
public class ExampleMain {

  public static void main(String[] args) throws IOException {
    GeneratorProperties properties = new GeneratorProperties();
    properties.getGenerations().add(new GenerationDef()
        .setInputPackage("mds.example.model")
        .setOutputDir(new File("mds-generator/src/test/gen/"))
        .setOutputPackage("mds.example.sql")
    );
    GeneratorRunner runner = new GeneratorRunner(properties);
    runner.run();
  }
}
