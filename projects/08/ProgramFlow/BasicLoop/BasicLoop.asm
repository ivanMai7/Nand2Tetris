// push constant 0
@0
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop local 0
@SP
M=M-1
@SP
A=M
D=M
@LCL
A=M
M=D

// label LOOP_START
(LOOP_START)

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

// add
@SP
M=M-1
A=M
D=M
A=A-1
M=D+M

// pop local 0
@SP
M=M-1
@SP
A=M
D=M
@LCL
A=M
M=D

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

// pop argument 0
@SP
M=M-1
@SP
A=M
D=M
@ARG
A=M
M=D

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

// if-goto LOOP_START
@SP
A=M
A=A-1
D=M
@SP
M=M-1
@LOOP_START
D;JNE

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