package dictionary;
import dictionary.interfaces.DictionaryHelper;
import trie.Trie;
import java.util.*;

public class TrieDictionaryHelper implements DictionaryHelper {
    private Trie dataTrie = new Trie();

    @Override
    public boolean insert(String word) {
        return dataTrie.insert(word);
    }

    @Override
    public boolean search(String word) {
        return dataTrie.search(word);
    }

    @Override
    public boolean delete(String word) {
        return dataTrie.delete(word);
    }

    @Override
    public List<String> autoSuggest(String prefix) {
        return dataTrie.autoSuggest(prefix);
    }
}
