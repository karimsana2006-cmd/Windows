
import java.awt.Color;

public class Test{ 

    public static void main(String[] args) {
        testEmptyList();
        testAddOneSquare();
        testAddMultipleSquares();
        testRemoveSquare();

        System.out.println("All tests passed!");
    }

    public static void testEmptyList() {
        SquareList list = new SquareList();

        check(list.numSquares() == 0,
                "Empty list should have size 0");

        check(list.getHead() == null,
                "Empty list should have no head");

        check(list.getTail() == null,
                "Empty list should have no tail");
    }

    public static void testAddOneSquare() {
        SquareList list = new SquareList();
        Square sq = new Square(50, 50, 20, Color.RED);

        list.add(sq);

        check(list.numSquares() == 1,
                "Size should be 1 after adding a square");

        check(list.getHead().value == sq,
                "Head should contain the added square");

        check(list.getTail() == list.getHead(),
                "Head and tail should be the same for one node");
    }

    public static void testAddMultipleSquares() {
        SquareList list = new SquareList();

        Square first = new Square(30, 30, 10, Color.RED);
        Square second = new Square(60, 60, 10, Color.BLUE);

        list.add(first);
        list.add(second);

        check(list.numSquares() == 2,
                "Size should be 2");

        check(list.getHead().value == first,
                "Head should contain the first square");

        check(list.getTail().value == second,
                "Tail should contain the second square");

        check(list.getHead().next == list.getTail(),
                "First node should point to second node");

        check(list.getTail().prev == list.getHead(),
                "Second node should point back to first node");
    }

    public static void testRemoveSquare() {
        SquareList list = new SquareList();
        Square sq = new Square(50, 50, 20, Color.RED);

        list.add(sq);

        boolean removed = list.handleClick(50, 50);

        check(removed,
                "Clicking inside the square should remove it");

        check(list.getHead() == null,
                "Head should be null after removing the only square");

        check(list.getTail() == null,
                "Tail should be null after removing the only square");

        check(list.numSquares() == 0,
                "Size should be 0 after removing the square");
    }

    public static void check(boolean condition, String message) {
        if (!condition) {
            throw new AssertionError("FAILED: " + message);
        }
    }
}

