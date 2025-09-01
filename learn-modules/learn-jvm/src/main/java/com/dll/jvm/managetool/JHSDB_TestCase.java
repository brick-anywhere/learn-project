package com.dll.jvm.managetool;

/**
 * @author dll
 * @date 2025-07-18 15:40
 * staticObj instanceObj localObj 存放在哪里？
 *
 *
 * -Xmx10M -XX:+UseSerialGC -XX:-UseCompressedOops
 * -XX:-UseCompressedOops  关闭压缩指针   打开的话主要作用是减少对象引用的内存占用，从而提高内存利用率和缓存命中率
 *
 *
 * jps -l 查询当前的进程ID
 *
 * jdk9之前的 打开JHSDB 的方式
 *    先找到jdk安装目录 其中的  JAVA_HOME/lib/sa-jdi.jar 就是
 *    启动命令： java -cp .\sa-jdi.jar sun.jvm.hotspot.HSDB
 *    简单的小窗口 最左上角File->Attach to Hotspot process
 *      Tools->Heap Parameters  注： 关注地址
 *      windows->Console 输入命令 scanoops 0x0000000012800000 0x0000000012b00000 com/dll/jvm/managetool/JHSDB_TestCase$ObjectHolder
 *                                                                   com/dll/jvm/managetool/JHSDB_TestCase$ObjectHolder
 *      0x0000000012810b88 com/dll/jvm/managetool/JHSDB_TestCase$ObjectHolder
 *      0x0000000012810b98 com/dll/jvm/managetool/JHSDB_TestCase$ObjectHolder
 *      在新生代气质范围内寻找 com/dll/jvm/managetool/JHSDB_TestCase$ObjectHolder    验证新对象在Eden中的分配规则
 *     https://cloud.tencent.com/developer/article/1903229
 * JDK9及以后得 打开的方式
 *    jhsdb hsdb --pid 11180
 *
 *
 *
 */
public class JHSDB_TestCase {
    static class Test {
        static ObjectHolder staticObj = new ObjectHolder();
        ObjectHolder instanceObj = new ObjectHolder();

        void foo() {
            ObjectHolder localObj = new ObjectHolder();
            System.out.println("suspend"); // 设置一个短点
        }
    }

    private static class ObjectHolder {
    }

    public static void main(String[] args) {
        Test test = new JHSDB_TestCase.Test();
        test.foo();
    }
}
