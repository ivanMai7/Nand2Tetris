package xyz.ivan.vmTranslator;

import java.io.*;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/9
 * @Description:
 */
public class VMTranslator {
    public static void main(String[] args) throws IOException {

        File source = new File(args[0]);
        FileReader fr = new FileReader(source);
        BufferedReader br = new BufferedReader(fr);

        String line = null;

        while((line=br.readLine())!=null){
            line = line.trim();

            // deal with the comments
            String[] split = line.split("//");

            line = split[0].trim();

            // deal with the empty lines
            if( line.trim().equals("") ){
                continue;
            }

            Parser parser = new Parser();

            parser.parse(line);

            System.out.println("// " + line);

            if( parser.type.value == 1 ){ // memory access commands
                System.out.println(Code.memoryAccess(parser));
            }else if( parser.type.value == 2 ){ // arithmetic or logical commands
                System.out.println(Code.arithmeticCommands(parser));
            }

        }

    }
}
