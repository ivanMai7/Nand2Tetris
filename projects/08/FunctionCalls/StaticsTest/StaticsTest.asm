// call Sys.init 0
@Sys.init_ret_0
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
@Sys.init
0;JMP
(Sys.init_ret_0)

// function Sys.init 0
(Sys.init)

// push constant 6
@6
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 8
@8
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Class1.set 2
@Class1.set_ret_1
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
@7
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
@Class1.set
0;JMP
(Class1.set_ret_1)

// pop temp 0
@SP
M=M-1
@SP
A=M
D=M
@5
M=D

// push constant 23
@23
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 15
@15
D=A
@SP
A=M
M=D
@SP
M=M+1

// call Class2.set 2
@Class2.set_ret_2
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
@7
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
@Class2.set
0;JMP
(Class2.set_ret_2)

// pop temp 0
@SP
M=M-1
@SP
A=M
D=M
@5
M=D

// call Class1.get 0
@Class1.get_ret_3
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
@Class1.get
0;JMP
(Class1.get_ret_3)

// call Class2.get 0
@Class2.get_ret_4
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
@Class2.get
0;JMP
(Class2.get_ret_4)

// label WHILE
(WHILE)

// goto WHILE
@WHILE
0;JMP

// function Class1.set 0
(Class1.set)

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

// pop static 0
@SP
M=M-1
@SP
A=M
D=M
@Class1.vm.0
M=D

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

// pop static 1
@SP
M=M-1
@SP
A=M
D=M
@Class1.vm.1
M=D

// push constant 0
@0
D=A
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

// function Class1.get 0
(Class1.get)

// push static 0
@Class1.vm.0
D=M
@SP
A=M
M=D
@SP
M=M+1

// push static 1
@Class1.vm.1
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

// function Class2.set 0
(Class2.set)

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

// pop static 0
@SP
M=M-1
@SP
A=M
D=M
@Class2.vm.0
M=D

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

// pop static 1
@SP
M=M-1
@SP
A=M
D=M
@Class2.vm.1
M=D

// push constant 0
@0
D=A
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

// function Class2.get 0
(Class2.get)

// push static 0
@Class2.vm.0
D=M
@SP
A=M
M=D
@SP
M=M+1

// push static 1
@Class2.vm.1
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