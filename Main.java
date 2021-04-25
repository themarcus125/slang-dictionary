import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {

  public static void findASlangByWord(Dictionary dict) {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter the word you wish to find: ");
    String keyword = keyboard.next();
    String result = dict.getWordByKey(keyword);
    if (result != null) {
      System.out.println("The word " + keyword + " means " + result);
      dict.addToHistory(keyword, result);
    }
    if (result == null) {
      System.out.println("There is no such word in dictionary.");
    }
    keyboard.close();
  }

  public static void findASlangByMeaning(Dictionary dict) {
    Scanner keyboard = new Scanner(System.in);
    System.out.println("Enter the keyword: ");
    String keyword = keyboard.next();
    HashMap<String, String> result = dict.getWordsByKeyword(keyword);
    if (result.size() == 0) {
      System.out.println("No slang has that keyword in its meaning!!");
    }
    if (result.size() > 0) {
      System.out.println("These slang have that keyword in their meanings:");
      for (String key : result.keySet()) {
        String meaning = result.get(key);
        System.out.println("Word:  " + key + ", Meaning: " + meaning);
        dict.addToHistory(key, meaning);
      }
    }
    keyboard.close();
  }

  public static void selectFeature() {
    System.out.println("Slang dictionary.");
    System.out.println("Please select a feature that you wish to continue:");
    System.out.println("1. Find a slang by word");
    System.out.println("2. Find a slang by definition keyword");
    System.out.println("3. Show slang word history");
    System.out.println("4. Add a new slang word");
    System.out.println("5. Edit a slang word");
    System.out.println("6. Delete a slang word");
    System.out.println("7. Restore default slang word list");
    System.out.println("8. I'm feeling lucky (Find a random slang word)");
    System.out.println("9. Game: Guess the slang's meaning");
    System.out.println("10. Game: Guess the slang word");
    System.out.println("11. Exit");
    Scanner keyboard = new Scanner(System.in);
    int feature = keyboard.nextInt();
    switch (feature) {
    case 1:
    default:
      System.out.println("Invalid input, please try again!");
      break;
    }
    keyboard.close();
  }

  public static void main(String[] args) throws IOException {
    Dictionary dict = new Dictionary();
    dict.initializeDictionary();
    dict.initializeHistory();
    findASlangByWord(dict);
    dict.saveHistory();
  }
}