package daily;

import pojo.Node;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 完美二叉树
 * 常数空间
 * https://leetcode-cn.com/problems/populating-next-right-pointers-in-each-node/
 */
public class No116 {
    public Node connect(Node root) {
        if (root == null) {
            return root;
        }

        // 从根节点开始
        Node leftmost = root;

        while (leftmost.left != null) {

            // 遍历这一层节点组织成的链表，为下一层的节点更新 next 指针
            Node head = leftmost;

            while (head != null) {

                // CONNECTION 1
                head.left.next = head.right;

                // CONNECTION 2
                if (head.next != null) {
                    head.right.next = head.next.left;
                }

                // 指针向后移动
                head = head.next;
            }

            // 去下一层的最左的节点
            leftmost = leftmost.left;
        }

        return root;
    }

    public Node connect2(Node root) {
        if (root == null) {
            return root;
        }
//        root.next = null;
        dfs(root);
        return root;
    }

    private void dfs(Node root) {
        if (root.left != null) {
            root.left.next = root.right;
            dfs(root.left);
        }
        if (root.right != null) {
            if (root.next != null) {
                root.right.next = root.next.left;
            }
            dfs(root.right);
        }
    }

    public Node connect3(Node root) {
        Deque<Node> deque = new LinkedList();
        deque.addLast(root);
        while (!deque.isEmpty()) {
            int size = deque.size();
            for (int i = 0; i < size; i++) {
                Node curr = deque.removeFirst();
                if (curr.left != null) {
                    deque.addLast(curr.left);
                }
                if (curr.right != null) {
                    deque.addLast(curr.right);
                }
                if (i < size - 1) {
                    curr.next = deque.getFirst();
                }
            }
        }
        return root;
    }

    public Node connect4(Node root) {
        Node head = root;
        while (head != null) {
            Node curr = head;
            while (curr != null) {
                if (curr.left != null) {
                    curr.left.next = curr.right;
                    if (curr.next != null) {
                        curr.right.next = curr.next.left;
                    } else {
                        break;
                    }
                } else {
                    return root;
                }
                curr = curr.next;
            }
            head = head.left;
        }
        return root;
    }
}
