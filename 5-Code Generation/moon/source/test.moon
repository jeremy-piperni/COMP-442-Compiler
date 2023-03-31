entry
     addi r14,r0,topaddr
     addi r1,r0,3
     sw t1(r0),r1
     lw r1,t1(r0)
     sw x(r0),r1
     addi r1,r0,12
     sw t2(r0),r1
     lw r1,t2(r0)
     sw y(r0),r1
     addi r1,r0,buf
     sw -8(r14),r1
     jl r15,getstr
     jl r15,strint
     sw z(r0),r13
     lw r1,y(r0)
     lw r2,z(r0)
     div r3,r1,r2
     sw t4(r0),r3
     lw r1,x(r0)
     lw r2,t4(r0)
     add r3,r1,r2
     sw t3(r0),r3
     lw r1,t3(r0)
     sw -8(r14),r1
     addi r1,r0, buf
     sw -12(r14),r1
     jl r15, intstr
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
t4   res 4
t3   res 4
buf  res 20
