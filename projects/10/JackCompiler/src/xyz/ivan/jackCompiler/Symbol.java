package xyz.ivan.jackCompiler;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/15
 * @Description:
 */
public class Symbol {
    public String name;
    public String type;
    public String dataType;
    public int index;

    public Symbol(String name, String type, String dataType, int index) {
        this.name = name;
        this.type = type;
        this.dataType = dataType;
        this.index = index;
    }
}
