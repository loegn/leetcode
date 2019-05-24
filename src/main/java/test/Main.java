package test;

/**
 * @date : 2019/04/28 10:31
 * @author: liangenmao
 */
public class Main {
    public static void main(String[] args) {
        QueueBuffer q = new QueueBuffer();
        new Producer(q);
        new Consumer(q);
        System.out.println("Press Control-C to stop.");
    }

    static class Producer implements Runnable {

        private QueueBuffer q;

        Producer(QueueBuffer q) {
            this.q = q;
            new Thread(this, "Producer").start();
        }

        @Override
        public void run() {
            int i = 0;
            while (true) {
                q.put(i++);
            }
        }

    }

    static class Consumer implements Runnable {

        private QueueBuffer q;

        Consumer(QueueBuffer q) {
            this.q = q;
            new Thread(this, "Consumer").start();
        }

        @Override
        public void run() {
            while (true) {
                q.get();
            }
        }

    }

    static class QueueBuffer {
        int n;
        boolean valueSet = false;

        synchronized int get() {
            if (!valueSet) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException caught");
                }
            }
            System.out.println("Got: " + n);
            valueSet = false;
            notify();
            return n;
        }

        synchronized void put(int n) {
            if (valueSet) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    System.out.println("InterruptedException caught");
                }
            }
            this.n = n;
            valueSet = true;
            System.out.println("Put: " + n);
            notify();
        }
    }
}
