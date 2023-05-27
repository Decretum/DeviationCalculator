import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    private static final String SOLUTION = "1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16";

    private static int calculateDeviation(String sequence) {
        String[] pieces = sequence.split(" ");
        validateInput(pieces);

        String[] solution = SOLUTION.split(" ");
        HashMap<String, Integer> iMap = new HashMap<>();
        HashMap<String, Integer> sMap = new HashMap<>();

        for (int x = 0; x < pieces.length; x++) {
            iMap.put(pieces[x], x);
            sMap.put(solution[x], x);
        }

        int totalDeviation = 0;

        for (int x = 0; x < pieces.length; x++) {
            totalDeviation += Math.abs(iMap.get(pieces[x]) - sMap.get(pieces[x]));
        }

        return totalDeviation;
    }

    private static void validateInput(String[] pieces) {
        if (pieces.length != 16) {
            throw new RuntimeException("Invalid input - expected 16 sequence elements but only found " + pieces.length);
        }

        for (String piece : pieces) {
            try {
                Integer.parseInt(piece);
            } catch (NumberFormatException nfe) {
                throw new RuntimeException("Invalid number in input: " + piece);
            }
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input/scores.txt"));
        String line = bufferedReader.readLine();
        while (line != null && !line.isEmpty()) {
            String name = line;
            int score = calculateDeviation(bufferedReader.readLine());
            System.out.println(name + ": " + score);
            line = bufferedReader.readLine();
        }
    }
}