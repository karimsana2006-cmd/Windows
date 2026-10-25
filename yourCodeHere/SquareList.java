//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 *  A list of squares within a single window.
 *
 *  <p>Adaptation of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated by
 *  K. Raven Russell.</p>
 */
public class SquareList {

    //head value, tail value, and size.
    /**
     * The first node in the list.
     */
    private Node<Square> head;

    /**
     * The last node in the list.
     */
    private Node<Square> tail;

    /**
     * The number of squares in the list.
     */
    private int size;

    /**
     *  Initialize an empty list of squares.
     */
    public SquareList() {

        head = null;
        tail = null;
        size = 0;

    }
    /**
     * Gets the head value.
     * @return head.
     */
    public Node<Square> getHead() {
        return head;
    }
    /**
     * gets the tail value.
     * @return tail.
     */
    public Node<Square> getTail() {
        return tail;
    }
    /**
     * gets the number of squares.
     * @return size.
     */
    public int numSquares() {
        return size;
    }

    /**
     * <p>Creates the new node square and adds it to the end.</p>
     * @param sq square that is going to be added.
     */
    public void add(Square sq) {
        //creates a new node.
        Node<Square> newNode = new Node<>(sq);

        //makes sure that the new square- sq isnt empty- if it is throws exception.
        if(sq == null){
            throw new IllegalArgumentException();
        }

        //checks if the list is null.
        if(head == null){
            //if its empty-new node is head and tail;
            tail = newNode;
            head=newNode;

        }
        else{

            //else- the new last node is the new node.
            tail.next = newNode;

            //else- new node is old last node.
            newNode.prev = tail;

            //tail points to new last node.
            tail = newNode;

        }

        //increments.
        size++;

    }
    /**
     * if the squares are on position x,y, then they will be deleted.
     * @param x coordinate to be deleted
     * @param y coordinate to be deleted
     * @return true if a square was deleted, else false.
     */
    public boolean handleClick (int x, int y) {

        boolean del = false;

        for(Node<Square> cur = head; cur != null;){
            Node<Square> nextNode = cur.next;

            if(cur.value.contains(x,y)) {
                size--;
                if(cur.prev==null) {
                    head = cur.next;

                }
                else {
                    cur.prev.next = cur.next;
                }
                if(cur.next == null){
                    tail = cur.prev;

                }
                else {
                    cur.next.prev= cur.prev;
                }
                del = true;
            }
            cur = nextNode;

        }
        return del;
    }

    /**
     *  Gets an iterator for the list of squares.
     *  Squares are returned in the order added.
     *
     *  @return the iterator requested
     */
    public Iterator<Square> elements() {
        //Note that this method uses your linked list!
        //so if the iterator doesn't work, that's on you...

        return new Iterator<>() {
            /**
             *  The current node pointed to by the
             *  iterator (containing the next value
             *  to be returned).
             */
            private Node<Square> current = getHead();

            /**
             * {@inheritDoc}
             */
            @Override
            public Square next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                Square ret = current.value;
                current = current.next;
                return ret;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return (current != null);
            }
        };
    }

    /**
     * Square sorter by creation.
     */
    public void sortCreation() {

        boolean s;

        do{
            s = false;

            Node<Square> cur= head;

            while(cur != null && cur.next != null){

                if(cur.value.id() > cur.next.value.id()){
                    Square t =cur.value;
                    cur.value = cur.next.value;
                    cur.next.value = t;
                    s= true;

                }
                cur = cur.next;

            }
        }
        while(s);
    }

    /**
     * sorts by Upper LEft coordinats.
     */
    public void sortLoc() {

        boolean sw;

        do{
            sw=false;
            Node<Square> cur = head;

            while(cur != null && cur.next != null){
                Square f = cur.value;
                Square s = cur.next.value;

                if(f.getUpperLeftX() < s.getUpperLeftX() ||  (f.getUpperLeftX() == s.getUpperLeftX() && f.getUpperLeftY() < s.getUpperLeftY())){
                    cur.value = s;
                    cur.next.value = f;
                    sw=true;

                }
                cur = cur.next;
            }
        }
        while(sw);
    }
}