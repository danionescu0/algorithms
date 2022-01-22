package strings;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import strings.Encription;

import static org.junit.jupiter.api.Assertions.*;

class EncriptionTest {

    @Test
    void encryptionLong() {
        var encription = new Encription();
        var text = "if man was meant to stay on the ground god would have given us roots";
        Assertions.assertEquals("imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau", encription.encryption(text));
    }

    @Test
    void encryptionShort() {
        var encription = new Encription();
        var text = "have a nice day";
        assertEquals("hae and via ecy", encription.encryption(text));
    }

//    @Test
//    void encryption3() {
//        var encription = new Encription();
//        var text = "chillout";
//        assertEquals("clu hlt io", encription.encryption(text));
//    }
}