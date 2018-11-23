package com;

/**
 * 多线程本地化对象的容器测试.
 * ThreadLocal 为使用的每个线程创建一个独立的变量副本，不影响其他线程所对应的副本
 */
public class SimpleThreadLocal
{
    private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>()
    {
        /**
         * 初始化值
         */
        @Override
        public Integer initialValue()
        {
            return 0;
        }
    };

    /**
     * 获取下一个序列值.
     * @return 下一个序列值 next num
     */
    public int getNextNum()
    {
        seqNum.set(seqNum.get() + 1);
        return seqNum.get();
    }

    public static void main(String[] args)
    {
        SimpleThreadLocal local = new SimpleThreadLocal();

        TestClient testClient1 = new TestClient(local);
        TestClient testClient2 = new TestClient(local);
        TestClient testClient3 = new TestClient(local);

        testClient1.start();
        testClient2.start();
        testClient3.start();
    }

    public static class TestClient extends Thread
    {
        private SimpleThreadLocal local;

        public TestClient(SimpleThreadLocal local)
        {
            this.local = local;
        }

        @Override
        public void run()
        {
            for(int i = 0; i < 3; i++)
            {
                System.out.println("thread[" + Thread.currentThread().getName() + "] local[" + local.getNextNum() + "]");
            }
        }
    }
}
