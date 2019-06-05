package pojo;

import java.util.List;

/**
 * @date : 2019/05/30 10:01
 * @author: liangenmao
 */
public class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
