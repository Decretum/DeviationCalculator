import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OverallRankingCalculator {

    private static final int[] PLACING_TO_SCORE_MAP = {0, 15, 12, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1};

    private static void printSortedDescending(HashMap<String, Integer> scores) {
        List<Map.Entry<String, Integer>> ascending = scores.entrySet().stream().sorted(Map.Entry.comparingByValue()).toList();
        for (int x = ascending.size() - 1; x >= 0; x--) {
            Map.Entry<String, Integer> entry = ascending.get(x);
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new FileReader("input/overall_rankings.txt"));
        HashMap<String, Integer> scores = new HashMap<>();

        String line = bufferedReader.readLine();
        while (line != null && !line.isEmpty()) {
            String name = line;
            String placing = bufferedReader.readLine();
            String[] placings = placing.split(" ");
            int totalScore = 0;
            for (int x = 0; x < placings.length; x++) {
                int score = PLACING_TO_SCORE_MAP[Integer.parseInt(placings[x])];
                totalScore += score;
            }
            scores.put(name, totalScore);
            line = bufferedReader.readLine();
        }

        printSortedDescending(scores);
    }
}
