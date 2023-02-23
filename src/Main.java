import java.util.List;


public class Main {
    public static void main(String[] args) {
        List<String> list = Example.list;

        List<String> result = DirectoriesSorter.sortDirs(list);

        for (String s : result) {
            System.out.println(s);
        }
    }
}
