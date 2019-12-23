<T> T invokeAny(Collection<? extends Callable<T>> tasks) : 阻塞方法，取得第一个正常完成任务的结果值，当第一个任务执行完成后，会调用interrupt()方法将其他任务中断标志位置为true；
    异常处理：
        假设：Callable A执行完成的快，Callable B执行完成的慢：
        A正常，B异常：
            (1)在B的call()中没有try catch，不会在控制台打印异常，在main()方法中不会被捕获，不影响主流程；
            (2)在B的call()中显示try catch，然后打印异常，可以在控制台看到异常信息，如果call()中catch后继续throw，在main()方法中仍然不会捕获该异常，主流程不受影响。
        A异常，B正常： 默认情况下不在控制台出现异常信息，除非在A显示try catch打印异常
            (1)在A的call()中没有try catch，由于A异常，此时invokeAny()会将关注点切换到B，返回的是B的结果；A异常不影响B的执行，不影响main()方法执行；
            (2)在A的call()中显示try catch后没有往外抛，此时invokeAny()不知道A发生了异常，不会将关注点切换到B中，返回的还是A的结果；
            (3)在A的call()中显示try catch后继续往外抛，此时invokeAny()会将关注点切换到B，返回的是B的结果；A异常不影响B的执行，不影响main()方法执行；
        全部异常：
            会返回最慢的那个任务的异常信息可以在main()中进行捕获；

<T> T invokeAny(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit) : 在指定时间内取得第一个先执行完任务的结果值，如果取不到则抛出TimeoutException，其他同上；

<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) : 阻塞方法，要把所有的结果都取回才继续向下执行；
    如果有的任务执行过程中出现异常，该异常不会影响invokeAll()，也不会在invokeAll()处抛出，异常会在future.get()时抛出；

<T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks, long timeout, TimeUnit unit)：
    在指定时间内全部获得全部结果，在超时后会继续向下执行，在future.get()时，如果future对应的callable是在timeout时间内执行完的，则返回执行结果；
    如果future对应的callable是没有在timeout时间内执行完的，则抛出：java.util.concurrent.Cancellation异常；
