package dev.gungeon.utilities;

public interface LinkedListInterface<T> {

    void Add(T element);

    void Remove(Node<T> target);

    boolean Contains(T element);

    Node<T> FindNode(T element);

    Node<T> FindPrev();

    Node<T> FindPrev(Node<T> node);

    T GoToNext();

    T GoToStart();

    Node<T> GetStart();

    Node<T> GetCurrent();

    Node<T> GetLast();

    int Size();
}