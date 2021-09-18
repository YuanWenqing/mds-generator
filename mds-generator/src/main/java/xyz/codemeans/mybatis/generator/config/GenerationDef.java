package xyz.codemeans.mybatis.generator.config;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author yuanwq
 */
@Data
@Accessors(chain = true)
public class GenerationDef {

  /**
   * input package name
   */
  private String inputPackage;
  /**
   * base output dir
   */
  private File outputDir;
  /**
   * charset for output files
   */
  private Charset outputCharset = StandardCharsets.UTF_8;
  /**
   * config for name changing of auto-generated SqlSupport type
   *
   * @apiNote suffix default is `DynamicSqlSupport`
   */
  @NestedConfigurationProperty
  private NamingProfile sqlSupportTypeNaming;
  /**
   * config for name changing of auto-generated SqlTable instance in SqlSupport
   */
  @NestedConfigurationProperty
  private NamingProfile sqlTableInstanceNaming;
  /**
   * config for name changing of static SqlColumn fields in auto-generated SqlSupport type
   */
  @NestedConfigurationProperty
  private NamingProfile sqlSupportFieldNaming;
  /**
   * config for name changing of auto-generated SqlTable subclass
   */
  @NestedConfigurationProperty
  private NamingProfile sqlTableTypeNaming;
  /**
   * config for name changing of fields in auto-generated SqlTable
   */
  @NestedConfigurationProperty
  private NamingProfile sqlTableFieldNaming;
  /**
   * base package name for output; if unset, use {@link #inputPackage}.sql
   */
  private String outputPackage;
  private int indentSize = 2;

  /**
   * default catalog
   */
  private String catalog;
  /**
   * default schema
   */
  private String schema;
  /**
   * config for changing of auto-generated table name
   */
  @NestedConfigurationProperty
  private NamingProfile tableNaming;
  /**
   * config for changing of auto-generated column name
   */
  @NestedConfigurationProperty
  private NamingProfile columnNaming;

  public GenerationDef() {
    sqlSupportTypeNaming.setSuffix("DynamicSqlSupport");
  }
}
