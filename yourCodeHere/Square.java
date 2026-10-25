//TODO: Complete java docs and code in missing spots.
//Missing spots are marked by < YOUR_CODE_HERE >.
//Do NOT edit any other parts of the code.

import java.awt.Color;
import java.awt.Graphics;

/**
 *  A single square in a window.
 *
 *  <p>Adaptation of Nifty Assignment (http://nifty.stanford.edu/) by
 *  Mike Clancy in 2001. Original code by Mike Clancy. Updated by
 *  K. Raven Russell.</p>
 */
public class Square {
    /**
     *  The next id when a new square is created.
     */
    private static int nextID = 0;

    /**
     *  The X position of the upper left corner of this square.
     */
    private int upperLeftX;

    /**
     *  The Y position of the upper left corner of this square.
     */
    private int upperLeftY;

    /**
     *  The size of this square.
     */
    private int size;

    /**
     *  The color this square.
     */
    private Color color;

    /**
     *  The id of this square.
     */
    private int id;

    /**
     *  Initialize a square with the given position (specified
     *  relative to the WindowManager in which all this is being
     *  run), dimensions, and color.
     *
     *  @param centerX the x position of the center of this square
     *  @param centerY the y position of the center of this square
     *  @param size the size of this square
     *  @param c the color of this square
     */
    public Square (int centerX, int centerY, int size, Color c) {
        this.upperLeftX = centerX - size/2;
        this.upperLeftY = centerY - size/2;
        this.size = size;
        this.color = c;
        this.id = nextID;
        nextID++;
    }
    /**
     * <p>Checks if the square is within the given x, y coordinates.</p>
     *
     * @param x the x coordinate to check
     * @param y the y coordinate to check
     * @return true if the point is inside the square, else false.
     */
    public boolean contains (int x, int y) {

        return x >=upperLeftX && x <= upperLeftX + size && y >= upperLeftY && y <= upperLeftY + size;

    }

    /**
     *  Fetches the id of this square.
     *
     *  @return the id of this square
     */
    public int id() {
        return id;
    }

    /**
     *  Gets the upper left x position of the square.
     *
     *  @return the upper left x position
     */
    public int getUpperLeftX() {
        return upperLeftX;
    }

    /**
     *  Gets the upper left y position of the square.
     *
     *  @return the upper left y position
     */
    public int getUpperLeftY() {
        return upperLeftY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals (Object o) {
        if(o instanceof Square) {
            Square sq = (Square) o;
            return upperLeftX == sq.upperLeftX &&
                upperLeftY == sq.upperLeftY &&
                size == sq.size;
        }
        return false;
    }

    /**
     *  Draw the square, surrounded by a border.
     *
     *  @param g the graphics to paint on, assumed not null
     */
    public void paint (Graphics g) {

        //sets the color at a random color
        g.setColor(color);
        //draws a fillled rectangle
        g.fillRect(upperLeftX,upperLeftY,size, size);

    }
}