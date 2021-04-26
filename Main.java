import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    private static Scanner keyboard = new Scanner(System.in);
    private static Boolean isExited = false;

    public static void findASlangByWord(Dictionary dict) {
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
    }

    public static void findASlangByMeaning(Dictionary dict) {
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
    }

    public static void showHistory(Dictionary dict) {
        System.out.println("History of searched words: ");
        dict.printHistory();
    }

    public static void addNewSlangWord(Dictionary dict) {
        System.out.println("Enter the word: ");
        String keyword = keyboard.next();
        System.out.println("Enter the word's meaning: ");
        String meaning = keyboard.next();
        if (dict.checkSlangExistence(keyword) == true) {
            System.out.println("This word already exists, do you want to (o)verwrite or (d)upplicate it (enter o/d): ");
            String option = keyboard.next();
            switch (option) {
                case "o":
                    dict.editSlang(keyword, meaning);
                    break;
                case "d":
                    dict.addSlang(keyword, meaning);
                    break;
                default:
                    System.out.println("Invalid option, aborting!!");
                    break;
            }
        } else {
            dict.addSlang(keyword, meaning);
        }
    }

    public static void editASlangWord(Dictionary dict) {
        System.out.println("Enter the word: ");
        String keyword = keyboard.next();
        if (dict.checkSlangExistence(keyword) == true) {
            System.out.println("Enter the word's meaning: ");
            String meaning = keyboard.next();
            dict.editSlang(keyword, meaning);
        } else {
            System.out.println("There is no such word in the library.");
        }
    }

    public static void removeASlang(Dictionary dict) {
        System.out.println("Enter the word: ");
        String keyword = keyboard.next();
        if (dict.checkSlangExistence(keyword) == true) {
            dict.removeSlang(keyword);
        } else {
            System.out.println("There is no such word in the library.");
        }
    }

    public static void selectFeature(Dictionary dict) {
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
        int feature = keyboard.nextInt();
        switch (feature) {
            case 1:
                findASlangByWord(dict);
                break;
            case 2:
                findASlangByMeaning(dict);
                break;
            case 3:
                showHistory(dict);
                break;
            case 4:
                addNewSlangWord(dict);
                break;
            case 5:
                editASlangWord(dict);
                break;
            case 6:
                removeASlang(dict);
                break;
            case 11:
                isExited = true;
                break;
            default:
                System.out.println("Invalid input, please try again!");
                break;
        }
        System.out.println("---------------------------------");
    }

    public static void main(String[] args) throws IOException {
        Dictionary dict = new Dictionary();
        dict.initializeDictionary();
        dict.initializeHistory();
        while (isExited == false) {
            selectFeature(dict);
        }
        dict.saveHistory();
        keyboard.close();
    }
}