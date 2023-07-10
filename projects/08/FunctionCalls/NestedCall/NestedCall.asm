// function Sys.init 0
(Sys.init)

// push constant 4000
@4000
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 0
@SP
M=M-1
@SP
A=M
D=M
@3
M=D

// push constant 5000
@5000
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 1
@SP
M=M-1
@SP
A=M
D=M
@4
M=D

// call Sys.main 0
@Sys.main_ret_0
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
@5
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
@Sys.main
0;JMP
(Sys.main_ret_0)

// pop temp 1
@SP
M=M-1
@SP
A=M
D=M
@6
M=D

// label LOOP
(LOOP)

// goto LOOP
@LOOP
0;JMP

// function Sys.main 5
(Sys.main)
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
@SP
A=M
M=0
@SP
M=M+1

// push constant 4001
@4001
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 0
@SP
M=M-1
@SP
A=M
D=M
@3
M=D

// push constant 5001
@5001
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 1
@SP
M=M-1
@SP
A=M
D=M
@4
M=D

// push constant 200
@200
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop local 1
@SP
M=M-1
@SP
A=M
D=M
@LCL
A=M
A=A+1
M=D

// push constant 40
@40
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop local 2
@SP
M=M-1
@SP
A=M
D=M
@LCL
A=M
A=A+1
A=A+1
M=D

// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop local 3
@SP
M=M-1
@SP
A=M
D=M
@LCL
A=M
A=A+1
A=A+1
A=A+1
M=D

// push constant 123
@123
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Sys.add12 1
@Sys.add12_ret_1
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
@Sys.add12
0;JMP
(Sys.add12_ret_1)

// pop temp 0
@SP
M=M-1
@SP
A=M
D=M
@5
M=D

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

// push local 2
@LCL
D=M
@2
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push local 3
@LCL
D=M
@3
A=D+A
D=M
@SP
A=M
M=D
@SP
M=M+1

// push local 4
@LCL
D=M
@4
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

// add
@SP
M=M-1
A=M
D=M
A=A-1
M=D+M

// add
@SP
M=M-1
A=M
D=M
A=A-1
M=D+M

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

// function Sys.add12 0
(Sys.add12)

// push constant 4002
@4002
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 0
@SP
M=M-1
@SP
A=M
D=M
@3
M=D

// push constant 5002
@5002
D=A
@SP
A=M
M=D
@SP
M=M+1

// pop pointer 1
@SP
M=M-1
@SP
A=M
D=M
@4
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

// push constant 12
@12
D=A
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