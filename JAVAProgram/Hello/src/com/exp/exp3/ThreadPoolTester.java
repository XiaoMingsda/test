package com.exp.exp3;

import jdk.nashorn.internal.ir.SplitReturn;

import java.util.LinkedList;

public class ThreadPoolTester {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("用法：java ThreadPoolTest numTaskspoolSize");
            System.out.println("numTasks integer:任务的数目");//6
            System.out.println("numThreads integer:线程池中的线程数目");//3
            return;
        }
        int numTasks = Integer.parseInt(args[0]);
        int poolSize = Integer.parseInt(args[1]);

        ThreadPool threadPool = new ThreadPool(poolSize);
        for (int i = 0; i < numTasks; i++) {
            System.out.println("execute执行1");
            threadPool.execute(createTask(i));//创建一个Runnable
            System.out.println("execute执行2");
        }
        threadPool.join();
    }

    private static Runnable createTask(final int taskID) {
        return new Runnable() {
            @Override
            public void run() {
                System.out.println("Task" + taskID + ":start");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {}
                System.out.println("Task" + taskID + ":end");
            }
        };
    }
}

class ThreadPool extends ThreadGroup {
    private boolean isClosed = false;
    private LinkedList<Runnable> workQueue;
    private static int threadPoolID;
    private int threadID;

    public ThreadPool(int poolSize) {
        super("ThreadPool" + (threadPoolID++));
        setDaemon(true);
        workQueue = new LinkedList<Runnable>();
        for (int i = 0; i < poolSize; i++) {
            new WorkThread().start();
        }
    }

    public synchronized void execute(Runnable task) {
        System.out.println("进入execute方法");
        if (isClosed) {
            throw new IllegalStateException();
        }
        if (task != null) {
            workQueue.add(task);//插入到workQueue队列里
            notify();
        }
        System.out.println("退出execute方法");
    }

    protected synchronized Runnable getTask() throws InterruptedException {
        while (workQueue.size() == 0) {
            if (isClosed) return null;
            wait();
        }
        System.out.println("workQueue：" + workQueue.getFirst());
        return workQueue.removeFirst();
    }

    public synchronized void close() {
        if (isClosed) {
            isClosed = true;
            workQueue.clear();
            interrupt();
        }
    }

    public void join() {
        synchronized (this) {
            isClosed = true;
            notifyAll();
        }
        Thread[] threads = new Thread[activeCount()];
        int count = enumerate(threads);
        System.out.println("count = " + count);
        for (int i = 0; i < count; i++) {
            try {
                System.out.println("join方法 " + threads[i]);
                threads[i].join();
                System.out.println(2);
            } catch (InterruptedException ex) {}
        }
    }

    private class WorkThread extends Thread {
        public WorkThread() {
            super(ThreadPool.this, "WorkThread" + (threadID++));
            System.out.println("WorkThread的构造方法");
        }
        public void run() {
            while (!isInterrupted()) {
                System.out.println("WorkThread2");
                Runnable task = null;
                try {
                    task = getTask();
                } catch (InterruptedException ex) {}
                if (task == null) {
                    System.out.println("task == null");
                    return;
                }
                System.out.println("WorkThread3");
                try {
                    task.run();
                } catch (Throwable t) {
                    t.printStackTrace();
                }
            }
        }
    }
}