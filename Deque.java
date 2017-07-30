import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
   
   private Node first, last;
   private int count;
    
   // construct an empty deque 
   public Deque(){
       first = null;
       last = null;
       count = 0;
   }
   
   private class Node{
       Item item; 
       Node next;
       Node prev;
   }
       
   // is the deque empty?    
   public boolean isEmpty(){
       return first == null;
   }
       
   // return the number of items on the deque    
   public int size(){
       return count;
   }
       
   // add the item to the front    
   public void addFirst(Item item){
        if (item == null){
           throw new NullPointerException();
       }
       
       Node oldfirst = first;
       first = new Node();
       first.item = item;
       first.next = oldfirst;
       first.prev = null;
       if (isEmpty()){
           last = first;
       }
       else{
           oldfirst.prev = first;
       }
       count++;
   }
       
   // add the item to the end    
   public void addLast(Item item){
       if (item == null){
           throw new NullPointerException();
       }
       
       Node oldlast = last;
       last = new Node();
       last.item = item;
       last.next = null;
       last.prev = oldlast;
       if (isEmpty()){
           first = last;
       }
       else{
           oldlast.next = last;
       }
       count++;
   }
   
   // remove and return the item from the front    
   public Item removeFirst(){
       if (isEmpty()){
           throw new NoSuchElementException("Deque is empty.");
       }
       
       Item item = first.item;
       first = first.next;
       first.prev = null;
       count--;
       return item;
   }
       
   // remove and return the item from the end     
   public Item removeLast(){
       if (isEmpty()){
           throw new NoSuchElementException("Deque is empty.");
       }
       
       Item item = last.item;
       last = last.prev;
       last.next = null;
       count--;
       return item;
   }   
       
   // return an iterator over items in order from front to end    
   public Iterator<Item> iterator(){
       return new DequeIterator();
   }
       
   private class DequeIterator implements Iterator<Item>{
       private Node current = first;
       
       public boolean hasNext(){
           return current != null;    
       }
       
       public void remove(){
           throw new UnsupportedOperationException();
       }
       
       public Item next(){
           if (current.next == null){
               throw new NoSuchElementException();
           }
           
           Item item = current.item;
           current = current.next;
           return item;
       }          
   }
       
   // unit testing (optional)    
   public static void main(String[] args){
   }
}