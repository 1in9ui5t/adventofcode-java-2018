import java.util.*;

public class Day1 {
    public static void main(String[] args) {
        List<Integer> frequencyDeltas = PuzzleInput.getInputForDay(1);
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
