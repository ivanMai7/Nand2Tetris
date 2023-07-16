package xyz.ivan.jackCompiler;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/13
 * @Description:
 */
public class CompilationEngine {
    public JackTokenizer jackTokenizer;
    public String code;

    public HashSet<Symbol> classSymbolTable = new HashSet<>();
    int fieldIdx = 0;
    int staticIdx = 0;

    public HashSet<Symbol> funcSymbolTable = new HashSet<>();

    public String className;

    public int labelCnt = 1;

    public CompilationEngine(String code) {
        this.code = code;
        jackTokenizer = new JackTokenizer(code);
    }

    public String nextTokenContent(){
        return jackTokenizer.tokens.get(jackTokenizer.curTokenPos + 1).content;
    }

    public void compileClass(){
        Token advance = jackTokenizer.advance();
        if( advance.content.equals("class") ){
//            System.out.println("<class>");
            eat(advance);
            className = jackTokenizer.advance().content;
//            eat(jackTokenizer.advance());
            eat(jackTokenizer.advance());

            while( nextTokenContent().equals("static") || nextTokenContent().equals("field") ){
                compileClassVarDec();
            }

            while( nextTokenContent().equals("function") || nextTokenContent().equals("constructor") || nextTokenContent().equals("method") ){
                compileSubroutine();
            }

            eat(jackTokenizer.advance());

//            System.out.println("</class>");
        }
    }

    // 编译静态声明或字段说明
    public void compileClassVarDec(){
//        System.out.println("<classVarDec>");
//        eat(jackTokenizer.advance());
//        eat(jackTokenizer.advance());
//        eat(jackTokenizer.advance());
        String type = jackTokenizer.advance().content;
        String dataType = jackTokenizer.advance().content;
        String name = jackTokenizer.advance().content;
        if( type.equals("field") ){
            classSymbolTable.add(new Symbol(name,"this",dataType,fieldIdx++));
        }else{
            classSymbolTable.add(new Symbol(name,type,dataType,staticIdx++));
        }

        while( nextTokenContent().equals(",") ){
            eat(jackTokenizer.advance());
//            eat(jackTokenizer.advance());
            name = jackTokenizer.advance().content;
            if( type.equals("field") ){
                classSymbolTable.add(new Symbol(name,"this",dataType,fieldIdx++));
            }else{
                classSymbolTable.add(new Symbol(name,type,dataType,staticIdx++));
            }
        }
        eat(jackTokenizer.advance());
//        System.out.println("</classVarDec>");
    }

    // 编译整个方法、函数或构造函数
    public void compileSubroutine(){
//        System.out.println("<subroutineDec>");
//        eat(jackTokenizer.advance());
//        eat(jackTokenizer.advance());
//        eat(jackTokenizer.advance());
        String type = jackTokenizer.advance().content;
        String returnType = jackTokenizer.advance().content;
        String name = jackTokenizer.advance().content;
        eat(jackTokenizer.advance());// (
        int isMethod = type.equals("method") ? 1 : 0;
        int nArgs = compileParameterList(isMethod);
        eat(jackTokenizer.advance());// )
//        System.out.println("<subroutineBody>");

        eat(jackTokenizer.advance());// {
        int cnt = 0;
        int curPos = jackTokenizer.curTokenPos;
        while (nextTokenContent().equals("var")){
            cnt = getLocalCnt(cnt);
        }
        jackTokenizer.curTokenPos = curPos;
//        VMWriter.writeFunction(className + "." + name,cnt);


        VMWriter.writeFunction(className + "." + name,cnt);


        if( type.equals("constructor") ){
            VMWriter.writePush("constant",fieldIdx);
            VMWriter.writeCall("Memory.alloc",1);
            VMWriter.writePop("pointer",0);
        }else if( type.equals("method") ){
            VMWriter.writePush("argument",0);
            VMWriter.writePop("pointer",0);
        }
        cnt = 0;
        while (nextTokenContent().equals("var")){
            cnt = compileVarDec(cnt);
        }
        compileStatements();
        eat(jackTokenizer.advance());// }
        funcSymbolTable.clear();
//        System.out.println("</subroutineBody>");
//        System.out.println("</subroutineDec>");
    }

    // 编译参数列表（可能为空），不包含 "()"
    public int compileParameterList(int isMethod){
//        System.out.println("<parameterList>");
        int cnt = 0;
        if(!nextTokenContent().equals(")")){
//            eat(jackTokenizer.advance());
//            eat(jackTokenizer.advance());
            String dataType = jackTokenizer.advance().content;
            String name = jackTokenizer.advance().content;
            funcSymbolTable.add(new Symbol(name,"argument",dataType,cnt + isMethod));
            ++cnt;
            while( nextTokenContent().equals(",") ){
                eat(jackTokenizer.advance());
//                eat(jackTokenizer.advance());
//                eat(jackTokenizer.advance());
                dataType = jackTokenizer.advance().content;
                name = jackTokenizer.advance().content;
                funcSymbolTable.add(new Symbol(name,"argument",dataType,cnt + isMethod));
                ++cnt;
            }
        }
        return cnt;
//        System.out.println("</parameterList>");
    }

