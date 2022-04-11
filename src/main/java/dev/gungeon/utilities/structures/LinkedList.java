package dev.gungeon.utilities.structures;

import dev.gungeon.utilities.exceptions.ElementExistsException;
import dev.gungeon.utilities.exceptions.ElementNotFoundException;
import dev.gungeon.utilities.exceptions.EmptyListException;

public class LinkedList<T> implements LinkedListInterface<T> {
    private Node<T> start;
    private Node<T> last;
    private Node<T> current;
    private int size;

    //---- Constructors ----//
    public LinkedList() {
        Node<T> start = null;
        Node<T> last = null;
        Node<T> current = null;
        int size = 0;
    }
    public LinkedList(T element) {
        Add(element);
    }

    //---- Manipulation ----//
    public void Add(T element) {
        if(start == null)
        {
            start = new Node<T>(element);
            last = start;
            size = 1;
        }
        else{
            Node<T> lastLast = last;
            last = new Node<T>(element);
            lastLast.SetNext(last);
            size++;
        }
    }

    public void Remove(Node<T> prev, Node<T> target) {
        if(prev != null)
        {
            prev.SetNext(target.Next());
        }
        else {
            start = null;
        }
        if(target.Next() == null) {
            last = null;
        }
    }

    public void Remove(T element) throws ElementNotFoundException, EmptyListException {
        Node<T> cur = start;
        if(cur == null) {
            throw new EmptyListException("Empty list");
        }
        Node<T> last = null;
        while(cur != null) {
            if(element.equals(cur.Get())) {
                Remove(last,cur);
                return;
            }
            last = cur;
            cur = cur.Next();
        }
        throw new ElementNotFoundException("Element Not Found");
    }

    //---- Query ----//
    public boolean Contains(T element) {
        if(size > 0){
            Node<T> cur = start;
            while(cur != null) {
                if (element.equals(cur.Get()))
                    return true;
                else {
                    cur = cur.Next();
                }
            }
        }
        return false;
    }

    public Node<T> FindNode(T element) throws EmptyListException, ElementNotFoundException {
        if(start == null)
            throw new EmptyListException("List is empty");

        Node<T> cur = start;

        while(cur != null){
            if(cur.Get().equals(element))
                return cur;
            else
                cur = cur.Next();
        }

        throw new ElementNotFoundException("Element not found");
    }

    public Node<T> FindPrev() throws Exception {
        if(start == null || last == start)
            throw new IndexOutOfBoundsException("Node does not exist");
        else
        {
            Node<T> cur = start;
            do {
                if (cur.Next() == current)
                    return cur;
                else
                    cur = cur.Next();
            } while (cur.Next() != null);

            throw new Exception("???");
        }
    }

    public Node<T> FindPrev(Node<T> node) throws IndexOutOfBoundsException {
        if(start == null || last == start)
            throw new IndexOutOfBoundsException("Out of bounds");
        else
        {
            Node<T> cur = start;
            do {
                if (cur.Next() == node)
                    return cur;
                else
                    cur = cur.Next();
            } while (cur.Next() != null);

            throw new IndexOutOfBoundsException("Out of bounds");
        }
    }

    public T GoToNext() throws IndexOutOfBoundsException {
        if(current == null) {
            current = start;
            return current.Get();
        }
        if(current.Next() != null) {
            current = current.Next();
            return current.Get();
        }
        else
            throw new IndexOutOfBoundsException("Out of bounds");
    }

    public T GoToStart() throws EmptyListException {
        if(start != null) {
            current = start;
            return start.Get();
        }
        else
            throw new EmptyListException("List is empty");
    }

    public T GoToIndex(int x) throws IndexOutOfBoundsException {
        ResetCrawl();
        for(int i = 0; i <= x; i++) {
            GoToNext();
        }
        return current.Get();
    }

    public void ResetCrawl() {
        current = null;
    }

    public Node<T> GetStart(){
        return start;
    }

    public Node<T> GetCurrentNode(){
        return current;
    }

    public T GetCurrent() {
        if(current != null) {
            return current.Get();
        }
        else
            return null;
    }

    public Node<T> GetLast(){
        return last;
    }

    public int Size(){
        return size;
    }

}