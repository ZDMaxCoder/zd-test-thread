一：集合框架结构简要
    (I)Iterable
        |-(I)Collection<E>
            |-(I)List<E>
            |-(I)Set<E>
            |-(I)Queue<E>
                |-(I)Deque<E>

    (I)List<E> 对Collection进行扩展，允许根据索引位置操作数据，并且内容允许重复；
        |-(C)ArrayList<E> 线程非安全；
        |-(C)Vector<E> 线程安全；但是在并发环境环境中功能有缺陷即：多个线程在获得了Iterator对象后，不允许修改其结构(比如：调用Iterator对象的remove方法，在并发环境下不支持删除操作)，否则会出现java.util.ConcurrentExeception；

    (I)Set<E> 对Collection进行扩展，内容不允许重复，允许对内容排序，防止元素重复的原理是元素需要重写hashCode()和equals()方法；
        |-(C)HashSet 线程非安全；
        |-(C)TreeSet 还实现了SortedSet和NavigableSet接口，对set的功能进行了扩展，比如可以获取set中内容的子集，以比较范围获得子集等等；

    (I)Queue 对Collection接口进行了扩展，可以方便的操作列头；
        |-(C)PriorityQueue 基于优先级的无界优先级队列；
        |-(I)Deque 不仅支持对列头进行操作，还支持对列尾进行操作，所以Deque的全称是“double ended queue（双端队列）”；

二：非阻塞队列
    非阻塞队列的特色就是队列里没有数据时，操作队列会出现异常或返回null，不具有等待/阻塞的特色。
    常见的阻塞队列有：
    ConcurentHashMap：支持并发操作，支持并发修改(即remove)；
        HashMap：线程不安全，并发环境中插入元素，在重新Hash的过程中会出现死循环或者数据错乱；
        HashTable：线程安全，但在并发环境中功能上有缺陷，不支持remove()，否则会抛出java.util.ConcurrentExeception；
        LinkedHashMap：线程不安全，支持key的顺序性；

    ConcurrentSkipListMap：支持并发操作，支持排序(需要key实现Comparable接口)；
    ConcurrentSkipListSet：支持并发操作，支持排序(需要元素实现Comparable接口)，不允许有重复的元素(需要元素重写hashCode()和equals()方法)；
    ConcurrentLinkedQueue：支持并发操作的队列，仅支持队头操作；
    ConcurrentLinkedDeque：支持并发操作的队列，支持队头和队尾的操作；

    CopyOnWriteArrayList：ArrayList是线程不安全的，而该类在并发中是线程安全的。
    CopyOnWriteArraySet：HashSet是线程不安全的，而该类在并发中是线程安全的。

三：阻塞队列
    所谓的阻塞队列BlockingQueue，其实就是如果BlockingQueue是空的，从BlockingQueue取东西的操作将会被阻塞进入等待状态，直到BlockingQueue
    添加进了元素才会被唤醒。同样，如果BlockingQueue是满的，也就是没有空余空间时，试图往对列中存放元素的操作也会被阻塞进入等待状态，直到BlockingQueue里
    有剩余空间才会被唤醒继续操作。
    ArrayBlockingQueue：有界
    PriorityBlockingQueue：支持在并发环境下的优先级阻塞队列。
    LinkedBlockingQueue：无界，也可以定义为有界
    LinkedBlockingDeque
    SynchrousQueue：内部没有缓存空间，不缓存数据，每个插入操作必须对应一个移除操作，否则会一直阻塞；