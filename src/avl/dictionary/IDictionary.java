package avl.dictionary;

import java.io.File;

/*
 * Created by ahmed on 5/1/17.
 */
public interface IDictionary {
    void load(File file);

    boolean insert(String word);

    boolean contains(String word);

    boolean remove(String word);

    Integer height();

    Integer size();
}
