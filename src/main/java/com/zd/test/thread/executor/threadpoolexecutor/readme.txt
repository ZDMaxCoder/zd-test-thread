类ThreadPoolExecutor可以非常方便的创建线程池对象，而不需要程序员设计大量的new 实例化Thread相关的代码。
在使用Executors工厂类的newXXXThreadExecutor()方法可以快速方便的创建线程池，但创建的细节未知，通过
查看源码在调用newSingleThreadExecutor()方法时内部其实是实例化了1个ThreadPoolExecutor的实例，源码如下：

    public static ExecutorService newSingleThreadExecutor() {
        return new FinalizableDelegatedExecutorService
            (new ThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>()));
    }

所以现在看一下ThreadPoolExecutor的构造方法：
常用的构造方法如下：

    public ThreadPoolExecutor(int corePoolSize,
                              int maximumPoolSize,
                              long keepAliveTime,
                              TimeUnit unit,
                              BlockingQueue<Runnable> workQueue) {
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,
             Executors.defaultThreadFactory(), defaultHandler);
    }
参数详细解释：
corePoolSize：
    核心池的大小，即核心池中保存的线程数，代表整个池中保存的最小线程数，包括空闲线程。
    当设置allowCoreThreadTimeOut=true时，如果在keep-alive time内没有任务到达，将终结核心池中的空闲线程；
    当设置allowCoreThreadTimeOut=false时，核心池中的空闲线程会一直存活，不会被终结，默认为false；
maximumPoolSize：
    整个池中允许的最大线程数，注意实际的最大值必须在CAPACITY范围内，CAPACITY = (1 << (Integer.SIZE - 3)) - 1;
keepAliveTime：
    空闲线程存活时间，空闲线程在存活时间内等待接收新的任务，超过存活时间空闲线程将被终结；
    当设置allowCoreThreadTimeOut=true时，该参数对池中所有的空闲线程有效，包括核心池中的空闲线程；
    当设置allowCoreThreadTimeOut=false时，该参数仅对池中非核心池的空闲线程有效；
unit：
    keepAliveTime的时间单位；
workQueue：
    用来缓冲提交的任务；此队列仅保存由execute方法提交的Runnable任务。

参数之间的关系详解，经测验，
当                                    0 < 并发量 <= corePoolSize                           提交的任务会直接被线程池处理掉，任务不会进入队列；
当                         corePoolSize < 并发量 <= corePoolSize + workQueue.capacity      线程池仅仅使用核心池中的线程进行处理，多余的缓冲到队列；
当    corePoolSize + workQueue.capacity < 并发量 <= maximumPoolSize + workQueue.capacity   线程池不仅仅使用核心池中的线程进行处理，同时启动(maximumPoolSize-corePoolSize-workQueue.capacity)个非核心池线程进行处理(非核心池的空闲线程在过了存活时间后必死)，多余的缓冲到队列；
当 maximumPoolSize + workQueue.capacity < 并发量                                           线程池拒绝处理多余的任务，此时进入拒绝策略；


常用的workQueue有3种：ArrayBlockingQueue，LinkedBlockingDeque,SynchronousQueue;
    ArrayBlockingQueue，LinkedBlockingDeque：内部有数据缓存空间。当池中所有线程（maximumPoolSize）都在处理任务没有空闲线程时，如果此时提交任务，任务会被缓存在该队列中，如果此时队列已满，任务会被拒绝执行，抛出：RejectedExecutionException异常；
    SynchronousQueue：内部没有数据缓存空间，数据在生产者和消费者之间直接传递，并不会数将数据缓存到队列中。可以理解为生产者和消费者互相等待对方，握手，然后一起离开。所以当池中所有线程（maximumPoolSize）都在处理任务没有空闲线程时，此时提交任务会被直接拒绝，抛出RejectedExecutionException异常;

所以线程池能承受的最大并发量为  maximumPoolSize + workQueue的容量(SynchronousQueue的容量为0)；

