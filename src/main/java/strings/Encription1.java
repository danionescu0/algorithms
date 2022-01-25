package strings;

/*
An English text needs to be encrypted using the following encryption scheme.
First, the spaces are removed from the text. Let  be the length of this text.
Then, characters are written into a grid, whose rows and columns have the following constraints:

Example: "If a man was ment to stay on the ground god would have given us roots"

After removing spaces, the string is 54 characters long. sqrt(54) is between 7 and 8, so it is written in the form of a grid with 7 rows and 8 columns.

ifmanwas
meanttos
tayonthe
groundgo
dwouldha
vegivenu
sroots
Ensure that
If multiple grids satisfy the above conditions, choose the one with the minimum area, i.e. .
The encoded message is obtained by displaying the characters of each column, with a space between column texts. The encoded message for the grid above is:

imtgdvs fearwer mayoogo anouuio ntnnlvt wttddes aohghn sseoau

Create a function to encode a message.

Function Description

Complete the encryption function in the editor below.

encryption has the following parameter(s):

 contains characters in the range ascii[a-z] and space, ascii(32).
 */
public class Encription1 {
    public String encryption(String input) {
        var trimmed = input.replaceAll("\\s", "");
        var rows = Math.floor(Math.sqrt(trimmed.length()));
        var columns = Math.ceil(Math.sqrt(trimmed.length()));
        var encripted = new StringBuilder();
        for(var i=0;i<columns;i++) {
            for (var j=0;j<rows;j++) {
                var position = j > 0 ? j * columns + i : i;
                if (position >= trimmed.length()) {
                    break;
                }
                encripted.append(trimmed.charAt((int) position));
            }
            encripted.append(" ");
        }
        var result = encripted.toString();

        return (result.charAt(result.length() - 1) == ' ') ? result.substring(0, result.length() - 1) : result;
    }
}