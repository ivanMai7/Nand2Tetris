// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.
(LOOP)
	@SCREEN
	D=A
	@a1
	M=D // a1 = 16384
	@i
	M=0 // i = 0
	@8192
	D=A
	@n
	M=D // n = 8192
(WHITE)
	@i
	D=M
	@n
	D=D-M
	@STOP
	D;JGE // if i >= n goto STOP
	@a1
	A=M
	M=0 // RAM[a1] = 0000000000000000
	
	@a1
	M=M+1 // a1 = a1 + 1
	@i
	M=M+1 // i = i + 1
	@WHITE
	0;JMP
	
	
(STOP)
	@KBD
	D=M
	@LOOP
	D;JEQ // if KBD == 0 goto LOOP
	
	@SCREEN
	D=A
	@a2
	M=D // a2 = 16384
	@i
	M=0 // i = 0
	@8192
	D=A
	@n
	M=D // n = 8192
(BLACK)
	@i
	D=M
	@n
	D=D-M
	@STOP
	D;JGE // if i >= n goto STOP
	@a2
	A=M
	M=-1 // RAM[a2] = 1111111111111111
	
	@a2
	M=M+1 // a2 = a2 + 1
	@i
	M=M+1 // i = i + 1
	@BLACK
	0;JMP