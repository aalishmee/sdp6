package fs;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class SearchVisitorTest {

    private DirectoryNode tree() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode a = new DirectoryNode("a");
        DirectoryNode b = new DirectoryNode("b");
        a.add(new FileNode("keep.txt", 100));
        a.add(new FileNode("skip.log", 100));
        b.add(new FileNode("keep.txt", 5));
        b.add(new FileNode("also.txt", 500));
        root.add(a);
        root.add(b);
        return root;
    }

    @Test
    void combinesNameAndSizeFilter() {
        SearchVisitor v = new SearchVisitor(n -> n.endsWith(".txt"), 50);
        tree().accept(v);
        assertEquals(List.of("a/keep.txt", "b/also.txt"), v.getMatches());
    }

    @Test
    void deterministicSortedRootRelativePaths() {
        SearchVisitor v = new SearchVisitor(n -> true, -1);
        tree().accept(v);
        List<String> m = v.getMatches();
        assertEquals(List.of("a/keep.txt", "a/skip.log", "b/also.txt", "b/keep.txt"), m);
        assertEquals(m.stream().sorted().toList(), m);
    }

    @Test
    void emptyTreeAndNoMatch() {
        DirectoryNode empty = new DirectoryNode("e");
        SearchVisitor v = new SearchVisitor(n -> true, 0);
        empty.accept(v);
        assertTrue(v.getMatches().isEmpty());

        SearchVisitor v2 = new SearchVisitor(n -> n.equals("nope"), 0);
        tree().accept(v2);
        assertTrue(v2.getMatches().isEmpty());
    }
}