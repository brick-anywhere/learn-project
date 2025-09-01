package com.dll.jvm.eden;

/**
 *  * 1.8jdk 默认使用 PS(Parallel Scavenge) + PO(Parallel Old) 切换为 Serial + SerialOld
 *  -XX:+UseSerialGC 打开此开关
 *JVM参数： -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 * -verbose:gc  用来监视虚拟机内存回收情况
 * verbose 控制 JVM 输出额外的信息
 *
 * @author dll
 * @date 2025-07-18 14:03
 */
public class TestAllocation {
    private static final int  _1MB =1024*1024;

    public static void testAllocationMethod() {
        byte[] allocation1, allocation2, allocation3, cllocation4;
        allocation1 = new byte [2* _1MB];
        allocation2 = new byte [2* _1MB];
        allocation3 = new byte [2* _1MB];
        cllocation4 = new byte [4* _1MB];// 出现一次YoungGC
    }

    public static void main(String[] args) {
            testAllocationMethod();
    }
}
