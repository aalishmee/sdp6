package fs;

import org.junit.jupiter.api.Test;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;

class DoubleDispatchTest {

    static class RecordingVisitor implements Visitor {
        final List<String> calls = new ArrayList<>();
        public void visit(FileNode f)  { calls.add("file:" + f.getName()); }
        public void visit(DirectoryNode d) { calls.add("dir:" + d.getName()); }
    }

    @Test
    void bothTypesHitTheirOverload() {
        FileNode f = new FileNode("a", 1);
        DirectoryNode d = new DirectoryNode("d");
        RecordingVisitor v = new RecordingVisitor();

        f.accept(v);
        d.accept(v);

        assertEquals(List.of("file:a", "dir:d"), v.calls);
    }
}