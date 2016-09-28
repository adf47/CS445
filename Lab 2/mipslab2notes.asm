#addi $a0,$zero,1492
#addi $a1,$a0,10

#li $a1,1492

.data
str: .asciiz "What is the first value"
str2: .asciiz "What is the second value"

.text
la $a0,str
addi $v0,$zero,4
syscall
