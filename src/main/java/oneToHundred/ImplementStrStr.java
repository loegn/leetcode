package oneToHundred;

import org.junit.jupiter.api.Test;

/**
 * @date : 2019/03/26 09:34
 * @author: liangenmao
 */
public class ImplementStrStr {
    public int result1(String haystack, String needle) {
        if ("".equals(needle)) {
            return 0;
        }
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        int index = -1;
        for (int i = 0; i <= h.length - n.length; i++) {
            for (int j = 0; i + j < h.length && j < n.length; j++) {
                if (h[i + j] != n[j]) {
                    break;
                }
                if (j == n.length - 1) {
                    return i;
                }
            }
        }
        return index;
    }

    public int result2(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public int result3(String haystack, String needle) {
        if (needle.equals("") || haystack.equals(needle)) {
            return 0;
        }
        int m = haystack.length();
        int n = needle.length();
        for (int i = 0; i <= m-n; i++) {
            if (haystack.substring(i,i+n).equals(needle)) {
                return i;
            }
        }
        return -1;
    }

     private static void KMP(String source, String pattern) {
        int[] N=getN(pattern);
        int res=0;
        int sourceLength=source.length();
        int patternLength=pattern.length();
        for(int i=0;i<=(sourceLength-patternLength);){
            res++;
            String str=source.substring(i, i+patternLength);//要比较的字符串
            p(str);
            int count=getNext(pattern, str,N);
            p("移动"+count+"步");
            if(count==0){
                p("oneToHundred.KMP：匹配成功");
                break;
            }
            i=i+count;
        }
        p("oneToHundred.KMP：一共匹配"+res+"次数");
    }

    private static int getNext(String pattern,String str,int[] N) {
        int n = pattern.length();
        char v1[] = str.toCharArray();
        char v2[] = pattern.toCharArray();
        int x = 0;
        while (n-- != 0) {
            if (v1[x] != v2[x]){
                if(x==0){
                    return 1;//如果第一个不相同，移动1步
                }
                return x-N[x-1];//x:第一次出现不同的索引的位置，即j
            }
            x++;
        }
        return 0;
    }
    private static int[] getN(String pattern) {
        char[] pat=pattern.toCharArray();
        int j=pattern.length()-1;
        int[] N=new int[j+1];
        for(int i=j;i>=2;i--){
            N[i-1]=getK(i,pat);
        }
        for(int a:N)
            p(a);
        return N;
    }
    private static int getK(int j, char[] pat) {
        int x=j-2;
        int y=1;
        while (x>=0 && compare(pat, 0, x, y, j-1)) {
            x--;
            y++;
        }
        return x+1;
    }
    private static boolean compare(char[] pat,int b1,int e1,int b2,int e2){
        int n = e1-b1+1;
        while (n-- != 0) {
            if (pat[b1] != pat[b2]){
                return true;
            }
            b1++;
            b2++;
        }
        return false;
    }
    public static void p(Object obj) {
        System.out.println(obj);
    }

    @Test
    public void strStr() {
        String haystack = "uycbasdbaask";
        String needle = "basdba";
        Object result = result1(haystack, needle);
        KMP(haystack, needle);
        System.out.println(result);
    }
}
