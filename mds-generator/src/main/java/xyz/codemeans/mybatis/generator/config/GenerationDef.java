package xyz.codemeans.mybatis.generator.config;

import com.google.common.base.CaseFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
   *
   * @apiNote required
   */
  @NotBlank
  private String inputPackage;
  /**
   * root output dir
   *
   * @apiNote required
   */
  @NotNull
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
  private NamingProfile sqlSupportTypeNaming = new NamingProfile();
  /**
   * config for name changing of auto-generated SqlTable instance in SqlSupport
   *
   * @apiNote default format conversion: UPPER_CAMEL to LOWER_CAMEL
   */
  private NamingProfile sqlTableInstanceNaming = new NamingProfile();
  /**
   * config for name changing of static SqlColumn fields in auto-generated SqlSupport type
   */
  private NamingProfile sqlSupportFieldNaming = new NamingProfile();
  /**
   * config for name changing of auto-generated SqlTable subclass
   */
  private NamingProfile sqlTableTypeNaming = new NamingProfile();
  /**
   * config for name changing of fields in auto-generated SqlTable
   */
  private NamingProfile sqlTableFieldNaming = new NamingProfile();
  /**
   * base package name for output; if unset, use {@code <inputType.package>.sql}
   */
  private String outputPackage;
  /**
   * whether to keep the same package structure for output as like #inputPackage. Only works if
   * {@link #outputPackage} is set.
   */
  private boolean keepPackageStructure = true;
  /**
   * whether to generate for inherited fields
   */
  private boolean inheritField = true;
  private int indentSize = 2;
  /**
   * whether to generate Fields class which contains namings of all fields in model type
   */
  private boolean generateFields = false;
  /**
   * config for name changing of auto-generated Fields class
   */
  private NamingProfile FieldsTypeNaming = new NamingProfile();

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
  private NamingProfile tableNaming = new NamingProfile();
  /**
   * config for changing of auto-generated column name
   *
   * @apiNote default format conversion: LOWER_CAMEL to LOWER_UNDERSCORE
   */
  private NamingProfile columnNaming = new NamingProfile();

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
