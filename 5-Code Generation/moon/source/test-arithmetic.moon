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
     % compute the addition of two values
     lw r1,x(r0)
     lw r2,y(r0)
     add r3,r1,r2
     sw t3(r0),r3
     % assigns statement
     lw r1,t3(r0)
     sw z(r0),r1
     % loads word
     lw r1,z(r0)
     % put value on stack
     sw -8(r14),r1
     % link buffer to stack
     addi r1,r0, buf
     sw -12(r14),r1
     % convert int to string for output
     jl r15, intstr
     sw -8(r14),r13
     % output to console
     jl r15, putstr
     % skips line
     addi r13,r0,cr
     sw -8(r14),r13
     jl r15, putstr
     % compute the addition of two values
     lw r2,x(r0)
     lw r1,y(r0)
     sub r3,r2,r1
     sw t4(r0),r3
     % assigns statement
     lw r2,t4(r0)
     sw z(r0),r2
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
     % compute the multiplication of two values
     lw r1,x(r0)
     lw r2,y(r0)
     mul r3,r1,r2
     sw t5(r0),r3
     % assigns statement
     lw r1,t5(r0)
     sw z(r0),r1
     % loads word
     lw r1,z(r0)
     % put value on stack
     sw -8(r14),r1
     % link buffer to stack
     addi r1,r0, buf
     sw -12(r14),r1
     % convert int to string for output
     jl r15, intstr
     sw -8(r14),r13
     % output to console
     jl r15, putstr
     % skips line
     addi r13,r0,cr
     sw -8(r14),r13
     jl r15, putstr
     % compute the multiplication of two values
     lw r2,x(r0)
     lw r1,y(r0)
     div r3,r2,r1
     sw t6(r0),r3
     % assigns statement
     lw r2,t6(r0)
     sw z(r0),r2
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
     %required to end program
     getc r0
hlt

x    res 4
y    res 4
z    res 4
t1   res 4
t2   res 4
t3   res 4
t4   res 4
t5   res 4
t6   res 4
buf  res 20
cr   db 10
