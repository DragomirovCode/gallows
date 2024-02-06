import java.util.*;

public class Main {

    private static int attemptsLeft;
    private static Set<Character> guessedLetters;
    private static String secretWord;
    private static String wordDescription;
    private static int counter = 0;
    private static StringBuilder encryptedWord;

    public static void main(String[] args) {
        startGame();
    }

    public static String[] wordStorage(){
        // Создаем HashMap для хранения слов и их описаний
        HashMap<String, String> wordDescriptionMap = new HashMap<>();
        // Заполняем HashMap словами и их описаниями
        wordDescriptionMap.put("одино", "Описание слова 1");
        wordDescriptionMap.put("двад", "Описание слова 2");
        wordDescriptionMap.put("трит", "Описание слова 3");
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

    public static HashMap<Character, ArrayList<Integer>> wordConceal(){
        HashMap<Character, ArrayList<Integer>> letterPositions = new HashMap<>();
        for(int i = 0; i < secretWord.length(); i++){
            char letter = secretWord.charAt(i);
            if(letterPositions.containsKey(letter)){
                letterPositions.get(letter).add(i);
            } else {
                ArrayList<Integer> positions = new ArrayList<>();
                positions.add(i);
                letterPositions.put(letter, positions);
            }
        }
        return letterPositions;
    }

    public static String revealWord(String encryptedWord, char character, int index){
        char[] chars = encryptedWord.toCharArray();
        chars[index] = character;
        return new String(chars);
    }

    private static void initializeGame() {
        String[] wordAndDescription = wordStorage();
        secretWord = wordAndDescription[0]; // само слово
        wordDescription = wordAndDescription[1]; // описание слова
        encryptedWord = wordEncryption(); // шифр
        attemptsLeft = 5; // Установите желаемое количество попыток
        guessedLetters = new HashSet<>(); // хранилище для букв, которые были введены пользователем
        counter = 0; // сбрасываем счетчик
    }

    public static StringBuilder wordEncryption(){
        StringBuilder encryptedWord = new StringBuilder();
        for (int i = 0; i < secretWord.length(); i++) {
            encryptedWord.append("*");
        }
        return encryptedWord;
    }

    public static void startGame(){
        initializeGame();
        System.out.println("Добро пожаловать в игру 'Виселица'!'");
        Scanner tryingToGuessTheWord = new Scanner(System.in);
        System.out.println(encryptedWord);
        while(attemptsLeft > 0){
            System.out.println("У вас есть " + attemptsLeft + " попыток отгадать слово");
            System.out.println(wordDescription);
            char guess = tryingToGuessTheWord.next().charAt(0);
            if(!Character.isLetter(guess)){
                System.out.println("Пожалуйста, введите букву");
                continue;
            }
            if(guessedLetters.contains(guess)){
                System.out.println("Вы уже вводили эту букву, попробуйте другую");
                continue;
            }else{
                guessedLetters.add(guess);
            }
            if (wordConceal().containsKey(guess)){
                System.out.println("буква есть");
                for (int i = 0; i < secretWord.length(); i++) {
                    if (secretWord.charAt(i) == guess) {
                        encryptedWord = new StringBuilder(revealWord(encryptedWord.toString(), guess, i));
                    }
                }
                System.out.println(encryptedWord);
            }else{
                System.out.println("буквы нет");
                attemptsLeft--;
            }
            if(isWordGuessed()){
                System.out.println("Поздравляем! Вы отгадали слово: " + secretWord);
                break;
            }
            if(attemptsLeft == 0){
                System.out.println("Извините, вы проиграли. Загаданное слово было: " + secretWord);
            }
        }
    }
    public static boolean isWordGuessed(){
        for(int i = 0; i < secretWord.length(); i++){
            if(!guessedLetters.contains(secretWord.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
