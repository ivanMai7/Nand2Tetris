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
        }
    }
}
