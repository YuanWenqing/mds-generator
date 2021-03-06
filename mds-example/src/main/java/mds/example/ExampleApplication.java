package mds.example;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import xyz.codemeans.mybatis.generator.GeneratorProperties;
import xyz.codemeans.mybatis.generator.GeneratorRunner;
import xyz.codemeans.mybatis.generator.core.TypeGeneration;

/**
 * @author yuanwq
 */
@SpringBootApplication
@EnableConfigurationProperties(GeneratorProperties.class)
public class ExampleApplication implements CommandLineRunner {

  @Autowired
  private GeneratorProperties properties;

  @Override
  public void run(String... args) throws Exception {
    GeneratorRunner runner = new GeneratorRunner(properties);
    List<TypeGeneration> generations = runner.run();
    System.out.println(generations.stream()
        .map(TypeGeneration::getOutfile)
        .collect(Collectors.toList()));
  }

  public static void main(String[] args) {
    SpringApplication.run(ExampleApplication.class, args);
  }
}
