.data
str: .asciiz "What is the first value?\n" 
str2: .asciiz "What is the second value?\n"
sum: .asciiz "The sum of "
is: .asciiz " is "
andd: .asciiz " and "

#displaying the first question
.text
la $a0, str
li $v0, 4
syscall

#Allowing user to type in the first value
li $v0, 5
syscall
add $t1,$v0,$zero

#displaying the second question
la $a0,str2
addi $v0, $zero, 4
syscall

li $v0,5
syscall
add $t2,$zero,$v0

#Getting the sum/answer
add $t3,$t1,$t2

la $a0,sum
li $v0,4
syscall
addi $a0,$t1,0
li $v0,1
syscall
la $a0,andd
li $v0,4
syscall
addi $a0,$t2,0
li $v0,1
syscall
la $a0,is
li $v0,4
syscall
addi $a0,$t3,0
li $v0,1
syscall

#ending the program
li $v0,10
syscall