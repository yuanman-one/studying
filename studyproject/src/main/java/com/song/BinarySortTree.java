package com.song;

import java.util.Stack;

public class BinarySortTree {
    private Node root;

    public Node getRoot() {
        return root;
    }

    public void setRoot(Node root) {
        this.root = root;
    }


    /**非递归查找二叉排序树中是否有key值*/
    public boolean searchBST(int key){
        //根结点，双链表
        Node current = root;
        while(current != null){
            if(key == current.getValue())
                return true;
            else if(key < current.getValue())
                current = current.getLeft();
            else
                current = current.getRight();
        }
        return false;
    }

    /**递归 查找二叉排序树中是否有key值*/
    public boolean searchBST(Node bitree ,int key){
        if(bitree == null){
            return Boolean.FALSE;
        }
        if(bitree.value == key)
            return Boolean.TRUE;
        else if(bitree.value > key)
            return searchBST(bitree.left, key);
        else if(bitree.value < key)
            return searchBST(bitree.right, key);

        return false;
    }

    /**向二叉排序树中插入结点*/
    public void insertBST(int key){
        Node p = root;
        /**记录查找结点的前一个结点*/
        Node prev = null;
        /**一直查找下去，直到到达满足条件的结点位置*/
        while(p != null){
            prev = p;
            if(key < p.getValue())
                p = p.getLeft();
            else if(key > p.getValue())
                p = p.getRight();
            else
                return;
        }
        /**prve是要安放结点的父节点，依据结点值得大小，放在对应的位置*/
        if(root == null)
            root = new Node(key);
        else if(key < prev.getValue())
            prev.setLeft(new Node(key));
        else prev.setRight(new Node(key));
    }



    /**
     * 删除二叉排序树中的结点
     * 分为三种情况：（删除结点为*p 。其父结点为*f）
     * （1）要删除的*p结点是叶子结点，仅仅须要改动它的双亲结点的指针为空
     * （2）若*p仅仅有左子树或者仅仅有右子树，直接让左子树/右子树取代*p
     * （3）若*p既有左子树，又有右子树
     * 		用p左子树中最大的那个值（即最右端S）取代P。删除s，重接其左子树
     * */





    public Node search(int value) {
        if(root==null) {
            return null;
        }else {
            return root.search(value);
        }

    }
    public Node searchParent(int value) {
        if (root == null) {
            return null;
        } else {
            return root.searchParent(value);
        }
    }


    public int delRightTreeMin(Node node) {
        Node target = node;
        // 循环的查找左子节点，就会找到最小值
        while (target.left != null) {
            target = target.left;
        }
        // target就指向了最小结点
        // 删除最小结点
        delNode(target.value);
        return target.value;
    }

