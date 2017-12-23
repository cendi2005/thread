package datastructure;

import java.util.Stack;

public class Main {
    public static void main(String[] args) {

    }
}


class Node {
    int  value;
    Node leftChild;
    Node rightChild;

    Node(int value) {
        this.value = value;
    }

    public void display() {
        System.out.print(this.value + "\t");
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return String.valueOf(value);
    }
}


class BinaryTree{
    private Node root = null;
    private int value;

    public BinaryTree(int value) {
        root = new Node(value);
        root.leftChild = null;
        root.rightChild = null;
        this.value = value;
    }

    //查找
    public Node findKey(int value){
        Node current = root;
        while(true){
            if(value == current.value){
                return current;
            }else if(value < current.value){
                current = current.leftChild;
            }else if(value > current.value){
                current = current.rightChild;
            }
            if(current == null) return null;
        }
    }


    //插入
    public String insert(int value){
        String error = null;
        Node node = new Node(value);
        if(root == null){
            root = node;
            root.leftChild = null;
            root.rightChild = null;
        }else{
            Node current = root;
            Node parent = null;
            while(true){
                if(value < current.value){
                    parent = root;
                    current = current.leftChild;
                    if(current == null){
                        parent.leftChild = node;
                        break;
                    }
                }else if(value > current.value){
                    parent = current;
                    current = current.rightChild;
                    if(current == null){
                        parent.rightChild = node;
                        break;
                    }
                }else{
                    error = "having same value in tree";
                }
            }
        }
        return error;

    }




    /**
     * //中序遍历(递归)：
     *    1、调用自身来遍历节点的左子树
     *    2、访问这个节点
     *    3、调用自身来遍历节点的右子树
     */
    public void inOrderTraverse(){
        System.out.println("中序遍历");
        inOrderTraverse(root);
    }

    public void inOrderTraverse(Node node){
        if(node == null) return;
        inOrderTraverse(node.leftChild);
        node.display();
        inOrderTraverse(node.leftChild);
    }

    /**
     * 中序非递归遍历：
     *     1）对于任意节点current，若该节点不为空则将该节点压栈，并将左子树节点置为current，重复此操作，直到current为空。
     *     2）若左子树为空，栈顶节点出栈，访问节点后将该节点的右子树置为current
     *     3) 重复1、2步操作，直到current为空且栈内节点为空。
     */

    public void inOrderByStack(){
        System.out.println("中序非递归遍历");
        Stack<Node> stack = new Stack<Node>();
        Node current = root;
        while(current != null || !stack.isEmpty()){
            while(current != null){
                stack.push(current);
                current = current.leftChild;
            }

            if(!stack.isEmpty()){
                current = stack.pop();
                current.display();
                current = current.rightChild;
            }

        }
        System.out.println();
    }





}