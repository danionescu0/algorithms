package recursive;

/**
 * Paint Fill: Implement the "paint fill" function that one might see on many image editing programs.
 * That is, given a screen (represented by a two-dimensional array of colors), a point, and a new color,
 * fill in the surrounding area until the color changes from the original color.
 */
public class PaintFill {
    public void fill(short[][] screen, int pointX, int pointY, short newColor) {
        var oldColor = screen[pointX][pointY];
        screen[pointX][pointY] = newColor;
        if (pointX > 0 && screen[pointX -1][pointY] == oldColor) {
            fill(screen, pointX - 1, pointY, newColor);
        }
        if (pointX < screen.length - 1 && screen[pointX + 1][pointY] == oldColor) {
            fill(screen, pointX + 1, pointY, newColor);
        }
        if (pointY > 0 && screen[pointX][pointY - 1] == oldColor) {
            fill(screen, pointX, pointY - 1, newColor);
        }
        if (pointY < screen[0].length - 1 && screen[pointX][pointY + 1] == oldColor) {
            fill(screen, pointX, pointY + 1, newColor);
        }
        if (pointX > 0 && pointY > 0 && screen[pointX - 1][pointY - 1] == oldColor) {
            fill(screen, pointX - 1, pointY - 1, newColor);
        }
        if (pointX > 0 && pointY < screen[0].length - 1 && screen[pointX - 1][pointY + 1] == oldColor) {
            fill(screen, pointX - 1, pointY + 1, newColor);
        }
        if (pointX < screen.length - 1 && pointY > 0 && screen[pointX + 1][pointY - 1] == oldColor) {
            fill(screen, pointX + 1, pointY - 1, newColor);
        }
        if (pointX < screen.length - 1 && pointY < screen[0].length - 1 && screen[pointX + 1][pointY + 1] == oldColor) {
            fill(screen, pointX + 1, pointY + 1, newColor);
        }
    }
}