package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;

public class DLL<E> implements List<E> {

    private Node<E> head = new Node<>(null);
    private Node<E> tail = new Node<>(null);
    protected int Size = 0;


    /*
        Finds the first node's information
     */
    @Override
    public E first() {
        if (isEmpty()) return null;

        return head.getNext().getData();

    }

    ///Finds the last node's info
    @Override
    public E last() {

        if (isEmpty()) return null;

        return tail.getPrev().getData();

    }


    ///Adds an element to the end of the DLL
    @Override
    public void addLast(E element) {

        Node<E> temp = new Node<>(element);

        if(isEmpty()){
           temp.setPrev(head);
           temp.setNext(tail);
           tail.setPrev(temp);
           head.setNext(temp);}
        else{
            temp.setPrev(tail.getPrev());
            temp.setNext(tail);
            tail.getPrev().setNext(temp);
            tail.setPrev(temp);
        }



        Size++;

    }
    ///Adds an element to the front of the DLL
    ///Returns E
    @Override
    public void addFirst(E element) {

        Node<E> temp = new Node<>(element);

        if (isEmpty()) {
            head.setNext(temp);
            temp.setPrev(head);
            temp.setNext(tail);
            tail.setPrev(temp);
        } else {
            head.getNext().setPrev(temp);
            temp.setNext(head.getNext());
            temp.setPrev(head);
            head.setNext(temp);

        }

        Size++;

    }

    ///Removes the first element
    @Override
    public E removeFirst() {

        if (isEmpty()) return null;

        Node<E> temp = head.getNext();
        E answer = head.getNext().getData();

        head.setNext(temp.getNext());
        temp.getNext().setPrev(head);
        temp.setPrev(null);
        temp.setNext(null);

        Size--;

        return answer;
    }

    /*
    Removes the last element from the DLL
    @return E
     */
    @Override
    public E removeLast() {
        if (isEmpty()) return null;

        Node<E> temp = tail.getPrev();
        E answer = tail.getPrev().getData();

        tail.setPrev(temp.getPrev());
        temp.getPrev().setNext(tail);
        temp.setPrev(null);
        temp.setNext(null);

        Size--;

        return answer;
    }

    /*
    inserts an element at the specified index
     */
    @Override
    public void insert(E element, int index) {

        if (element == null || index < 0 || index > size()) return;

        Node<E> temp = new Node<>(element);
        Node<E> newNode = new Node<>(element);

        if (isEmpty()) addFirst(element);

        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        newNode.setNext(temp.getNext());
        temp.setPrev(newNode);
        newNode.setPrev(temp);
        temp.setNext(newNode);

        Size++;

    }

    /*
    Removes an element at specified index
    @return returns E (information about element removed
     */

    @Override
    public E remove(int index) {

        if (isEmpty() || index < 0 || index >= size()) return null;

        Node<E> temp = head;

        for (int i = 0; i < index; i++) {
            temp = temp.getNext();
        }

        Node<E> toRemove = temp.getNext();
        E answer = toRemove.getData();
        toRemove.getNext().setPrev(temp);
        temp.setNext(toRemove.getNext());
        toRemove.setNext(null);
        toRemove.setPrev(null);

        Size--;

        return answer;
    }


    /*
    gets the information at the element specified by the index
    @reutrn information about element E
     */
    @Override
    public E get(int index) {
        if (isEmpty() || index < 0 || index > size()) return null;

        Node<E> temp = head.getNext();

        while (index != 0) {
            temp = temp.getNext();
            index--;
        }
        return temp.getData();
    }

    /*
    @return size of DLL
     */
    @Override
    public int size() {
        return Size;
    }

    /*
    @Return if empty or not
     */
    @Override
    public boolean isEmpty() {
       if(Size == 0){return true;}
       else {return false;}
    }

    /*
    Prints the list starting from the first item
     */
    @Override
    public void printList() {

        if (isEmpty()) return;

        Node<E> temp = head.getNext();

        while (temp.getNext() != tail && temp.getNext() != null)
            for (int i = 0; i < Size; i++) {
                System.out.println(get(i));
                temp = temp.getNext();
            }
    }

}