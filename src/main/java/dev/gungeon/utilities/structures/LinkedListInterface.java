package dev.gungeon.utilities.structures;

import dev.gungeon.utilities.exceptions.ElementExistsException;
import dev.gungeon.utilities.exceptions.ElementNotFoundException;
import dev.gungeon.utilities.exceptions.EmptyListException;

public interface LinkedListInterface<T> {

    void Add(T element);

    void Remove(Node<T> prev, Node<T> target);

    void Remove(T element) throws ElementNotFoundException, EmptyListException;

    boolean Contains(T element);

    Node<T> FindNode(T element) throws EmptyListException, ElementNotFoundException;

    Node<T> FindPrev() throws Exception;

    Node<T> FindPrev(Node<T> node);

    T GoToNext();

    T GoToStart() throws EmptyListException;

    T GoToIndex(int x) throws IndexOutOfBoundsException;

    void ResetCrawl();

    Node<T> GetStart();

    Node<T> GetCurrentNode();

    T GetCurrent();

    Node<T> GetLast();

    int Size();
}