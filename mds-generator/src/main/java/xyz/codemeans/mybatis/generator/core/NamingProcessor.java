package xyz.codemeans.mybatis.generator.core;

import com.google.common.base.Strings;
import java.io.File;
import java.lang.reflect.Field;
import xyz.codemeans.mybatis.generator.annotation.MdsColumn;
import xyz.codemeans.mybatis.generator.annotation.MdsTable;
import xyz.codemeans.mybatis.generator.config.GenerationDef;
import xyz.codemeans.mybatis.generator.config.NamingProfile;

/**
 * @author yuanwq
 */
public class NamingProcessor {

  public String generateName(String name, NamingProfile profile) {
    StringBuilder sb = new StringBuilder();
    if (profile.getPrefix() != null) {
      sb.append(profile.getPrefix());
    }
    if (profile.getReplaceRegex() != null && profile.getReplaceRegex().length() == 0) {
      name = name.replace(profile.getReplaceRegex(), profile.getReplacement());
    }
    if (profile.getFromFormat() != null && profile.getToFormat() != null) {
      name = profile.getFromFormat().to(profile.getToFormat(), name);
    }
    sb.append(name);
    if (profile.getSuffix() != null) {
      sb.append(profile.getSuffix());
    }
    return sb.toString();
  }

  public String sqlSupportTypeName(Class<?> type, NamingProfile profile) {
    MdsTable mdsTable = type.getAnnotation(MdsTable.class);
    if (mdsTable != null && !Strings.isNullOrEmpty(mdsTable.sqlSupportType())) {
      return mdsTable.sqlSupportType();
    }
    return generateName(type.getSimpleName(), profile);
  }

  public String sqlTableTypeName(Class<?> type, NamingProfile profile) {
    MdsTable mdsTable = type.getAnnotation(MdsTable.class);
    if (mdsTable != null && !Strings.isNullOrEmpty(mdsTable.sqlTableType())) {
      return mdsTable.sqlTableType();
    }
    return generateName(type.getSimpleName(), profile);
  }

  public String sqlTableInstanceName(Class<?> type, NamingProfile profile) {
    MdsTable mdsTable = type.getAnnotation(MdsTable.class);
    if (mdsTable != null && !Strings.isNullOrEmpty(mdsTable.sqlTableInstance())) {
      return mdsTable.sqlTableInstance();
    }
    return generateName(type.getSimpleName(), profile);
  }

  public String tableName(Class<?> type, NamingProfile profile) {
    MdsTable mdsTable = type.getAnnotation(MdsTable.class);
    if (mdsTable != null && !Strings.isNullOrEmpty(mdsTable.table())) {
      return mdsTable.table();
    }
    return generateName(type.getSimpleName(), profile);
  }

  public String qualifiedTableName(Class<?> type, GenerationDef def) {
    String catalog = def.getCatalog();
    String schema = def.getSchema();
    String table = tableName(type, def.getTableNaming());
    MdsTable mdsTable = type.getAnnotation(MdsTable.class);
    if (mdsTable != null) {
      if (!Strings.isNullOrEmpty(mdsTable.schema())) {
        schema = mdsTable.schema();
      }
      if (!Strings.isNullOrEmpty(mdsTable.catalog())) {
        catalog = mdsTable.catalog();
      }
    }
    StringBuilder sb = new StringBuilder();
    if (!Strings.isNullOrEmpty(catalog)) {
      sb.append(catalog).append(".");
      if (!Strings.isNullOrEmpty(schema)) {
        sb.append(schema);
      }
      sb.append(".");
    } else if (!Strings.isNullOrEmpty(schema)) {
      sb.append(schema).append(".");
    }
    sb.append(table);
    return sb.toString();
  }

  public String packageName(Class<?> type, GenerationDef def) {
    if (Strings.isNullOrEmpty(def.getOutputPackage())) {
      return type.getPackage().getName() + ".sql";
    }
    if (def.isKeepPackageStructure()) {
      return def.getOutputPackage() + type.getPackage().getName()
          .substring(def.getInputPackage().length());
    }
    return def.getOutputPackage();
  }

  public String sqlSupportFieldName(Field field, NamingProfile profile) {
    MdsColumn mdsColumn = field.getAnnotation(MdsColumn.class);
    if (mdsColumn != null && !Strings.isNullOrEmpty(mdsColumn.sqlSupportField())) {
      return mdsColumn.sqlSupportField();
    }
    return generateName(field.getName(), profile);
  }

  public String sqlTableFieldName(Field field, NamingProfile profile) {
    MdsColumn mdsColumn = field.getAnnotation(MdsColumn.class);
    if (mdsColumn != null && !Strings.isNullOrEmpty(mdsColumn.sqlTableField())) {
      return mdsColumn.sqlTableField();
    }
    return generateName(field.getName(), profile);
  }

  public String columnName(Field field, NamingProfile profile) {
    MdsColumn mdsColumn = field.getAnnotation(MdsColumn.class);
    if (mdsColumn != null && !Strings.isNullOrEmpty(mdsColumn.column())) {
      return mdsColumn.column();
    }
    return generateName(field.getName(), profile);
  }

  public File file(File outputDir, String packageName, String typeName) {
    String subpath = packageName.replace(".", File.separator);
    File dir = new File(outputDir, subpath);
    return new File(dir, typeName + ".java");
  }
}
