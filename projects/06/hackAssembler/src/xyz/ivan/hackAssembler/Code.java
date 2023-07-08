package xyz.ivan.hackAssembler;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/7
 * @Description:
 */
public class Code {
    public static String calcNumber(String number){
        int num = Integer.parseInt(number);
        String res = "";
        int k = 1;
        for (int i = 0; i < 15; i++) {
            if( (num & k) != 0 ){
                res = "1" + res;
            }else{
                res = "0" + res;
            }
            k = k << 1;
        }
        return res;
    }


    public static String calcComp(String comp){
        String res = "";
        switch(comp){
            case "0": case "": res = "101010"; break;
            case "1": res = "111111"; break;
            case "-1": res = "111010"; break;
            case "D": res = "001100"; break;
            case "A": case "M": res = "110000"; break;
            case "!D": res = "001101"; break;
            case "!A": case "!M": res = "110001"; break;
            case "-D": res = "001111"; break;
            case "-A": case "-M": res = "110011"; break;
            case "D+1": res = "011111"; break;
            case "A+1": case "M+1": res = "110111"; break;
            case "D-1": res = "001110"; break;
            case "A-1": case "M-1": res = "110010"; break;
            case "D+A": case "D+M": res = "000010"; break;
            case "D-A": case "D-M": res = "010011"; break;
            case "A-D": case "M-D": res = "000111"; break;
            case "D&A": case "D&M": res = "000000"; break;
            case "D|A": case "D|M": res = "010101"; break;
        }
        if( comp.contains("M") ){
            res = "1" + res;
        }else{
            res = "0" + res;
        }
        return res;
    }

    public static String calcDest(String dest){
        String res = "";
        switch(dest){
            case "":res = "000";break;
            case "M":res = "001";break;
            case "D":res = "010";break;
            case "MD":res = "011";break;
            case "A":res = "100";break;
            case "AM":res = "101";break;
            case "AD":res = "110";break;
            case "AMD":res = "111";break;
        }
        return res;
    }

    public static String calcJmp(String jmp){
        String res = "";
        switch (jmp){
            case "":res = "000";break;
            case "JGT":res = "001";break;
            case "JEQ":res = "010";break;
            case "JGE":res = "011";break;
            case "JLT":res = "100";break;
            case "JNE":res = "101";break;
            case "JLE":res = "110";break;
            case "JMP":res = "111";break;
        }
        return res;
    }


    public static void main(String[] args) {
        // Test the function of calcNumber
        String s = calcNumber("8");
        System.out.println(s);
        // Test the function of calcComp
        String calcComp = calcComp("D-M");
        System.out.println(calcComp);
    }
}
