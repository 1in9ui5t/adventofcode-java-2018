import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Day1 {
    public static void main(String[] args) {
        List<Integer> frequencyDeltas = PuzzleInput.getFrequencyDeltas();
        System.out.println("Result of Day 1, Part 1: " + sum(frequencyDeltas));
        System.out.println("Result of Day 1, Part 2: " + detectFirstDuplicateFrequencyLooparound(frequencyDeltas));
    }

    private static int sum(List<Integer> input) {
        return input.stream().mapToInt(Integer::intValue).sum();
    }

    private static Integer detectFirstDuplicateFrequencyLooparound(List<Integer> input) {
        int currentSum = 0;
        Integer delta = currentSum;
        Map<Integer, Boolean> resultingFrequencies = new HashMap<>();
        resultingFrequencies.put(new Integer(currentSum), true);
        int index = 0;
        int loopsAround = 0;
        do {
            resultingFrequencies.put(new Integer(currentSum), true);
            delta = input.get(index);
            currentSum += delta;
            if (resultingFrequencies.containsKey(new Integer(currentSum))) {
                System.out.println("Duplicate frequency encountered after " + loopsAround + " loops of the input list");
                return currentSum;
            }
            if (++index == input.size()) {
                index = 0;
                loopsAround++;
            }
        } while (true);
    }
}

class PuzzleInput {
    private static final String PUZZLE_INPUT_FILE = "src/main/resources/puzzle-inputs/day1.txt";

    private static List<Integer> frequencyDeltas = null;

    public static List<Integer> getFrequencyDeltas() {
        if (frequencyDeltas == null) {
            frequencyDeltas = processInput();
        }
        return frequencyDeltas;
    }

    private static List<Integer> processInput() {
        List<Integer> frequencyDeltas = new ArrayList<>();
        // Used try-with-resource statement below, a less verbose option for resources that implement AutoCloseable
        try (BufferedReader br = new BufferedReader(new FileReader(PUZZLE_INPUT_FILE))) {
            br.lines().forEach(line -> frequencyDeltas.add(Integer.parseInt(line)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return frequencyDeltas;
    }
}
