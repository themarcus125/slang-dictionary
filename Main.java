import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class Main {
    // Using scanner for reading string, newlines (useDelimiter)
    // https://stackoverflow.com/questions/39514730/how-to-take-input-as-string-with-spaces-in-java-using-scanner/39514943
    private static Scanner keyboard = new Scanner(System.in).useDelimiter("\n");;
    private static Boolean isExited = false;

    public static void findASlangByWord(Dictionary dict) {
        System.out.println("Enter the word you wish to find: ");
        String keyword = keyboard.next();
        String result = dict.getWordMeaning(keyword);
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
            System.out.println("Are you sure about deleting the word? (y/n) ");
            String result = keyboard.next();
            switch (result) {
                case "y":
                    dict.removeSlang(keyword);
                    break;
                case "n":
                    System.out.println("Aborting...");
                    break;
                default:
                    System.out.println("Wrong input, aborting...");
                    break;
            }

        } else {
            System.out.println("There is no such word in the library.");
        }
    }

    public static void resetDictionary(Dictionary dict) throws IOException {
        dict.resetDictionary();
    }

    public static void imFeelingLucky(Dictionary dict) {
        String randomWord = dict.getRandomWord();
        System.out.println("Your random word of the day is : " + randomWord);
        System.out.println("It means: " + dict.getWordMeaning(randomWord));
    }

    public static void guessTheSlangMeaning(Dictionary dict) {
        HashMap<String, String> wordsWithMeaning = dict.getRandomWords(4);
        List<String> words = new ArrayList<String>(wordsWithMeaning.keySet());
        System.out.println(words.size());
        String answer = words.get(0);
        Integer count = 1;
        Collections.shuffle(words);
        System.out.println("What does the word " + answer + " mean? ");
        for (String key : words) {
            System.out.println(count + ". " + wordsWithMeaning.get(key));
            count += 1;
        }
        System.out.println("Please enter your answer (1,2,3,...): ");
        Integer yourAnswer = keyboard.nextInt();
        if (words.get(yourAnswer - 1).equals(answer)) {
            System.out.println("You answered correctly!!");
        } else {
            System.out.println("You answered incorrectly, it actually means " + wordsWithMeaning.get(answer));
        }
    }

    public static void guessTheSlang(Dictionary dict) {
        HashMap<String, String> wordsWithMeaning = dict.getRandomWords(4);
        List<String> words = new ArrayList<String>(wordsWithMeaning.keySet());
        String answer = words.get(0);
        Integer count = 1;
        Collections.shuffle(words);
        System.out.println("Which word means: " + wordsWithMeaning.get(answer));
        for (String key : words) {
            System.out.println(count + ". " + key);
            count += 1;
        }
        System.out.println("Please enter your answer (1,2,3,...): ");
        Integer yourAnswer = keyboard.nextInt();
        if (words.get(yourAnswer - 1).equals(answer)) {
            System.out.println("You answered correctly!!");
        } else {
            System.out.println("You answered incorrectly, it actually is " + answer);
        }
    }

    public static void selectFeature(Dictionary dict) throws IOException {
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
        System.out.println("---------------------------------");
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
            case 7:
                resetDictionary(dict);
                break;
            case 8:
                imFeelingLucky(dict);
                break;
            case 9:
                guessTheSlangMeaning(dict);
                break;
            case 10:
                guessTheSlang(dict);
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