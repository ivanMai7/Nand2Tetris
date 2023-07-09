package xyz.ivan.vmTranslator;

import com.sun.org.apache.bcel.internal.generic.POP;

/**
 * @Author: ivan_Mai
 * @Date: 2023/7/9
 * @Description:
 */
public enum CommandType {
    PUSH(1),
    POP(1),
    ADD(2),
    SUB(2),
    NEG(2),
    EQ(2),
    GT(2),
    LT(2),
    AND(2),
    OR(2),
    NOT(2);

    public int value;

    CommandType(int value) {
        this.value = value;
    }

    CommandType() {
    }

}
