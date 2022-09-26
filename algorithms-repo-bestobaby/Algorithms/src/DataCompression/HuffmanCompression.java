package DataCompression;

import java.util.Iterator;
import java.util.PriorityQueue;

public class HuffmanCompression {

    // alphabet size of extended ASCII
    private static final int R = 256;

    // Huffman trie node
    private record Node(char ch, int freq, HuffmanCompression.Node left, HuffmanCompression.Node right) implements Comparable<Node> {

        // is the node a leaf node?
        private boolean isLeaf() {
            assert ((left == null) && (right == null)) || ((left != null) && (right != null));
            return left == null;
        }

        // compare, based on frequency
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    /**
     * Reads a sequence of 8-bit bytes from standard input; compresses them
     * using Huffman codes with an 8-bit alphabet; and writes the results
     * to standard output.
     */
    public static void compress() {

        // Reads input from file
        String s = BinaryStdIn.readString();
        char[] input = s.toCharArray();

        // Stores the frequency of each ASCII characters
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++) {
            freq[input[i]]++;
        }

        // Builds Huffman code trie and returns the root node
        Node root = buildTrie(freq);

        // Traverses through the Huffman tree and stores each codeword in the String array
        String[] codewords = new String[R];
        buildCode(codewords, root, "");

        // writes the Huffman Trie into the file which will be used for decoding the file
        writeTrie(root);

        // Print number of chars.
        BinaryStdOut.write(input.length);
        BinaryStdOut.flush();

        // Uses Huffman code to encode the file
        for (int i = 0; i < input.length; i++) {

            String code = codewords[input[i]];
            for (int j = 0; j < code.length(); j++)
                if (code.charAt(j) == '1')
                    BinaryStdOut.write(true);
                else
                    BinaryStdOut.write(false);
        }
        BinaryStdOut.close();
    }

    /**
     * Reads a sequence of bits that represents a Huffman-compressed message from
     * standard input; expands them; and writes the results to standard output.
     */
    public static void expand() {

        Node root = readTrie(); //reads the encoded Huffman Trie in the file and creates a Trie from it
        int N = BinaryStdIn.readInt();

        for (int i = 0; i < N; i++) {
            // Expand ith codeword.
            Node node = root;
            while (!node.isLeaf()) {
                if (BinaryStdIn.readBoolean())
                    node = node.right();
                else
                    node = node.left();
            }
            // prints the decoded character
            BinaryStdOut.write(node.ch());
        }
        BinaryStdOut.close();
    }

    // write bitstring-encoded trie to standard output
    private static void writeTrie(Node node) {

        if (node.isLeaf())
        {
            BinaryStdOut.write(true);
            BinaryStdOut.write(node.ch());
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(node.left());
        writeTrie(node.right());
    }

    // Creates a lookup table of a Huffman Trie
    private static void buildCode(String[] st, Node node, String s) {

        if (node.isLeaf()) {
            st[node.ch()] = s;
            return;
        }

        buildCode(st, node.left(), s + '0');
        buildCode(st, node.right(), s + '1');
    }

    private static Node buildTrie(int[] freq)
    {
        // Initialize priority queue with singleton trees.
        PriorityQueue<Node> pq = new PriorityQueue<>();

        // if character has frequency greater than, a node is created and added to the queue
        for (char c = 0; c < R; c++) {

            if (freq[c] > 0) {
                pq.add(new Node(c, freq[c], null, null));
            }
        }

        while (pq.size() > 1) {

            // Finds two smallest trees.
            Node x = removeMin(pq);
            Node y = removeMin(pq);

            // Two smallest trees are merged and added to the queue
            Node parent = new Node('\0', x.freq() + y.freq(), x, y);
            pq.add(parent);
        }
        return removeMin(pq);
    }

    public static Node removeMin(PriorityQueue<Node> pq) {

        Node min = new Node(' ', 0, null, null);
        int count = 0;

        // iterate over each node a find node with minimum frequency
        Iterator<Node> iterator = pq.iterator();
        while (iterator.hasNext()) {

            Node curr = iterator.next();

            // assigns first node as minimum
            if (count == 0) {
                min = curr;
            }
            else {
                // if frequency of current node is less than min, current node = min
                if (curr.compareTo(min) < 0) {
                    min = curr;
                }
            }
            count++;
        }
        // removes the node with minimum frequency from queue
        pq.remove(min);

        return min;
    }

    // reads the Huffman Trie encoded in the file and creates a Trie from it
    private static Node readTrie()
    {
        if (BinaryStdIn.readBoolean()) {
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        }

        return new Node('\0', 0, readTrie(), readTrie());
    }

    public static void main(String[] args) {
        if (args[0].equals("-")) compress();
        else if (args[0].equals("+")) expand();
        else throw new IllegalArgumentException("Illegal command line argument");
    }
}

