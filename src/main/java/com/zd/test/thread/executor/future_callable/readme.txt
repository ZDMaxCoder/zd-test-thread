在默认情况下，线程对象不具有返回值的功能，如果在需要取得返回值的情况下是极为不方便的，但在JAVA1.5的并发包中可以使用Future和Callable
来使线程具有返回值的功能。

接口Callable与线程功能密不可分，但和Runnable的主要区别是：
    (1)Callable接口的call()方法可以有返回值，而Runnable接口的run()方法没有返回值；
    (2)Callable接口的call()方法可以抛出异常，而Runnable接口的run()方法不可以抛出异常；

执行完Callable接口中的任务后，返回值是通过Future接口获得的；

一个Callable任务对应一个Future(默认的实现类是FutureTask);

向线程池提交Callable任务,通过返回值Future来获取结果：
    (1)使用ExecutorService接口的 <T> Future<T> submit(Callable<T> task) 方法提交一个任务并获得该任务的Future；
    (2)使用ExecutorService接口的 <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) 方法批量提交任务；

方法execute()与submit()的区别：
    (1)方法execute()在默认的情况下异常直接抛出，不能捕获，但可以通过自定义的ThreadFactory的方式进行捕获，而submit()方法在默认的情况下
       可以通过 try catch ExecutionExeption捕获异常；
    (2)方法submit()有返回值,execute()没有返回值；


线程池在默认情况下使用的是接口Future的实现类FutureTask：
    get(): 获得线程执行的结果；
           该方法是阻塞的，也就是如果调用Future对象的get()方法时，任务尚未完成，则一直阻塞到任务完成为止。
           一个Callable对应一个Future，如果前面先执行的任务一旦耗时很多，则后面的任务调用get()方法就呈阻塞状态，也就是排队进行等待，
           大大影响运行效率。也就是主线程并不能保证首先获得的是最先完成任务的返回值，这就是Future的缺点，影响效率；
           JDK提供了CompletionService接口可以解决这个问题。
    get(long timeout, TimeUnit unit): 在指定的时间内获得返回值，如果没有返回则抛出TimeoutExeption;

使用建议：
(1) 由于get()方法是阻塞，在有关闭线程池的需求下一定要先关闭线程池，伪代码如下：
    ExecutorService pool = Executors.newFixedThreadPool(10);
    List<Callable<T>> tasks = new ArrayList<Callable<T>>();
    ...//实现Callable接口，实现call()接口
    List<Future<T>> results = pool.invokeAll(T); //提交任务到线程池,注意invokeAll()是阻塞的，直到取回所有的任务结果才续走，所以这里可能出现阻塞；

    // 关闭线程池
    pool.shutdown();
    if (!pool.awaitTermination(600000, TimeUnit.MILLISECONDS)) {
    	pool.shutdownNow();
    }
    // 分析结果
    for (int i = 0; i < results.size(); i++) {
		results.get(i).get() // 获取结果，由于先关闭的线程
    }
(2) 由于get()方法是阻塞，在不能关闭线程池的情况下，需要使用get(long timeout, TimeUnit unit)，防止一直阻塞；
———————————————————————————————————————————————————————————————————————————————————————————————————
|                                                                                                  |
|     private final class Worker extends AbstractQueuedSynchronizer implements Runnable {          |
|                                                                                                  |
|        ......                                                                                    |
|                                                                                                  |
|        Worker(Runnable firstTask) {                                                              |
|            setState(-1); // inhibit interrupts until runWorker                                   |
|            this.firstTask = firstTask;                                                           |
|            this.thread = getThreadFactory().newThread(this);                                     |
|        }                                                                                         |
|                                                                                                  |
|        public void run() {                                                                       |
|            runWorker(this);                                                                      |
|        }                                                                                         |
|                                                                                                  |
|        ......                                                                                    |
|                                                                                                  |
|    }                                                                                             |
___________________________________________________________________________________________________|
                                            |
                                          w.run()   <- Thread.start()
                                            |
                                    ThreadPoolExecute.runWork()
                                            |
                                        RunnableTask.run()
                                            |
                                FutureTask implement Future,Runnable
                                            |
                                          f.run()
                                            |
                                   result = callable.call()
                                            |
                                      f.outcome = result
                                            |
                                        f.get() 返回outcome





