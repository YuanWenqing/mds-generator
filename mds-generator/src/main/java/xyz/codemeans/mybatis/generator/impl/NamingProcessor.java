package xyz.codemeans.mybatis.generator.impl;

import xyz.codemeans.mybatis.generator.config.GenerationDef;
import xyz.codemeans.mybatis.generator.config.NamingProfile;

/**
 * @author yuanwq
 */
public class NamingProcessor {

  public String buildName(String name, NamingProfile profile) {
    StringBuilder sb = new StringBuilder();
    if (profile.getPrefix() != null) {
      sb.append(profile.getPrefix());
    }
    if (profile.getReplaceRegex() == null || profile.getReplaceRegex().length() == 0) {
      sb.append(name);
    } else {
      sb.append(name.replace(profile.getReplaceRegex(), profile.getReplacement()));
    }
    if (profile.getSuffix() != null) {
      sb.append(profile.getSuffix());
    }
    return sb.toString();
  }

  public String sqlSupportTypeName(Class<?> type, NamingProfile sqlSupportTypeNaming) {
  }

  public String sqlTableTypeName(Class<?> type, NamingProfile sqlTableTypeNaming) {
  }

  public String sqlTableInstanceName(Class<?> type, NamingProfile sqlTableInstanceNaming) {
  }

  public String qualifiedTableName(Class<?> type, GenerationDef def) {
  }

  public String tableName(String qualifiedTableName) {
  }

  public String packageName(Class<?> type, GenerationDef def) {
    return def.getOutputPackage() + type.getPackage().getName()
        .substring(def.getInputPackage().length());
  }
}
