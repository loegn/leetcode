package oneToHundred;

/**
 * @date : 2019/02/27 11:45
 * @author: liangenmao
 */
public class IntegerToRoman {
    public static void main(String[] args) {
        String result = intToRoman(61);
        System.out.println(result);
    }

    public static String intToRoman(int num) {
        if (num < 1 || num > 3999) {
            return "";
        }
        String result = "";
        String[] strings = {"I", "V", "X", "L", "C", "D", "M"};
        for (int i = 0; 0 < num; i++) {
            int remainder = num % 10;
            num = num / 10;
            if (remainder >= 1 && remainder <= 3) {
                for (int i1 = 0; i1 < remainder; i1++) {
                    result = strings[2 * i] + result;
                }
            } else if (remainder == 4) {
                result = strings[2 * i] + strings[2 * i + 1] + result;
            } else if (remainder >= 5 && remainder <= 8) {
                for (int i1 = 0; i1 < remainder - 5; i1++) {
                    result = strings[2 * i] + result;
                }
                result = strings[2 * i + 1] + result;
            } else if (remainder == 9) {
                result = strings[2 * i] + strings[2 * i + 2] + result;
            }
        }
        return result;
    }
}
