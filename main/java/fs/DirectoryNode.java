package fs;

import java.util.*;

public final class DirectoryNode implements Element {
    private final String name;
    private final List<Element> children = new ArrayList<>();

    public DirectoryNode(String name) {
        if (name == null || name.isBlank())
            throw new IllegalArgumentException("Directory name must not be blank");
        this.name = name;
    }

    @Override public String getName() { return name; }

    public List<Element> getChildren() {
        return Collections.unmodifiableList(children);
    }

    public void add(Element child) {
        if (child == null) throw new IllegalArgumentException("child == null");
        if (child == this) throw new IllegalArgumentException("cycle: self-add");
        if (children.stream().anyMatch(c -> c.getName().equals(child.getName())))
            throw new IllegalArgumentException("duplicate sibling name: " + child.getName());
        children.add(child);
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}