package threading;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BuildingH2OTest {
    @Test
    void getAtoms() throws InterruptedException {
        var sut = new BuildingH2O();
        checkWater(sut.getAtoms("OOHHHHHHO"));
    }

    @Test
    void insuficientAtoms() throws InterruptedException {
        var sut = new BuildingH2O();
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            sut.getAtoms("OOHH");
        });
        assertTrue(exception.getMessage().contains("Not all atoms can form water"));
    }

    @Test
    void atomsAreNotMatched() throws InterruptedException {
        var sut = new BuildingH2O();
        var exception = assertThrows(IllegalArgumentException.class, () -> {
            sut.getAtoms("OOHHHO");
        });
        assertTrue(exception.getMessage().contains("Not all atoms can form water"));
    }

    private void checkWater(List<Character> elements) {
        for (var i = 0; i < elements.size(); i += 3) {
            var x0 = elements.get(i).toString();
            var x1 = elements.get(i + 1).toString();
            var x2 = elements.get(i + 2).toString();
            Assertions.assertTrue(x0.equals("H") && x1.equals("H") && x2.equals("O")
                    || x0.equals("H") && x1.equals("O") && x2.equals("H")
                    || x0.equals("O") && x1.equals("H") && x2.equals("H"));
            }
    }
}