import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class DirectoriesSorter {

    /**
     * Метод для сортировки списка директорий.
     */
    public static List<String> sortDirs(List<String> list) {
        List<String> oneLevelDirs = new ArrayList<>();
        List<String> multiLevelDirs = new ArrayList<>();

        for (String dir : list) {
            String[] parts = dir.split("/");
            if (parts.length == 2) {
                oneLevelDirs.add(dir);
            } else {
                multiLevelDirs.add(dir);
            }
        }

        multiLevelDirs.sort(new TrueListComparator());
        Map<String, List<String>> dirsMap = createDirsMap(multiLevelDirs);

        List<String> result = new ArrayList<>();
        for (String key : dirsMap.keySet()) {
            List<String> subDirs = dirsMap.get(key);
            result.addAll(recursiveSortDirs(subDirs));
        }

        oneLevelDirs.sort(new TrueListComparator());
        result.addAll(oneLevelDirs);
        return result;
    }

    /**
     * Рекурсивный метод для сортировки списка директорий, любого уровня вложенности.
     * @param dirs Список директорий у которых единая первая нода.
     * @return
     */
    private static List<String> recursiveSortDirs(List<String> dirs) {
        // Разделяем директории на одноузловые и многоузловые (1 слой)
        List<String> oneLevelDirs = new ArrayList<>();
        List<String> multiLevelDirs = new ArrayList<>();
        for (String dir : dirs) {
            String[] parts = dir.split("/");
            if (parts.length == 2) {
                oneLevelDirs.add(dir);
            } else {
                multiLevelDirs.add(dir);
            }
        }

        if (multiLevelDirs.size() > 0) {
            // Сортируем
            multiLevelDirs.sort(new TrueListComparator());

            // Сохраняем имя первой ноды
            String firstNodeName = multiLevelDirs.get(0).split("/")[1];

            // Обрезаем первую ноду у многоузловых директорий
            List<String> cutMultiLevelDirs = new ArrayList<>();
            for (String directory : multiLevelDirs) {
                cutMultiLevelDirs.add(directory.replaceFirst("/" + firstNodeName, ""));
            }

            // Разделяем директории на одноузловые и многоузловые (2 слой)
            List<String> oneLevelDirs2 = new ArrayList<>();
            List<String> multiLevelDirs2 = new ArrayList<>();
            for (String dir : cutMultiLevelDirs) {
                String[] parts = dir.split("/");
                if (parts.length == 2) {
                    oneLevelDirs2.add(dir);
                } else {
                    multiLevelDirs2.add(dir);
                }
            }

            // Создаем мапу, разбивая директории по первой ноде в отдельные списки
            Map<String, List<String>> directoryMap = createDirsMap(multiLevelDirs2);

            // Рекурсивно обрабатываем все списки из мапы и возвращаем единый список
            List<String> processedSubDirs = new ArrayList<>();
            for (String key : directoryMap.keySet()) {
                List<String> subDirs = directoryMap.get(key);
                // recursion
                processedSubDirs.addAll(recursiveSortDirs(subDirs));
            }

            // Добавляем к списку одноузловые директории (2 слой)
            oneLevelDirs2.sort(new TrueListComparator());
            processedSubDirs.addAll(oneLevelDirs2);

            // Добавляем к каждой директории первую ноду
            List<String> restoredProcessedSubDir = addFirstNodeToDirs(processedSubDirs, firstNodeName);

            // Добавляем к списку одноузловые директории (1 слой)
            restoredProcessedSubDir.addAll(oneLevelDirs);

            return restoredProcessedSubDir;
        } else {
            oneLevelDirs.sort(new TrueListComparator());
            return oneLevelDirs;
        }
    }

    /**
     * Метод для создании мапы на основе списка директорий.
     * Мапа нам нужна для того чтобы проводить дальнейшую сортировку секционированно и рекурсивно.
     * @param directories список директорий
     * @return key: firstNodeName , value: {firstNodeName/.../Path_1, ..., firstNodeName/.../Path_N}
     */
    private static Map<String, List<String>> createDirsMap(List<String> directories) {
        Map<String, List<String>> directoryMap = new LinkedHashMap<>();

        for (String directoryPath : directories) {
            String[] nodes = directoryPath.split("/");
            String firstNode = nodes[1];
            if (!directoryMap.containsKey(firstNode)) {
                directoryMap.put(firstNode, new ArrayList<>());
            }
            directoryMap.get(firstNode).add(directoryPath);
        }
        return directoryMap;
    }

    private static List<String> addFirstNodeToDirs(List<String> list, String firstNodes) {
        return list.stream().
                map(x -> "/" + firstNodes + x).
                collect(Collectors.toList());
    }

}
