package avl.dictionary;

import avl.implementation.AVL_Tree;
import avl.implementation.IAVL_Tree;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * Created by ahmed on 5/1/17.
 */
public class Dictionary implements IDictionary {
    private IAVL_Tree<String> tree;
    private Integer size;

    Dictionary() {
        tree = new AVL_Tree<>();
        size = 0;
    }

    @Override
    public void load(File file) {
        try {
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String word;
            while ((word = bufferedReader.readLine()) != null) {
                insert(word);
            }
            bufferedReader.close();
            fileReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean insert(String word) {
        if (tree.search(word)) return false;
        size++;
        tree.insert(word);
        return true;
    }

    @Override
    public boolean contains(String key) {
        return tree.search(key);
    }

    @Override
    public boolean remove(String key) {
        if (!tree.search(key)) return false;
        size--;
        return tree.remove(key);
    }

    @Override
    public Integer height() {
        return tree.getHeight();
    }

    @Override
    public Integer size() {
        return size;
    }
}
