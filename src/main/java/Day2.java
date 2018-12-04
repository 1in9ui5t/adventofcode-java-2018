import java.util.*;

public class Day2 {
    public static void main(String[] args) {
        List<String> boxIds = PuzzleInput.getInputForDay(2);
        System.out.println("Result of Day 2, Part 1: " + generateChecksum(boxIds));
        System.out.println("Result of Day 2, Part 2: " + findCommonLettersInFabricBoxesIds(boxIds));
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
                charactersOccurrence.put(letter, occurrences);
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

    private static String findCommonLettersInFabricBoxesIds(List<String> input) {
        String commonLetters = null;
        int i, j;
        for (i = 0; i < input.size() && commonLetters == null; i++) {
            for (j = i; j < input.size(); j++) {
                commonLetters = getCommonLettersIfOffByOneCharacter(input.get(i), input.get(j));
                if (commonLetters != null) {
                    break;
                }
            }
        }
        return commonLetters;
    }

    private static String getCommonLettersIfOffByOneCharacter(String target, String other) {
        int variations = 0;
        int variationIndex = -1;
        for (int i = 0; variations <= 1 && i < target.length(); i++) {
            if (target.charAt(i) != other.charAt(i)) {
                variations++;
                variationIndex = i;
            }
        }
        return variations == 1 ? new StringBuilder(target).deleteCharAt(variationIndex).toString() : null;
    }
}