package xyz.ivan.hackAssembler;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/7
 * @Description:
 */
public class Parser {
    public boolean isAInstruction;
    public String instruction;
    public String number;
    public String opStr;
    public String dest;
    public String comp;
    public String jmp;

    public void parse(String instruction){
        this.instruction = instruction;
        char op = instruction.charAt(0);
        if( op == '@' ){
            this.isAInstruction = true;
            opStr = "0";
            number = instruction.substring(1);
        }else{
            this.isAInstruction = false;
            opStr = "111";
            if( instruction.split(";").length > 1 ){
                jmp = instruction.split(";")[1];
                instruction = instruction.split(";")[0];
            }else{
                jmp = "";
            }
            if( instruction.split("=").length > 1 ){
                 dest = instruction.split("=")[0];
                 comp = instruction.split("=")[1];
            }else{
                comp = instruction;
                dest = "";
            }

        }
    }

    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.parse("D=A");
        System.out.println(parser.comp);
        System.out.println(parser.dest);
        System.out.println(parser.jmp);
    }
}
