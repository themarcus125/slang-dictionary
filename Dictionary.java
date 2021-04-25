import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Dictionary {
    HashMap<String, String> dict = new HashMap<String, String>();

    public void initializeDictionary() throws IOException {
        String dictPath = "test.txt";
        String line;
        BufferedReader reader = new BufferedReader(new FileReader(dictPath));
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