    /**
     * 功能描述：删除结点
     *
     *
     */
    public void delNode(int value) {
        if (root == null) {
            return;
        } else {
            // 1 先去找到要删除的结点 targetNode
            Node targetNode = search(value);
            // 如果没有找到要删除的结点
            if (targetNode == null) {
                return;
            }
            // 如果发现当前这颗二叉排序树只有一个结点
            if (root.left == null && root.right == null) {
                root = null;
                return;
            }

            // 去找到 targetNode 的父结点
            Node parent = searchParent(value);
            // 如果要删除的结点是叶子结点
            if (targetNode.left == null && targetNode.right == null) {
                // 判断 targetNode 是父结点的左子结点，还是右子结点
                if (parent.left != null && parent.left.value == value) { // 是左子结点
                    parent.left = null;
                } else if (parent.right != null && parent.right.value == value) { // 是由子结点
                    parent.right = null;
                }
            } else if (targetNode.left != null && targetNode.right != null) { // 删除有两颗子树的节点
                targetNode.value = delRightTreeMin(targetNode.right);
            } else { // 删除只有一颗子树的结点
                // 如果要删除的结点有左子结点
                if (targetNode.left != null) {
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.left;
                        } else { //  targetNode 是 parent 的右子结点
                            parent.right = targetNode.left;
                        }
                    } else {
                        root = targetNode.left;
                    }
                } else { // 如果要删除的结点有右子结点
                    if (parent != null) {
                        // 如果 targetNode 是 parent 的左子结点
                        if (parent.left.value == value) {
                            parent.left = targetNode.right;
                        } else { // 如果 targetNode 是 parent 的右子结点
                            parent.right = targetNode.right;
                        }
                    } else {
                        root = targetNode.right;
                    }
                }
            }
        }
    }




    /**中序非递归遍历二叉树
     * 获得有序序列
     * */

    public void nrInOrderTraverse(){
        if(root!=null){
            root.nrInOrderTraverse();
        }else{
            System.out.println("二叉排序树为空，不能遍历");
        }

    }

    /**
     * 判断是否是平衡二叉树
     * 从下往上遍历，如果子树是平衡二叉树，则返回子树的高度；如果发现子树不是平衡二叉树，则直接停止遍历，这样至多只对每一个结点访问一次
     * 左右子树的高度差的绝对值不超过1
     */
    public boolean isBalanced (Node root){

        return etDepth(root) != -1;

    }

    private int etDepth (Node root){

        if (root == null) return 0;
        //递归
        int left = etDepth(root.left);
        if (left == -1) return -1;
        int right = etDepth(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) > 1 ? -1 : 1 + Math.max(left, right);

    }

    public int height (Node root){
        if (root == null) {
            return 0;
        }
        return Math.max(height(root.left), height(root.right)) + 1;
    }


    public static void main (String[] args){
        BinarySortTree bst = new BinarySortTree();
        /**构建的二叉树没有同样元素*/
        int[] nums = {4, 7, 8, 15, 10, 16, 85, 65, 70, 45, 25, 42, 63, 58, 90};
        for (int i = 0; i < nums.length; i++) {
            bst.insertBST(nums[i]);
        }
        //中序遍历
        System.out.println("中序遍历结果为：");
        bst.nrInOrderTraverse();

        //查询

        System.out.println(bst.searchBST(2));
        System.out.println(bst.searchBST(10));
        System.out.println(bst.searchBST(58));
        //删除
        //bst.delNode(16);
//            bst.delNode(4);
//            bst.delNode(7);
//            bst.delNode(8);
//            bst.delNode(10);
//            bst.delNode(15);
//            bst.delNode(25);
//            bst.delNode(42);
//            bst.delNode(45);
//            bst.delNode(58);
//            bst.delNode(63);
//            bst.delNode(65);
//            bst.delNode(70);
//            bst.delNode(85);
//            bst.delNode(90);
//            System.out.println("root=" + bst.getRoot());
//            System.out.println("刪除结点后");
//            bst.nrInOrderTraverse();
//            //判断是不是平衡二叉树
//            System.out.println(bst.isBalanced(bst.root));
//            //判断高度
//            System.out.println(bst.height(bst.root));
//            //添加
//            bst.insertBST(2);
//            System.out.println(bst.searchBST(2));
//	bst.nrInOrderTraverse();
    }


    /**二叉树的结点定义*/
    /**
     * 代表二叉树的链表节点
     */
    class Node {
        private int value;
        private Node left;
        private Node right;


        public Node() {
        }

        public Node(Node left, Node right, int value) {
            this.left = left;
            this.right = right;
            this.value = value;
        }

        public Node(int value) {
            this(null, null, value);
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getValue() {
            return this.value;
        }

        public void setValue(int value) {
            this.value = value;
        }
        public Node  search(int value) {
            if (value == this.value) { // 找到该结点
                return this;
            } else if (value < this.value) {// 如果查找的值小于当前结点值，向左子树递归查找
                // 如果左子结点为空
                if (this.left == null) {
                    return null;
                }
                return this.left.search(value);
            } else { // 如果查找的值不小于当前结点，向右子树递归查找
                if (this.right == null) {
                    return null;
                }
                return this.right.search(value);
            }
        }
        public Node searchParent(int value) {
            // 如果当前结点就是要删除的结点的父结点
            if ((this.left != null && this.left.value == value) ||
                    (this.right != null && this.right.value == value)) {
                return this;
            } else {
                // 如果查找的值小于当前结点的值, 并且当前结点的左子结点不为空
                if (value < this.value && this.left != null) {
                    return this.left.searchParent(value); // 向左子树递归查找
                } else if (value >= this.value && this.right != null) {
                    return this.right.searchParent(value); // 向右子树递归查找
                } else {
                    return null; // 找到父结点
                }
            }
        }
        public void nrInOrderTraverse () {
            Stack<Node> stack = new Stack<Node>();
            Node node = root;
            while (node != null || !stack.isEmpty()) {
                while (node != null) {
                    stack.push(node);
                    node = node.getLeft();
                }
                node = stack.pop();
                System.out.println(node.getValue());
                node = node.getRight();
            }
        }

    }}
