// Sharvil Singh 20011939

import java.util.*;

public class Treap<E extends Comparable<E>>{
    //	Constructor
    private static class Node<E extends Comparable<E>>{
        public E data;  // Key variable for the search
        public int priority;    // Random Heap Priority
        public Node <E> left;   // Left Node
        public Node <E> right;  // Right Node

        //	FUnction for initializing the data
        public Node(E data, int priority){
            if(data == null){
                throw new NoSuchElementException("Data can't be null!, Try Again");
            }
            this.data = data;
            this.priority = priority;
            this.left = null;
            this.right = null;
        }

        //	Function for Right Rotation
        Node<E> rotateRight(){
            Node<E> x = new Node<E>(this.data, this.priority);
            if(this.left != null){
                if(this.right != null){
                    x.right = this.right;
                }
                if(this.left.right != null ){
                    x.left = this.left.right;
                }
                this.priority = this.left.priority;
                this.data = this.left.data;
                this.right = x;
                if(this.left.left == null){
                    this.left = null;
                }else{
                    this.left = this.left.left;
                }
            }
            return x;
        }

        //	FUnction for Left Rotation
        Node<E> rotateLeft(){
            Node<E> x = new Node<E>(this.data, this.priority);
            if(this.right != null){
                if(this.left != null){
                    x.left = this.left;
                }
                if(this.right.left != null ){
                    x.right = this.right.left;
                }
                this.priority = this.right.priority;
                this.data = this.right.data;
                this.left = x;
                if(this.right.right == null){
                    this.right = null;
                }else{
                    this.right = this.right.right;
                }
            }
            return x;
        }

        //	"toString" Function
        @Override
        public String toString(){
            return data.toString();
        }
    }

    private Random priorityGenerator;
    private Node<E> root;

    //	Constructor for initializing the random variable (priority generator)
    public Treap(){
        priorityGenerator = new Random();
        root = null;
    }

    public Treap(long seed){
        priorityGenerator = new Random(seed);
        root = null;
    }

    //	Function for adding a Node that takes a key and priority as an argument
    public boolean add(E key){
        return add(key, priorityGenerator.nextInt());
    }

    //	Add function with a key and a priority
    public boolean add(E key, int priority){
        Node<E> r = root;
        Stack<Node<E>> stack= new Stack<Node<E>>();
        if (root != null){
            while(r != null){
                stack.push(r);
                if(r.data.compareTo(key) == 0){
                    return false;
                }
                if(r.data.compareTo(key) > 0){
                    r = r.left;
                }else{
                    r = r.right;
                }
            }
            r = stack.peek();
            if(r.data.compareTo(key) > 0){
                Node<E> a = new Node<E>(key, priority);
                r.left = a;
                stack.push(a);
            } else{
                Node<E> a = new Node<E>(key, priority);
                r.right = a;
                stack.push(a);
            }

            Node<E> a = stack.pop();
            reheap(stack, a);
            return true;
        }
        root = new Node<E>(key, priority);
        return true;
    }

    //	Function for Deleting a Node
    public boolean delete(E key){
        if(find(key) == true){
            Node<E> del = root;
            Node<E> n = null;
            while(del.data.compareTo(key) != 0){
                n = del;
                if(del.data.compareTo(key) > 0){
                    del = del.left;
                } else{
                    del = del.right;
                }
            }

            while(!(del.left == null && del.right == null)){
                n = del;
                if(del.left == null){
                    del = del.rotateLeft();
                } else if(del.right == null){
                    del = del.rotateRight();
                } else if(del.left.priority > del.right.priority){
                    del = del.rotateRight();
                } else{
                    del = del.rotateLeft();
                }
            }
            if(root.data.compareTo(key) == 0 && root.left == null && root.right == null){
                root = null;
            } else if(n.left != null && n.left.data.compareTo(key) == 0){
                n.left = null;
            } else{
                n.right = null;
            }
            return true;
        }
        return false;
    }

    // function returning void to take stack and a Node as input and then empties the stack.
    private void reheap(Stack<Node<E>> stack, Node<E> a){
        Node<E> x = stack.pop();
        while(x != null && x.priority < a.priority){
            if(a.data.compareTo(x.data) > 0){
                x.rotateLeft();
                if(stack.isEmpty()){
                    return;
                }
                x = stack.pop();
            } else{
                x.rotateRight();
                if(stack.isEmpty()){
                    return;
                }
                x = stack.pop();
            }
        }
    }

    // Find Function that takes a 'key' as an argument
    public boolean find(E key){
        if(key == null){
            throw new NoSuchElementException("Key cannot be null!, Try Again");
        }
        return find(root, key);
    }

    //	Find function that takes root and key as arguments
    public boolean find(Node<E> root, E key){
        if(root == null) {
            return false;
        }
        if(key.compareTo(root.data) == 0){
            return true;
        }
        if(key.compareTo(root.data) < 0){
            return find(root.left, key);
        }
        return find(root.right, key);
    }

    public String toString(){
        StringBuilder S = new StringBuilder();
        preOrderTraversal(root,S,1);
        return S.toString();
    }

    // Function for pre-order traversal
    public void preOrderTraversal(Node<E> curr, StringBuilder S, int n){
        int i = 0;
        while(i < n) {
            S.append(" ");
            i++;
        }

        if(curr != null){
            S.append("(key="+curr.toString()+", priority="+curr.priority+")\n");
            preOrderTraversal(curr.left, S, n+1);
            preOrderTraversal(curr.right, S, n+1);
        }else{
            S.append("null\n");
        }
    }

    // Main Function

    public static void main(String[] args) {

        Treap<Integer> t=new Treap<Integer>();
        // Demonstrating 'add' Function by passing key, priority pair as argument
        t.add(31,9);

        t.add(22,3);

        t.add(99,21);

        t.add(2,4);

        t.add(67,11);

        t.add(45,77);
        // Demonstrating 'add' Function which takes 'key' as an argument
        t.add(56);

        System.out.println("Demonstrating Treaps Operations : ");

        System.out.println("Tree :\n"+t.toString());

        System.out.println("Find result="+t.find(56));

        System.out.println(t.delete(56));

        System.out.println(t.delete(45)+"\n");

        System.out.println("Tree:\n"+t.toString());
    }
}