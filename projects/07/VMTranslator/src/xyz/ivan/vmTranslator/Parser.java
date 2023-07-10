package xyz.ivan.vmTranslator;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/9
 * @Description:
 */
public class Parser {
    public CommandType type;
    public SegmentType segmentType;
    public int segmentPos;
    public String labelName;
    public String functionName;
    public int argsN;
    public int localsN;
    public String fileName;
    public void parse(String command){
        String type = command.split(" ")[0];
        switch (type){
            case "push": this.type = CommandType.PUSH; break;
            case "pop": this.type = CommandType.POP; break;
            case "add": this.type = CommandType.ADD; break;
            case "sub": this.type = CommandType.SUB; break;
            case "neg": this.type = CommandType.NEG; break;
            case "eq": this.type = CommandType.EQ; break;
            case "gt": this.type = CommandType.GT; break;
            case "lt": this.type = CommandType.LT; break;
            case "and": this.type = CommandType.AND; break;
            case "or": this.type = CommandType.OR; break;
            case "not": this.type = CommandType.NOT; break;
            case "label": this.type = CommandType.LABEL; break;
            case "goto": this.type = CommandType.GOTO; break;
            case "if-goto": this.type = CommandType.IF_GOTO; break;
            case "call": this.type = CommandType.CALL; break;
            case "function": this.type = CommandType.FUNCTION; break;
            case "return": this.type = CommandType.RETURN; break;
        }
        if( this.type.value == 1 ){ // memory access commands
            String segmentType = command.split(" ")[1];
            segmentPos = Integer.parseInt(command.split(" ")[2]);
            switch (segmentType){
                case "argument": this.segmentType = SegmentType.ARGUMENT; break;
                case "local": this.segmentType = SegmentType.LOCAL; break;
                case "static": this.segmentType = SegmentType.STATIC; break;
                case "constant": this.segmentType = SegmentType.CONSTANT; break;
                case "this": this.segmentType = SegmentType.THIS; break;
                case "that": this.segmentType = SegmentType.THAT; break;
                case "pointer": this.segmentType = SegmentType.POINTER; break;
                case "temp": this.segmentType = SegmentType.TEMP; break;
            }
        }else if( this.type.value == 3 ){ // control commands
            String label = command.split(" ")[1];
            this.labelName = label;
        } else if( this.type.value == 4 ){ // function commands
            if( this.type == CommandType.FUNCTION ){
                this.functionName = command.split(" ")[1];
                this.localsN = Integer.parseInt(command.split(" ")[2]);
            }else if( this.type == CommandType.CALL ){
                this.functionName = command.split(" ")[1];
                this.argsN = Integer.parseInt(command.split(" ")[2]);
            }
        }
    }
}
