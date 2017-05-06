package AVL.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

/*
 * Created by ahmed on 5/1/17.
 */
public class Main {
    private static final Scanner input = new Scanner(System.in);
    private static final String relativePath = System.getProperty("user.dir");
    private static final String dictionaryFile = relativePath + "/src/AVL/files/dictionary.txt";
    private static final String deletionsFile = relativePath + "/src/AVL/files/deletions.txt";
    private static final String queriesFile = relativePath + "/src/AVL/files/queries.txt";
    private static Dictionary dictionary = new Dictionary();
    private static ArrayList<String> words = new ArrayList<>();

    public static void main(String[] args) {
        boolean run = true;
        dictionary.load(new File(dictionaryFile));
        out("Type ? for help");
        while (run) {
            switch (input.nextLine()) {
                case "?":
                    showMenu();
                    break;
                case "1":
                    printSize();
                    break;
                case "2":
                    insert(input);
                    break;
                case "3":
                    search(input);
                    break;
                case "4":
                    remove(input);
                    break;
                case "5":
                    batch_LookUp();
                    break;
                case "6":
                    batch_Deletion();
                    break;
                case "7":
                    treeHeight();
                    break;
                case "8":
                    run = false;
                    out("####  TERMINATED  ####");
                    break;
                default:
                    out("WRONG ENTRY !, RETRY");
            }
        }
    }

    private static void treeHeight() {
        out("Tree Height = " + dictionary.height());
    }

    private static void showMenu() {
        out("[1] Print Dictionary Size");
        out("[2] Insert new word");
        out("[3] Search for a word");
        out("[4] Remove a word");
        out("[5] Batch Look-Up");
        out("[6] Batch Deletion");
        out("[7] Tree Height");
        out("[8] Exit");
    }

    private static void printSize() {
        out("Dictionary size is: " + dictionary.size());
    }

    private static void insert(Scanner in) {
        out(">>> ");
        String word = in.nextLine();
        if (!dictionary.insert(word))
            out("ERROR: Word already in the dictionary!");
    }

    private static void search(Scanner in) {
        out(">>> ");
        if (dictionary.contains(in.nextLine()))
            out("YES, Word Exists");
        else
            out("NO, Word does NOT exist");
    }

    private static void remove(Scanner in) {
        out(">>> ");
        if (dictionary.remove(in.nextLine()))
            out("Word Removed");
        else
            out("ERROR: NO such word found!");
    }

    private static void batch_LookUp() {
        loadFile(queriesFile);
        int totalFound = 0;
        for (String word : words) {
            if (dictionary.contains(word)) {
                totalFound++;
                out("YES, " + word + " Found !");
            } else
                out("NO, " + word + " was NOT found !");
            treeHeight();
        }
        out("Total Number of Found words: " + totalFound);
    }

    private static void batch_Deletion() {
        loadFile(deletionsFile);
        for (String word : words) {
            if (dictionary.remove(word)) {
                out(word + " REMOVED !");
            } else
                out(word + " was NOT found");
            treeHeight();
        }
    }

    private static void loadFile(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String word;
            while ((word = bufferedReader.readLine()) != null) {
                words.add(word);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void out(String s) {
        System.out.println(s);
    }
}
