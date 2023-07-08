package xyz.ivan.hackAssembler;

import java.io.*;
import java.util.HashMap;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/7
 * @Description:
 */
public class HackAssembler {
    private static HashMap<String,Integer> symbolTable = new HashMap<>();
    static  {
        symbolTable.put("SP",0);
        symbolTable.put("LCL",1);
        symbolTable.put("ARG",2);
        symbolTable.put("THIS",3);
        symbolTable.put("THAT",4);
        symbolTable.put("SCREEN",16384);
        symbolTable.put("KBD",24576);
        for (int i = 0; i <= 15; i++) {
            symbolTable.put("R" + i,i);
        }
    }
    public static void main(String[] args) throws IOException {

        File source = new File(args[0]);
        FileReader fr = new FileReader(source);
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        int lineCnt = 0;
        int allocateMemPos = 16;

        // the first pass
        while((line=br.readLine())!=null){
            line = line.trim();

            // deal with the comments
            String[] split = line.split("//");

            line = split[0].trim();

            // deal with the empty lines
            if( line.trim().equals("") ){
                continue;
            }

            // save the labels
            if( line.charAt(0) == '(' ){
                String label = line.substring(1, line.indexOf(")"));
                symbolTable.put(label,lineCnt);
            }else{
                ++lineCnt;
            }
        }


        // the second pass
        // reset the location of br
        br = new BufferedReader(new FileReader(source));
        while((line=br.readLine())!=null){
            // deal with the comments
            String[] split = line.split("//");

            line = split[0].trim();

            // deal with the empty lines and the labels
            if( line.trim().equals("") || line.trim().charAt(0) == '(' ){
                continue;
            }

            Parser parser = new Parser();

            parser.parse(line.trim());

            if( parser.isAInstruction ){
                if( !(parser.number.charAt(0) >= '0' && parser.number.charAt(0) <= '9') ){
                    if( symbolTable.containsKey(parser.number) ){
                        parser.number = symbolTable.get(parser.number).toString();
                    }else{
                        symbolTable.put(parser.number,allocateMemPos);
                        parser.number = allocateMemPos + "";
                        ++allocateMemPos;
                    }
                }
                String calcNumber = Code.calcNumber(parser.number);
                System.out.println(parser.opStr + calcNumber);
            }else{
                String calcDest = Code.calcDest(parser.dest);
                String calcComp = Code.calcComp(parser.comp);
                String calcJmp = Code.calcJmp(parser.jmp);
                System.out.println(parser.opStr + calcComp + calcDest + calcJmp);
            }
        }
    }
}
