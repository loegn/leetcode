import org.junit.jupiter.api.Test;

import java.util.Random;

/**
 * @date : 2019/07/18
 * @author: liangenmao
 */
public class RandomTest {
    private final int addRate = 557;
    private final int max = 10000;
    private int nowRate = addRate;

    @Test
    public void test() {
        int count = 0;
        int times = 0;
        Random random = new Random();
        int qiwang = 0;
        for (int i = 0; i < 1000000; i++) {
            count++;
            int value = random.nextInt(max);
            if (value < nowRate) {
                times++;
                nowRate = addRate;
            } else {
                nowRate += addRate;
            }
            float rate = (float) times / count;
            if (Math.abs(rate - 0.2f) < 0.01f) {
                qiwang++;
            }
//            System.out.printf("count:" + count + ",times:" + times + ",rate:%f\n", rate);
        }
        System.out.println("count:" + count + ",times:" + times + ",qiwang:" + qiwang);
    }

    public static void main(String[] args) {

    }
}
