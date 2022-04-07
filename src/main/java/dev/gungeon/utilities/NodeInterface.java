package dev.gungeon.utilities;

public interface NodeInterface<T> {

    public T Get();

    public void Set(T d);

    public Node<T> Next();

    public void SetNext(Node<T> n);

    public void ClearNext();
}
