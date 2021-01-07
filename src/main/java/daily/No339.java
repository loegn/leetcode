package daily;

import java.util.*;

/**
 * https://leetcode-cn.com/problems/evaluate-division/
 * 给你一个变量对数组 equations 和一个实数值数组 values 作为已知条件，其中 equations[i] = [Ai, Bi] 和 values[i] 共同表示等式 Ai / Bi = values[i] 。每个 Ai 或 Bi 是一个表示单个变量的字符串。
 * 另有一些以数组 queries 表示的问题，其中 queries[j] = [Cj, Dj] 表示第 j 个问题，请你根据已知条件找出 Cj / Dj = ? 的结果作为答案。
 * 返回 所有问题的答案 。如果存在某个无法确定的答案，则用 -1.0 替代这个答案。
 * 注意：输入总是有效的。你可以假设除法运算中不会出现除数为 0 的情况，且不存在任何矛盾的结果。
 */
public class No339 {
    /**
     * 广度优先搜索
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation1(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars = 0;
        Map<String, Integer> variables = new HashMap<String, Integer>();

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }

        // 对于每个点，存储其直接连接到的所有点及对应的权值
        List<Pair>[] edges = new List[nvars];
        for (int i = 0; i < nvars; i++) {
            edges[i] = new ArrayList<Pair>();
        }
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            edges[va].add(new Pair(vb, values[i]));
            edges[vb].add(new Pair(va, 1.0 / values[i]));
        }

        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                if (ia == ib) {
                    result = 1.0;
                } else {
                    Queue<Integer> points = new LinkedList<Integer>();
                    points.offer(ia);
                    double[] ratios = new double[nvars];
                    Arrays.fill(ratios, -1.0);
                    ratios[ia] = 1.0;

                    while (!points.isEmpty() && ratios[ib] < 0) {
                        int x = points.poll();
                        for (Pair pair : edges[x]) {
                            int y = pair.index;
                            double val = pair.value;
                            if (ratios[y] < 0) {
                                ratios[y] = ratios[x] * val;
                                points.offer(y);
                            }
                        }
                    }
                    result = ratios[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    class Pair {
        int index;
        double value;

        Pair(int index, double value) {
            this.index = index;
            this.value = value;
        }
    }

    /**
     * Floyd算法
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation2(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars = 0;
        Map<String, Integer> variables = new HashMap<String, Integer>();

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }
        double[][] graph = new double[nvars][nvars];
        for (int i = 0; i < nvars; i++) {
            Arrays.fill(graph[i], -1.0);
        }
        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            graph[va][vb] = values[i];
            graph[vb][va] = 1.0 / values[i];
        }

        for (int k = 0; k < nvars; k++) {
            for (int i = 0; i < nvars; i++) {
                for (int j = 0; j < nvars; j++) {
                    if (graph[i][k] > 0 && graph[k][j] > 0) {
                        graph[i][j] = graph[i][k] * graph[k][j];
                    }
                }
            }
        }

        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                if (graph[ia][ib] > 0) {
                    result = graph[ia][ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    /**
     * 带权并查集
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation3(List<List<String>> equations, double[] values, List<List<String>> queries) {
        int nvars = 0;
        Map<String, Integer> variables = new HashMap<String, Integer>();

        int n = equations.size();
        for (int i = 0; i < n; i++) {
            if (!variables.containsKey(equations.get(i).get(0))) {
                variables.put(equations.get(i).get(0), nvars++);
            }
            if (!variables.containsKey(equations.get(i).get(1))) {
                variables.put(equations.get(i).get(1), nvars++);
            }
        }
        int[] f = new int[nvars];
        double[] w = new double[nvars];
        Arrays.fill(w, 1.0);
        for (int i = 0; i < nvars; i++) {
            f[i] = i;
        }

        for (int i = 0; i < n; i++) {
            int va = variables.get(equations.get(i).get(0)), vb = variables.get(equations.get(i).get(1));
            merge(f, w, va, vb, values[i]);
        }
        int queriesCount = queries.size();
        double[] ret = new double[queriesCount];
        for (int i = 0; i < queriesCount; i++) {
            List<String> query = queries.get(i);
            double result = -1.0;
            if (variables.containsKey(query.get(0)) && variables.containsKey(query.get(1))) {
                int ia = variables.get(query.get(0)), ib = variables.get(query.get(1));
                int fa = findf(f, w, ia), fb = findf(f, w, ib);
                if (fa == fb) {
                    result = w[ia] / w[ib];
                }
            }
            ret[i] = result;
        }
        return ret;
    }

    public void merge(int[] f, double[] w, int x, int y, double val) {
        int fx = findf(f, w, x);
        int fy = findf(f, w, y);
        f[fx] = fy;
        w[fx] = val * w[y] / w[x];
    }

    public int findf(int[] f, double[] w, int x) {
        if (f[x] != x) {
            int father = findf(f, w, f[x]);
            w[x] = w[x] * w[f[x]];
            f[x] = father;
        }
        return f[x];
    }

    /**
     * 我的解答
     *
     * @param equations
     * @param values
     * @param queries
     * @return
     */
    public double[] calcEquation4(List<List<String>> equations, double[] values, List<List<String>> queries) {
//        List<String> node = new ArrayList<>();
        Map<String, Map<String, Double>> edge = new HashMap<>();
        int length = equations.size();
        for (int i = 0; i < length; i++) {
            List<String> curr = equations.get(i);
            if (!edge.containsKey(curr.get(0))) {
                edge.put(curr.get(0), new HashMap<>());
            }
            if (!edge.containsKey(curr.get(1))) {
                edge.put(curr.get(1), new HashMap<>());
            }
            edge.get(curr.get(0)).put(curr.get(1), values[i]);
            //FIXME 精度问题
            edge.get(curr.get(1)).put(curr.get(0), 1 / values[i]);
        }
        double[] result = new double[queries.size()];
        for (int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);
            if (edge.containsKey(dividend) && edge.containsKey(divisor)) {
                if (dividend.equals(divisor)) {
                    result[i] = 1;
                } else {
                    Map<String, Double> map = new HashMap<>();
                    map.put(dividend, 1D);
                    Deque<String> deque = new ArrayDeque<>();
                    deque.add(dividend);
                    while (!deque.isEmpty()) {
                        String node = deque.poll();
                        edge.get(node).forEach((k, v) -> {
                            if (!map.containsKey(k)) {
                                map.put(k, map.get(node) * v);
                                deque.add(k);
                            }
                        });
                    }
                    result[i] = map.containsKey(divisor) ? map.get(divisor) : -1;
                }
            } else {
                result[i] = -1;
            }
        }
        return result;
    }

    public static void main(String[] args) {

    }
}
