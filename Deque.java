import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
	
	private int size; 		// number of objects
	private Node first;		// beginning (head)
	private Node last;		// the end (tail)
	
	// construct an empty deque
    public Deque() {
    	size = 0;
    	first = null;
    	last = null;
    	assert check();
    }
	
	private class Node {
		private Item item;
		private Node next;
		private Node prev;
	}

    // is the deque empty?
    public boolean isEmpty() {
    	return first == null;
    }

    // return the number of items on the deque
    public int size() {
    	return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
    	if (item == null) throw new IllegalArgumentException("null value");
    	size++;
    	Node oldFirst = first;
    	first = new Node();
    	first.item = item;
    	if (oldFirst == null) {
    		last = first;
    	}
    	else {
    		first.next = oldFirst;
    		oldFirst.prev = first;
    	}
    	
    	first.prev = null;
    	assert check();
    }

    // add the item to the back
    public void addLast(Item item) {
    	if (item == null) throw new IllegalArgumentException("null value");
    	size++;
    	Node oldLast = last;
    	last = new Node();
    	last.item = item;
    	if (oldLast == null) {
    		first = last;
    	}
    	else {
    		oldLast.next = last;
    		last.prev = oldLast;
    	}
    	last.next = null;
    	assert check();
    }

    // remove and return the item from the front
   public Item removeFirst() {
	   if (isEmpty()) throw new NoSuchElementException("Deque underflow");
	   size--;
	   Item save = first.item;
	   if (first.next == null) {
		   first = null;
		   last = null;
	   }
	   else {
		   first = first.next;
		   first.prev = null;
	   }
	   assert check();
	   return save;
   }

    // remove and return the item from the back
    public Item removeLast() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	size--;
    	Item save = last.item;
    	if (last.prev == null) {
    		last = null;
    		first = null;
    	}
    	else {
    		last = last.prev;
    		last.next = null;
    	}
    	assert check();
    	return save;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator(){
    	return new ListIterator();
    }

    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() { 
        	return current != null;
        }
        public void remove() {
        	throw new UnsupportedOperationException("No remove() command");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No next");
            Item item = current.item;
            current = current.next; 
            return item;
        }
    }
    
 // check internal invariants
    private boolean check() {
        if (size < 0) {
            return false;
        }
        else if (size == 0) {
            if (first != null) return false;
            if (last  != null) return false;
        }
        else if (size == 1) {
            if (first == null || last == null) return false;
            if (first != last)                 return false;
            if (first.next != null)            return false;
        }
        else {
            if (first == null || last == null) return false;
            if (first == last)      return false;
            if (first.next == null) return false;
            if (last.next  != null) return false;

            // check internal consistency of instance variable n
            int numberOfNodes = 0;
            for (Node x = first; x != null && numberOfNodes <= size; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != size) return false;

            // check internal consistency of instance variable last
            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
            if (last != lastNode) return false;
        }

        return true;
    } 
    
    // unit testing (required)
    public static void main(String[] args) {
    	/*Deque<Integer> deck = new Deque<Integer>();
    	
    	deck.addFirst(1);
    	deck.addLast(2);
    	deck.addLast(3);
    	deck.addLast(4);
    	deck.addLast(5);
    	deck.addFirst(100);
    	int n = deck.size();
    	
    	for (int x = 0; x < n; x++) {
    		System.out.println(deck.removeLast());
    	}
    	*/
    }
}

