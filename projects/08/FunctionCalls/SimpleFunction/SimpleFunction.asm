// function SimpleFunction.test 2
(SimpleFunction.test)
@SP
A=M
M=0
@SP
M=M+1
@SP
A=M
M=0
@SP
M=M+1

// push local 0
@LCL
D=M
@0
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push local 1
@LCL
D=M
@1
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// add
@SP
M=M-1
A=M
D=M
A=A-1
M=D+M

// not
@SP
A=M-1
M=!M

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

// add
@SP
M=M-1
A=M
D=M
A=A-1
M=D+M

// push argument 1
@ARG
D=M
@1
A=D+A
D=M
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

// return
@LCL
D=M
@5
M=D
@5
D=A
D=-D
@5
A=M
A=D+A
D=M
@6
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
@5
D=D+M
A=D
D=M
@THAT
M=D
@2
D=A
D=-D
@5
D=D+M
A=D
D=M
@THIS
M=D
@3
D=A
D=-D
@5
D=D+M
A=D
D=M
@ARG
M=D
@4
D=A
D=-D
@5
D=D+M
A=D
D=M
@LCL
M=D
@6
A=M
0;JMP