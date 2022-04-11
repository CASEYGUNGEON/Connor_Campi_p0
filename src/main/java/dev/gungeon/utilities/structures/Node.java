package dev.gungeon.utilities.structures;

//to be used in linked lists.
public class Node<T> implements NodeInterface<T> {
    private T data;
    private Node<T> nextNode;

    //---- Constructors ----//
    public Node(){
        data = null;
        nextNode = null;
    }

    public Node(T element){
        data = element;
        nextNode = null;
    }

    //---- everything else ----//
    //if youre confused about what a node does, check LinkedList.
    public T Get(){
        return data;
    }

    public void Set(T d){
        data = d;
    }

    public Node<T> Next(){
        return nextNode;
    }

    public void SetNext(Node<T> n){
        nextNode = n;
    }

    public void ClearNext(){
        nextNode = null;
    }
}