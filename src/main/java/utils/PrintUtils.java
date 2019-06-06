package utils;

/**
 * @date : 2019/04/24 17:00
 * @author: liangenmao
 */
public class PrintUtils {
    public static void print() {
        System.out.println("finish");
    }

    public static void uncompleted(){
        System.out.println("该方法未实现");
        throw new UnsupportedOperationException("该方法未实现");
    }

    public static void error() {
        error("该实现有错误");
    }

    public static void error(String string) {
        System.out.println(string);
        throw new RuntimeException(string);
    }

    public static void print(Object o) {
        if (o instanceof int[][]) {
            print((int[][]) o);
        } else if (o instanceof int[]) {
            print((int[]) o);
        } else {
            System.out.println(o);
        }
    }

    public static void print(int[][] ints) {
        System.out.println("[");
        for (int[] anInt : ints) {
            print(anInt);
        }
        System.out.println("]");
    }

    public static void print(int[] ints) {
        System.out.print("[");
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i]);
            if (i < ints.length - 1) {
                System.out.print(",");
            }
        }
        System.out.println("]");

    }

}
