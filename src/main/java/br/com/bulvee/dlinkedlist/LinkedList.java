package br.com.bulvee.dlinkedlist;

public class LinkedList<T> {
    private Node firstNode = null;
    private Node lastNode = null;


    private int totalItems = 0;

    public void add(T item) {
        if (totalItems == 0) {
            addFirst(item);
        } else {
            Node newNode = new Node(item);
            this.lastNode.setNextNode(newNode);
            newNode.setPreviousNode(lastNode);

            this.lastNode = newNode;

            totalItems ++;
        }
    }


    public void addFirst(T item) {
        if (this.totalItems == 0) {
            Node newNode = new Node(item);
            this.firstNode = newNode;
            this.lastNode = newNode;
        } else {
            Node newNode = new Node(item, this.firstNode);
            this.firstNode.setPreviousNode(newNode);
            this.firstNode = newNode;
        }
        this.totalItems++;
    }

    public void add(int index, T item) {

        if (index == 0) {
            addFirst(item);
        } else if (index == this.totalItems) {
            add(item);
        } else {
            Node previous = this.getNode(index-1);
            //Current Node
            Node next = previous.getNextNode();
            //The current node becames the next node for the new node.
            Node newNode = new Node(item, next);
            //The previus node of the index becomes the previus node of new node.
            newNode.setPreviousNode(previous);
            //The new node becomes the next node of the previous node.
            previous.setNextNode(newNode);
            //The new node becomes the previous node of the old nextNext node.
            next.setPreviousNode(newNode);

            totalItems++;
        }
    }

    public void addLast(T item) {

        if (this.totalItems == 0) {
            this.addFirst(item);
        }

        Node newItem = new Node(item, null);
        this.lastNode.setNextNode(newItem);
        this.lastNode = newItem;

        this.totalItems++;
    }

    public T get(int index) {
        return (T) this.getNode(index).getItem();
    }


    private Node getNode(int index) {

        if (!isIndexInUse(index)) {
            throw new IllegalArgumentException("This index does not exist.");
        }

        Node current = this.firstNode;

        for (int i = 0; i < index; i++) {
            current = current.getNextNode();
        }

        return current;
    }


    private boolean isIndexInUse(int index) {
        return index >= 0 && index < totalItems;
    }

    public void removeFirst() {
        if (totalItems == 0) {
            throw new IllegalArgumentException("This list is empty");
        }

        this.firstNode = this.firstNode.getNextNode();
        totalItems--;

        if (this.totalItems == 0) {
            lastNode = null;
        }

    }

    public void remove(int index) {

        if (isIndexInUse(index)) {
            Node node = getNode(index);
            Node nextNode = node.getNextNode();
            Node previusNode = getNode(index - 1);
            previusNode.setNextNode(nextNode);


            totalItems--;
        } else {
            throw new IllegalArgumentException("Invalid index.");
        }

    }

    public int size() {
        return totalItems;
    }

    public boolean contains(Object item) {
        return false;
    }

    @Override
    public String toString() {
        if (this.totalItems == 0) {
            return "[]";
        }

        Node current = firstNode;
        StringBuilder builder = new StringBuilder("[");

        for (int i = 0; i < totalItems; i++) {
            builder.append(current.getItem());
            builder.append(",");

            current = current.getNextNode();
        }

        builder.append("]");
        return builder.toString();
    }
}
