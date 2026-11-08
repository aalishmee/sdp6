package fs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TotalSizeVisitorTest {

    @Test
    void accumulatesAcrossNesting() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode a = new DirectoryNode("a");
        DirectoryNode b = new DirectoryNode("b");
        a.add(new FileNode("f1", 10));
        b.add(new FileNode("f2", 20));
        b.add(new FileNode("f3", 30));
        a.add(b);
        root.add(a);
        root.add(new FileNode("f4", 40));

        TotalSizeVisitor v = new TotalSizeVisitor();
        root.accept(v);
        assertEquals(100, v.getTotal());
    }

    @Test
    void repeatRunDoesNotDoubleCount() {
        DirectoryNode root = new DirectoryNode("r");
        root.add(new FileNode("x", 7));
        TotalSizeVisitor v = new TotalSizeVisitor();
        root.accept(v);
        root.accept(v);
        v.reset();
        root.accept(v);
        assertEquals(7, v.getTotal());
    }
}