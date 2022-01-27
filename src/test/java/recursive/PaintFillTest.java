package recursive;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PaintFillTest {

    @Test
    public void fillBigArray() {
        var fill = new PaintFill();
        var screen = new short[][]{
                {11, 11, 22, 11},
                {15, 11, 22, 11},
                {10, 11, 11, 15},
                {10, 11, 22, 15},
                {11, 11, 22, 11},
        };
        fill.fill(screen, 1, 1, (short) 0);
        var expected = new short[][]{
                {0,  0, 22, 0},
                {15, 0, 22, 0},
                {10, 0,  0, 15},
                {10, 0, 22, 15},
                {0,  0, 22, 11},
        };
        assertMatrixEqual(expected, screen);
    }

    @Test
    public void fillSmallArray() {
        var fill = new PaintFill();
        var screen = new short[][]{
                {11, 11},
                {15, 11}
        };
        fill.fill(screen, 1, 0, (short) 0);
        var expected = new short[][]{
                {11, 11},
                {0, 11}
        };
        assertMatrixEqual(expected, screen);
    }

    private void assertMatrixEqual(short[][] expected, short[][] actual) {
        for (var i=0; i < expected.length - 1;i++) {
            for (var j=0; j < expected.length -1; j++) {
                Assertions.assertEquals(expected[i][j], actual[i][j]);
            }
        }
    }
}