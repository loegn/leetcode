package test;

/**
 * @date : 2019/04/28 10:05
 * @author: liangenmao
 */
public class YeildThread {
    public static void main(String[] args) {
        MyThread mt = new MyThread();
        mt.start();
        while (true) {
            System.out.println("主线程");
        }
    }
}
class MyThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("被放弃线程");
            yield();
        }
    }
}