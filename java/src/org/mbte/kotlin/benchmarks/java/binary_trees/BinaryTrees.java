package org.mbte.kotlin.benchmarks.java.binary_trees;

public class BinaryTrees implements Runnable {

    private final static int minDepth = 4;

    @Override
    public void run() {
        int maxDepth = 20;
        int stretchDepth = maxDepth + 1;

        int check = (TreeNode.bottomUpTree(0,stretchDepth)).itemCheck();
        System.out.println("stretch tree of depth "+stretchDepth+"\t check: " + check);

        TreeNode longLivedTree = TreeNode.bottomUpTree(0,maxDepth);

        for (int depth=minDepth; depth<=maxDepth; depth+=2){
            int iterations = 1 << (maxDepth - depth + minDepth);
            check = 0;

            for (int i=1; i<=iterations; i++){
                check += (TreeNode.bottomUpTree(i,depth)).itemCheck();
                check += (TreeNode.bottomUpTree(-i,depth)).itemCheck();
            }
            System.out.println((iterations*2) + "\t trees of depth " + depth + "\t check: " + check);
        }
        System.out.println("long lived tree of depth " + maxDepth + "\t check: "+ longLivedTree.itemCheck());
    }


    private static class TreeNode
    {
        private TreeNode left, right;
        private int item;

        TreeNode(int item){
            this.item = item;
        }

        private static TreeNode bottomUpTree(int item, int depth){
            if (depth>0){
                return new TreeNode(
                        bottomUpTree(2*item-1, depth-1)
                        , bottomUpTree(2*item, depth-1)
                        , item
                );
            }
            else {
                return new TreeNode(item);
            }
        }

        TreeNode(TreeNode left, TreeNode right, int item){
            this.left = left;
            this.right = right;
            this.item = item;
        }

        private int itemCheck(){
            // if necessary deallocate here
            if (left==null)
                return item;
            else {
                return item + left.itemCheck() - right.itemCheck();
            }
        }
    }
}

