package xyz.ivan.jackCompiler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/13
 * @Description:
 */
public class JackAnalyzer {
    public static void main(String[] args) throws IOException {
        File source = new File(args[0]);
        FileReader fr = new FileReader(source);
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        String code = "";

        while((line=br.readLine())!=null){
            code += line;
            code += "\n";
        }

        CompilationEngine compilationEngine = new CompilationEngine(code);
        compilationEngine.compileClass();
    }
}
