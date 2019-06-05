package datastructure;

import org.junit.jupiter.api.Test;
import utils.PrintUtils;
import pojo.Node;

import java.util.*;

/**
 * @date : 2019/05/30 16:13:19
 * @author: liangenmao
 */
public class CloneGraph {
    public Node result1(Node node) {
        Map<Integer,Node> map = new HashMap<>();
        return helper(node,map);
    }

    private Node helper(Node node, Map<Integer, Node> map) {
        if (map.containsKey(node.val)) {
            return map.get(node.val);
        }else {
            Node newNode = new Node();
            newNode.val = node.val;
            map.put(newNode.val,newNode);
            if (node.neighbors != null){
                newNode.neighbors = new ArrayList<>();
                for (Node neighbor : node.neighbors) {
                    newNode.neighbors.add(helper(neighbor,map));
                }
            }
            return newNode;
        }
    }

    @Test
    public void cloneGraph() {
        Node node1 = new Node(1,null);
        Node node2 = new Node(2,null);
        Node node3 = new Node(3,null);
        Node node4 = new Node(4,null);
        node1.neighbors = Arrays.asList(node2,node4);
        node2.neighbors = Arrays.asList(node1,node3);
        node3.neighbors = Arrays.asList(node4,node2);
        node4.neighbors = Arrays.asList(node3,node1);
        Object result = result1(node1);
        PrintUtils.print(result);
    }
}
