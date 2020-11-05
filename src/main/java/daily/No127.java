package daily;

import java.util.*;

public class No127 {
    /**
     * 广度优先搜索 + 优化建图
     * 首先为了方便表示，我们先给每一个单词标号，即给每个单词分配一个 id。创建一个由单词 word 到 id 对应的映射 wordId，并将 beginWord 与 wordList 中所有的单词都加入这个映射中。之后我们检查 endWord 是否在该映射内，若不存在，则输入无解。我们可以使用哈希表实现上面的映射关系。
     * 然后我们需要建图，依据朴素的思路，我们可以枚举每一对单词的组合，判断它们是否恰好相差一个字符，以判断这两个单词对应的节点是否能够相连。但是这样效率太低，我们可以优化建图。
     * 具体地，我们可以创建虚拟节点。对于单词 hit，我们创建三个虚拟节点 *it、h*t、hi*，并让 hit 向这三个虚拟节点分别连一条边即可。如果一个单词能够转化为 hit，那么该单词必然会连接到这三个虚拟节点之一。对于每一个单词，我们枚举它连接到的虚拟节点，把该单词对应的 id 与这些虚拟节点对应的 id 相连即可。
     * 最后我们将起点加入队列开始广度优先搜索，当搜索到终点时，我们就找到了最短路径的长度。注意因为添加了虚拟节点，所以我们得到的距离为实际最短路径长度的两倍。同时我们并未计算起点对答案的贡献，所以我们应当返回距离的一半再加一的结果。
     */
    Map<String, Integer> wordId = new HashMap<String, Integer>();
    List<List<Integer>> edge = new ArrayList<List<Integer>>();
    int nodeNum = 0;

    public int ladderLength1(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }
        int[] dis = new int[nodeNum];
        Arrays.fill(dis, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord), endId = wordId.get(endWord);
        dis[beginId] = 0;

        Queue<Integer> que = new LinkedList<Integer>();
        que.offer(beginId);
        while (!que.isEmpty()) {
            int x = que.poll();
            if (x == endId) {
                return dis[endId] / 2 + 1;
            }
            for (int it : edge.get(x)) {
                if (dis[it] == Integer.MAX_VALUE) {
                    dis[it] = dis[x] + 1;
                    que.offer(it);
                }
            }
        }
        return 0;
    }

    public void addEdge(String word) {
        addWord(word);
        int id1 = wordId.get(word);
        char[] array = word.toCharArray();
        int length = array.length;
        for (int i = 0; i < length; ++i) {
            char tmp = array[i];
            array[i] = '*';
            String newWord = new String(array);
            addWord(newWord);
            int id2 = wordId.get(newWord);
            edge.get(id1).add(id2);
            edge.get(id2).add(id1);
            array[i] = tmp;
        }
    }

    public void addWord(String word) {
        if (!wordId.containsKey(word)) {
            wordId.put(word, nodeNum++);
            edge.add(new ArrayList<Integer>());
        }
    }