    // 编译Var声明
    public int getLocalCnt(int cnt){
//        System.out.println("<varDec>");
        eat(jackTokenizer.advance());// var
        String dataType = jackTokenizer.advance().content;
        String name = jackTokenizer.advance().content;
        ++cnt;
        while( nextTokenContent().equals(",") ){
            eat(jackTokenizer.advance());// ,
//            eat(jackTokenizer.advance());
            name = jackTokenizer.advance().content;
            ++cnt;
        }
        eat(jackTokenizer.advance()); // ;
//        System.out.println("</varDec>");
        return cnt;
    }

    // 编译Var声明
    public int compileVarDec(int cnt){
//        System.out.println("<varDec>");
        eat(jackTokenizer.advance());// var
        String dataType = jackTokenizer.advance().content;
        String name = jackTokenizer.advance().content;
        funcSymbolTable.add(new Symbol(name,"local",dataType,cnt++));
        while( nextTokenContent().equals(",") ){
            eat(jackTokenizer.advance());// ,
//            eat(jackTokenizer.advance());
            name = jackTokenizer.advance().content;
            funcSymbolTable.add(new Symbol(name,"local",dataType,cnt++));
        }
        eat(jackTokenizer.advance());
//        System.out.println("</varDec>");
        return cnt;
    }

    public void compileStatements(){
//        System.out.println("<statements>");
        while( nextTokenContent().equals("let") || nextTokenContent().equals("do") || nextTokenContent().equals("while") || nextTokenContent().equals("if") || nextTokenContent().equals("return") ){
            switch (nextTokenContent()){
                case "let":compileLet();break;
                case "do":compileDo();break;
                case "while":compileWhile();break;
                case "if":compileIf();break;
                case "return":compileReturn();break;
            }
        }
//        System.out.println("</statements>");
    }

    public void compileDo(){
//        System.out.println("<doStatement>");
        eat(jackTokenizer.advance());// do
//        eat(jackTokenizer.advance());
        String name = jackTokenizer.advance().content;
        if( nextTokenContent().equals("(") ){
            VMWriter.writePush("pointer",0);
            eat(jackTokenizer.advance());// (
            int nArgs = compileExpressionList();
            eat(jackTokenizer.advance());// )
            VMWriter.writeCall(className + "." + name,nArgs + 1);
            VMWriter.writePop("temp",0);
        }else{
//            eat(jackTokenizer.advance());
//            eat(jackTokenizer.advance());
            name += jackTokenizer.advance().content;
            name += jackTokenizer.advance().content;

            String clsName = "";

            boolean isObj = name.charAt(0) >= 'a' && name.charAt(0) <= 'z' || name.charAt(0) == '_';
            if( isObj ){
                boolean isDone = false;
                for (Symbol symbol : funcSymbolTable) {
                    if( symbol.name.equals(name.split("\\.")[0]) ){
                        VMWriter.writePush(symbol.type,symbol.index);
                        clsName = symbol.dataType;
                        isDone = true;
                        break;
                    }
                }
                if( !isDone ){
                    for (Symbol symbol : classSymbolTable) {
                        if( symbol.name.equals(name.split("\\.")[0]) ){
                            VMWriter.writePush(symbol.type,symbol.index);
                            clsName = symbol.dataType;
                            break;
                        }
                    }
                }
            }
            eat(jackTokenizer.advance());// (
            int nArgs = compileExpressionList();
            eat(jackTokenizer.advance());// )
            if( isObj ){
                VMWriter.writeCall(clsName + "." +name.split("\\.")[1],nArgs + 1);
            }else{
                VMWriter.writeCall(name,nArgs);
            }
            VMWriter.writePop("temp",0);
        }
        eat(jackTokenizer.advance());// ;
//        System.out.println("</doStatement>");
    }

