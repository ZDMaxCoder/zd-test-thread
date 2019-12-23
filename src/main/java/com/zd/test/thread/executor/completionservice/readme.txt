接口CompletionService的功能是以异步的方式一边生产新的任务，一边处理已经完成的任务的结果，这样就可以将执行任务和处理任务分离开来进行处理。
使用submit提交任务，使用take()获取最先执行完成任务的Future对象，take()方法按任务执行速度从快到慢的顺序获得Future对象，在CompletionService接口中哪个任务先执行完就先得到哪个任务的返回值，如果当前没有任务
执行完，则take()阻塞，这样就解决了Future的缺点。

接口CompletionService的结构比较简单，仅有一个实现类ExecutorCompletionService，构造方法如下：
    ExecutorCompletionService(Executor executor) //需要一个线程池，将LinkedBlokingQueue作为完成队列；
    ExecutorCompletionService(Executor executor,BlokingQueue<Future<V>> completionQueue) // 需要一个线程池，并将提供的队列作为完成队列

poll() :也是获取Future对象，不阻塞，如果没有直接返回null；
poll(long timeout, TimeUnit unit) :也是获取Future对象，阻塞timeout时间；在timeout时间内获得Future继续执行，没有获得Future（返回null）也继续向下执行；

CompletionService接口的take()和poll()方法不会被池中任务抛出的异常打断，在获得Future后调用其get()方法时会抛出池中任务的异常；

CompletionService接口的Future<V> submit(Runnable task, V result); 在Runnable task中操作V result，然后通过future.get()获得线程执行后的V result；

