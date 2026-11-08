package fs;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class TreeIntegrityTest {

    @Test
    void negativeSizeRejected() {
        assertThrows(IllegalArgumentException.class, () -> new FileNode("f", -1));
    }

    @Test
    void duplicateSiblingRejected() {
        DirectoryNode d = new DirectoryNode("d");
        d.add(new FileNode("x", 1));
        assertThrows(IllegalArgumentException.class, () -> d.add(new FileNode("x", 2)));
    }

    @Test
    void validNestingStillWorks() {
        DirectoryNode d = new DirectoryNode("d");
        DirectoryNode sub = new DirectoryNode("sub");
        sub.add(new FileNode("x", 1));
        assertDoesNotThrow(() -> d.add(sub));
        assertEquals(1, d.getChildren().size());
    }
}