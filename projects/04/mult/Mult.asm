// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Mult.asm

// Multiplies R0 and R1 and stores the result in R2.
// (R0, R1, R2 refer to RAM[0], RAM[1], and RAM[2], respectively.)
//
// This program only needs to handle arguments that satisfy
// R0 >= 0, R1 >= 0, and R0*R1 < 32768.

// Put your code here.

	@R0
	D=M
	@a1
	M=D // a1 = R0

	@R1
	D=M
	@a2
	M=D // a2 = R1

	@i
	M=0 // i = 0
	@res
	M=0 // res = 0

(LOOP)
	@i
	D=M
	@a2
	D=D-M
	@STOP
	D;JGE // if i >= a2 goto STOP

	@res
	D=M
	@a1
	D=D+M
	@res
	M=D // res = res + a1
	@i
	M=M+1 // i = i + 1
	@LOOP
	0;JMP

(STOP)
	@res
	D=M
	@R2
	M=D // RAM[2] = res

(END)
	@END
	0;JMP