//    Map<String, Integer> wordId = new HashMap<String, Integer>();
//    List<List<Integer>> edge = new ArrayList<List<Integer>>();
//    int nodeNum = 0;

    /**
     * 双向广度优先搜索
     * 根据给定字典构造的图可能会很大，而广度优先搜索的搜索空间大小依赖于每层节点的分支数量。假如每个节点的分支数量相同，搜索空间会随着层数的增长指数级的增加。考虑一个简单的二叉树，每一层都是满二叉树的扩展，节点的数量会以 222 为底数呈指数增长。
     * 如果使用两个同时进行的广搜可以有效地减少搜索空间。一边从 beginWord 开始，另一边从 endWord 开始。我们每次从两边各扩展一层节点，当发现某一时刻两边都访问过同一顶点时就停止搜索。这就是双向广度优先搜索，它可以可观地减少搜索空间大小，从而提高代码运行效率。
     */
    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        for (String word : wordList) {
            addEdge(word);
        }
        addEdge(beginWord);
        if (!wordId.containsKey(endWord)) {
            return 0;
        }

        int[] disBegin = new int[nodeNum];
        Arrays.fill(disBegin, Integer.MAX_VALUE);
        int beginId = wordId.get(beginWord);
        disBegin[beginId] = 0;
        Queue<Integer> queBegin = new LinkedList<Integer>();
        queBegin.offer(beginId);

        int[] disEnd = new int[nodeNum];
        Arrays.fill(disEnd, Integer.MAX_VALUE);
        int endId = wordId.get(endWord);
        disEnd[endId] = 0;
        Queue<Integer> queEnd = new LinkedList<Integer>();
        queEnd.offer(endId);

        while (!queBegin.isEmpty() && !queEnd.isEmpty()) {
            int queBeginSize = queBegin.size();
            for (int i = 0; i < queBeginSize; ++i) {
                int nodeBegin = queBegin.poll();
                if (disEnd[nodeBegin] != Integer.MAX_VALUE) {
                        return (disBegin[nodeBegin] + disEnd[nodeBegin]) / 2 + 1;
                }
                for (int it : edge.get(nodeBegin)) {
                    if (disBegin[it] == Integer.MAX_VALUE) {
                        disBegin[it] = disBegin[nodeBegin] + 1;
                        queBegin.offer(it);
                    }
                }
            }

            int queEndSize = queEnd.size();
            for (int i = 0; i < queEndSize; ++i) {
                int nodeEnd = queEnd.poll();
                if (disBegin[nodeEnd] != Integer.MAX_VALUE) {
                    return (disBegin[nodeEnd] + disEnd[nodeEnd]) / 2 + 1;
                }
                for (int it : edge.get(nodeEnd)) {
                    if (disEnd[it] == Integer.MAX_VALUE) {
                        disEnd[it] = disEnd[nodeEnd] + 1;
                        queEnd.offer(it);
                    }
                }
            }
        }
        return 0;
    }

//    public void addEdge(String word) {
//        addWord(word);
//        int id1 = wordId.get(word);
//        char[] array = word.toCharArray();
//        int length = array.length;
//        for (int i = 0; i < length; ++i) {
//            char tmp = array[i];
//            array[i] = '*';
//            String newWord = new String(array);
//            addWord(newWord);
//            int id2 = wordId.get(newWord);
//            edge.get(id1).add(id2);
//            edge.get(id2).add(id1);
//            array[i] = tmp;
//        }
//    }
//
//    public void addWord(String word) {
//        if (!wordId.containsKey(word)) {
//            wordId.put(word, nodeNum++);
//            edge.add(new ArrayList<Integer>());
//        }
//    }

    /**
     * 暴力解法，超时
     */
    public int ladderLength3(String beginWord, String endWord, List<String> wordList) {
        boolean[] used = new boolean[wordList.size()];
        String currWord = beginWord;
        dfs(currWord, endWord, wordList, used, 0);
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    int min = Integer.MAX_VALUE;

    private void dfs(String currWord, String endWord, List<String> wordList, boolean[] used, int sum) {
        sum++;
        if (Objects.equals(currWord, endWord)) {
            min = Math.min(sum, min);
            return;
        }
        for (int i = 0; i < used.length; i++) {
            if (!used[i]) {
                String nextWord = wordList.get(i);
                if (compare(currWord, nextWord)) {
                    used[i] = true;
                    dfs(nextWord, endWord, wordList, used, sum);
                    used[i] = false;
                }
            }
        }
    }

    private boolean compare(String currWord, String nextWord) {
        boolean end = false;
        for (int i = 0; i < currWord.length(); i++) {
            if (currWord.charAt(i) != nextWord.charAt(i)) {
                if (end) {
                    return false;
                }
                end = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        No127 no127 = new No127();
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        no127.ladderLength1("hit", "cog", wordList);
    }
}
