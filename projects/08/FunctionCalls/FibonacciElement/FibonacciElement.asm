// function Sys.init 0
(Sys.init)

// push constant 4
@4
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Main.fibonacci 1
@Main.fibonacci_ret_0
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@6
D=A
D=-D
@SP
D=D+M
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
(Main.fibonacci_ret_0)

// label WHILE
(WHILE)

// goto WHILE
@WHILE
0;JMP

// function Main.fibonacci 0
(Main.fibonacci)

// push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1

// lt
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D
D=M
M=-1
@LT_JMP0
D;JLT
@SP
A=M
A=A-1
M=0
(LT_JMP0)

// if-goto IF_TRUE
@SP
A=M
A=A-1
D=M
@SP
M=M-1
@IF_TRUE
D;JNE

// goto IF_FALSE
@IF_FALSE
0;JMP

// label IF_TRUE
(IF_TRUE)

// push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// return
@LCL
D=M
@13
M=D
@5
D=A
D=-D
@13
A=M
A=D+A
D=M
@14
M=D
@SP
M=M-1
@SP
A=M
D=M
@ARG
A=M
M=D
@ARG
D=M
D=D+1
@SP
M=D
@1
D=A
D=-D
@13
D=D+M
A=D
D=M
@THAT
M=D
@2
D=A
D=-D
@13
D=D+M
A=D
D=M
@THIS
M=D
@3
D=A
D=-D
@13
D=D+M
A=D
D=M
@ARG
M=D
@4
D=A
D=-D
@13
D=D+M
A=D
D=M
@LCL
M=D
@14
A=M
0;JMP

// label IF_FALSE
(IF_FALSE)

// push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push constant 2
@2
D=A
@SP
A=M
M=D
@SP
M=M+1

// sub
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D

// call Main.fibonacci 1
@Main.fibonacci_ret_1
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@6
D=A
D=-D
@SP
D=D+M
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
(Main.fibonacci_ret_1)

// push argument 0
@ARG
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push constant 1
@1
D=A
@SP
A=M
M=D
@SP
M=M+1

// sub
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D

// call Main.fibonacci 1
@Main.fibonacci_ret_2
D=A
@SP
A=M
M=D
@SP
M=M+1
@LCL
D=M
@SP
A=M
M=D
@SP
M=M+1
@ARG
D=M
@SP
A=M
M=D
@SP
M=M+1
@THIS
D=M
@SP
A=M
M=D
@SP
M=M+1
@THAT
D=M
@SP
A=M
M=D
@SP
M=M+1
@6
D=A
D=-D
@SP
D=D+M
@ARG
M=D
@SP
D=M
@LCL
M=D
@Main.fibonacci
0;JMP
(Main.fibonacci_ret_2)

// add
@SP
M=M-1
A=M
D=M
A=A-1
M=D+M

// return
@LCL
D=M
@13
M=D
@5
D=A
D=-D
@13
A=M
A=D+A
D=M
@14
M=D
@SP
M=M-1
@SP
A=M
D=M
@ARG
A=M
M=D
@ARG
D=M
D=D+1
@SP
M=D
@1
D=A
D=-D
@13
D=D+M
A=D
D=M
@THAT
M=D
@2
D=A
D=-D
@13
D=D+M
A=D
D=M
@THIS
M=D
@3
D=A
D=-D
@13
D=D+M
A=D
D=M
@ARG
M=D
@4
D=A
D=-D
@13
D=D+M
A=D
D=M
@LCL
M=D
@14
A=M
0;JMP