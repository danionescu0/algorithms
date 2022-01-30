package bits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * You have an integer and you can flip exactly one bit from a 0 to a 1. Write code to find the length of the
 * longest sequence of 1s you could create.
 * Note: Implement binary conversion manually
 *
 * Example:
 *
 * Input: 1775 (11011101111)
 * Output: 8
 */
public class LongestFlipBit {
    public int findLongestFlip(int number) {
        var binary = convertToBinary(number);
        var oneChunks = new ArrayList<List<Integer>>();
        var i = 0;
        Integer start = null;
        while (i < binary.size()) {
            if (start == null && binary.get(i) != 0) {
                start = i;
            } else if (start != null && (binary.get(i) == 0)) {
                oneChunks.add(Arrays.asList(start, i - 1));
                start = null;
            }
            i++;
        }
        if (start != null) {
            oneChunks.add(Arrays.asList(start, binary.size() - 1));
        }
        var longestFlip = 0;
        for (i = 0; i < oneChunks.size(); i++){
            var currentFlip = 1 + oneChunks.get(i).get(1) - oneChunks.get(i).get(0) + 1;
            if (oneChunks.get(i).get(0) > 0) {
                var currentFlipLeft = currentFlip + countUntilZero(binary, -1, oneChunks.get(i).get(0) - 2);
                longestFlip = Math.max(longestFlip, currentFlipLeft);
            }
            if (oneChunks.get(i).get(1) < binary.size()) {
                var currentFlipRight = currentFlip + countUntilZero(binary, 1, oneChunks.get(i).get(1) + 2);
                longestFlip = Math.max(longestFlip, currentFlipRight);
            }
        }

        return longestFlip;
    }

    private int countUntilZero(List<Byte> binary, int pace, int from) {
        var nr = 0;
        while (from > 0 && from < binary.size() && binary.get(from) > 0) {
            from += pace;
            nr ++;
        }

        return nr;
    }

    private ArrayList<Byte> convertToBinary(int number) {
        var asBinary = new ArrayList<Byte>();
        var workingNr = number;
        while (workingNr >= 2) {
            var bit = (byte) (workingNr % 2);
            asBinary.add(bit);
            workingNr = workingNr / 2;
        }
        asBinary.add((byte) workingNr);
        Collections.reverse(asBinary);

        return asBinary;
    }
}