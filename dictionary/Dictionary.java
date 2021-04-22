package dictionary;
import dictionary.interfaces.DictionaryHelper;
import trie.Trie;
import java.util.*;

public class Dictionary {
    private DictionaryHelper helper;
    private Trie trie = new Trie();
    public static enum Operation {
        INSERT, SEARCH, DELETE, AUTOSUGGEST;
        public static Operation parse(String str) {
            return valueOf(str.toUpperCase());
        }
    }

    public Dictionary(DictionaryHelper helper) {
        this.helper = helper;
    }

    public boolean insert(String word) {
        return helper.insert(word);
    }

    public boolean search(String word) {
        return helper.search(word);
    }

    public boolean delete(String word) {
        return helper.delete(word);
    }

    public List<String> autoSuggest(String prefix) {
        return helper.autoSuggest(prefix);
    }

}
