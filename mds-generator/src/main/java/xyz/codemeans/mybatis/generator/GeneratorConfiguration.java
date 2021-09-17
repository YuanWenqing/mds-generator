package xyz.codemeans.mybatis.generator;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author yuanwq
 */
@Configuration
@ComponentScan
@EnableConfigurationProperties(GeneratorProperties.class)
public class GeneratorConfiguration {

}
