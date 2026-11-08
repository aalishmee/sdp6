package fs;

public final class TotalSizeVisitor implements Visitor {
    private long total;

    @Override public void visit(FileNode file) {
        total += file.getSize();
    }

    @Override public void visit(DirectoryNode dir) {
        for (Element child : dir.getChildren()) {
            child.accept(this);
        }
    }

    public long getTotal() { return total; }

    public void reset() { total = 0; }
}