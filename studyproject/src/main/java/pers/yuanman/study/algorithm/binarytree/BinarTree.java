package pers.yuanman.study.algorithm.binarytree;


/**
 * 功能概要：排序/平衡二叉树
 * 二叉排序树的定义
 */
public class BinarTree {

    public TreeNode root;

    public long size;

    /**
     * 往树中加节点
     *
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
     *
     * @param data
     * @return Boolean 插入成功返回true
     */
    public Boolean deteleTreeNode(Integer data) {
        /**
         * 假设被删结点是*p，其双亲是*f，不失一般性，设*p是*f的左孩子，下面分三种情况讨论： 　　
         * ⑴ 若结点*p是叶子结点，则只需修改其双亲结点*f的指针即可。 　　
         * ⑵ 若结点*p只有左子树PL或者只有右子树PR，则只要使PL或PR 成为其双亲结点的左子树即可。 　　
         * ⑶ 若结点*p的左、右子树均非空，先找到*p的中序前趋(或后继)结点*s
         * （注意*s是*p的左子树中的最右下的结点，它的右链域为空），
         * 然后有两种做法：
         * ① 令*p的左子树直接链到*p的双亲结点*f的左链上,而*p的右子树链到*p的中序前趋结点*s的右链上。
         * ② 以*p的中序前趋结点*s代替*p（即把*s的数据复制到*p中），将*s的左子树链到*s的双亲结点*q的左（或右）链上。
         */

        //删除时先判断是否存在
        TreeNode treeNode = findTreeNode(data);
        if (treeNode != null) {
            System.out.println("删除的数据存在，执行删除......");
            //判断是否是叶子节点
            if (treeNode.right == null && treeNode.left == null) {
                //叶子节点直接删除
                treeNode = null;
                System.out.println("删除的数据成功：" + data);
                return true;
            } else
                //判断是否是根节点
                if (treeNode.parent == null) {
                    //根节点,默认以左树最大值替换根节点，无左树则以右树最小值替换
                    if (treeNode.left != null) {
                        return deleteLeftTreeNode(treeNode);
                    } else {
                        return deleteRightTreeNode(treeNode);
                    }
                } else {
                    //非根节点
                    //判断左右树
                    if (treeNode.data < root.data) {
                        //左树
                        return deleteLeftTreeNode(treeNode);
                    } else {
                        //右树
                        return deleteRightTreeNode(treeNode);
                    }
                }
        }
        System.out.println("删除的数据不存在：" + data);
        //treeNode.data=null;
        //自己写 先删除左边的，然后再一起考虑右边的
        return false;
    }

    /**
     * 删除左树节点逻辑
     *
     * @param treeNode
     * @return
     */
    private Boolean deleteLeftTreeNode(TreeNode treeNode) {
        Integer data = treeNode.data;
        //左树
        /*   ⑶ 若结点*p的左、右子树均非空，先找到*p的中序前趋(或后继)结点*s
         * （注意*s是*p的左子树中的最右下的结点，它的右链域为空），
         * 然后有两种做法：
         * ① 令*p的左子树直接链到*p的双亲结点*f的左链上,而*p的右子树链到*p的中序前趋结点*s的右链上。
         * ② 以*p的中序前趋结点*s代替*p（即把*s的数据复制到*p中），将*s的左子树链到*s的双亲结点*q的左（或右）链上。
         */
        //*p是treeNode   *s是bottomRightTreeNode
        //*f是*p的上级节点即parent
        //查找*s
        TreeNode bottomRightTreeNode = findBottomRightTreeNode(treeNode.left);
        TreeNode parent = treeNode.parent;
        if (parent != null) {
            //① 令*p的左子树直接链到*p的双亲结点*f的左链上
            parent.left = treeNode.left;
            parent.left.parent = treeNode.parent;
            //而*p的右子树链到*p的中序前趋结点*s的右链上。
            bottomRightTreeNode.right = treeNode.right;
            bottomRightTreeNode.parent = treeNode.parent;
        } else {
            //treeNode为根节点,即被删除的是根节点
            treeNode.data = bottomRightTreeNode.data;
            if (bottomRightTreeNode.left != null) {
                bottomRightTreeNode.left.parent = bottomRightTreeNode.parent;
                bottomRightTreeNode.parent.right = bottomRightTreeNode.left;
            } else {
                bottomRightTreeNode.parent.right = null;
            }
        }
        //完成了
        System.out.println("删除的数据成功：" + data);
        return true;
    }


    /**
     * 删除右树节点逻辑
     *
     * @param treeNode
     * @return
     */
    private Boolean deleteRightTreeNode(TreeNode treeNode) {
        Integer data = treeNode.data;
        /*   ⑶ 若结点*p的左、右子树均非空，先找到*p的中序前趋(或后继)结点*s
         * （注意*s是*p的右子树中的最右下的结点，它的左链域为空），
         * 然后有两种做法：
         * ① 令*p的右子树直接链到*p的双亲结点*f的右链上,而*p的左子树链到*p的中序前趋结点*s的左链上。
         * ② 以*p的中序前趋结点*s代替*p（即把*s的数据复制到*p中），将*s的右子树链到*s的双亲结点*q的左（或右）链上。
         */
        //右树
        //逻辑跟左树相反
        //注意这里的*s是*p的右子树中的最左下的结点，它的左链域为空）
        TreeNode bottomLeftTreeNode = findBottomLeftTreeNode(treeNode.right);
        TreeNode parent = treeNode.parent;
        if (parent != null) {
            //① 令*p的右子树直接链到*p的双亲结点*f的右链上
            parent.right = treeNode.right;
            parent.right.parent = treeNode.parent;
            //而*p的左子树链到*p的中序前趋结点*s的左链上。
            bottomLeftTreeNode.left = treeNode.left;
            bottomLeftTreeNode.parent = treeNode.parent;
        } else {
            //treeNode为根节点,即被删除的是根节点
            treeNode.data = bottomLeftTreeNode.data;
            if (bottomLeftTreeNode.right != null) {
                bottomLeftTreeNode.right.parent = bottomLeftTreeNode.parent;
                bottomLeftTreeNode.parent.left = bottomLeftTreeNode.right;
            } else {
                bottomLeftTreeNode.parent.left = null;
            }
        }

        //完成了
        System.out.println("删除的数据成功：" + data);
        return true;
    }


    /**
     * 根据树节点查找它的最右下的结点，它的右链域为空
     *
     * @param tree
     * @return
     */
    public TreeNode findBottomRightTreeNode(TreeNode tree) {
        //判断是否存在右子树
        if (tree.right != null) {
            //存在右子树就继续找到
            return findBottomRightTreeNode(tree.right);
        } else {
            return tree;
        }
    }

    /**
     * 根据树节点查找它的最左下的结点，它的左链域为空
     *
     * @param tree
     * @return
     */
    public TreeNode findBottomLeftTreeNode(TreeNode tree) {
        //判断是否存在右子树
        if (tree.left != null) {
            //存在右子树就继续找到
            return findBottomLeftTreeNode(tree.left);
        } else {
            return tree;
        }
    }

    /**
     * 查询ee
     *
     * @param data
     * @return TreeNode
     */
    public TreeNode findTreeNode(Integer data) {
        if (null == root) {
            return null;
        }
        TreeNode current = root;
        while (current != null) {
            if (current.data > data) {
                current = current.left;
            } else if (current.data < data) {
                current = current.right;
            } else {
                return current;
            }

        }
        return null;
    }

}
