package edu.isu.cs.cs3308.structures.impl;


public class LinkedStack<E> {

    DLL<E> data = new DLL<>();


    
    public void push(E element) {

        data.addFirst(element);
    }

    public E peek() {
        if(isEmpty()){return null;}
        return data.first();
    }

    
    public E pop() {
        if(isEmpty()){return null;}
        return data.removeFirst();
    }

    
    public int size() {
        return data.size();
    }

    
    public boolean isEmpty() {
        return data.isEmpty();
    }


    
    public void transfer(LinkedStack to) {

        if(to == null){return;}

        while(isEmpty() != true){
            to.push(this.pop());
        }

    }

    
    public void reverse() {

        LinkedStack<E> temp = new LinkedStack<>();

        while(isEmpty() != true){

            this.transfer(temp);
        }

        this.data = temp.data;

    }

    
    public void merge(LinkedStack other) {

        LinkedStack<E> temp = new LinkedStack<>();
        this.transfer(temp);
        other.transfer(temp);
        other.reverse();
        this.data = temp.data;
    }

    
    public void printStack() {
        data.printList();
    }
}
