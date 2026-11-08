package fs;

import java.util.*;
import java.util.function.Predicate;

public final class SearchVisitor implements Visitor {
    private final Predicate<String> nameFilter;
    private final long minSizeExclusive;
    private final List<String> matches = new ArrayList<>();
    private String currentPath = "";

    public SearchVisitor(Predicate<String> nameFilter, long minSizeExclusive) {
        this.nameFilter = Objects.requireNonNull(nameFilter);
        this.minSizeExclusive = minSizeExclusive;
    }

    @Override
    public void visit(FileNode file) {
        if (nameFilter.test(file.getName()) && file.getSize() > minSizeExclusive) {
            String path = currentPath.isEmpty() ? file.getName() : currentPath + "/" + file.getName();
            matches.add(path);
        }
    }

    @Override
    public void visit(DirectoryNode dir) {
        String previousPath = currentPath;

        if (previousPath.isEmpty()) {
            currentPath = "";
        } else {
            currentPath = previousPath + "/" + dir.getName();
        }
        if (previousPath.isEmpty() && !dir.getName().equals("root")) {
            currentPath = dir.getName();
        } else if (!previousPath.isEmpty()) {
            currentPath = previousPath + "/" + dir.getName();
        }

        for (Element child : dir.getChildren()) {
            child.accept(this);
        }

        currentPath = previousPath;
    }

    public List<String> getMatches() {
        List<String> copy = new ArrayList<>(matches);
        Collections.sort(copy);
        return copy;
    }

    public void reset() {
        matches.clear();
        currentPath = "";
    }
}