package util;

public class TreeTraversal {
    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        root.left.left = new Node(4);
        root.left.right = new Node(5);

        System.out.println("preorder");
        preorder(root);
        System.out.println();

        System.out.println("inorder");
        inorder(root);
        System.out.println();

        System.out.println("postorder");
        postorder(root);
        System.out.println();
    }


    // root left right
    static void preorder(Node node) {
        System.out.print(node.num + " ");
        if (node.left != null) {
            preorder(node.left);
        }
        if (node.right != null) {
            preorder(node.right);
        }
    }

    // left root right
    static void inorder(Node node) {
        if (node.left != null) {
            inorder(node.left);
        }
        System.out.print(node.num + " ");
        if (node.right != null) {
            inorder(node.right);
        }
    }

    // left right root
    static void postorder(Node node) {
        if (node.left != null) {
            postorder(node.left);
        }
        if (node.right != null) {
            postorder(node.right);
        }
        System.out.print(node.num + " ");
    }

    static class Node{
        int num;
        Node left;
        Node right;
        Node(int num) {
            this.num = num;
        }
    }
}
