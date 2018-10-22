## threadlocal的作用：    
threadlocal的作用是提供线程内的局部变量，这种变量在多线程条件下能保证局部变量的独立性。
在多线程环境下，为每一个局部变量保存一个副本，而且总是得到这个局部变量最新的值（使用了volatile修饰）

如何为每一个线程保存一个变量的副本：使用ThreadLocalMap，这是一个自定义hash映射，仅用于维护线程本地变量的值。它的key值是当前ThreadLocal，value是需要设置的值，例如：“threadLocal.set(5)”，会将“threadLocal”和“5”作为键值对保存在该线程的threadLocals里。每个线程都有一个ThreadLocalMap类型的ThreadLocals变量。


## threadlocal部分源码：
    
    // hash code
    private final int threadLocalHashCode = nextHashCode();
    // AtomicInteger类型，从0开始
    private static AtomicInteger nextHashCode =  new AtomicInteger();
    // hash code每次增加1640531527
    private static final int HASH_INCREMENT = 0x61c88647;
    // 下一个hash code
    private static int nextHashCode() {
    return nextHashCode.getAndAdd(HASH_INCREMENT);
    }
从源码可知，ThreadLocal的hashcode是从0开始，每次建立一个ThreadLocal，对应的HashCode就增加一个固定值
在ThreadLocal设置新值时，会先根据传入的threadlocal hashcode计算出索引位置，然后从索引位置开始遍历，找到与key相等的值，用新的值替换它，如果没找到，则建立一个新的key-value。

ThreadLocalMap的扩容问题：如果size超过阈值的3/4，则进行扩容，扩容到老表的2倍大小，通过hashcode计算新表的索引位置。