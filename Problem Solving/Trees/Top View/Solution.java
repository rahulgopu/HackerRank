import java.util.*;
import java.io.*;

class Node {
    Node left;
    Node right;
    int data;

    Node(int data) {
        this.data = data;
        left = null;
        right = null;
    }
}

class Solution {

    /*
     * 
     * class Node int data; Node left; Node right;
     */
    static Map<Integer, Integer> views = new HashMap<>();

    static void traverse(Node root, int pos) {
        // -------------------
        // left side of tree
        // -------------------
        if (pos < 0 && !views.containsKey(pos)) {
            views.put(pos, root.data);
        }
        if (root.left != null) {
            traverse(root.left, pos - 1);
        }
        // -------------------
        // right side of tree
        // -------------------
        if (root.right != null) {
            traverse(root.right, pos + 1);
        }
        if (pos >= 0) {
            views.put(pos, root.data);
        }
    }

    public static void topView(Node root) {
        traverse(root, 0);

        views.keySet().stream().sorted().forEach(pos -> {
            System.out.printf("%d ", views.get(pos));

            // debug
            // System.out.printf("%d(%d) ", views.get(pos), pos);
        });
    }

    public static Node insert(Node root, int data) {
        if (root == null) {
            return new Node(data);
        } else {
            Node cur;
            if (data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int t = scan.nextInt();
        Node root = null;
        while (t-- > 0) {
            int data = scan.nextInt();
            root = insert(root, data);
        }
        scan.close();
        topView(root);
    }
}