    public void compileLet(){
//        System.out.println("<letStatement>");
        eat(jackTokenizer.advance()); // let
//        eat(jackTokenizer.advance());
        String name = jackTokenizer.advance().content;
        if( nextTokenContent().equals("[") ){
            boolean isDone = false;
            for (Symbol symbol : funcSymbolTable) {
                if( symbol.name.equals(name) ){
                    VMWriter.writePush(symbol.type,symbol.index);
                    isDone = true;
                    break;
                }
            }
            if( !isDone ){
                for (Symbol symbol : classSymbolTable) {
                    if( symbol.name.equals(name) ){
                        VMWriter.writePush(symbol.type,symbol.index);
                        break;
                    }
                }
            }

            eat(jackTokenizer.advance());// [
            compileExpression();
            VMWriter.writeArithmetic("+");

            eat(jackTokenizer.advance());// ]
            eat(jackTokenizer.advance()); // =
            compileExpression();
            VMWriter.writePop("temp",0);
            VMWriter.writePop("pointer",1);
            eat(jackTokenizer.advance()); // ;
            VMWriter.writePush("temp",0);
            VMWriter.writePop("that",0);
        }else{
            eat(jackTokenizer.advance()); // =
            compileExpression();
            eat(jackTokenizer.advance()); // ;
            // 找
            boolean isDone = false;
            for (Symbol symbol : funcSymbolTable) {
                if( symbol.name.equals(name) ){
                    VMWriter.writePop(symbol.type,symbol.index);
                    isDone = true;
                    break;
                }
            }
            if( !isDone ){
                for (Symbol symbol : classSymbolTable) {
                    if( symbol.name.equals(name) ){
                        VMWriter.writePop(symbol.type,symbol.index);
                        break;
                    }
                }
            }
        }
//        System.out.println("</letStatement>");
    }

    public void compileWhile(){
//        System.out.println("<whileStatement>");
        eat(jackTokenizer.advance());// while
        eat(jackTokenizer.advance());// (
        String l1 = "Label_" + className + "_" + labelCnt;
        String l2 = "Label_" + className + "_" + (labelCnt + 1);
        labelCnt += 2;
        VMWriter.writeLabel(l1);
        compileExpression();
        VMWriter.writeArithmetic("~");
        VMWriter.writeIf(l2);
        eat(jackTokenizer.advance());// )
        eat(jackTokenizer.advance());// {
        compileStatements();
        VMWriter.writeGoto(l1);
        VMWriter.writeLabel(l2);
        eat(jackTokenizer.advance());// }
//        System.out.println("</whileStatement>");
    }

    public void compileReturn(){
//        System.out.println("<returnStatement>");
        eat(jackTokenizer.advance());
        if( !nextTokenContent().equals(";") ){
            compileExpression();
            eat(jackTokenizer.advance());
            VMWriter.writeReturn();
        }else{
            eat(jackTokenizer.advance());
            VMWriter.writePush("constant",0);
            VMWriter.writeReturn();
        }
//        System.out.println("</returnStatement>");
    }

    public void compileIf(){
//        System.out.println("<ifStatement>");
        eat(jackTokenizer.advance());// if
        eat(jackTokenizer.advance());// (
        compileExpression();
        eat(jackTokenizer.advance());// )
        eat(jackTokenizer.advance());// {
        VMWriter.writeArithmetic("~");
        String l1 = "Label_" + className + "_" + labelCnt;
        String l2 = "Label_" + className + "_" + (labelCnt + 1);
        labelCnt += 2;
        VMWriter.writeIf(l1);
        compileStatements();
        eat(jackTokenizer.advance());// }

        if( nextTokenContent().equals("else") ){
            eat(jackTokenizer.advance());// else
            eat(jackTokenizer.advance());// {
            VMWriter.writeGoto(l2);
            VMWriter.writeLabel(l1);
            compileStatements();
            VMWriter.writeLabel(l2);
            eat(jackTokenizer.advance());// }
        }else{
            VMWriter.writeLabel(l1);
        }
//        System.out.println("</ifStatement>");
    }

    public void compileExpression(){
//        System.out.println("<expression>");
        compileTerm();
        List<String> opCollection = new ArrayList<>();
        opCollection.add("+");
        opCollection.add("-");
        opCollection.add("*");
        opCollection.add("/");
        opCollection.add("&amp;");
        opCollection.add("|");
        opCollection.add("&lt;");
        opCollection.add("&gt;");
        opCollection.add("=");

        while( opCollection.contains(nextTokenContent())  ){
//            eat(jackTokenizer.advance());
            String op = jackTokenizer.advance().content;
            compileTerm();
            VMWriter.writeArithmetic(op);
        }
//        System.out.println("</expression>");
    }

