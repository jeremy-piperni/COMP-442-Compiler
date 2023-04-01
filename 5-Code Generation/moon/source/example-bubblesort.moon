     lw r1,null(r0)
     sw n(r0),r1
     addi r1,r0,0
     sw t1(r0),r1
     lw r1,t1(r0)
     sw i(r0),r1
     addi r1,r0,0
     sw t2(r0),r1
     lw r1,t2(r0)
     sw j(r0),r1
     addi r1,r0,0
     sw t3(r0),r1
     lw r1,t3(r0)
     sw temp(r0),r1
gowhile01
     addi r2,r0,1
     sw t5(r0),r2
     lw r2,n(r0)
     lw r3,t5(r0)
     sub r4,r2,r3
     sw t6(r0),r4
     lw r2,i(r0)
     lw r3,t6(r0)
     clt r4,r2,r3
     sw t4(r0),r4
     lw r1,t4(r0)
     bz r1,endwhile01
     addi r2,r0,0
     sw t7(r0),r2
     lw r2,t7(r0)
     sw j(r0),r2
gowhile11
     lw r3,n(r0)
     lw r4,i(r0)
     sub r5,r3,r4
     sw t11(r0),r5
     addi r3,r0,1
     sw t9(r0),r3
     lw r3,t11(r0)
     lw r4,t9(r0)
     sub r5,r3,r4
     sw t10(r0),r5
     lw r3,j(r0)
     lw r4,t10(r0)
     clt r5,r3,r4
     sw t8(r0),r5
     lw r2,t8(r0)
     bz r2,endwhile11
     addi r3,r0,1
     sw t13(r0),r3
     lw r3,j(r0)
     lw r4,t13(r0)
     add r5,r3,r4
     sw t14(r0),r5
     lw r3,arr(r0)
     lw r4,arr(r0)
     cgt r5,r3,r4
     sw t12(r0),r5
     lw r3,t12(r0)
     bz r3,else1
     lw r4,null(r0)
     sw temp(r0),r4
     addi r4,r0,1
     sw t15(r0),r4
     lw r4,j(r0)
     lw r5,t15(r0)
     add r6,r4,r5
     sw t16(r0),r6
     lw r4,null(r0)
     sw arr(r0),r4
     addi r4,r0,1
     sw null(r0),r4
     lw r4,j(r0)
     lw r5,null(r0)
     add r6,r4,r5
     sw t17(r0),r6
     lw r4,null(r0)
     sw arr(r0),r4
     j endif1
else1
endif1
     addi r3,r0,1
     sw t18(r0),r3
     lw r3,j(r0)
     lw r4,t18(r0)
     add r5,r3,r4
     sw t19(r0),r5
     lw r3,t19(r0)
     sw j(r0),r3
     j gowhile11
endwhile11
     addi r2,r0,1
     sw t110(r0),r2
     lw r2,i(r0)
     lw r3,t110(r0)
     add r4,r2,r3
     sw t111(r0),r4
     lw r2,t111(r0)
     sw i(r0),r2
     j gowhile01
endwhile01
     lw r1,null(r0)
     sw n(r0),r1
     addi r1,r0,0
     sw t112(r0),r1
     lw r1,t112(r0)
     sw i(r0),r1
gowhile21
     lw r2,i(r0)
     lw r3,n(r0)
     clt r4,r2,r3
     sw t113(r0),r4
     lw r1,t113(r0)
     bz r1,endwhile21
     lw r2,arr(r0)
     sw -8(r14),r2
     addi r2,r0, buf
     sw -12(r14),r2
     jl r15, intstr
     sw -8(r14),r13
     jl r15, putstr
     addi r13,r0,cr
     sw -8(r14),r13
     jl r15, putstr
     addi r2,r0,1
     sw t114(r0),r2
     lw r2,i(r0)
     lw r3,t114(r0)
     add r4,r2,r3
     sw t115(r0),r4
     lw r2,t115(r0)
     sw i(r0),r2
     j gowhile21
endwhile21
entry
     addi r14,r0,topaddr
     addi r1,r0,7
     sw null(r0),r1
     addi r1,r0,0
     sw null(r0),r1
     addi r1,r0,64
     sw t116(r0),r1
     lw r1,t116(r0)
     sw arr(r0),r1
     addi r1,r0,1
     sw null(r0),r1
     addi r1,r0,34
     sw t117(r0),r1
     lw r1,t117(r0)
     sw arr(r0),r1
     addi r1,r0,2
     sw null(r0),r1
     addi r1,r0,25
     sw t118(r0),r1
     lw r1,t118(r0)
     sw arr(r0),r1
     addi r1,r0,3
     sw null(r0),r1
     addi r1,r0,12
     sw t119(r0),r1
     lw r1,t119(r0)
     sw arr(r0),r1
     addi r1,r0,4
     sw null(r0),r1
     addi r1,r0,22
     sw t1110(r0),r1
     lw r1,t1110(r0)
     sw arr(r0),r1
     addi r1,r0,5
     sw null(r0),r1
     addi r1,r0,11
     sw t1111(r0),r1
     lw r1,t1111(r0)
     sw arr(r0),r1
     addi r1,r0,6
     sw null(r0),r1
     addi r1,r0,90
     sw t1112(r0),r1
     lw r1,t1112(r0)
     sw arr(r0),r1
     addi r1,r0,7
     sw t1113(r0),r1
     addi r1,r0,7
     sw t1114(r0),r1
     addi r1,r0,7
     sw t1115(r0),r1
     %required to end program
     getc r0
hlt

n    res 4
i    res 4
j    res 4
temp    res 4
t1   res 4
t2   res 4
t3   res 4
t5   res 4
t6   res 4
t4   res 4
t7   res 4
t11   res 4
t9   res 4
t10   res 4
t8   res 4
t13   res 4
t14   res 4
t12   res 0
t15   res 4
t16   res 4
null   res 4
t17   res 0
t18   res 4
t19   res 4
t110   res 4
t111   res 4
n    res 4
i    res 4
t112   res 4
t113   res 4
t114   res 4
t115   res 4
null   res 4
arr    res 4
null   res 4
t116   res 4
null   res 4
t117   res 4
null   res 4
t118   res 4
null   res 4
t119   res 4
null   res 4
t1110   res 4
null   res 4
t1111   res 4
null   res 4
t1112   res 4
t1113   res 4
t1114   res 4
t1115   res 4
buf  res 20
cr   db 10
