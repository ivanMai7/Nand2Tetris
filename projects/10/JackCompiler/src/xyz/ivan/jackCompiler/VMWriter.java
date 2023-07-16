package xyz.ivan.jackCompiler;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/15
 * @Description:
 */
public class VMWriter {
    public static void writePush(String segment,int index){
        System.out.println("push " + segment + " " + index);
    }
    public static void writePop(String segment,int index){
        System.out.println("pop " + segment + " " + index);
    }
    public static void writeArithmetic(String command){
        switch (command){
            case "+" :
                System.out.println("add"); break;
            case "-" :
                System.out.println("sub"); break;
            case "neg-" :
                System.out.println("neg"); break;
            case "~" :
                System.out.println("not"); break;
            case "*" :
                System.out.println("call Math.multiply 2"); break;
            case "/" :
                System.out.println("call Math.divide 2"); break;
            case "&amp;" :
                System.out.println("and"); break;
            case "|" :
                System.out.println("or"); break;
            case "&lt;" :
                System.out.println("lt"); break;
            case "&gt;" :
                System.out.println("gt"); break;
            case "=" :
                System.out.println("eq"); break;

        }
    }
    public static void writeLabel(String label){
        System.out.println("label " + label);
    }
    public static void writeGoto(String label){
        System.out.println("goto " + label);
    }
    public static void writeIf(String label){
        System.out.println("if-goto " + label);
    }
    public static void writeCall(String name,int nArgs){
        System.out.println("call " + name + " " + nArgs);
    }
    public static void writeFunction(String name,int nArgs){
        System.out.println("function " + name + " " + nArgs);
    }
    public static void writeReturn(){
        System.out.println("return");
    }
}
