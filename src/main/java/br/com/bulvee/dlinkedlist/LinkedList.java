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

            totalItems++;
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
            Node previous = this.getNode(index - 1);
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

        // The next node becomes the first node.
        this.firstNode = this.firstNode.getNextNode();
        totalItems--;
    }

    public void remove(int index) {
        if (index == 0) {
            this.removeFirst();
        } else if (index == this.totalItems - 1) {
            this.removeLast();
        }
        if (isIndexInUse(index)) {
            Node node = getNode(index);

            Node nextNode = node.getNextNode();
            Node previousNode = node.getPreviousNode();
            //The next node becomes the next node of previous node.
            previousNode.setNextNode(nextNode);
            //The previous node of index becomes the previous node of next node.
            nextNode.setPreviousNode(previousNode);

            //It's not necessary, only to remember that this object has not any link in memory.
            //In some cases set the variable to null can help the vm to free memory faster.
            node = null;
            totalItems--;
        } else {
            throw new IllegalArgumentException("Invalid index.");
        }
    }

    public void removeLast() {
        if (totalItems == 1) {
            this.removeFirst();
        } else {
            //The lastButOne node becomes the last node, then the next node for it does not exist any more.
            Node lastButOne = lastNode.getPreviousNode();
            lastButOne.setNextNode(null);
            this.lastNode = lastButOne;

            totalItems--;
        }
    }

    public int size() {
        return totalItems;
    }

    public boolean contains(Object item) {
        return false;
    }

    public boolean isEmpty() {
        return totalItems == 0;
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
