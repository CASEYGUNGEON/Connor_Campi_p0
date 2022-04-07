package dev.gungeon.utilities;

public class LinkedList<T> implements LinkedListInterface<T> {
    private Node<T> start = null;
    private Node<T> last = null;
    private Node<T> current = null;
    private int size = 0;

    public void Add(T element){
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

    public void Remove(Node<T> target){
        switch(size){
            case 0: break;
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
                    if(iter.Next() == null)
                        return;
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

    public Node<T> FindNode(T element){
        if(start == null)
            return null;

        Node<T> cur = start;

        do{
            if(cur.Get() == element)
                return cur;
            else
                cur = cur.Next();
        } while (cur.Next() != null);

        return null;
    }

    public Node<T> FindPrev(){
        if(start == null || last == start)
            return null;
        else
        {
            Node<T> cur = start;
            do {
                if (cur.Next() == current)
                    return cur;
                else
                    cur = cur.Next();
            } while (cur.Next() != null);

            return null;
        }
    }

    public Node<T> FindPrev(Node<T> node){
        if(start == null || last == start)
            return null;
        else
        {
            Node<T> cur = start;
            do {
                if (cur.Next() == node)
                    return cur;
                else
                    cur = cur.Next();
            } while (cur.Next() != null);

            return null;
        }
    }

    public T GoToNext(){
        if(current == null) {
            current = start;
            return current.Get();
        }
        if(current.Next() != null) {
            current = current.Next();
            return current.Get();
        }
        else
            return null;
    }

    public T GoToStart(){
        if(start != null) {
            current = start;
            return start.Get();
        }
        else
            return null;
    }

    public Node<T> GetStart(){
        return start;
    }

    public Node<T> GetCurrent(){
        return current;
    }

    public Node<T> GetLast(){
        return last;
    }

    public int Size(){
        return size;
    }

}
