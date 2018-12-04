import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day2 {
    public static void main(String[] args) {
        List<String> boxIds = PuzzleInput.getInputForDay(2);
        System.out.println("Result of Day 2, Part 1: " + generateChecksum(boxIds));
    }

    private static int generateChecksum(List<String> input) {
        int countOfThrees = 0;
        int countOfTwos = 0;
        for (String id: input) {
            Map<Integer, Integer> charactersOccurrence = new HashMap<>();
            id.chars().forEach(letter -> {
                Integer occurrences = charactersOccurrence.get(new Integer(letter));
                if (occurrences == null) {
                    occurrences = 1;
                } else {
                    occurrences++;
                }
                charactersOccurrence.put(new Integer(letter), occurrences);
            });
            boolean hasExactlyTwoOfSomeLetter = false;
            boolean hasExactlyThreeOfSomeLetter = false;

            for (Map.Entry<Integer, Integer> characterOccurence: charactersOccurrence.entrySet()) {
                switch (characterOccurence.getValue()) {
                    case 3:
                        hasExactlyThreeOfSomeLetter = true;
                        break;
                    case 2:
                        hasExactlyTwoOfSomeLetter = true;
                        break;
                }
            }
            if (hasExactlyThreeOfSomeLetter) {
                countOfThrees++;
            }
            if (hasExactlyTwoOfSomeLetter) {
                countOfTwos++;
            }
        }
        return countOfTwos * countOfThrees;
    }
}