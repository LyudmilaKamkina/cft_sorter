import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Reader implements Messages {
    private boolean isInts = true;
    private boolean isStrings = false;

    private List<List<String>> toReadStrings(ArrayList<Path> pathsInput) {
        List<List<String>> inputStrings = new ArrayList<>();

        for (int count = 0; count < pathsInput.size(); count++) {

            try {
                List<String> file = Files.readAllLines(pathsInput.get(count));
                if (file.isEmpty()){
                    System.out.printf(Messages.FILE_IS_EMPTY, pathsInput.get(count).toString());
                } else {
                    inputStrings.add(file);

                    for (int g = 0; g < inputStrings.get(count).size(); g++) {
                        if (inputStrings.get(count).get(g).contains(" ")) {
                            inputStrings.get(count).remove(g);
                            g--;
                        }
                        if (inputStrings.get(count).get(g).isEmpty()) {
                            inputStrings.get(count).remove(g);
                            g--;
                        }

                        if (g > 1 && inputStrings.get(count).get(g).compareTo(inputStrings.get(count).get(g - 1)) < 0){
                            inputStrings.get(count).remove(g);
                            g--;
                            //System.out.printf("Invalid element deleted from file %s \n", pathsInput.get(count).toString());
                        }
                    }
                }


            } catch (IOException e) {
                System.out.printf(Messages.FILE_NOT_FOUND, pathsInput.get(count).toString());
                continue;
            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println("=== File END ===");
            }
        }

        return inputStrings;
    }

    private List<List<String>> toReadInts(ArrayList<Path> pathsInput) {
        List<List<String>> inputInts = new ArrayList<>();
        for (int count = 0; count < pathsInput.size(); count++) {
            try {
                List<String> file = Files.readAllLines(pathsInput.get(count));
                if (file.isEmpty()){
                    System.out.printf(Messages.FILE_IS_EMPTY, pathsInput.get(count).toString());
                } else {
                    inputInts.add(file);

                    for (int g = 0; g < inputInts.get(count).size() - 1; g++) {
                        try {
                            int i = Integer.parseInt(inputInts.get(count).get(g));
                            int j = Integer.parseInt(inputInts.get(count).get(g + 1));
                            if (j < i ){
                                inputInts.get(count).remove(g + 1);
                                g--;
                                //System.out.printf("Invalid element deleted from file %s \n", pathsInput.get(count).toString());
                            }
                        } catch (NumberFormatException n) {
                            inputInts.get(count).remove(g);
                            g--;
                        }
                    }
                }
            } catch (IOException e) {
                System.out.printf(Messages.FILE_NOT_FOUND, pathsInput.get(count).toString());
                return inputInts;
            }

        }

        return inputInts;
    }

    public List<List<String>> toRead(ArrayList<Path> pathsInput) {
        List<List<String>> inputElements = new ArrayList<>();
        if (pathsInput.isEmpty()) {
            System.out.println(Messages.NO_FILES_TO_READ);
        } else {
            if (isStrings) {
                inputElements = toReadStrings(pathsInput);
            }
            if (isInts) {
                inputElements = toReadInts(pathsInput);
            }
        }
        return inputElements;
    }

    public Reader(boolean isInts, boolean isStrings) {
        this.isInts = isInts;
        this.isStrings = isStrings;
    }
}
