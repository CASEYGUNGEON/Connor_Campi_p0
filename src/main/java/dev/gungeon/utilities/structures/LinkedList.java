package dev.gungeon.utilities.structures;

import dev.gungeon.utilities.exceptions.ElementExistsException;
import dev.gungeon.utilities.exceptions.ElementNotFoundException;
import dev.gungeon.utilities.exceptions.EmptyListException;

public class LinkedList<T> implements LinkedListInterface<T> {
    private Node<T> start;
    private Node<T> last;
    private Node<T> current;
    private int size;

    public LinkedList() {
        Node<T> start = null;
        Node<T> last = null;
        Node<T> current = null;
        int size = 0;
    }
    public LinkedList(T element) {
        Add(element);
    }

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

    public void Remove(Node<T> target) throws EmptyListException, ElementNotFoundException {
        if(!Contains(target.Get())){
            throw new ElementNotFoundException("Element not found");
        }

        switch(size){
            case 0: throw new EmptyListException("List is empty");
            case 1:{
                if(start.Get() == target) {
                    start = null;
                    last = null;
                    size = 0;
                }
            }break;
            default:{
                Node<T> iter = start;
                Node<T> prev = null;
                while(iter.Next() != target){
                    prev = iter;
                    iter = iter.Next();
                }
                if(iter == last) {
                    last = prev;
                    prev.SetNext(null);
                    size--;
                }
                else{
                    prev.SetNext(iter.Next());
                    size--;
                }
            }break;
        }
    }

    public void Remove(T element) throws ElementNotFoundException, EmptyListException {
        Remove(FindNode(element));
    }

    public boolean Contains(T element){
        if(size == 0)
            return false;

        if(size == 1){
            return start.Get() == element;
        }

        Node<T> cur = start.Next();
        do {
            if(cur.Get() == element)
                return true;
            else
                cur = cur.Next();
        } while(cur.Next() != null);
        return false;
    }

    public Node<T> FindNode(T element) throws EmptyListException, ElementNotFoundException {
        if(start == null)
            throw new EmptyListException("List is empty");

        Node<T> cur = start;

        do{
            if(cur.Get() == element)
                return cur;
            else
                cur = cur.Next();
        } while (cur.Next() != null);

        throw new ElementNotFoundException("Element not found");
    }

    public Node<T> FindPrev() throws IndexOutOfBoundsException, Exception {
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
        return current.Get();
    }

    public Node<T> GetLast(){
        return last;
    }

    public int Size(){
        return size;
    }

}