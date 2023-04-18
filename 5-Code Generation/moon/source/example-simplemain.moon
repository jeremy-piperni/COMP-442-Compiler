     % main function starts
     entry
     addi r14,r0,topaddr
     % store value of int literals
     addi r1,r0,1
     sw t3(r0),r1
     % store value of int literals
     addi r1,r0,2
     sw t4(r0),r1
     % store value of int literals
     addi r1,r0,3
     sw t5(r0),r1
     % compute the multiplication of two values
     lw r1,t4(r0)
     lw r2,t5(r0)
     mul r3,r1,r2
     sw t1(r0),r3
     % compute the addition of two values
     lw r1,t3(r0)
     lw r2,t1(r0)
     add r3,r1,r2
     sw t2(r0),r3
     % assigns statement
     lw r1,t2(r0)
     sw y(r0),r1
     % reads word from input
     addi r1,r0,buf
     sw -8(r14),r1
     jl r15,getstr
     jl r15,strint
     sw x(r0),r13
     % store value of int literals
     addi r1,r0,10
     sw t8(r0),r1
     % compute the addition of two values
     lw r1,y(r0)
     lw r2,t8(r0)
     add r3,r1,r2
     sw t6(r0),r3
     % computes the relop of two values
     lw r1,x(r0)
     lw r2,t6(r0)
     cgt r3,r1,r2
     sw t7(r0),r3
     % checks value of relative expression for if statement
     lw r1,t7(r0)
     % jumps to else if needed
     bz r1,else1
     % store value of int literals
     addi r2,r0,10
     sw t10(r0),r2
     % compute the addition of two values
     lw r2,x(r0)
     lw r3,t10(r0)
     add r4,r2,r3
     sw t9(r0),r4
     lw r2,t9(r0)
     % put value on stack
     sw -8(r14),r2
     % link buffer to stack
     addi r2,r0, buf
     sw -12(r14),r2
     % convert int to string for output
     jl r15, intstr
     sw -8(r14),r13
     % output to console
     jl r15, putstr
     % skips line
     addi r13,r0,cr
     sw -8(r14),r13
     jl r15, putstr
     % jumps to end of if statement
     j endif1
else1
     % store value of int literals
     addi r3,r0,1
     sw t12(r0),r3
     % compute the addition of two values
     lw r3,x(r0)
     lw r2,t12(r0)
     add r4,r3,r2
     sw t11(r0),r4
     lw r3,t11(r0)
     % put value on stack
     sw -8(r14),r3
     % link buffer to stack
     addi r3,r0, buf
     sw -12(r14),r3
     % convert int to string for output
     jl r15, intstr
     sw -8(r14),r13
     % output to console
     jl r15, putstr
     % skips line
     addi r13,r0,cr
     sw -8(r14),r13
     jl r15, putstr
endif1
     % store value of int literals
     addi r1,r0,0
     sw t13(r0),r1
     % assigns statement
     lw r1,t13(r0)
     sw z(r0),r1
gowhile01
     % store value of int literals
     addi r2,r0,10
     sw t15(r0),r2
     % computes the relop of two values
     lw r2,z(r0)
     lw r3,t15(r0)
     cle r4,r2,r3
     sw t14(r0),r4
     % computes while loop
     lw r1,t14(r0)
     % while loop terminates if needed
     bz r1,endwhile01
     % loads word
     lw r2,z(r0)
     % put value on stack
     sw -8(r14),r2
     % link buffer to stack
     addi r2,r0, buf
     sw -12(r14),r2
     % convert int to string for output
     jl r15, intstr
     sw -8(r14),r13
     % output to console
     jl r15, putstr
     % skips line
     addi r13,r0,cr
     sw -8(r14),r13
     jl r15, putstr
     % store value of int literals
     addi r3,r0,1
     sw t17(r0),r3
     % compute the addition of two values
     lw r3,z(r0)
     lw r2,t17(r0)
     add r4,r3,r2
     sw t16(r0),r4
     % assigns statement
     lw r3,t16(r0)
     sw z(r0),r3
     % while loop continues
     j gowhile01
endwhile01
     %required to end program
     getc r0
hlt

x    res 4
y    res 4
z    res 4
t3   res 4
t4   res 4
t5   res 4
t1   res 4
t2   res 4
t8   res 4
t6   res 4
t7   res 4
t10   res 4
t9   res 4
t12   res 4
t11   res 4
t13   res 4
t15   res 4
t14   res 4
t17   res 4
t16   res 4
buf  res 20
cr   db 10
