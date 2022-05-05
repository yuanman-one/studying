package pers.yuanman.study.algorithm.binarytree;


/**
 * 功能概要：排序/平衡二叉树
 * 二叉排序树的定义
 */
public class BinarTree {

    public TreeNode root;

    public long size;
    private int value;
    private BinarTree left;

    private BinarTree right;

    /**
     * 往树中加节点
     * @param data
     * @return Boolean 插入成功返回true
     */
    public Boolean addTreeNode(Integer data) {

        if (null == root) {
            root = new TreeNode(data);
            System.out.println("数据成功插入到平衡二叉树中");
            return true;
        }

        TreeNode treeNode = new TreeNode(data);// 即将被插入的数据
        TreeNode currentNode = root;
        TreeNode parentNode;

        while (true) {
            parentNode = currentNode;// 保存父节点
            // 插入的数据比父节点小
            if (currentNode.data > data) {
                currentNode = currentNode.left;
                // 当前父节点的左子节点为空
                if (null == currentNode) {
                    parentNode.left = treeNode;
                    treeNode.parent = parentNode;
                    System.out.println("数据成功插入到二叉查找树中");
                    size++;
                    return true;
                }
                // 插入的数据比父节点大
            } else if (currentNode.data < data) {
                currentNode = currentNode.right;
                // 当前父节点的右子节点为空
                if (null == currentNode) {
                    parentNode.right = treeNode;
                    treeNode.parent = parentNode;
                    System.out.println("数据成功插入到二叉查找树中");
                    size++;
                    return true;
                }
            } else {
                System.out.println("输入数据与节点的数据相同");
                return false;
            }
        }
    }

    /**
     * 删除树中节点
     * @param value
     */
    public void deteleTreeNode(Integer value){

            if (root == null) {
                return;
            } else {
                TreeNode node = findTreeNode(value);
                TreeNode parent = (TreeNode) searchParent(value);
                //如果找不到要删除的结点，退出方法
                if (node == null) {
                    return;
                }
                //只有一个结点，该结点为根结点，则该结点一定会被删除
                if (root.left == null && root.right == null) {
                    root = null;
                    return;
                }
                //如果要删除的结点为叶子结点
                if (node.left == null && node.right == null) {
                    //判断叶子结点为左子结点还是右子结点
                    if (parent.left != null && parent.left.value == value) {
                        parent.left = null;
                        return;
                    } else if (parent.right != null && parent.right.value == value) {
                        parent.right = null;
                        return;
                    }
                } else if (node.left != null && node.right != null) {
//                要删除的结点有两颗子树
//                找到要删除结点右子树的最小结点
                    node.value = delRightMin(node.right);
                } else {
//                要删除的结点有一颗子树
                    if (root.value == value) {
//                    当删除结点为根结点
                        if (root.left != null) {
                            root = root.left;
                        } else {
                            root = root.right;
                        }
                    } else if (parent.left == node) {
//                    删除结点为左子结点
                        if (node.left != null) {
//                       删除的结点有左子树
                            parent.left = node.left;
                        } else {
//                        删除的结点有右子树
                            parent.left = node.right;
                        }
                    } else {
//                    删除的结点为右子结点
                        if (node.left != null) {
                            parent.right = node.left;
                        } else {
                            parent.right = node.right;
                        }
                    }
                }
            }
        }

    private Integer delRightMin(TreeNode right) {
        return null;
    }


    //查找结点，返回值为该结点，如果找不到，返回null
        public BinarTree searchNode(int value) {
            if (value == this.value) {
                return this;
            } else if (value < this.value) {
                if (this.left == null) {
                    return null;
                }
                return this.left.searchNode(value);
            } else {
                if (this.right == null) {
                    return null;
                }
                return this.right.searchNode(value);
            }
        }

        //查找要删除结点的父结点
        public Object searchParent(int value) {
            if ((this.left != null && this.left.value == value) || (this.right != null && this.right.value == value)) {
                return this;
            } else {
                if (this.left != null && value < this.value) {
                    return this.left.searchParent(value);
                } else if (this.right != null && value >= this.value) {
                    return this.right.searchParent(value);
                } else {
                    return null;
                }
            }
        }




        /**
         * 查询
         * @param data
         * @return TreeNode
         */
    public TreeNode findTreeNode(Integer data){
        if(null == root){
            return null;
        }
        TreeNode current = root;
        while(current != null){
            if(current.data > data){
                current = current.left;
            }else if(current.data < data){
                current = current.right;
            }else {
                return current;
            }

        }
        return null;
    }

}
