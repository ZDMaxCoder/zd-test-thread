CyclicBarrier
Cyclic：循环的；Barrier：屏障；
意义在于屏障可以循环使用，当线程数量达到指定数量时（也就是有指定数量的线程执行了CyclicBarrier的await()方法），这一批线程才可以继续执行，
此时CyclicBarrier的partie(同伴)重新置为0，等待下一批线程（线程执行CyclicBarrier的await()方法，CyclicBarrier的计数器加1，直到达到CyclicBarrier设置的计数时放行此批线程）

此时有如下需求：
学校共有100个学生进行体能测试，要求每10个学生一组进行测试；
由于CountDownLatch的屏障是一次性的，所以需要不断地创建CoutDownLatch构建屏障。
此时可以使用CyclicBarrier：

new CyclicBarrier(10,new Runnable(){

    public void run() {

    }

})

CyclicBarrier的屏障是可以重复使用的，每当一个学生到达时调用CyclicBarrier的await()，此时CyclicBarrier
的partie(同伴)会加1，当partie(同伴)的值达到10时（表示此时有10个线程在屏障处等待），表示已经凑够10个学生，最后一个到达CyclicBarrier的线程负责执行CyclicBarrier时的Action（初始化时传参Runnale），执行完CyclicBarrier的Action后放行；
在放行后，partie(同伴)的值会重置为0（表示此时没有线程在屏障处等待），然后循环，直到再次凑够10个学生，然后放行；
