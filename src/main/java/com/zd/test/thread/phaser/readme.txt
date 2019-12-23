通过CyclicBarrier类解决了CountDownLatch的种种缺点，但不可否认的是，CyclicBarrier类还是有一些自身上的缺陷，比如不可动态
添加parties计数，调用一次await()方法仅仅占用1个partie计数，所以在JDK1.7中新增了一个名称为Phaser的类来解决这样的问题。
类Phaser提供了动态增减parties计数的功能，这点比CyclicBarrier类操作parties更加方便，通过若干个方法来控制多个线程之间同步运行的效果，
还可以实现针对某一个线程取消同步运行的效果，而且支持在指定屏障处等待，在等待时还支持中断或非中断等功能，使用java并发类对线程进行分组同步
控制时，Phaser比CyclicBarrier类功能更加强大。

1：arriveAndAwaitAdvance()
arriveAndAwaitAdvance()的作用与CountDownLatch类中的await()方法大体一样，通过从方法的名称解释来看，arrive是到达的意思，wait是
等待的意思，而advance是前进、促进的意思，所以执行这个方法的作用就是当前线程已经到达屏障，在此等待一段时间，等条件满足后继续向下一个
屏障继续执行。
由此可知，类Phaser具有设置多屏障的功能，有些类似于体育比赛中“赛段”的作用，运动员在第一赛段结束后，开始休整准备，然后集体到达第二赛段
的起跑点，等待比赛开始后，运动员们又继续比赛了，说明Phaser类与CyclicBarrier类在功能上有重叠。

通过测试，当计数不足时，线程呈阻塞状态，不能继续向下运行。比如：运动员A,B,C参加一个2阶段的比赛，比赛完第一阶段后，C中途退出比赛（没有参加第二阶段的比赛），
导致后面的比赛不能正常继续，此时我们可以使用Phaser类实现一个注销比赛的功能；

2：arriveAndDeregister()
arriveAndDeregister()的作用是使当前线程（运动员）退出比赛，并且使parties的值减一；

3：getParties()和onAdvance()
方法getParties()获取的是已经到达第几个屏障；
方法onAdvance()的作用是通过新的屏障时被调用；

4：getRegisteredParties()和register()
方法getRegisteredParties()获得注册的parties数量
方法register()每执行一次就动态添加一个parties的值

5：bulkRegister()
方法bulkRegister()可以批量增加parties的数量

6：getArrivedParties()和getUnarrivedParties()
getArrivedParties()获得已经使用的parties个数
getUnarrivedParties()获得未被使用的parties个数

7：arrive()
方法arrive()的作用是使parties值加1，并且不在屏障处等待，直接向下面的代码继续执行，当paties值增加到初始化指定的值时会重置为0，Phaser类有计数重置功能；

8：awaitAdvance(int phase)
方法awaitAdvance(int phase)的作用是：如果传入参数phase值和当前的getPhase()方法返回值一样，则在屏障处等待，否则继续向下运行，有些
类似于旁观者的作用，当观察的条件满足了就等待，如果条件不满足则程序向下继续运行。
该方法并不参与parties的计数操作，仅仅具有判断的功能。
该方法是不可中断的，即当线程中断标志位为true时，该方法不会抛出InterruptedException异常，会继续阻塞;

9：awaitAdvanceInterruptibly(int phase)
和上面awaitAdvance的作用一样，唯一的区别就是该方法是可中断的。

10：forceTermination()和isTerminated()
方法forceTermination()将Phaser对象的屏障功能取消，屏障处的线程继续执行后面的代码，并不出现异常。而CyclicBarrier类的reset()方法执行时会抛出异常。
方法isTerminated()判断Phaser对象是否已经呈销毁状态。

11：通过使用上面的方法进行组合可以控制Phaser类的运行时机；
比如：register()使计数加1，然后通过逻辑代码的方式来决定线程是否继续向下运行。
    public static void main(String[] args) {
        Phaser phaser = new Phaser(3);
        phaser.register();

        // 启动3个线程，每个线程的run方法中调用phaser.awaitAndWaitingAdvance()

        Thread.sleep(5000);
        phaser.awaitAndWaitingAdvance();

    }



