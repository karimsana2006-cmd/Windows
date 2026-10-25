//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 *  A stack of windows within the window.
 *
 *  <p>Adaptation of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated by
 *  K. Raven Russell.</p>
 */
public class WindowStack {
    //You'll need some instance variables probably...
    /** Head node.
     */
    private Node<Window> head;
    /** tail node. */
    private Node<Window> tail;
    /** size node.  */
    private int size;


    /**
     * Initialize variables.
     */
    public WindowStack() {

        head = null;
        tail = null;
        size = 0;

    }
    /**
     * returns the top of the stack of windows.
     * @return head
     */
    public Node<Window> getTop() {
        //Returns the head (top) of the stack of windows,
        //may return null if appropriate.

        if(head == null){
            return null;
        }

        return head;

    }
    /**
     * gets the bottom , tail.
     * @return tail
     */
    public Node<Window> getBottom() {

        //Returns the tail (bottom) of the stack of windows,
        //may return null if appropriate.

        if(tail == null){
            return null;
        }

        return tail;

    }
    /**
     * gets the size.
     * @return size
     */
    public int numWindows() {
        return size;
    }
    /**
     * Add a new window to the top of stack.
     * @param r the window to add.
     */
    public void add(Window r) {
        //make sure there is a window.
        if(r == null){
            throw new IllegalArgumentException();
        }

        //nodes.
        Node<Window> temp = head;
        Node<Window> neww = new Node<Window>(r);

        neww.next = head;

        if(head!=null){
            head.prev = neww;
            head.value.setSelected(false);
        }
        else{
            tail = neww;
        }

        head = neww;

        head.value.setSelected(true);
        size++;

    }

    /**
     * Removes a node from the stack.
     * @param cur the node to remove.
     */
    private void helperRem(Node<Window> cur){
        if(cur.prev != null){
            cur.prev.next = cur.next;

        }
        else{
            head = cur.next;
        }
        if(cur.next != null){
            cur.next.prev = cur.prev;
        }
        else{
            tail = cur.prev;
        }
        cur.value.setSelected(false);
        size--;
    }
    /**
     * move node helper function. Moves to the top of the stack and updates everything.
     * @param cur top node.
     */
    public void helperMove(Node<Window> cur){

        if(cur == head){
            return;
        }

        if(cur.prev !=null) {
            cur.prev.next = cur.next;
        }

        if(cur.next != null){
            cur.next.prev = cur.prev;
        }

        else{
            tail = cur.prev;
        }

        cur.prev = null;
        cur.next = head;
        head.prev = cur;
        head = cur;

    }

    /**
     * Right click removes window clicked.
     * Left click sel/rem top window.
     * @param x x coordinate
     * @param y y coordniate
     * @param leftClick if left clicked- leftClick true.
     * @return If window was clicked- return true.
     */
    public boolean handleClick (int x, int y, boolean leftClick) {

        Node<Window> current = head;

        while ( current != null && !current.value.contains(x,y)) {
            current = current.next;
        }

        if (current == null){
            return false;
        }

        if(!leftClick) {
            helperRem(current);

            if(head != null){
                head.value.setSelected(true);
            }

            return true;
        }

        if(current == head){
            current.value.handleClick(x,y);

            return true;

        }

        head.value.setSelected(false);
        helperMove(current);
        head.value.setSelected(true);

        return true;

    }

    /**
     *  Gets an iterator for the stack of windows.
     *  Windows are returned from bottom to top.
     *
     *  @return the iterator requested
     */
    public Iterator<Window> windows() {
        //Note that this method uses your linked list!
        //so if the iterator doesn't work, that's on you...

        return new Iterator<>() {
            /**
             *  The current node pointed to by the
             *  iterator (containing the next value
             *  to be returned).
             */
            private Node<Window> current = getBottom();

            /**
             * {@inheritDoc}
             */
            @Override
            public Window next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                Window ret = current.value;
                current = current.prev;
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
     * Sorts the window in the stack by their area.
     */
    public void sortSize() {
        if(head == null){
            return;
        }
        //Sorts the windows in the stack by their area (length x width).
        //MOST of this is done for you, but you still need to assign
        //the returned head and tail back.

        //unselect the top window
        this.getTop().value.setSelected(false);

        //create a way to compare windows by area
        Comparator<Window> comp = new Comparator<>() {
            public int compare(Window w1, Window w2) {
                return (w1.getWidth()*w1.getHeight())-(w2.getWidth()*w2.getHeight());
            }
        };

        //create a pair of nodes to pass into the sort function
        ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getTop(), getBottom());

        //call the sort function with the comparator
        ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);

        //make the returned list the head and tail of this list
        //this is for PART 5 of the project... don't try to do this
        //before you complete ThreeTenLinkedList.sort()!
        //< YOUR_CODE_HERE >

        head = ret.head;
        tail = ret.tail;

        //re-select the top of the stack
        this.getTop().value.setSelected(true);
    }
    /**
     * Sorts window by coordinates.
     */
    public void sortLoc() {
        if (head == null) {
            return;
        }

        head.value.setSelected(false);

        Comparator<Window> comp = new Comparator<>() {
            public int compare(Window w1, Window w2) {
                if (w1.getUpperLeftX() != w2.getUpperLeftX()) {
                    return w2.getUpperLeftX() - w1.getUpperLeftX();
                }

                return w2.getUpperLeftY() - w1.getUpperLeftY();
            }
        };

        ThreeTenLinkedList.NodePair<Window> pair =
            new ThreeTenLinkedList.NodePair<>(getTop(), getBottom());

        ThreeTenLinkedList.NodePair<Window> ret =
            ThreeTenLinkedList.sort(pair, comp);

        head = ret.head;
        tail = ret.tail;

        head.value.setSelected(true);
    }
}