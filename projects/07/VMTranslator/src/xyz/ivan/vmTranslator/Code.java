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
                    str += "@Ivan." + segmentPos + "\n";
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
                    str += "@Ivan." + segmentPos + "\n";
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

}
