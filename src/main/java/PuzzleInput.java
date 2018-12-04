import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PuzzleInput {

    private static final String INPUT_FILENAME_ROOT = "src/main/resources/puzzle-inputs/day";
    private static final String INPUT_FILENAME_EXTENSION = ".txt";

    public static List getInputForDay(int day) {
        String filename;
        Class clazz;
        if (day > 0 && day <= 2) {
            filename = INPUT_FILENAME_ROOT + day + INPUT_FILENAME_EXTENSION;
            switch (day) {
                case 1:
                    clazz = Integer.class;
                    break;
                case 2:
                default:
                    clazz = String.class;
                    break;
            }
        } else {
            throw new IllegalArgumentException("No puzzle input is available for the specified day.");
        }
        return processInput(filename, clazz);
    }

    public static List processInput(String filename, Class clazz) {
        List input = new ArrayList();
        // Used try-with-resource statement below, a less verbose option for resources that implement AutoCloseable
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            if (clazz.equals(Integer.class)) {
                input = br.lines().map(Integer::parseInt).collect(Collectors.toList());
            } else {
                input = br.lines().collect(Collectors.toList());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return input;
    }
}
