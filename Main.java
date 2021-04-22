import java.util.List;
import java.util.Scanner;

import dictionary.Dictionary;
import dictionary.interfaces.DictionaryHelper;
import dictionary.TrieDictionaryHelper;

public class Main {
    private static void printMessage(Dictionary.Operation operation, boolean isSuccess) {
        System.out.printf("<<<<< %s operation, success = %s >>>>>\n", operation, isSuccess);
    }

    public static void printMessage(Dictionary.Operation operation, List<String> output) {
        System.out.printf("<<<<< %s operation, here is the list:-\n", operation);
        for (String s : output) {
            System.out.printf(" ----> %s\n", s);
        }
    }

    public static void main(String[] args) {
        DictionaryHelper dictionaryHelper = new TrieDictionaryHelper();
        Dictionary dictionary = new Dictionary(dictionaryHelper);
        Scanner reader = new Scanner(System.in);
        boolean breakOutOfLoop = false;
        System.out.println("Welcome to the Dictionary Application");
        for(Dictionary.Operation operation : Dictionary.Operation.values()) {
            System.out.println(operation);
        }

        while(!breakOutOfLoop) {
            System.out.println("===== Input the operation you want to perform! =====");
            try {
                Dictionary.Operation requestedOperation = Dictionary.Operation.parse(reader.next());
                String word = "";
                switch (requestedOperation) {
                case INSERT:
                    System.out.println("=== Input the desired word ===");
                    word = reader.next();
                    printMessage(requestedOperation, dictionary.insert(word));
                    break;
                case SEARCH:
                    System.out.println("=== Input the desired word ===");
                    word = reader.next();
                    printMessage(requestedOperation, dictionary.search(word));
                    break;
                case DELETE:
                    System.out.println("=== Input the desired word ===");
                    word = reader.next();
                    printMessage(requestedOperation, dictionary.delete(word));
                    break;
                case AUTOSUGGEST:
                    System.out.println("=== Input the desired word ===");
                    word = reader.next();
                    List<String> output = dictionary.autoSuggest(word);
                    printMessage(requestedOperation, output);
                    break;
                
                }


            } catch (Exception e) {
                breakOutOfLoop = true;
                System.out.println("Exitting");
            }
        }

        reader.close();
    }
}
