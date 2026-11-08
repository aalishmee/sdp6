package fs;

public final class Demo {
    public static void main(String[] args) {
        DirectoryNode root = new DirectoryNode("root");
        DirectoryNode docs = new DirectoryNode("docs");
        DirectoryNode img  = new DirectoryNode("img");

        docs.add(new FileNode("report.pdf", 1_200));
        docs.add(new FileNode("notes.txt", 300));
        img.add(new FileNode("photo.jpg", 5_000));
        img.add(new FileNode("thumb.jpg", 120));
        root.add(docs);
        root.add(img);
        root.add(new FileNode("README.md", 50));

        TotalSizeVisitor size = new TotalSizeVisitor();
        root.accept(size);
        System.out.println("Total bytes: " + size.getTotal());          // 6670

        SearchVisitor search = new SearchVisitor(
                n -> n.endsWith(".jpg"), 100);
        root.accept(search);
        System.out.println("JPG > 100 bytes: " + search.getMatches());

        StatisticsVisitor stats = new StatisticsVisitor();
        root.accept(stats);
        System.out.println("Files=" + stats.getFileCount()
                + " Dirs=" + stats.getDirectoryCount()
                + " MaxDepth=" + stats.getMaxDepth());
    }
}