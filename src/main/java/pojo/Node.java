package pojo;

import java.util.List;

/**
 * @date : 2019/05/30 10:01
 * @author: liangenmao
 */
public class Node {
    public int val;
    public List<Node> neighbors;
    public Node left;
    public Node right;
    public Node next;

    public Node() {
    }

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, Node _left, Node _right, Node _next) {
        val = _val;
        left = _left;
        right = _right;
        next = _next;
    }

    public Node(int _val, List<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
