import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private int size;
	private Node first;
	
	private class Node{
		private Item item;
		private Node next;
	}

    // construct an empty randomized queue
    public RandomizedQueue() {
    	first = null;
    	size = 0;
    	assert check();
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
    	//return first == null; // what if:
    	return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
    	return size;
    }

    // add the item
    public void enqueue(Item item) {
    	if (item == null) throw new IllegalArgumentException("null value");
    	size++;
    	Node oldFirst = first;
    	first = new Node();
    	first.item = item;
    	first.next = oldFirst;
    	assert check();
    }

    // remove and return a random item
    public Item dequeue() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	int limit = StdRandom.uniform(1, size+1);
    	size--;
    	if (limit == 1) {
    		Item save = first.item;
    		first = first.next;
        	assert check();
        	return save;
    	}
    	Node x = first;
    	Node prev = first;
    	int i = 1;
    	while (i < limit && x.next != null) {    		
    		if (i == limit-1) {
    			prev = x;
    		}
    		x = x.next;
    		i++;
    	}
    	Item save = x.item;
    	prev.next = x.next;
    	assert check();
    	return save;
    }

    // return a random item (but do not remove it)
    public Item sample() {
    	if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    	int i = 1;
    	Node x = first;
    	while (i < StdRandom.uniform(1, size+1) && x.next != null) {
    		x = x.next;
    		i++;
    	}
    	return x.item;
    }

    
    // return an independent iterator over items in random order
    public Iterator<Item> iterator(){
    	return new ListIterator();
    }
    
    private Deque<Item> generate() {
    	Deque<Item> dek = new Deque<Item>();
    	Node current = first;
    	int x = StdRandom.uniform(0, 2);
    	for (int i = 0; i < size; i++) {
    		if (x == 0) {
    			dek.addLast(current.item);
    		}
    		else {
    			dek.addFirst(current.item);
    		}
    		x = StdRandom.uniform(0, 2);
    		current = current.next;
    	}
    	return dek;
    }
    
    // an iterator, doesn't implement remove() since it's optional
    private class ListIterator implements Iterator<Item> { 
    	Deque<Item> dek = generate();
    	
    	Iterator<Item> x = dek.iterator();
    	
        public boolean hasNext() { 
        	return x.hasNext();
        }
        public void remove() {
        	throw new UnsupportedOperationException("No remove() command");
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException("No next");
            return x.next();
        }
    }
    
    // check internal invariants
    private boolean check() {
        if (size < 0) {
            return false;
        }
        else if (size == 0) {
            if (first != null) return false;
        }
        else if (size == 1) {
            if (first == null) return false;
            if (first.next != null) return false;
        }
        else {
            if (first == null) return false;
            if (first.next == null) return false;

            int numberOfNodes = 0;
            for (Node x = first; x != null && numberOfNodes <= size; x = x.next) {
                numberOfNodes++;
            }
            if (numberOfNodes != size) return false;

            Node lastNode = first;
            while (lastNode.next != null) {
                lastNode = lastNode.next;
            }
        }
        return true;
    } 
    

    // unit testing (required)
    public static void main(String[] args) {
    	RandomizedQueue<String> lit = new RandomizedQueue<String>();
    	lit.enqueue("5");
    	lit.enqueue("10");
    	lit.enqueue("15");
    	lit.enqueue("20");
    	lit.enqueue("25");
    	lit.enqueue("30");
    	lit.enqueue("35");
    	lit.enqueue("40");
    	lit.enqueue("45");
    	lit.enqueue("50");
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    	System.out.println(lit.dequeue());
    }

}