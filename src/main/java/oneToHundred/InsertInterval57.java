package oneToHundred;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import pojo.Interval;
import utils.ParamUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @date : 2019/04/22 14:46
 * @author: liangenmao
 */
public class InsertInterval57 {
    /**
     * 未经过仔细测试，可优化到O(n)
     */
    public List<Interval> result1(List<Interval> intervals, Interval newInterval) {
        Interval start = intervals.get(0);
        Interval end = intervals.get(intervals.size() - 1);
        Integer startIndex = null;
        Integer endIndex = null;
        int newIntervalStart = newInterval.start;
        int newIntervalEnd = newInterval.end;
        Integer startValue = newIntervalStart;
        Integer endValue = newIntervalEnd;
        if (newIntervalStart < start.start) {
            startIndex = 0;
        } else if (newIntervalStart > end.end) {
            startIndex = intervals.size() * 2 - 1;
        }
        if (newIntervalEnd < start.start) {
            endIndex = 0;
        } else if (newIntervalEnd > end.end) {
            endIndex = intervals.size() * 2 - 1;
        }
        if (startIndex == null || endIndex == null) {
            Interval curr;
            Interval next = start;
            for (int i = 0; i < intervals.size() - 1; i++) {
                if (startIndex != null && endIndex != null) {
                    break;
                }
                curr = next;
                next = intervals.get(i + 1);
                if (startIndex == null){
                    if (curr.start <= newIntervalStart && newIntervalStart <= curr.end) {
                        startIndex = 2 * i + 1;
                        startValue = curr.start;
                    } else if (newIntervalStart > curr.end && newIntervalStart < next.start) {
                        startIndex = 2 * i + 2;
                    }
                }
                if (endIndex == null){
                    if (curr.start <= newIntervalEnd && newIntervalEnd <= curr.end) {
                        endIndex = 2 * i + 1;
                        endValue = curr.end;
                    } else if (newIntervalEnd > curr.end && newIntervalEnd < next.start) {
                        endIndex = 2 * i + 2;
                    }
                }
            }
        }
        List<Interval> result = new ArrayList<>();
        Interval addInterval = new Interval(startValue, endValue);
        if ((startIndex & 1) == 1 || (endIndex & 1) == 1) {
            for (int i = 0; i < intervals.size(); i++) {
                if (i == startIndex >> 1) {
                    result.add(addInterval);
                }
                if (i < startIndex >> 1 || i > (endIndex - 1) >> 1) {
                    result.add(intervals.get(i));
                }
            }
        } else {
            result = intervals;
            result.add(startIndex >> 1, addInterval);
        }
        return result;
    }

    private int[][] convert(List<Interval> intervals) {
        int[][] result = new int[intervals.size()][];
        for (int i = 0; i < intervals.size(); i++) {
            int[] ints = {intervals.get(i).start, intervals.get(i).end};
            result[i] = ints;
        }
        return result;
    }

    private List<Interval> convert(int[][] ints) {
        List<Interval> list = new ArrayList<>(ints.length);
        for (int i = 0; i < ints.length; i++) {
            list.add(new Interval(ints[i][0],ints[i][1]));
        }
        return list;
    }

    /**
     * 网友回答
     */
    public List<Interval> result2(List<Interval> intervals, Interval newInterval) {
        List<Interval> r = new ArrayList<Interval>();
        for(int i =0; i < intervals.size(); i++) {
            if(intervals.get(i).start >= newInterval.start && intervals.get(i).end <= newInterval.end){//重合
                //System.out.println(i+","+0);
                continue;
            }
            else if(intervals.get(i).start <= newInterval.start && intervals.get(i).end >= newInterval.end){//重合
                //System.out.println(i+","+1);
                return intervals;
            }
            else if(intervals.get(i).start > newInterval.end) {//完全不相交
                //System.out.println(i+","+2);
                r.add(newInterval);
                for(; i < intervals.size(); i++) {
                    r.add(intervals.get(i));
                }
                return r;
            }
            else if(intervals.get(i).end < newInterval.start) {//完全不相交
                //System.out.println(i+","+3);
                r.add(intervals.get(i));
            }
            else if(intervals.get(i).start <= newInterval.end && intervals.get(i).start >= newInterval.start) { //重叠
                //System.out.println(i+","+4);
                newInterval.end = intervals.get(i).end;
            }
            else if(intervals.get(i).end >= newInterval.start && intervals.get(i).end <= newInterval.end) {//重叠
                //System.out.println(i+","+5);
                newInterval.start = intervals.get(i).start;
            }
        }
        r.add(newInterval);
        return r;
    }

    @Test
    public void merge() {
        String determinant = "[[1,2],[3,5],[6,7],[8,10],[12,16]]";
        List<Interval> intervals = ParamUtils.getIntervals(determinant);
        Interval interval = ParamUtils.getInterval("[4,8]");
        Object result = result1(intervals, interval);
        print(result);
    }

    private void print(Object result) {
        Object json = JSONObject.toJSON(result);
        System.out.println(json);
    }
}
