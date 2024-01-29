import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        String[] wordAndDescription = wordStorage();

        System.out.println("Случайное слово: " + wordAndDescription[0]);
        System.out.println("Описание: " + wordAndDescription[1]);
    }

    public static String[] wordStorage(){
        // Создаем HashMap для хранения слов и их описаний
        HashMap<String, String> wordDescriptionMap = new HashMap<>();
        // Заполняем HashMap словами и их описаниями
        wordDescriptionMap.put("слово1", "Описание слова 1");
        wordDescriptionMap.put("слово2", "Описание слова 2");
        wordDescriptionMap.put("слово3", "Описание слова 3");
        return getRandomWord(wordDescriptionMap);
    }

    private static String[] getRandomWord(HashMap<String, String> wordDescriptionMap) {
        // Получаем список ключей (слов) из Map
        ArrayList<String> wordList = new ArrayList<>(wordDescriptionMap.keySet());
        // Получаем случайный индекс
        Random random = new Random();
        int index = random.nextInt(wordList.size());
        String randomWord = wordList.get(index);
        String description = wordDescriptionMap.get(randomWord);

        // Создаем массив, содержащий случайное слово и его описание
        String[] wordAndDescription = {randomWord, description};
        return wordAndDescription;
    }

    public static void attemptTracker(){}

    public static void riddleContainer(){}

}
