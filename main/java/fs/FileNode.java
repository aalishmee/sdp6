package fs;

public final class FileNode implements Element {
    private final String name;
    private final long size;

    public FileNode(String name, long size) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("File name must not be blank");
        if (size < 0)
            throw new IllegalArgumentException("File size must be non-negative: " + size);
        this.name = name;
        this.size = size;
    }

    @Override public String getName() { return name; }
    public long getSize() { return size; }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}