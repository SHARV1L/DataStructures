 import java.util.ArrayList;

public class IDLList<E> {

    private class Node<T> {              //Inner Class
        T data;
        Node <T> prev;
        Node <T> next;

        public Node(T elem) {                               // Constructor# 1
            data = elem;
            prev = null;
            next = null;
        }

        public Node(T val, Node<T> prev, Node<T> next) {    // Constructor# 2
            data = val;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node<E> head;               // List Head
    private Node<E> tail;               // List Tail
    private int size;                   // List Size
    private ArrayList<Node<E>> indices; // Node Indices List

    public IDLList() {                  // Constructor to create Empty List
        head = null;
        tail = null;
        size = 0;
        indices = new ArrayList<Node<E>>();
    }

    public boolean add(int index, E elem) {     //function adds an element at the certain index position
        if (index >= 0 && index <= size) {
            if (index == 0) {
                add(elem);
            } else if (index == size) {
                append(elem);
            } else {
                Node<E> curr = indices.get(index);

                Node<E> node = new Node<E>(elem, curr.prev, curr);

                node.prev.next = node;

                curr.prev = node;

                indices.add(index, node);

                indices.set(index - 1, node.prev);

                indices.set(index + 1, curr);

                size++;
            }
            return true;
        }
        return false;
    }

    public boolean add(E elem) {        // function adds the given element at the starting of the list
        Node<E> node = new Node<E>(elem, null, head);
        if (head == null) {
            tail = node;
        } else {
            head.prev = node;
            indices.set(0, head);
        }
        head = node;
        indices.add(0, node);
        size++;
        return true;
    }

    public boolean append(E elem) {         // adds elements at the ending of the list
        Node<E> node = new Node<E>(elem, tail, null);
        tail.next = node;
        indices.set(size - 1, tail);
        tail = node;
        indices.add(node);
        size++;
        return true;
    }

    public E get(int index) {       // the function returns the element at a given index
        if (index >= 0 && index < size) {
            return indices.get(index).data;
        } else {
            throw new IndexOutOfBoundsException("List index out of Bounds, Please enter valid index");
        }
    }

    public E getHead() {        //function returns the head element
        if (size > 0) {
            return head.data;
        } else {
            throw new IllegalStateException("Empty List");
        }
    }

    public E getLast() {        // function returns the last element of the list
        if (size > 0) {
            return tail.data;
        } else {
            throw new IllegalStateException("List is Empty");
        }
    }

    public int size() {         // function returns the size of the list
        return size;
    }

    //method to remove element at head and return it
    public E remove() {
        if (size > 0) {
            
            E data = head.data;
            head = head.next;
            size--;
            indices.remove(0);
            if (head != null) {
                head.prev = null;
                indices.set(0, head);
            } else {
                tail = null;
            }
                return data;
                
            }
          else {
              throw new IllegalStateException("List is Empty");
                }
        }
    

    //method to remove element at tail and return it
    public E removeLast() {
        if (size > 0) {
            E data = tail.data;
            tail = tail.prev;
            size--;
            indices.remove(size);

            if (tail != null) {
                tail.next = null;
                indices.set(size - 1, tail);
                
            } else 
             {
                head = null;}

                return data;
            } else {
                throw new IllegalStateException("List is Empty");
            }
        }
    
    
    //method to remove element at given index
    public E removeAt(int index) {

        if (index >= 0 && index < size) {
            if (index == 0) {
                return remove();
            } else if (index == size - 1) {
                return removeLast();
            } else {
                Node<E> node = indices.get(index);
                indices.remove(index);
                indices.get(index).prev = node.prev;
                indices.get(index - 1).next = node.next;
                node.next = null;
                node.prev = null;
                size--;
                return node.data;
            }
        } else {
            throw new IndexOutOfBoundsException("Out of Bounds");
        }
    }

    //method to remove a given element
    public boolean remove(E elem) {
        Node<E> curr = head;
        int curr_index = 0;

        while (curr != null) {
            if (curr.data.equals(elem))
                break; //break if element is found in the list
                curr = curr.next;
                curr_index++;
            }
        

        if (curr == null) {
            return false;
        } else {
            removeAt(curr_index);
            return true;
        }
    }

    //method to return List as String
    public String toString() {
        String str = "";
        Node<E> curr = head;

        while (curr != null) {
            str += curr.data.toString();
            if (curr != tail) {
                str += "-->";
            }
            curr = curr.next;
        }
        return str;
    }

        public static void main(String[] args) {
            IDLList<Integer> list = new IDLList<Integer>();

            // adding the elements
            list.add(0, 3);
            list.add(1, 21);
            list.add(1, 112);
            list.add(0, 44);
            list.add(2, 99);
            list.add(2, 56);
            list.add(1, 33);

            System.out.println("Initial List Size: " + list.size());  // printing the initial size of the list
            System.out.println(list);                                 // printing the list elements

            //  Accessing the element at a certain index(2)
            System.out.println("Accessing the element at index 2: " + list.get(1));
            System.out.println("List Head: " + list.getHead()); // returning the head element
            System.out.println("List Tail: " + list.getLast()); // returning the tail element

            // Removing the element at index 3
            System.out.println("Removing element at index 3: "+list.removeAt(2));
            System.out.println("Current List Size: " + list.size()); // returning the current size of the IDLL
            System.out.println(list);                                // printing the list elements

            System.out.println("Removing Element at index 28: " + list.remove(67)); // removing element at certain index
            System.out.println("Current List Size: " + list.size()); // returning the current size of the IDLL
            System.out.println(list);                                // printing the list elements

            
            System.out.println("Remove Operation: " + list.remove()); // removes the element at the head position
            System.out.println("Current List Size: " + list.size());  // returning the current size of the IDLL
            System.out.println(list);                                 // printing the list elements

            System.out.println("Removing Last Element: " + list.removeLast());  // removes the element at the tail position
            System.out.println("Current List Size: " + list.size());  // returning the current size of the IDLL
            System.out.println(list);                                 // printing the list elements
        }
    }

