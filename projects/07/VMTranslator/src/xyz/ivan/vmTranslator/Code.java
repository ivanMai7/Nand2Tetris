package xyz.ivan.vmTranslator;


/**
 * @Author: ivan_Mai
 * @Date: 2023/7/9
 * @Description:
 */
public class Code {
    public static String memoryAccess(Parser parser){
        CommandType type = parser.type;
        SegmentType segmentType = parser.segmentType;
        int segmentPos = parser.segmentPos;
        String str = "";
        int ramPos = 0;
        if( type == CommandType.PUSH ){ // push
            switch (segmentType){
                case TEMP:
                    ramPos = 5 + segmentPos;
                    str += "@" + ramPos + "\n";
                    str += "D=M\n";
                    break;
                case POINTER:
                    ramPos = 3 + segmentPos;
                    str += "@" + ramPos + "\n";
                    str += "D=M\n";
                    break;
                case STATIC:
                    str += "@" + parser.fileName + "." + segmentPos + "\n";
                    str += "D=M\n";
                    break;
                case CONSTANT:
                    str += "@" + segmentPos + "\n";
                    str += "D=A\n";
                    break;
                case ARGUMENT: case LOCAL: case THIS: case THAT:
                    str += "@" + segmentType.value + "\n";
                    str += "D=M\n";
                    str += "@" + segmentPos + "\n";
                    str += "A=D+A\n";
                    str += "D=M\n";
                    break;
            }
            str += "@SP\n";
            str += "A=M\n";
            str += "M=D\n";
            str += "@SP\n";
            str += "M=M+1\n";
        }else{ // pop
            str += "@SP\n";
            str += "M=M-1\n";
            str += "@SP\n";
            str += "A=M\n";
            str += "D=M\n";
            switch (segmentType){
                case TEMP:
                    ramPos = 5 + segmentPos;
                    str += "@" + ramPos + "\n";
                    str += "M=D\n";
                    break;
                case POINTER:
                    ramPos = 3 + segmentPos;
                    str += "@" + ramPos + "\n";
                    str += "M=D\n";
                    break;
                case STATIC:
                    str += "@" + parser.fileName + "." + segmentPos + "\n";
                    str += "M=D\n";
                    break;
                case ARGUMENT: case LOCAL: case THIS: case THAT:
                    str += "@" + segmentType.value + "\n";
                    str += "A=M\n";
                    for (int i = 0; i < segmentPos; i++) {
                        str += "A=A+1\n";
                    }
                    str += "M=D\n";
                    break;
            }
        }
        return str;
    }

    static int jmpCounter = 0;

    public static String arithmeticCommands(Parser parser){

        CommandType type = parser.type;
        String str = "";
        switch (type){
            case ADD:
                str += "@SP\n";
                str += "M=M-1\n";
                str += "A=M\n";
                str += "D=M\n";
                str += "A=A-1\n";
                str += "M=D+M\n";
                break;
            case SUB:
                str += "@SP\n";
                str += "M=M-1\n";
                str += "A=M\n";
                str += "D=M\n";
                str += "A=A-1\n";
                str += "M=M-D\n";
                break;
            case EQ:
                str += "@SP\n";
                str += "M=M-1\n";
                str += "A=M\n";
                str += "D=M\n";
                str += "A=A-1\n";
                str += "M=M-D\n";
                str += "D=M\n";
                str += "M=-1\n";
                str += "@EQ_JMP" + jmpCounter + "\n";
                str += "D;JEQ\n";
                str += "@SP\n";
                str += "A=M\n";
                str += "A=A-1\n";
                str += "M=0\n";
                str += "(EQ_JMP" + jmpCounter++ +")\n";
                break;
            case GT:
                str += "@SP\n";
                str += "M=M-1\n";
                str += "A=M\n";
                str += "D=M\n";
                str += "A=A-1\n";
                str += "M=M-D\n";
                str += "D=M\n";
                str += "M=-1\n";
                str += "@GT_JMP" + jmpCounter + "\n";
                str += "D;JGT\n";
                str += "@SP\n";
                str += "A=M\n";
                str += "A=A-1\n";
                str += "M=0\n";
                str += "(GT_JMP"+ jmpCounter++ +")\n";
                break;
            case LT:
                str += "@SP\n";
                str += "M=M-1\n";
                str += "A=M\n";
                str += "D=M\n";
                str += "A=A-1\n";
                str += "M=M-D\n";
                str += "D=M\n";
                str += "M=-1\n";
                str += "@LT_JMP" + jmpCounter + "\n";
                str += "D;JLT\n";
                str += "@SP\n";
                str += "A=M\n";
                str += "A=A-1\n";
                str += "M=0\n";
                str += "(LT_JMP" + jmpCounter++ + ")\n";
                break;
            case AND:
                str += "@SP\n";
                str += "M=M-1\n";
                str += "A=M\n";
                str += "D=M\n";
                str += "A=A-1\n";
                str += "M=D&M\n";
                break;
            case OR:
                str += "@SP\n";
                str += "M=M-1\n";
                str += "A=M\n";
                str += "D=M\n";
                str += "A=A-1\n";
                str += "M=D|M\n";
                break;
            case NOT:
                str += "@SP\n";
                str += "A=M-1\n";
                str += "M=!M\n";
                break;
            case NEG:
                str += "@SP\n";
                str += "A=M-1\n";
                str += "M=-M\n";
                break;
        }
        return str;
    }

