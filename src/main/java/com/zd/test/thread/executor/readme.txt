在JDK1.5中提供了线程池的支持，主要的作用是支持高并发的访问处理，并且可以将线程对象进行复用，
核心原理即创建一个运行效率比较优异的“线程池ThreadPool”，在池中支持线程对象管理，包括创建与销毁，
使用池时只需要执行具体的任务即可，线程对象的处理都在池中被封装了。

在介绍线程池之前，先要了解一下接口java.util.concurrent.Excutor，与线程池有关的大部分类都是实现此
接口的，该接口非常简单，只有一个方法void execute(Runnable command)，但Executor是接口，并不能直接使用，
下图描述了主要的Excecutor接口的继承结构。
Executor
    |-(C)DefaultExecutor
    |-(C)LinearExecutor
    |-(C)ThreadPerTaskExecutor
    |-(I)ExecutorService
        |-(C)AbstractExecutorService
            |-(C)ThreadPoolExecutor
            |-(C)ForkJoinPool
            |-(C)DelegatedExecutorService
        |-(I)ScheduledExecutorService
            |-(C)ScheduledThreadPoolExecutor
            |-(C)DelegatedScheduledExecutorService


接口Executor仅仅是一种规范，是一种声明，是一种定义，并没有实现任何的功能，所以大多数的情况下，需要使用接口的
实现类，但ThreadPoolExecutor在使用上并不是那么方便，在实例化时需要传入很多参数，还要考虑线程的并发数等与线程
池运行效率有关的参数，所以官方建议使用Executors工厂类来创建线程池对象。


