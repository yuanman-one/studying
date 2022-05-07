package pers.yuanman.study.algorithm.binarytree;


/**
 * 功能概要：树节点类的定义
 */
public class TreeNode {

    public Integer data;

    /*该节点的父节点*/
    public TreeNode parent;

    /*该节点的左子节点*/
    public TreeNode left;

    /*该节点的右子节点*/
    public TreeNode right;

    public TreeNode(Integer data) {
        this.data = data;

    }

    public TreeNode() {
        super();
    }

    @Override
    public String toString() {
        return "BinaryTree [data=" + data + "]";
    }

}

