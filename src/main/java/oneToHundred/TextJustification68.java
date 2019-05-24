package oneToHundred;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @date : 2019/04/28 11:08:20
 * @author: liangenmao
 */
public class TextJustification68 {
    public List<String> result1(String[] words, int maxWidth) {
        List<String> result = new ArrayList<>();
        int currWidth = 0;
        int start = 0;
        int add;
        //有几个分隔
        StringBuilder sb;
        char separator = ' ';
        for (int i = 0; i < words.length; i++) {
            if (currWidth == 0) {
                add = words[i].length();
            } else {
                add = words[i].length() + 1;
            }
            if (currWidth + add > maxWidth || i == words.length - 1) {
                if (i == words.length - 1) {
                    currWidth += add;
                }
                sb = new StringBuilder();
                sb.append(words[start]);
                if (i > start + 1) {
                    int difference = maxWidth - currWidth;
                    int longNum = i == words.length - 1 ? 0 : difference % (i - start - 1);
                    int basic = i == words.length - 1 ? 1 : difference / (i - start - 1);
                    for (int j = start + 1; j < i; j++) {
                        if (longNum > 0) {
                            sb.append(separator);
                            longNum--;
                        }
                        for (int j1 = 0; j1 < basic; j1++) {
                            sb.append(separator);
                        }
                        sb.append(words[j]);
                    }
                }
                if (i == words.length - 1) {
                    for (int i1 = 0; i1 < maxWidth - sb.length(); i1++) {
                        sb.append(separator);
                    }
                }
                result.add(sb.toString());
                 start = i;
                currWidth = words[i].length();
            }else {
                currWidth += add;
            }
        }
        return result;
    }

    @Test
    public void fullJustify() {
        String[] words = {"This", "is", "an", "example", "of", "text", "justification."};
        int maxWidth = 16;
        Object result = result1(words, maxWidth);
        PrintUtils.print(result);
    }
}
