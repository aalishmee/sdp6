package fs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class StatisticsVisitorTest {

    @Test
    void countsAndDepth() {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode a = new DirectoryNode("a");
        DirectoryNode b = new DirectoryNode("b");
        a.add(new FileNode("f1", 1));
        b.add(new FileNode("f2", 1));
        a.add(b);
        root.add(a);

        StatisticsVisitor s = new StatisticsVisitor();
        root.accept(s);

        assertEquals(2, s.getFileCount());
        assertEquals(3, s.getDirectoryCount());
        assertEquals(3, s.getMaxDepth());
    }
}