    public static String controlCommands(Parser parser){
        String str = "";
        if( parser.type == CommandType.LABEL ){ // LABEL
            str += "(" + parser.labelName + ")\n";
        }else if( parser.type == CommandType.IF_GOTO ){ // IF_GOTO
            str += "@SP\n";
            str += "A=M\n";
            str += "A=A-1\n";
            str += "D=M\n";
            str += "@SP\n";
            str += "M=M-1\n";
            str += "@" + parser.labelName + "\n";
            str += "D;JNE\n";
        }else{ // GOTO
            str += "@" + parser.labelName + "\n";
            str += "0;JMP\n";
        }
        return str;
    }

    static int callTimes = 0;

    public static String functionCommands(Parser parser){
        String str = "";
        if( parser.type == CommandType.CALL ){ // CALL
            // push returnAddress
            str += "@" + parser.functionName + "_ret_" + callTimes + "\n";
            str += "D=A\n";
            str += "@SP\n";
            str += "A=M\n";
            str += "M=D\n";
            str += "@SP\n";
            str += "M=M+1\n";

            // push LCL
            str += "@LCL\n";
            str += "D=M\n";
            str += "@SP\n";
            str += "A=M\n";
            str += "M=D\n";
            str += "@SP\n";
            str += "M=M+1\n";

            // push ARG
            str += "@ARG\n";
            str += "D=M\n";
            str += "@SP\n";
            str += "A=M\n";
            str += "M=D\n";
            str += "@SP\n";
            str += "M=M+1\n";

            // push THIS
            str += "@THIS\n";
            str += "D=M\n";
            str += "@SP\n";
            str += "A=M\n";
            str += "M=D\n";
            str += "@SP\n";
            str += "M=M+1\n";

            // push THAT
            str += "@THAT\n";
            str += "D=M\n";
            str += "@SP\n";
            str += "A=M\n";
            str += "M=D\n";
            str += "@SP\n";
            str += "M=M+1\n";

            // ARG = SP - 5 - nArgs
            int num = parser.argsN + 5;
            str += "@" + num + "\n";
            str += "D=A\n";
            str += "D=-D\n";
            str += "@SP\n";
            str += "D=D+M\n";
            str += "@ARG\n";
            str += "M=D\n";

            // LCL = SP
            str += "@SP\n";
            str += "D=M\n";
            str += "@LCL\n";
            str += "M=D\n";

            // goto functionName
            str += "@" + parser.functionName + "\n";
            str += "0;JMP\n";

            // (returnAddress)
            str += "(" + parser.functionName + "_ret_" + callTimes + ")\n";
            ++callTimes;

        }else if( parser.type == CommandType.FUNCTION ){ // FUNCTION

            str += "(" + parser.functionName + ")\n";

            // repeat "push 0" localsN times
            for (int i = 0; i < parser.localsN; i++) {
                str += "@SP\n";
                str += "A=M\n";
                str += "M=0\n";
                str += "@SP\n";
                str += "M=M+1\n";
            }

        }else{ // RETURN
            // endFrame = LCL
            str += "@LCL\n";
            str += "D=M\n";
            str += "@13\n";
            str += "M=D\n";

            // retAddress = *(endFrame - 5)
            str += "@5\n";
            str += "D=A\n";
            str += "D=-D\n";
            str += "@13\n";
            str += "A=M\n";
            str += "A=D+A\n";
            str += "D=M\n";
            str += "@14\n";
            str += "M=D\n";

            // *ARG = pop()
            str += "@SP\n";
            str += "M=M-1\n";
            str += "@SP\n";
            str += "A=M\n";
            str += "D=M\n";
            str += "@ARG\n";
            str += "A=M\n";
            str += "M=D\n";

            // SP = ARG + 1
            str += "@ARG\n";
            str += "D=M\n";
            str += "D=D+1\n";
            str += "@SP\n";
            str += "M=D\n";

            // THAT = *(endFrame - 1)
            str += "@1\n";
            str += "D=A\n";
            str += "D=-D\n";
            str += "@13\n";
            str += "D=D+M\n";
            str += "A=D\n";
            str += "D=M\n";
            str += "@THAT\n";
            str += "M=D\n";

            // THIS = *(endFrame - 2)
            str += "@2\n";
            str += "D=A\n";
            str += "D=-D\n";
            str += "@13" + "\n";
            str += "D=D+M\n";
            str += "A=D\n";
            str += "D=M\n";
            str += "@THIS\n";
            str += "M=D\n";

            // ARG = *(endFrame - 3)
            str += "@3\n";
            str += "D=A\n";
            str += "D=-D\n";
            str += "@13\n";
            str += "D=D+M\n";
            str += "A=D\n";
            str += "D=M\n";
            str += "@ARG\n";
            str += "M=D\n";

            // LCL = *(endFrame - 4)
            str += "@4\n";
            str += "D=A\n";
            str += "D=-D\n";
            str += "@13\n";
            str += "D=D+M\n";
            str += "A=D\n";
            str += "D=M\n";
            str += "@LCL\n";
            str += "M=D\n";

            // goto retAddress
            str += "@14\n";
            str += "A=M\n";
            str += "0;JMP\n";

        }
        return str;
    }

}
