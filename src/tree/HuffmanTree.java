package tree;


import java.util.*;

/**
 * @author wuxiaoming
 * @date 2017-11-20 15:22
 */
public class HuffmanTree {
    Node mRoot;
    static Node diyNode;

    /**
     * 创建哈弗曼树
     *
     * @param nodeList
     * @return
     */
    public Node createTree(List<Node> nodeList) {
        int count = 1;
        while (nodeList.size() > 1) {
            Collections.sort(nodeList);
            Node left = nodeList.get(nodeList.size() - 1);
            Node right = nodeList.get(nodeList.size() - 2);
            Node parent = new Node("P" + count++, left.weight + right.weight);
            if (count == 5) {
                diyNode = parent;
                System.out.println("count=" + count + "的时候,节点为:" + parent.data);
            }
            parent.left = left;
            left.parent = parent;
            parent.right = right;
            right.parent = parent;
            nodeList.remove(left);
            nodeList.remove(right);
            nodeList.add(parent);
        }
        mRoot = nodeList.get(0);
        return nodeList.get(0);
    }

    /**
     * 层次遍历
     *
     * @param root
     * @return
     */
    public List<Node> breadth(Node root) {
        ArrayList<Node> list = new ArrayList<>();
        Queue<Node> queue = new ArrayDeque<>();
        if (root == null) {
            return null;
        }
        queue.offer(root);
        while (!queue.isEmpty()) {
            list.add(queue.peek());
            Node node = queue.poll();
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return list;
    }

    /**
     * 获取哈夫曼编码 left 0  right 1
     *
     * @param node
     */
    public void getCode(Node node) {
        if (node.parent == null) {
            System.out.println("您所查找的元素不存在，请重新选择");
            return;
        }
        Stack<String> stack = new Stack<>();
        Node tNode = node;
        while (tNode != null && tNode.parent != null) {
            if (tNode.parent.left == tNode) {
                stack.push("0");
            }
            if (tNode.parent.right == tNode) {
                stack.push("1");
            }
            tNode = tNode.parent;
        }
        System.out.println("节点" + node.data + "的哈夫曼编码为:");
        while (!stack.isEmpty()) {
            System.out.print(stack.pop());
        }
    }

    /**
     * 获取树的深度  递归实现
     *
     * @param root
     * @return
     */
    public int getDeep(Node root) {
        int index = 0;
        if (root != null) {
            int leftDeep = getDeep(root.left);
            int rightDeep = getDeep(root.right);
            index = leftDeep > rightDeep ? leftDeep + 1 : rightDeep + 1;
        }
        return index;
    }

    /**
     * 获取树的深度  层次遍历实现
     *
     * @param root
     * @return
     */
    public int getDeep2(Node root) {
        int level = 0, last, cur;
        Queue<Node> queue = new ArrayDeque<>();
        Node node;
        if (root == null) {
            return 0;
        } else {
            queue.offer(root);
            while (!queue.isEmpty()) {
                last = queue.size();
                cur = 0;
                while (cur < last) {
                    node = queue.poll();
                    cur++;
                    if (node.left != null) {
                        queue.offer(node.left);
                    }
                    if (node.right != null) {
                        queue.offer(node.right);
                    }
                }
                level++;//循环完一层，加1
            }
            return level;
        }
    }

    static class Node<T> implements Comparable<Node<T>> {
        public T data;
        public double weight;
        public Node left;
        public Node right;
        public Node parent;

        public Node(T data, double weight) {
            this.data = data;
            this.weight = weight;
            this.left = null;
            this.right = null;
        }

        @Override
        public int compareTo(Node<T> o) {
            if (o.weight > weight) {
                return 1;
            }
            if (o.weight < weight) {
                return -1;
            }
            return 0;

        }
    }

    public static void main(String[] args) {
        HuffmanTree huffmanTree = new HuffmanTree();
        ArrayList<Node> list = new ArrayList<>();
        Node node = new Node("f", 10);
        list.add(node);
        list.add(new Node("a", 7));
        list.add(new Node("b", 2));
        list.add(new Node("c", 14));
        list.add(new Node("d", 5));
        list.add(new Node("e", 79));
//        list.add(new Node("f", 10));
        list.add(new Node("g", 42));
        list.add(new Node("h", 9));
        list.add(new Node("i", 18));
        String leftData, rightData;
        huffmanTree.createTree(list);
        List<Node> breadthList = huffmanTree.breadth(huffmanTree.mRoot);
        for (Node<String> stringNode : breadthList) {
            leftData = stringNode.left != null ? (String) stringNode.left.data : null;
            rightData = stringNode.right != null ? (String) stringNode.right.data : null;
            System.out.println("data:" + stringNode.data + " left:" + leftData + " right:" + rightData + " weight:" + stringNode.weight);
        }
        huffmanTree.getCode(node);
        System.out.println("\n树的深度为：" + huffmanTree.getDeep(diyNode) + "---" + huffmanTree.getDeep2(diyNode));
    }
}
