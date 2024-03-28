package ru.naumen.collection.task3;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.ArrayList;
import java.util.AbstractMap;
import java.util.Objects;
import java.util.Arrays;

/**
 * <p>Написать консольное приложение, которое принимает на вход произвольный текстовый файл в формате txt.
 * Нужно собрать все встречающийся слова и посчитать для каждого из них количество раз, сколько слово встретилось.
 * Морфологию не учитываем.</p>
 * <p>Вывести на экран наиболее используемые (TOP) 10 слов и наименее используемые (LAST) 10 слов</p>
 * <p>Проверить работу на романе Льва Толстого “Война и мир”</p>
 *
 * @author vpyzhyanov
 * @since 19.10.2023
 *
 * <p>Было выбрано сочетание HashMap и PriorityQueue, для достижения лучшей сложности.
 * В начале мы заполняем словарь слов с их подсчетом, что дает нам сложность O(N).
 * Затем каждый из этих элементов добавляется в две PriorityQueue(различаются направлением сортировки),
 * что дает нам сложность O(N*logN). Потом мы можем извлечь отсортированные слова за O(1) для каждого top K слова.</p>
 * <p>Итого:  O(n*logN)</p>
 * @author A.Slivnay
 * @since 29.03.2023
 */
public class WarAndPeace {

    private static final Path WAR_AND_PEACE_FILE_PATH = Path.of("src/main/resources",
            "Лев_Толстой_Война_и_мир_Том_1,_2,_3,_4_(UTF-8).txt");

    public static void main(String[] args) {
        Path filePath;
        if (args.length == 0)
            filePath = WAR_AND_PEACE_FILE_PATH;
        else
            filePath = Paths.get(args[0]);

        var wordsCnt = new HashMap<String, Integer>();
        var downK = new PriorityQueue<Map.Entry<String, Integer>>(Comparator.comparingInt(Map.Entry::getValue));
        var topK = new PriorityQueue<Map.Entry<String, Integer>>((a, b) -> b.getValue() - a.getValue());
        var topkList = new ArrayList<String>();
        var downkList = new ArrayList<String>();

        new WordParser(filePath)
                .forEachWord(word -> {
                    wordsCnt.put(word, wordsCnt.getOrDefault(word, 0)+1);
                });

            for (var key: wordsCnt.keySet()){
                topK.add(new AbstractMap.SimpleEntry<>(key, wordsCnt.get(key)));
                downK.add(new AbstractMap.SimpleEntry<>(key, wordsCnt.get(key)));
            }

            for (int i =0; i< 10; i++){
                topkList.add(Objects.requireNonNull(topK.poll()).getKey());
                downkList.add(Objects.requireNonNull(downK.poll()).getKey());
            }
            System.out.println("Top K частых слов: " + Arrays.toString(topkList.toArray()));
            System.out.println("Top K редких слов: " +Arrays.toString(downkList.toArray()));
    }
}
