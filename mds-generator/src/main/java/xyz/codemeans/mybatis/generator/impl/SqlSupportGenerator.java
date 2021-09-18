package xyz.codemeans.mybatis.generator.impl;

import com.github.javaparser.ParseResult;
import com.github.javaparser.ast.CompilationUnit;
import com.github.javaparser.utils.SourceRoot;
import com.github.javaparser.utils.SourceRoot.Callback;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 * @author yuanwq
 */
@Component
public class SqlSupportGenerator {

  public List<File> generate(GenerationDef generation) throws IOException {
    List<File> outfiles = new ArrayList<>();
    SourceRoot sourceRoot = new SourceRoot(generation.getSrcDir().toPath());
    sourceRoot.parse(generation.getInputPackage(), new Callback() {
      @Override
      public Result process(Path localPath, Path absolutePath,
          ParseResult<CompilationUnit> result) {
        // TODO generate
        System.out.println(result.getResult().get().getPrimaryTypeName().get());
        outfiles.add(absolutePath.toFile());
        return Result.DONT_SAVE;
      }
    });
    return outfiles;
  }

}
