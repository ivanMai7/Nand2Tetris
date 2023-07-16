package xyz.ivan.jackCompiler;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/13
 * @Description:
 */
public class Token {
    // 单词内容
    public String content;
    // 单词所在行
    public int line;
    // 单词索引位置（起始位置）
    public int sPos;
    // 单词索引位置（结束位置）
    public int pos;
    // 单词类型
    public int type;

    public Token(String content, int line, int sPos, int pos, int type) {
        this.content = content;
        this.line = line;
        this.sPos = sPos;
        this.pos = pos;
        this.type = type;
    }

    public static Map<String, Integer> types = new TreeMap<String, Integer>();

    // 未定义的符号
    public static int UNK = -1;
    // 注释
    public static int COM = 0;


    // 标识符
    public static int IDF = 1;
    // 整型常量
    public static int INT = 2;
    // 字符串
    public static int STR = 3;


    static {
        // 关键字
        types.put("class",22);
        types.put("method",23);
        types.put("int", 24);
        types.put("function", 25);
        types.put("boolean", 26);
        types.put("constructor", 27);
        types.put("char", 28);
        types.put("void", 29);
        types.put("var", 31);
        types.put("static", 32);
        types.put("field", 33);
        types.put("let", 34);
        types.put("do", 35);
        types.put("if",36);
        types.put("else", 37);
        types.put("while", 38);
        types.put("return",39);
        types.put("true", 40);
        types.put("false",41);
        types.put("null",42);
        types.put("this", 43);

        // 字符
        types.put("[", 71);
        types.put("]", 72);
        types.put("{", 74);
        types.put("}", 75);
        types.put("(", 76);
        types.put(")", 77);
        types.put(";", 73);
        types.put(",", 81);
        types.put("//", 78);
        types.put("/*", 79);
        types.put("*/", 80);
        types.put(".",81);

        types.put("/", 94);
        types.put("+", 91);
        types.put("-", 92);
        types.put("*", 93);
        types.put("~", 105);
        types.put("=", 104);
        types.put(">", 95);
        types.put("<", 96);
        types.put("&", 108);
        types.put("|", 109);


    }

    public String getContent() {
        return content;
    }

    public int getLine() {
        return line;
    }

    public int getsPos() {
        return sPos;
    }

    public int getPos() {
        return pos;
    }

    public int getType() {
        return type;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setLine(int line) {
        this.line = line;
    }

    public void setsPos(int sPos) {
        this.sPos = sPos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Token [content=" + content + ", line=" + line + ", sPos=" + sPos + ", pos=" + pos + ", type=" + type
                + "]";
    }
}
