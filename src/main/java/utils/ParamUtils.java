package utils;

import pojo.Interval;
import pojo.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @date : 2019/04/19 14:56
 * @author: liangenmao
 */
public class ParamUtils {
    /**
     * [[1,3],[2,6],[8,10],[15,18]]
     */
    public static int[][] getInts(String intsStr) {
        if (Objects.equals(intsStr.trim(), "[]")) {
            return new int[0][];
        } else if (Objects.equals(intsStr.trim(), "[[]]")) {
            return new int[1][];
        }
        intsStr = intsStr.replace("\n", "").replace(" ", "");
        String[] strings = intsStr.substring(2, intsStr.length() - 2).split("],\\[");
        int[][] determinant = new int[strings.length][];
        for (int i = 0; i < strings.length; i++) {
            String[] ints = strings[i].split(",");
            determinant[i] = new int[ints.length];
            for (int j = 0; j < ints.length; j++) {
                determinant[i][j] = Integer.parseInt(ints[j]);
            }
        }
        return determinant;
    }

    public static char[][] getChars(String charsStr) {
        if (Objects.equals(charsStr.trim(), "[]")) {
            return new char[0][];
        } else if (Objects.equals(charsStr.trim(), "[[]]")) {
            return new char[1][];
        }
        charsStr = charsStr.replace("\n", "").replace(" ", "");
        String[] strings = charsStr.substring(2, charsStr.length() - 2).split("],\\[");
        char[][] determinant = new char[strings.length][];
        for (int i = 0; i < strings.length; i++) {
            String[] chars = strings[i].split(",");
            determinant[i] = new char[chars.length];
            for (int j = 0; j < chars.length; j++) {
                determinant[i][j] = chars[j].charAt(1);
            }
        }
        return determinant;
    }

    public static int[] getInt(String intsStr) {
        if (Objects.equals(intsStr.trim(), "[]")) {
            return new int[0];
        }
        intsStr = intsStr.replace("\n", "").replace(" ", "");
        String[] strings = intsStr.substring(1, intsStr.length() - 1).split(",");
        int[] ints = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            ints[i] = Integer.parseInt(strings[i]);
        }
        return ints;
    }

    /**
     * [[1,3],[2,6],[8,10],[15,18]]
     */
    public static List<Interval> getIntervals(String intervalsStr) {
        intervalsStr = intervalsStr.replace("\n", "").replace(" ", "");
        List<Interval> intervals = new ArrayList<>();
        String[] strings = intervalsStr.substring(2, intervalsStr.length() - 2).split("],\\[");
        for (int i = 0; i < strings.length; i++) {
            String[] ints = strings[i].split(",");
            Interval interval = new Interval(Integer.parseInt(ints[0]), Integer.parseInt(ints[1]));
            intervals.add(interval);
        }
        return intervals;
    }

    /**
     * [1,2,3]
     */
    public static Interval getInterval(String intervalStr) {
        String[] strings = intervalStr.substring(1, intervalStr.length() - 1).split(",");
        return new Interval(Integer.parseInt(strings[0]), Integer.parseInt(strings[1]));
    }

    public static Object get(Object o) {
        return o;
    }

    public static void main(String[] args) {
        String d = "[[1,3],[2,6],[8,10],[15,18]]";
        System.out.println(getInts(d));
    }

    /**
     * 1->2->3->3->4->4->5
     */
    public static ListNode getListNode(String listNodes){
        ListNode result = new ListNode(0);
        ListNode curr = result;
        String[] strings;
        if (listNodes.contains("->")){
            strings = listNodes.trim().replaceAll(" ","").split("->");
        }else if (listNodes.contains("[")){
            strings = listNodes.trim().replaceAll(" ","")
                    .replaceAll("]","")
                    .replaceAll("\\[","")
                    .split(",");
        }else {
            throw new RuntimeException("convert error");
        }
        for (String string : strings) {
            ListNode listNode = new ListNode(Integer.parseInt(string));
            curr.next = listNode;
            curr = listNode;
        }
        return result.next;
    }
}
