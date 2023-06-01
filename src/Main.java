import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;

public class Main {

    private static final String SOLUTION = "12 13 7 9 10 11 14 5 15 3 2 8 4 16 1 6";

    private static int calculateDeviation(String sequence) {
        String[] pieces = sequence.split(" ");
        validateInput(pieces);

        String[] solution = SOLUTION.split(" ");
        HashMap<String, Integer> iMap = new HashMap<>();
        HashMap<String, Integer> sMap = new HashMap<>();

        for (int x = 0; x < pieces.length; x++) {
            iMap.put(pieces[x], x);
        }
        for (int x = 0; x < solution.length; x++) {
            sMap.put(solution[x], x);
        }

        int totalDeviation = 0;

        // Fastest cars
        for (int x = 0; x < 3; x++) {
            String guessedFastestCar = pieces[x];
            int guessedPosition = x;
            int actualPosition = sMap.get(guessedFastestCar);
            totalDeviation += Math.abs(guessedPosition - actualPosition);
        }

        // Slowest cars
        for (int x = 0; x < 3; x++) {
            String guessedSlowestCar = pieces[pieces.length - 3 + x];
            int guessedPosition = solution.length - 1 - x;
            int actualPosition = sMap.get(guessedSlowestCar);
            totalDeviation += Math.abs(guessedPosition - actualPosition);
        }

        return totalDeviation;
    }

    private static void validateInput(String[] pieces) {
        if (pieces.length != 6) {
            throw new RuntimeException("Invalid input - expected 6 sequence elements but only found " + pieces.length);
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