在创建线程池时可以指定线程池ThreadPoolExecutor的拒绝策略：
    当提交的任务被线程池拒绝时，默认情况下，ThreadPoolExecutor类中有4种不同的处理方式：
    AbortPolicy：当任务添加到线程池中被拒绝时，它将抛出RejectedExecutionException异常;
    CallerRunsPolicy：当任务添加到线程池中被拒绝时，会使用调用线程池的线程处理被拒绝的任务；
    DiscardOldestPolicy：当任务添加到线程池中被拒绝时，线程池会放弃等待队列中最旧的未处理任务，然后将被拒绝的任务添加到等待队列中；
    DiscardPolicy：当任务添加到线程池中被拒绝时，线程池将丢弃被拒绝的任务；

接口Runnable在ThredPoolExecutor的队列中是按顺序取出的，执行确实乱序的；

关闭线程池：
    shutdown() :线程池的状态立刻变成SHUTDOWN状态，此时不能再往线程池中添加任何任务，否则会抛出RejectedExecutionException异常，但是，此时线程池不会立刻退出，直到线程池中的任务(包括队列中等待的)都已经处理完成才退出。
    shutdownNow() :使线程池的状态立刻变成STOP状态，并试图停止所有正在执行的线程(正在执行的线程会抛出InterruptedException异常)并且取消正在队列中等待的任务(会将还在队列中等待的任务以List<Runnable>返回)，调用该方法后不能再往线程池中添加任何任务，否则会抛出RejectedExecutionException异常。

    awaitTermination(long timeout, TimeUnit unit) :判断线程池中是否有任务在执行(包括队列中是否有等待任务), 如果没有则不阻塞；如果有，阻塞timeout时间；该方法和shutdown()或者shutdownNow()配合使用，防止线程池中有的任务在执行过程中出现阻塞或执行时间过长；

判断线程池的关闭状态：
    方法shutdown()和shutdownNow()的功能是发出一个关闭线程池的命令，
    isShutdown() :是判断关闭线程池的命令发出或未发出；
    isTerminating() :是判断线程池是否正在关闭进行中；
    isTerminated() :是判断线程池是否已经关闭了；

选择合适的构造函数或者调用线程池的setThreadFactory()方法，可以自定义ThreadFactory，还可以在自定义的线程对象中处理异常
    public class MyThreadFactory Implements ThreadFactory {
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            thread.setName("自定义线程池中的线程对象");
            thread.setUncaughtException(new UncaughtExceptionHandler() {
                public void uncaughtExeception(Thread t, Throwable e) {
                    // 异常处理
                }
            });
        }
    }

方法get/setRejectExceptionHandler(): 可以自定义任务被拒绝时的行为；

线程池在建立后如果没有任务是不会启动核心线程的，随着任务并发的增多逐渐启动全部核心线程，可以调用以下方法来启动所有核心线程：
方法prestartCoreThred(): 每调用一次就创建一个核心线程，返回值是boolean，表示是否启动了；
方法prestartAllCoreThreads(): 启动全部核心线程，返回值是启动核心线程的数量；

方法getCompletedTaskCount()：取得已经执行完成的任务数；

可以重写afterExecute()和beforeExecute()对线程池中的线程对象进行监控
    public class MyThreadPoolExecutor extends ThreadPoolExecutor {

        public MyThreadPoolExecutor(int corePoolSize,int maximumPoolSize,long keepAliveTime,
                                    TimeUnit unit,BlockingQueue<Runnable> workQueue) {
            super(corePoolSize,maximumPoolSize,keepAliveTime,unit,workQueue);
        }

        @Override
        protected void beforeExecute(Runnable r, Throwable t) {
            super.beforeExecute(r,t);
            // 准备执行
            ...
        }

        @Override
        protected void afterExecute(Runnable r, Throwable t) {
            super.afterExecute(r,t);
            // 执行完毕
            ...
        }
    }

remove(Runnable): 可以删除使用execute方式提交的尚未被执行的任务，但是使用submit()方法提交的任务未被执行时不能被删除；

自己想的几个问题：
（1）如何终止线程池，或者线程池如何终止线程，或者shutdown和shutdownNow的区别
（2）如何哪个线程是空闲的
（3）空闲的线程在干什么
（4）多个消费者如何防止并发的从阻塞队列中获取消息
（5）多个生产者并发向阻塞队列中添加消息
（5）添加和执行任务的原理是什么
（6）如何处理异常（Runnable和Callable）
（7）多余核心线程数的线程如何管理，或者多余核心线程数的线程如何定时清理；
（8）Callable原理

