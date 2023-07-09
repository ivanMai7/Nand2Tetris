// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// eq
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D
D=M
M=-1
@EQ_JMP0
D;JEQ
@SP
A=M
A=A-1
M=0
(EQ_JMP0)

// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// eq
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D
D=M
M=-1
@EQ_JMP1
D;JEQ
@SP
A=M
A=A-1
M=0
(EQ_JMP1)

// push constant 16
@16
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// eq
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D
D=M
M=-1
@EQ_JMP2
D;JEQ
@SP
A=M
A=A-1
M=0
(EQ_JMP2)

// push constant 892
@892
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 891
@891
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
@LT_JMP3
D;JLT
@SP
A=M
A=A-1
M=0
(LT_JMP3)

// push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 892
@892
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
@LT_JMP4
D;JLT
@SP
A=M
A=A-1
M=0
(LT_JMP4)

// push constant 891
@891
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 891
@891
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
@LT_JMP5
D;JLT
@SP
A=M
A=A-1
M=0
(LT_JMP5)

// push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// gt
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D
D=M
M=-1
@GT_JMP6
D;JGT
@SP
A=M
A=A-1
M=0
(GT_JMP6)

// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 32767
@32767
D=A
@SP
A=M
M=D
@SP
M=M+1

// gt
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D
D=M
M=-1
@GT_JMP7
D;JGT
@SP
A=M
A=A-1
M=0
(GT_JMP7)

// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 32766
@32766
D=A
@SP
A=M
M=D
@SP
M=M+1

// gt
@SP
M=M-1
A=M
D=M
A=A-1
M=M-D
D=M
M=-1
@GT_JMP8
D;JGT
@SP
A=M
A=A-1
M=0
(GT_JMP8)

// push constant 57
@57
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 31
@31
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 53
@53
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

// push constant 112
@112
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

// neg
@SP
A=M-1
M=-M

// and
@SP
M=M-1
A=M
D=M
A=A-1
M=D&M

// push constant 82
@82
D=A
@SP
A=M
M=D
@SP
M=M+1

// or
@SP
M=M-1
A=M
D=M
A=A-1
M=D|M

// not
@SP
A=M-1
M=!M