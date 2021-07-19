package structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

public class BinaryAVLTree<T> {

    private TreeNode<T> root;
    private Comparator<T> comparator;

    public BinaryAVLTree(Comparator<T> comparator) {
        root = null;
        this.comparator = comparator;
    }

    public void cleanAll(){
        root = null;
    }

    public boolean isEmpty() {
        return (root == null);
    }

    public void insert(T data){
        root = insert(root,data);
    }

    private TreeNode<T> insert(TreeNode<T> node,T data){
        if (node == null){
            node = new TreeNode<>(data);
        }else if(comparator.compare(node.getData(), data) > 0){
            node.setLeft(insert(node.getLeft(),data));

        }else if(comparator.compare(node.getData(), data) < 0){
            node.setRight(insert(node.getRight(),data));
        }
        return reBalance(node,data);
    }

    private TreeNode<T> reBalance(TreeNode<T> node,T data){
        if (getBalance(node) == -2){
            if (comparator.compare(node.getLeft().getData(), data) > 0){
                node = leftSimpleRotation(node);
            }else{
                node = compositeRightLeft(node);
            }
        }
        if (getBalance(node) == 2){
            if (comparator.compare(node.getRight().getData(), data) < 0){
                node = rightSimpleRotation(node);
            }else{
                node = compositeLeftRight(node);
            }
        }
        updateHeight(node);
        return node;
    }

    private int height(TreeNode<T> node) {
        return (node == null) ? -1 : node.getHeight();
    }

    private void updateHeight(TreeNode<T> node){
         node.setHeight(1 + Math.max(height(node.getRight()), height(node.getLeft())));
    }

    private int getBalance(TreeNode<T> node){
        return (node == null) ? 0: (height(node.getRight()) - height(node.getLeft()));
    }

    private TreeNode<T> compositeRightLeft(TreeNode<T> n){
        n.setLeft(rightSimpleRotation(n.getLeft())); // Entra como parametro el n1
        return leftSimpleRotation(n);
    }

    private TreeNode<T> compositeLeftRight(TreeNode<T> n){
        n.setRight(leftSimpleRotation(n.getRight()));
        return rightSimpleRotation(n);
    }

    private TreeNode<T> rightSimpleRotation(TreeNode<T> n){
        TreeNode<T> n1 = n.getRight();
        n.setRight(n1.getLeft());
        n1.setLeft(n);
        updateHeight(n);
        updateHeight(n1);
        return n1;
    }

    private TreeNode<T> leftSimpleRotation(TreeNode<T> n){
        TreeNode<T> n1 = n.getLeft();
        n.setLeft(n1.getRight());
        n1.setRight(n);
        updateHeight(n);
        updateHeight(n1);
        return n1;
    }

    private TreeNode<T> getRoot() {
        return root;
    }

    public boolean isExist(T data){
        return isExist(root,data);
    }

    private boolean isExist(TreeNode<T> node,T data){
        if (node == null){
            return false;
        }else{
            if (comparator.compare(node.getData(),data) == 0){
                return true;
            }else if(comparator.compare(node.getData(),data) > 0){
                return isExist(node.getLeft(),data);
            }else {
                return isExist(node.getRight(),data);
            }
        }
    }

    private T getNode(TreeNode<T> node, T data){
        if (node == null){
            return null;
        }else{
            if (comparator.compare(node.getData(), data) == 0){
                return node.getData();
            }else if (comparator.compare(node.getData(), data) > 0){
                return getNode(node.getLeft(),data);
            }else{
                return getNode(node.getRight(),data);
            }
        }
    }

    public T searchElement(T element){
        return getNode(root, element);
    }

    public int countTree(){
        if (root != null){
            return countTree(root);
        }else {
            return 0;
        }
    }

    private int countTree(TreeNode<T> node){
        int count = 1;
        if (node.getLeft() != null){
            count += countTree(node.getLeft());
        }
        if (node.getRight() != null){
            count += countTree(node.getRight());
        }
        return count;
    }

    private TreeNode<T> findPredecessor(TreeNode<T> node){
        if (node.getRight() != null){
            return findPredecessor(node.getRight());
        }else{
            return node;
        }
    }

    public void delete(T data){
        root = delete(root,data);
    }

    private TreeNode<T> delete(TreeNode<T> node,T data){
        TreeNode<T> auxNode = node;
        if (comparator.compare(node.getData(),data) < 0){
            node.setRight(delete(node.getRight(),data));
        }else if(comparator.compare(node.getData(),data) > 0){
            node.setLeft(delete(node.getLeft(),data));
        }else{
            if (node.getRight() != null && node.getLeft() != null){
                TreeNode<T> temp = node;
                TreeNode<T> maxOfTheLeft = findPredecessor(node.getLeft());
                node.setData(maxOfTheLeft.getData());
                temp.setLeft(delete(temp.getLeft(), maxOfTheLeft.getData()));
            }else if(node.getLeft() != null){
                auxNode = node.getLeft();
            }else if (node.getRight() != null){
                auxNode = node.getRight();
            }else{
                auxNode = null;
            }
        }
        if (auxNode != null){
            auxNode = reBalance(node,data);
        }
        return auxNode;
    }

    public Iterator<T> travelOf(ArrayList<T> list,int value){
        return depthTravel(root,list,value);
    }

    private Iterator<T> depthTravel(TreeNode<T> node, ArrayList<T> list, int value){
        if (root == null){
            return null;
        }
        if (list == null){
            list = new ArrayList<>();
        }
        if (value ==  0){
            list.add(node.getData());
        }
        if (node.getLeft() != null){
            depthTravel(node.getLeft(),list,value);
        }
        if (value ==  1){
            list.add(node.getData());
        }
        if (node.getRight() != null){
            depthTravel(node.getRight(),list,value);
        }
        if (value ==  2){
            list.add(node.getData());
        }
        return list.iterator();
    }

    public T getLeast(){
        return getLeast(root);
    }

    private T getLeast(TreeNode<T> node) {
        if (node == null){
            return null;
        }
        if(node.getLeft() != null){
            return getLeast(node.getLeft());
        }else {
            return node.getData();
        }
    }

    public T getBiggest(){
        return getBiggest(root);
    }

    private T getBiggest(TreeNode<T> node) {
        if (node == null){
            return null;
        }
        if(node.getRight() != null){
            return getBiggest(node.getRight());
        }else {
            return node.getData();
        }
    }
}
