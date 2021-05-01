import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.List;
import java.util.ArrayList;

public class Dictionary {
    HashMap<String, String> dict = new HashMap<String, String>();
    HashMap<String, String> history = new HashMap<String, String>();
    String dictPath = "indexed.txt";
    String defaultDictPath = "slang.txt";
    String historyPath = "history.txt";

    public HashMap<String, String> loadHashMapFromFile(String path) throws IOException {
        HashMap<String, String> result = new HashMap<String, String>();
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("`", 2);
            if (parts.length >= 2) {
                String key = parts[0];
                String value = parts[1];
                result.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }
        reader.close();
        return result;
    }

    public void saveHashMapToFile(String path, HashMap<String, String> hashMap) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, false);
        BufferedWriter writer = new BufferedWriter(fw);
        for (String key : hashMap.keySet()) {
            writer.write(key + '`' + hashMap.get(key));
            writer.newLine();
        }
        writer.close();
    }

    public void initializeDictionary() throws IOException {
        dict = loadHashMapFromFile(dictPath);
    }

    public void initializeHistory() throws IOException {
        history = loadHashMapFromFile(historyPath);
    }

    public void saveHistory() throws IOException {
        saveHashMapToFile(historyPath, history);
    }

    public void saveDictState() throws IOException {
        saveHashMapToFile(dictPath, dict);
    }

    public void resetDictionary() throws IOException {
        dict = loadHashMapFromFile(defaultDictPath);
        saveHashMapToFile(dictPath, dict);
    }

    public void addToHistory(String key, String value) {
        history.putIfAbsent(key, value);
    }

    public void printHashMap(HashMap<String, String> hashMap) {
        for (String key : hashMap.keySet()) {
            System.out.println("Word:  " + key + ", Meaning: " + hashMap.get(key));
        }
    }

    public void printDictionary() {
        printHashMap(dict);
    }

    public void printHistory() {
        printHashMap(history);
    }

    public String getWordMeaning(String key) {
        return dict.get(key);
    }

    public HashMap<String, String> getWordsByKeyword(String keyword) {
        keyword.toLowerCase();
        HashMap<String, String> result = new HashMap<String, String>();
        for (String key : dict.keySet()) {
            String currentValue = dict.get(key);
            if (currentValue.toLowerCase().contains(keyword)) {
                result.put(key, currentValue);
            }
        }
        return result;
    }

    public Boolean checkSlangExistence(String word) {
        return dict.containsKey(word);
    }

    public void addSlang(String word, String meaning) {
        dict.put(word, meaning);
        System.out.println("Word has been added!!");
    }

    public void editSlang(String word, String meaning) {
        dict.replace(word, meaning);
        System.out.println("Word has been modified!!");
    }

    public void removeSlang(String word) {
        dict.remove(word);
        System.out.println("Word has been removed!!");
    }

    public String getRandomWord() {
        List<String> valuesList = new ArrayList<String>(dict.keySet());
        int randomIndex = new Random().nextInt(valuesList.size());
        String randomValue = valuesList.get(randomIndex);
        return randomValue;
    }

    public HashMap<String, String> getRandomWords(Integer count) {
        HashMap<String, String> result = new HashMap<String, String>();
        while (result.size() != count) {
            String randomWord = getRandomWord();
            result.put(randomWord, getWordMeaning(randomWord));
        }
        return result;
    }
}
