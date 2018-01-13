import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import java.util.Arrays;
import java.util.Comparator;

// An immutable data type that represents an autocomplete term: a query string 
// and an associated real-valued weight.
public class Term implements Comparable<Term> {
    String query;
    long weight;
    // Construct a term given the associated query string, having weight 0.
    public Term(String query) {
        if (query == null) {
        	throw new java.lang.NullPointerException();
        }
        this.query = query;
        weight = 0;
    }

    // Construct a term given the associated query string and weight.
    public Term(String query, long weight) {
        if (query == null) {
        	throw new java.lang.NullPointerException();
        }
        if (weight < 0) {
        	throw new java.lang.IllegalArgumentException();
        }
        this.query = query;
        this.weight = weight;
    }

    // A reverse-weight comparator.
    public static Comparator<Term> byReverseWeightOrder() {
        return new ReverseWeightOrder();
    }

    // Helper reverse-weight comparator.
    private static class ReverseWeightOrder implements Comparator<Term> {
        public int compare(Term v, Term w) {
            t1 = v.weight;
            t2 = w.weight;
            return Long.compare(t1, t2);
        }
    }

    // A prefix-order comparator.
    public static Comparator<Term> byPrefixOrder(int r) {
        if (r < 0) {
        	throw new IllegalArgumentException();
        }
        return new PrefixOrder(r);
    }

    // Helper prefix-order comparator.
    private static class PrefixOrder implements Comparator<Term> {
        private final int r;

        PrefixOrder(int r) {
        	if (r < 0) {
        		throw new IllegalArgumentException();
        	}
        	this.r = r;
        }

        public int compare(Term v, Term w) {
            String t1, t2;
            if (r > v.query.length()) {
            	t1 = v.query;
            }
            else {
            	t1 = v.query.substring(0, r);
            }
            if (r > w.query.length()) {
            	t2 = w.query;
            }
            else {
            	t2 = w.query.substring(0, r);
            }
            return t1.compareTo(t2);
        }
    }

    // Compare this term to that in lexicographic order by query and 
    // return a negative, zero, or positive integer based on whether this 
    // term is smaller, equal to, or larger than that term.
    public int compareTo(Term that) {
        return this.query.compareTo(that.query);
    }

    // A string representation of this term.
    public String toString() {
        return weight + "\t" + query;
    }

    // Test client. [DO NOT EDIT]
    public static void main(String[] args) {
        String filename = args[0];
        int k = Integer.parseInt(args[1]);
        In in = new In(filename);
        int N = in.readInt();
        Term[] terms = new Term[N];
        for (int i = 0; i < N; i++) {
            long weight = in.readLong(); 
            in.readChar(); 
            String query = in.readLine(); 
            terms[i] = new Term(query.trim(), weight); 
        }
        StdOut.printf("Top %d by lexicographic order:\n", k);
        Arrays.sort(terms);
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
        StdOut.printf("Top %d by reverse-weight order:\n", k);
        Arrays.sort(terms, Term.byReverseWeightOrder());
        for (int i = 0; i < k; i++) {
            StdOut.println(terms[i]);
        }
    }
}
