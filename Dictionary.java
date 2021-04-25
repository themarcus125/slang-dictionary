import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Dictionary {
    HashMap<String, String> dict = new HashMap<String, String>();
    HashMap<String, String> history = new HashMap<String, String>();

    public void initializeDictionary() throws IOException {
        String path = "test.txt";
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("`", 2);
            if (parts.length >= 2) {
                String key = parts[0];
                String value = parts[1];
                dict.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }
        reader.close();
    }

    public void initializeHistory() throws IOException {
        String path = "history.txt";
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(path));
        while ((line = reader.readLine()) != null) {
            String[] parts = line.split("`", 2);
            if (parts.length >= 2) {
                String key = parts[0];
                String value = parts[1];
                history.put(key, value);
            } else {
                System.out.println("ignoring line: " + line);
            }
        }
        reader.close();
    }

    public void saveHistory() throws IOException {
        String path = "history.txt";
        String line;
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileWriter fw = new FileWriter(file, false);
        BufferedWriter writer = new BufferedWriter(fw);
        for (String key : history.keySet()) {
            writer.write(key + '`' + history.get(key));
            writer.newLine();
        }
        writer.close();
    }

    public void addToHistory(String key, String value) {
        history.putIfAbsent(key, value);
    }

    public void printDictionary() {
        for (String key : dict.keySet()) {
            System.out.println("Word:  " + key + ", Meaning: " + dict.get(key));
        }
    }

    public String getWordByKey(String key) {
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
}
