entry
     addi r14,r0,topaddr
     addi r1,r0,0
     sw t1(r0),r1
     addi r1,r0,5
     sw t2(r0),r1
     addi r2,r0,0
     lw r1,t2(r0)
     sw arr(r2),r1
     addi r1,r0,1
     sw t3(r0),r1
     addi r1,r0,0
     sw t4(r0),r1
     addi r2,r0,4
     lw r1,null(r0)
     sw arr(r2),r1
     addi r1,r0,1
     sw t5(r0),r1
     addi r2,r0,4
     lw r1,arr(r2)
     sw -8(r14),r1
     addi r1,r0, buf
     sw -12(r14),r1
     jl r15, intstr
     sw -8(r14),r13
     jl r15, putstr
     addi r13,r0,cr
     sw -8(r14),r13
     jl r15, putstr
     %required to end program
     getc r0
hlt

arr  res 8
t1   res 4
t2   res 4
t3   res 4
t4   res 4
t5   res 4
buf  res 20
cr   db 10
