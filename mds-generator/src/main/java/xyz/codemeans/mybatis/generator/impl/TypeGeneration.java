package xyz.codemeans.mybatis.generator.impl;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yuanwq
 */
@Data
@Accessors(chain = true)
public class TypeGeneration {

  private String packageName;
  private String sqlSupportTypeName;
  private String sqlTableTypeName;
  private String sqlTableInstanceName;
  private String tableName;
  private String qualifiedTableName;
  private String content;
}
