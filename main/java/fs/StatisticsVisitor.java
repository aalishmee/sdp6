package fs;

public final class StatisticsVisitor implements Visitor {
    private int fileCount;
    private int dirCount;
    private int maxDepth;
    private int currentDepth;

    @Override
    public void visit(FileNode file) {
        fileCount++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;
    }

    @Override
    public void visit(DirectoryNode dir) {
        dirCount++;
        if (currentDepth > maxDepth) maxDepth = currentDepth;

        currentDepth++;
        for (Element child : dir.getChildren()) {
            child.accept(this);
        }
        currentDepth--;
    }

    public int getFileCount() { return fileCount; }
    public int getDirectoryCount() { return dirCount; }
    public int getMaxDepth() { return maxDepth; }

    public void reset() {
        fileCount = dirCount = maxDepth = currentDepth = 0;
    }
}