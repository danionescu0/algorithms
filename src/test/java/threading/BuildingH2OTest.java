package threading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BuildingH2OTest {
    @Test
    void getAtoms() {
        var sut = new BuildingH2O();
        var result = sut.getAtoms("OOHHHH");
    }
}