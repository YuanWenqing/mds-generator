package xyz.codemeans.mybatis.generator.config;

import com.google.common.base.CaseFormat;
import java.io.File;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import lombok.Data;
import lombok.experimental.Accessors;

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
   * @apiNote default suffix: `DynamicSqlSupport`
   */
  private NamingProfile sqlSupportTypeNaming;
  /**
   * config for name changing of auto-generated SqlTable instance in SqlSupport
   *
   * @apiNote default format conversion: UPPER_CAMEL to LOWER_CAMEL
   */
  private NamingProfile sqlTableInstanceNaming;
  /**
   * config for name changing of static SqlColumn fields in auto-generated SqlSupport type
   */
  private NamingProfile sqlSupportFieldNaming;
  /**
   * config for name changing of auto-generated SqlTable subclass
   */
  private NamingProfile sqlTableTypeNaming;
  /**
   * config for name changing of fields in auto-generated SqlTable
   */
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
   *
   * @apiNote default format conversion: UPPER_CAMEL to LOWER_UNDERSCORE
   */
  private NamingProfile tableNaming;
  /**
   * config for changing of auto-generated column name
   *
   * @apiNote default format conversion: LOWER_CAMEL to LOWER_UNDERSCORE
   */
  private NamingProfile columnNaming;

  public GenerationDef() {
    sqlSupportTypeNaming.setSuffix("DynamicSqlSupport");
    sqlTableInstanceNaming
        .setFromFormat(CaseFormat.UPPER_CAMEL)
        .setToFormat(CaseFormat.LOWER_CAMEL);
    tableNaming
        .setFromFormat(CaseFormat.UPPER_CAMEL)
        .setToFormat(CaseFormat.LOWER_UNDERSCORE);
    columnNaming
        .setFromFormat(CaseFormat.LOWER_CAMEL)
        .setToFormat(CaseFormat.LOWER_UNDERSCORE);
  }
}