    public void compileTerm(){
//        System.out.println("<term>");
        if( jackTokenizer.tokens.get(jackTokenizer.curTokenPos + 1).type == 3 ){// 处理字符串
            String content = jackTokenizer.advance().content;
            VMWriter.writePush("constant",content.length());
            VMWriter.writeCall("String.new",1);

            for (int i = 0; i < content.length(); i++) {
                VMWriter.writePush("constant",content.charAt(i));
                VMWriter.writeCall("String.appendChar",2);
            }

        }else if( nextTokenContent().equals("true") ){
            String content = jackTokenizer.advance().content;
            VMWriter.writePush("constant",0);
            VMWriter.writeArithmetic("~");
        }else if( nextTokenContent().equals("false") || nextTokenContent().equals("null") ){
            String content = jackTokenizer.advance().content;
            VMWriter.writePush("constant",0);
        }else if( nextTokenContent().equals("this") ){
            String content = jackTokenizer.advance().content;
            VMWriter.writePush("pointer",0);
        } else if( jackTokenizer.tokens.get(jackTokenizer.curTokenPos + 1).type == 2 ){
            String content = jackTokenizer.advance().content;
            VMWriter.writePush("constant",Integer.parseInt(content));
        }else if( nextTokenContent().equals("~") || nextTokenContent().equals("-") ){
            String op = jackTokenizer.advance().content;
            compileTerm();
            if( op.equals("-") ){
                VMWriter.writeArithmetic("neg-");
            }else{
                VMWriter.writeArithmetic(op);
            }
        }else if(nextTokenContent().equals("(")){
            eat(jackTokenizer.advance());
            compileExpression();
            eat(jackTokenizer.advance());
        }else {
            String varName = jackTokenizer.advance().content;
            if( nextTokenContent().equals("[") ){
                boolean isDone = false;
                for (Symbol symbol : funcSymbolTable) {
                    if( symbol.name.equals(varName) ){
                        VMWriter.writePush(symbol.type,symbol.index);
                        isDone = true;
                        break;
                    }
                }
                if( !isDone ){
                    for (Symbol symbol : classSymbolTable) {
                        if( symbol.name.equals(varName) ){
                            VMWriter.writePush(symbol.type,symbol.index);
                            break;
                        }
                    }
                }

                eat(jackTokenizer.advance());// [
                compileExpression();
                VMWriter.writeArithmetic("+");
                VMWriter.writePop("pointer",1);
                VMWriter.writePush("that",0);
                eat(jackTokenizer.advance());// ]
            }else if( nextTokenContent().equals("(") ){
                VMWriter.writePush("pointer",0);
                eat(jackTokenizer.advance());
                int nArgs = compileExpressionList();
                eat(jackTokenizer.advance());
                VMWriter.writeCall(className + "." +varName,nArgs + 1);
            }else if( nextTokenContent().equals(".") ){
//                eat(jackTokenizer.advance());
//                eat(jackTokenizer.advance());
                String content = jackTokenizer.advance().content;
                varName += content;
                content = jackTokenizer.advance().content;
                varName += content;
                String clsName = "";
                boolean isObj = varName.charAt(0) >= 'a' && varName.charAt(0) <= 'z' || varName.charAt(0) == '_';
                if( isObj ){
                    boolean isDone = false;
                    for (Symbol symbol : funcSymbolTable) {
                        if( symbol.name.equals(varName.split("\\.")[0]) ){
                            VMWriter.writePush(symbol.type,symbol.index);
                            clsName = symbol.dataType;
                            isDone = true;
                            break;
                        }
                    }
                    if( !isDone ){
                        for (Symbol symbol : classSymbolTable) {
                            if( symbol.name.equals(varName.split("\\.")[0]) ){
                                VMWriter.writePush(symbol.type,symbol.index);
                                clsName = symbol.dataType;
                                break;
                            }
                        }
                    }
                }
                eat(jackTokenizer.advance());// (
                int nArgs = compileExpressionList();
                eat(jackTokenizer.advance());// )
                if( isObj ){
                    VMWriter.writeCall(clsName + "." + varName.split("\\.")[1],nArgs + 1);
                }else{
                    VMWriter.writeCall(varName,nArgs);
                }
            }else{
                // 找
                boolean isDone = false;
                for (Symbol symbol : funcSymbolTable) {
                    if( symbol.name.equals(varName) ){
                        VMWriter.writePush(symbol.type,symbol.index);
                        isDone = true;
                        break;
                    }
                }
                if( !isDone ){
                    for (Symbol symbol : classSymbolTable) {
                        if( symbol.name.equals(varName) ){
                            VMWriter.writePush(symbol.type,symbol.index);
                            break;
                        }
                    }
                }
            }
        }
//        System.out.println("</term>");
    }

    public int compileExpressionList(){
        int cnt = 0;
//        System.out.println("<expressionList>");
        if( !nextTokenContent().equals(")") ){
            compileExpression();
            ++cnt;
            while( nextTokenContent().equals(",") ){
                eat(jackTokenizer.advance());
                compileExpression();
                ++cnt;
            }
        }
//        System.out.println("</expressionList>");
        return cnt;
    }

    public void eat(Token token){
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
//        System.out.print(labelLeft);
//        System.out.print(" " + token.content + " ");
//        System.out.println(labelRight);
    }

}
