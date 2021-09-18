package xyz.codemeans.mybatis.generator.impl;

import java.io.File;
import lombok.Data;
import org.springframework.boot.context.properties.NestedConfigurationProperty;

/**
 * @author yuanwq
 */
@Data
public class GenerationDef {

  /**
   * base input dir for source code
   */
  private File srcDir;
  /**
   * input package name
   */
  private String inputPackage = "";
  private boolean recursive = false;
  /**
   * base output dir; if unset, use {@link #srcDir}
   */
  private File outDir;
  /**
   * base package name for output; if unset, use {@link #inputPackage}.sql
   */
  private String outputPackage;

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
  private NamingProfile table;
  /**
   * config for changing of auto-generated column name
   */
  @NestedConfigurationProperty
  private NamingProfile column;

}
