import java.util.ArrayList;
import java.util.List;

public class Sorter implements Messages {
    private boolean isStrings = false;
    private boolean isInts = true;
    private boolean isAscSort = true;
    private boolean isDescSort = false;

    private static List<String> ascSortInts(List<String> leftStr, List<String> rightStr){
        List<String> result = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (String l : leftStr){
            left.add(Integer.parseInt(l));
        }
        for (String r : rightStr){
            right.add(Integer.parseInt(r));
        }
        int i = 0;
        int j = 0;
        for (int k = 0; k < (left.size() + right.size()); ) {
            if (i < left.size() && j < right.size()) {
                if (left.get(i) > right.get(j)) {
                    result.add(k, right.get(j).toString());
                    k++;
                    j++;
                } else {
                    result.add(k, left.get(i).toString());
                    k++;
                    i++;
                }

            } else if (i >= left.size()) {
                result.add(k, right.get(j).toString());
                k++;
                j++;
            } else if (j >= right.size()) {
                result.add(k, left.get(i).toString());
                k++;
                i++;
            }
        }

        return result;
    }

    private static List<String> descSortInts(List<String> leftStr, List<String> rightStr){
        List<String> result = new ArrayList<>();
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (String l : leftStr){
            left.add(Integer.parseInt(l));
        }
        for (String r : rightStr){
            right.add(Integer.parseInt(r));
        }
        int i = left.size() - 1;
        int j = right.size() - 1;
        for (int k = 0; k < (left.size() + right.size()); ) {
            if (i >= 0 && j >= 0) {
                if (left.get(i) < right.get(j)) {
                    result.add(k, right.get(j).toString());
                    k++;
                    j--;
                } else {
                    result.add(k, left.get(i).toString());
                    k++;
                    i--;
                }

            } else if (i < 0) {
                result.add(k, right.get(j).toString());
                k++;
                j--;
            } else if (j < 0) {
                result.add(k, left.get(i).toString());
                k++;
                i--;
            }
        }
        return result;
    }
    private static List<String> ascSortStrings(List<String> leftStr, List<String> rightStr){
        List<String> result = new ArrayList<>();
        int i = 0;
        int j = 0;
        for (int k = 0; k < (leftStr.size() + rightStr.size()); ){
            if (i < leftStr.size() && j < rightStr.size()) {
                int res = leftStr.get(i).compareTo(rightStr.get(j));
                if (res < 0) {
                    result.add(k, leftStr.get(i));
                    k++;
                    i++;
                } else {
                    result.add(k, rightStr.get(j));
                    k++;
                    j++;
                }
            } else if (i >= leftStr.size()){
                result.add(k, rightStr.get(j));
                k++;
                j++;
            } else if ( j >= rightStr.size()){
                result.add(k, leftStr.get(i));
                k++;
                i++;
            }
        }
        return result;
    }
    private static List<String> descSortStrings(List<String> leftStr, List<String> rightStr){
        List<String> result = new ArrayList<>();
        int i = leftStr.size() - 1;
        int j = rightStr.size() - 1;
        for (int k = 0; k < (leftStr.size() + rightStr.size()); ){
            if (i >= 0 && j >= 0) {
                int res = leftStr.get(i).compareTo(rightStr.get(j));
                if (res > 0) {
                    result.add(k, leftStr.get(i));
                    k++;
                    i--;
                } else {
                    result.add(k, rightStr.get(j));
                    k++;
                    j--;
                }
            } else if (i < 0){
                result.add(k, rightStr.get(j));
                k++;
                j--;
            } else if ( j < 0){
                result.add(k, leftStr.get(i));
                k++;
                i--;
            }
        }
        return result;
    }
    private List<String> toSort(List<String> first, List<String> second){
        List<String> result = new ArrayList<>();
        if (isInts && isAscSort){
            result = ascSortInts(first, second);
        }
        if (isInts && isDescSort){
            result = descSortInts(first, second);
        }
        if (isStrings && isAscSort){
            result = ascSortStrings(first, second);
        }
        if (isStrings && isDescSort){
            result = descSortStrings(first, second);
        }
        return result;
    }
    public List<String> toSort(List<List <String>> inStrings){
        List<String> result = new ArrayList<>();
        if (inStrings.isEmpty()){
            System.out.println(Messages.NO_FILES_TO_SORT);
        }
        if (inStrings.size() == 1){

            result = inStrings.get(0);
        }
        if (inStrings.size() > 1) {
            System.out.printf(Messages.FILES_SORTING, inStrings.size());
            for (int count = 1; count < inStrings.size(); count++) {
                result = toSort(inStrings.get(0), inStrings.get(1));
                inStrings.remove(1);
                inStrings.add(0, result);
            }
        }
        return result;
    }

    public Sorter(boolean isStrings, boolean isInts, boolean isAscSort, boolean isDescSort){
        this.isStrings = isStrings;
        this.isInts = isInts;
        this.isAscSort = isAscSort;
        this.isDescSort = isDescSort;
    }
}
