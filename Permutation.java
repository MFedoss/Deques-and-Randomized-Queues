import edu.princeton.cs.algs4.StdIn;
//import java.util.Iterator;

public class Permutation {
   public static void main(String[] args) {
	   int k = Integer.parseInt(args[0]);
	   RandomizedQueue<String> lit = new RandomizedQueue<String>();
	   while (!StdIn.isEmpty()) {
		   String input = StdIn.readString();
		   lit.enqueue(input);
	   }
	   //Iterator<String> x = lit.iterator();
	   for (int i = 0; i < k; i++) {
		   //System.out.println(x.next());
		   System.out.println(lit.dequeue());
	   }
   }
}