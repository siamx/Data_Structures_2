package AVL.implementation;

import org.junit.Test;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

/*
 * Created by ahmed on 5/1/17.
 */
public class AVL_Tree_Test {
    private static final Random RANDOM = new Random();
    private static final int TEST_SIZE = 1000;
    private static final int MAX_INT = 1000;


    @Test
    public void testAVLTree() {
        for (int j = 0; j < TEST_SIZE; j++) {
            IAVL_Tree<Integer> actual = new AVL_Tree<>();
            Set<Integer> expected = new TreeSet<>();
            for (int i = 0; i < MAX_INT; i++) {
                int temp = RANDOM.nextInt();
                if (RANDOM.nextBoolean()) {
                    expected.add(temp);
                    actual.insert(temp);
                }
                temp = RANDOM.nextInt();
                if (RANDOM.nextBoolean())
                    assertEquals(expected.remove(temp), actual.remove(temp));
                if (RANDOM.nextBoolean())
                    assertEquals(expected.contains(temp), actual.search(temp));
            }
        }
    }

}
