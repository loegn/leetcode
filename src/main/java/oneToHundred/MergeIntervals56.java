package oneToHundred;

import com.alibaba.fastjson.JSONObject;
import org.junit.jupiter.api.Test;
import pojo.Interval;
import utils.ParamUtils;

import java.util.*;

/**
 * @date : 2019/04/19 14:55
 * @author: liangenmao
 */
public class MergeIntervals56 {
    public int[][] result1(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return intervals;
        }
        List<int[]> list = new ArrayList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        int start = intervals[0][0];
        int end = 0;
        for (int i = 0; i < intervals.length - 1; i++) {
            end = Math.max(intervals[i][1], end);
            if (end < intervals[i + 1][0]) {
                int[] ints = {start, end};
                list.add(ints);
                start = intervals[i + 1][0];
            }
        }
        end = Math.max(intervals[intervals.length - 1][1], end);
        int[] ints = {start, end};
        list.add(ints);
        int[][] result = new int[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            result[i] = list.get(i);
        }
        return result;
    }

    // merges all of the nodes in this connected component into one interval.
    private Interval result2(List<Interval> nodes) {
        int minStart = nodes.get(0).start;
        for (Interval node : nodes) {
            minStart = Math.min(minStart, node.start);
        }

        int maxEnd = nodes.get(0).end;
        for (Interval node : nodes) {
            maxEnd= Math.max(maxEnd, node.end);
        }

        return new Interval(minStart, maxEnd);
    }

    private Map<Interval, List<Interval> > graph;
    private Map<Integer, List<Interval> > nodesInComp;
    private Set<Interval> visited;

    // return whether two intervals overlap (inclusive)
    private boolean overlap(Interval a, Interval b) {
        return a.start <= b.end && b.start <= a.end;
    }

    // build a graph where an undirected edge between intervals u and v exists
    // iff u and v overlap.
    private void buildGraph(List<Interval> intervals) {
        graph = new HashMap<>();
        for (Interval interval : intervals) {
            graph.put(interval, new LinkedList<>());
        }

        for (Interval interval1 : intervals) {
            for (Interval interval2 : intervals) {
                if (overlap(interval1, interval2)) {
                    graph.get(interval1).add(interval2);
                    graph.get(interval2).add(interval1);
                }
            }
        }
    }

    public List<Interval> result3(List<Interval> intervals) {
        Collections.sort(intervals, new IntervalComparator());

        LinkedList<Interval> merged = new LinkedList<Interval>();
        for (Interval interval : intervals) {
            // if the list of merged intervals is empty or if the current
            // interval does not overlap with the previous, simply append it.
            if (merged.isEmpty() || merged.getLast().end < interval.start) {
                merged.add(interval);
            }
            // otherwise, there is overlap, so we merge the current and previous
            // intervals.
            else {
                merged.getLast().end = Math.max(merged.getLast().end, interval.end);
            }
        }

        return merged;
    }

    private class IntervalComparator implements Comparator<Interval> {
        @Override
        public int compare(Interval a, Interval b) {
            return a.start < b.start ? -1 : a.start == b.start ? 0 : 1;
        }
    }

    @Test
    public void merge() {
        String determinant = "[[2,3],[4,5],[6,7],[8,9],[1,10]]";
        int[][] intervals = ParamUtils.getInts(determinant);
        List<Interval> intervals2 = ParamUtils.getIntervals(determinant);
        Object result = result1(intervals);
        Object result2 = result2(intervals2);
        print(result);
        print(result2);
    }

    private void print(Object result) {
        Object json = JSONObject.toJSON(result);
        System.out.println(json);
    }

 /*   public List<Interval> result1(List<Interval> intervals) {
        List<Interval> result = new ArrayList<>();
        in
        int start = intervals.get(0).start;
        int end = 0;
        for (int i = 0; i < intervals.size() - 1; i++) {
            end = intervals.get(i).end;
            if (intervals.get(i).end < intervals.get(i + 1).start) {
                Interval interval = new Interval(start, end);
                result.add(interval);
                start = intervals.get(i + 1).start;
            }
        }
        end = intervals.get(intervals.size() - 1).end;
        Interval interval = new Interval(start, end);
        result.add(interval);
        return result;
    }

    @Test
    public void merge() {
        String determinant = "[[1,3],[2,6],[8,10],[15,18]]";
        List<Interval> intervals = ParamUtils.getIntervals(determinant);
        Object result = result1(intervals);
        print(result);
    }*/

    private void print(List<Interval> result) {
        Object json = JSONObject.toJSON(result);
        System.out.println(json);
    }
}
