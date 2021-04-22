package trie;
import java.util.*;

public class TrieNode {

    private int numPrefixes = 0;  //Frequency
    private boolean isEndOfWord = false;  //Marking the word end
    HashMap<Character, TrieNode> children = new HashMap<>();  
    //HashMap is used for keeping an account of children of every node. For ex:
    //For the word "hello", h is the root, e is the child to h, l is the child to e
    //l is the child to l and o is the child to o. 
    //This is maily used for searching in the tree


    //Insert
    boolean insert(String word, int index) {
        //Base case
        if(index == word.length()) {   
            if(isEndOfWord) return false;  //Word already Present
            else {
                numPrefixes++;
                isEndOfWord=true;
                return true;
            }
        }
        if(children.get(word.charAt(index))==null) {
            children.put(word.charAt(index), new TrieNode());
        }
        if(children.get(word.charAt(index)).insert(word, index+1)) {  //If this returns true only the increase the freq
            numPrefixes++;                                            //Because freq must not be increased while searching
            return true;                                              //Rather, it must be increased when the word is found
        }
        return false;
    }

    //Searching
    boolean search(String word, int index) {
        //base case
        if(index==word.length())
          return isEndOfWord;     //Returns true only when endOfWord is encountered

        if(children.get(word.charAt(index))==null) {
            return false;
        }
        return children.get(word.charAt(index)).search(word, index+1);
    }

    //Delete
    //Here we simply search for the word first and perform the delete operation consecutively
    boolean delete(String word, int index) {
        if(index==word.length()) {
            if(isEndOfWord) {    //It has to be the endOfWord to be deleted. Similar to that in search function
                isEndOfWord=false;
                numPrefixes--;
                return true;
            }
            return false;
        }

        if(children.get(word.charAt(index))==null) {   //Some character from the word is not found. That means it's not the word itself!
            return false;
        }

        if( children.get(word.charAt(index)).delete(word, index+1) ) {  //This is actually searching via delete function
            numPrefixes--;
            if(numPrefixes==0)
              children.remove(word.charAt(index));  //If the frequency is 0 then no other word is using the character. So it can be deleted
            return true;
        }
        return false;
    }


    //For Autosuggest and Autocorrect
    //This returns the prefix of a word. For ex: it will return BA for the words BALL, BALD, BAT, etc 
    TrieNode getPrefixNode(String word, int index) {   
        
        if(index==word.length()) {
              return this;
        }
        if(children.get(word.charAt(index))==null) {   
            return null;
        }
        return children.get(word.charAt(index)).getPrefixNode(word, index+1);
    }

    List<String> getAllDescendantWords(String word) {
        List<String> descendants = new ArrayList<>();
        if(isEndOfWord) {
            descendants.add(word);
        }
        for(char c : children.keySet()) {
            String newPrefix = word + c;
            descendants.addAll(children.get(c).getAllDescendantWords(newPrefix));
        }
        return descendants;
    }

}