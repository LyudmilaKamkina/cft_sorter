
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        boolean isInts = false;
        boolean isStrings = true;
        boolean isAcs = true;
        boolean isDesc = false;
        Path pathOutput = null;
        ArrayList<Path> pathsInput = new ArrayList<>();

        Writer writer = new Writer();
            try {
                if (args[0].startsWith("-")) {
                    if (args[0].equals("-s")) {
                        isInts = false;
                        isStrings = true;
                    } else if (args[0].equals("-i")) {
                        isInts = true;
                        isStrings = false;
                    } else {
                        System.out.println(Messages.ENTER_PARAMS);
                    }
                } else {
                    System.out.println(Messages.ENTER_PARAMS);
                }
                if (args[1].startsWith("-")) {
                    if (args[1].equals("-a")) {
                        isAcs = true;
                        isDesc = !isAcs;
                    } else if (args[1].equals("-d")) {
                        isAcs = false;
                        isDesc = !isAcs;
                    } else {
                        System.out.println(Messages.ENTER_PARAMS);
                    }
                } else {
                    pathOutput = Paths.get(args[1]);
                }
                Sorter multiSorter = new Sorter(isStrings, isInts, isAcs, isDesc);
                Reader reader = new Reader(isInts, isStrings);
                if (pathOutput == null) {
                    pathOutput = Paths.get(args[2]);
                    for (int f = 3; f < args.length; f++) {
                        pathsInput.add(Paths.get(args[f]));
                    }
                    List<List<String>> toSorting = reader.toRead(pathsInput);
                    List<String> multiSortResult = multiSorter.toSort(toSorting);
                    writer.toWrite(multiSortResult, pathOutput);
                    System.out.println("=====complete!=====");
                } else {
                    for (int f = 2; f < args.length; f++) {
                        pathsInput.add(Paths.get(args[f]));
                    }
                    List<List<String>> toSorting = reader.toRead(pathsInput);
                    List<String> multiSortResult = multiSorter.toSort(toSorting);
                    writer.toWrite(multiSortResult, pathOutput);
                    System.out.println("=====complete!=====");
                }

            } catch (ArrayIndexOutOfBoundsException a) {
                System.out.println(Messages.ENTER_PARAMS);
            }

        }
    }

