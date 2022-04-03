package br.com.bulvee.dlinkedlist;

import lombok.AllArgsConstructor;
import lombok.ToString;

@ToString
public class Node {

    private Object item;
    private Node nextNode;
    private Node previousNode;

    public Node(Object item, Node nextNode) {
        this.item = item;
        this.nextNode = nextNode;
    }

    public Node(Object item) {
        this.item = item;
    }


    public Node getNextNode() {
        return nextNode;
    }

    public void setNextNode(Node nextNode) {
        this.nextNode = nextNode;
    }

    public Object getItem() {
        return item;
    }

    public Node getPreviousNode() {
        return previousNode;
    }

    public void setPreviousNode(Node previousNode) {
        this.previousNode = previousNode;
        }


}
