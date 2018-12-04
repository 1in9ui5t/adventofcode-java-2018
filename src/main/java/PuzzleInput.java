import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PuzzleInput {
    private static final String PUZZLE_INPUT_FILE = "src/main/resources/puzzle-input.txt";

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
