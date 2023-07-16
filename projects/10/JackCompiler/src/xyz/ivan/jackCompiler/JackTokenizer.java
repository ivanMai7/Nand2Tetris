package xyz.ivan.jackCompiler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/13
 * @Description:
 */
public class JackTokenizer {
    public List<Token> tokens = new ArrayList<>();
    public String code;
    public int curTokenPos = -1;

    public JackTokenizer(String code) {
        this.code = code;
        this.analysis();
    }

    public JackTokenizer() {

    }

    public Token advance(){
        ++curTokenPos;
        return tokens.get(curTokenPos);
    }

    public boolean hasMoreTokens(){
        return (curTokenPos + 1) < tokens.size();
    }

    public void analysis() {
        int sPos = 0;
        int pos = 0;
        int line = 1;

        while (pos >= 0 && pos < this.code.length()) {
            char c = this.code.charAt(pos);

            // 遇到空格
            if (isBlank(c)) {
                pos++;
                // 如果是换行符
                if ('\n' == c ) {
                    ++line;
                }
                continue;
            }

            sPos = pos;

            // 遇到字母，分析出整个单词
            if (isLetter(c)) {
                String token = "";
                do {
                    token += c;
                    if( pos + 1 >= code.length() ) {
                        ++pos;
                        break;
                    }
                    c = this.code.charAt(++pos);
                } while (isLetter(c) || isNumber(c));
                if (Token.types.containsKey(token)) {

                    this.tokens.add(new Token(token, line,sPos, pos, Token.types.get(token)));

                } else {
                    this.tokens.add(new Token(token, line,sPos, pos, Token.IDF));
                }
                continue;
            }

            // 遇到数值
            if (isNumber(c)) {
                String token = "";
                do {
                    token += c;
                    if( pos + 1 >= code.length() ) {
                        ++pos;
                        break;
                    }
                    c = this.code.charAt(++pos);
                } while (isNumber(c));

                this.tokens.add(new Token(token, line, sPos,pos, Token.INT));

                continue;
            }

            // 遇到符号
            switch (c) {
                case '{':
                case '}':
                case '(':
                case ')':
                case '[':
                case ']':
                case ';':
                case '-':
                case '*':
                case '+':
                case '~':
                case '=':
                case '|':
                case '.':
                case ',':
                    this.tokens.add(new Token("" + c, line,sPos, pos + 1, Token.types.get("" + c)));
                    break;
                case '>':
                    this.tokens.add(new Token("&gt;", line,sPos, pos + 1, Token.types.get("" + c)));
                    break;
                case '<':
                    this.tokens.add(new Token("&lt;", line,sPos, pos + 1, Token.types.get("" + c)));
                    break;
                 case '&':
                    this.tokens.add(new Token("&amp;", line,sPos, pos + 1, Token.types.get("" + c)));
                    break;

                case '"':
                    String token = "";
                    ++pos;
                    while (code.charAt(pos) != '"'){
                        token += code.charAt(pos);
                        ++pos;
                    }
                    this.tokens.add(new Token(token, line,sPos, pos + 1, Token.STR));
                    break;

                case '/':
                    // 如果是单行注释
                    if( '/' == this.code.charAt(pos + 1)  ) {
                        int comStart = pos + 2;
                        int comEnd = pos + 2;
                        while( '\n' != code.charAt(comEnd) ) {
                            ++comEnd;
                        }
                        pos = comEnd;
                        ++line;
//                        this.tokens.add(new Token("//" + code.substring(comStart, comEnd), line++, sPos, pos, Token.COM));
                    }
                    // 如果是多行注释
                    else if( '*' == this.code.charAt(pos + 1) ) {
                        ++pos;
                        int start = pos + 1;
                        do {
                            ++pos;
                            if( this.code.charAt(pos) == '\n' ) {
                                ++line;
                            }
                        }while(this.code.charAt(pos - 1) != '*' || this.code.charAt(pos) != '/');
//                        this.tokens.add(new Token("/*" + code.substring(start,pos + 1), line,sPos, pos + 1, Token.COM));
                    }
                    // 如果是除法
                    else {
                        this.tokens.add(new Token("" + c, line,sPos, pos + 1, Token.types.get("" + c)));
                    }
                    break;

                default:this.tokens.add(new Token("" + c, line,sPos, pos + 1, Token.UNK));
            }
            ++pos;
        }
    }

    private boolean isBlank(char c) {
        if (c == ' ' || c == '\t' || c == '\n' || c == '\r')
            return true;
        return false;
    }

    private boolean isNumber(char c) {
        if ('0' <= c && c <= '9')
            return true;
        return false;
    }

    private boolean isLetter(char c) {
        if (('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || c == '_')
            return true;
        return false;
    }

    public List<Token> getTokens() {
        return tokens;
    }

    public String getCode() {
        return code;
    }

    public void setTokens(List<Token> tokens) {
        this.tokens = tokens;
    }

    public void setCode(String code) {
        this.code = code;
    }


    /**
     * Generate the XxxTG.xml
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        File source = new File(args[0]);
        FileReader fr = new FileReader(source);
        BufferedReader br = new BufferedReader(fr);

        String line = null;
        String code = "";

        while((line=br.readLine())!=null){
           code += line;
           code += "\n";
        }

        System.out.println(code);

        System.out.println("***********************");

        JackTokenizer jackTokenizer = new JackTokenizer();

        jackTokenizer.code = code;

        jackTokenizer.analysis();

        System.out.println("<tokens>");

        for (Token token : jackTokenizer.tokens) {
            String content = token.content;
            int type = token.type;
            String labelLeft = "", labelRight = "";
            if( type == 1 ){
                labelLeft = "<identifier>";
                labelRight = "</identifier>";
            }else if( type == 2 ){
                labelLeft = "<integerConstant>";
                labelRight = "</integerConstant>";
            }else if( type == 3 ){
                labelLeft = "<stringConstant>";
                labelRight = "</stringConstant>";
            }else if( type <= 43 ){
                labelLeft = "<keyword>";
                labelRight = "</keyword>";
            }else{
                labelLeft = "<symbol>";
                labelRight = "</symbol>";
            }
            System.out.print(labelLeft);
            System.out.print(" " + token.content + " ");
            System.out.println(labelRight);
        }

        System.out.println("</tokens>");

    }
}
