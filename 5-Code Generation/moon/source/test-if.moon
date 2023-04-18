     % main function starts
     entry
     addi r14,r0,topaddr
     % store value of int literals
     addi r1,r0,10
     sw t1(r0),r1
     % assigns statement
     lw r1,t1(r0)
     sw x(r0),r1
     % store value of int literals
     addi r1,r0,5
     sw t2(r0),r1
     % assigns statement
     lw r1,t2(r0)
     sw y(r0),r1
     % computes the relop of two values
     lw r1,x(r0)
     lw r2,y(r0)
     cgt r3,r1,r2
     sw t3(r0),r3
     % checks value of relative expression for if statement
     lw r1,t3(r0)
     % jumps to else if needed
     bz r1,else1
     % loads word
     lw r2,x(r0)
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
     % loads word
     lw r3,y(r0)
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
     % computes the relop of two values
     lw r1,x(r0)
     lw r2,y(r0)
     cgt r3,r1,r2
     sw t4(r0),r3
     % checks value of relative expression for if statement
     lw r1,t4(r0)
     % jumps to else if needed
     bz r1,else2
     % computes the relop of two values
     lw r2,x(r0)
     lw r3,y(r0)
     cge r4,r2,r3
     sw t5(r0),r4
     % checks value of relative expression for if statement
     lw r2,t5(r0)
     % jumps to else if needed
     bz r2,else3
     % compute the addition of two values
     lw r3,x(r0)
     lw r4,y(r0)
     add r5,r3,r4
     sw t6(r0),r5
     lw r3,t6(r0)
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
     % jumps to end of if statement
     j endif3
else3
     % loads word
     lw r4,x(r0)
     % put value on stack
     sw -8(r14),r4
     % link buffer to stack
     addi r4,r0, buf
     sw -12(r14),r4
     % convert int to string for output
     jl r15, intstr
     sw -8(r14),r13
     % output to console
     jl r15, putstr
     % skips line
     addi r13,r0,cr
     sw -8(r14),r13
     jl r15, putstr
endif3
     % jumps to end of if statement
     j endif2
else2
     % loads word
     lw r2,y(r0)
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
endif2
     % computes the relop of two values
     lw r1,x(r0)
     lw r3,y(r0)
     ceq r2,r1,r3
     sw t7(r0),r2
     % checks value of relative expression for if statement
     lw r1,t7(r0)
     % jumps to else if needed
     bz r1,else4
     % loads word
     lw r3,x(r0)
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
     % jumps to end of if statement
     j endif4
else4
endif4
     % computes the relop of two values
     lw r1,x(r0)
     lw r2,y(r0)
     cne r3,r1,r2
     sw t8(r0),r3
     % checks value of relative expression for if statement
     lw r1,t8(r0)
     % jumps to else if needed
     bz r1,else5
     % jumps to end of if statement
     j endif5
else5
     % loads word
     lw r2,x(r0)
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
endif5
     %required to end program
     getc r0
hlt

x    res 4
y    res 4
t1   res 4
t2   res 4
t3   res 4
t4   res 4
t5   res 4
t6   res 4
t7   res 4
t8   res 4
buf  res 20
cr   db 10
