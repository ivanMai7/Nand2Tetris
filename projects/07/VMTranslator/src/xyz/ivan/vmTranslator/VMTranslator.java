package xyz.ivan.vmTranslator;

import java.io.*;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/9
 * @Description:
 */
public class VMTranslator {
    public static void main(String[] args) throws IOException {

        for (int i = 0; i < Integer.parseInt(args[0]); i++) {

            File source = new File(args[i + 1]);
            FileReader fr = new FileReader(source);
            BufferedReader br = new BufferedReader(fr);

            // Get the fileName
            String fileName = "";
            for (int j = args[i + 1].length() - 1; j >= 0; --j) {
                if( args[i + 1].charAt(j) != '\\' ){
                    fileName = args[i + 1].charAt(j) + fileName;
                }else{
                    break;
                }
            }

            String line = null;

            while((line=br.readLine())!=null){
                line = line.trim();

                // deal with the comments
                String[] split = line.split("//");

                if( split.length == 0 ){
                    continue;
                }

                line = split[0].trim();

                // deal with the empty lines
                if( line.trim().equals("") ){
                    continue;
                }

                Parser parser = new Parser();

                parser.parse(line);

                parser.fileName = fileName;

                System.out.println("// " + line);

                if( parser.type.value == 1 ){ // memory access commands
                    System.out.println(Code.memoryAccess(parser));
                }else if( parser.type.value == 2 ){ // arithmetic or logical commands
                    System.out.println(Code.arithmeticCommands(parser));
                }else if( parser.type.value == 3 ){ // control commands
                    System.out.println(Code.controlCommands(parser));
                }else{ // function commands
                    System.out.println(Code.functionCommands(parser));
                }

            }


        }


    }
}
