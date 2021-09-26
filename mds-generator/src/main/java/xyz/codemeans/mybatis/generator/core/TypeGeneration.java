package xyz.codemeans.mybatis.generator.core;

import java.io.File;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author yuanwq
 */
@Data
@Accessors(chain = true)
public class TypeGeneration {

  private final Class<?> type;
  private String packageName;
  private String sqlSupportTypeName;
  private String sqlTableTypeName;
  private String sqlTableInstanceName;
  private String fieldsTypeName;
  private String tableName;
  private String qualifiedTableName;
  private File outfile;
  private String content;
}
