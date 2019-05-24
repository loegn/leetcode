package oneToHundred;

/**
 * @date : 2019/02/20 15:36
 * @author: liangenmao
 */
public class ReverseInteger {
    public static void main(String[] args) {
        int result = result1(123);
        System.out.println(result);
    }

    public static int result1(int x) {
        Boolean flag = true;
        String origin = new Integer(x).toString();
        String result = "";
        String maxIntStr = new Integer(Integer.MAX_VALUE).toString();
        String minIntStr = new Integer(Integer.MIN_VALUE).toString();
        if (origin.charAt(0) == '-') {
            flag = false;
            result = new StringBuilder(origin.substring(1, origin.length())).reverse().toString();
        } else {
            result = new StringBuilder(origin).reverse().toString();
        }
        if (flag == false && minIntStr.equals("-" + result)) {
            return Integer.MIN_VALUE;
        }
        if (result.length() < maxIntStr.length() || result.compareTo(maxIntStr) <= 0) {
            return new Integer((flag ? "" : "-") + result);
        }
        return 0;
    }
}
