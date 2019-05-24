package oneToHundred;

/**
 * @date : 2019/02/20 16:19
 * @author: liangenmao
 */
public class StringtoInteger {
    public static void main(String[] args) {
        System.out.println(result1("   +0 123"));
    }

    public static int result1(String str) {
        StringBuilder intStrBuilding = new StringBuilder();
        Boolean fistNotBlankIsNum = null;
        Boolean plus = null;
        for (char c : str.toCharArray()) {
            if (!Character.isWhitespace(c) && fistNotBlankIsNum != null) {
                if (fistNotBlankIsNum == null) {
                    if (Character.isDigit(c) || c == '+' || c == '-') {
                        fistNotBlankIsNum = true;
                        if (c == '+') {
                            plus = true;
                            continue;
                        }
                        if (c == '-') {
                            plus = false;
                            continue;
                        }
                    } else {
                        return 0;
                    }
                }
                if (!Character.isDigit(c)) {
                    break;
                }
                if (c == '0' && intStrBuilding.length() == 0) {
                    continue;
                }
                intStrBuilding.append(c);
            }
        }
        String intS = intStrBuilding.toString();
        if (plus == null) {
            plus = true;
        }
        if (intS.length() > 0) {
            if (intS.length() > 10 ||
                    (plus && intS.length() == 10 && intS.compareTo(new Integer(Integer.MAX_VALUE).toString()) > 0) ||
                    (!plus && intS.length() == 10 && intS.compareTo(new Integer(Integer.MAX_VALUE).toString()) > 0)) {
                return plus ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }
            return new Integer((plus ? "+" : "-") + intS);
        }
        return 0;
    }
}
