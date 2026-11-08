# Assignment 6 — Visitor Pattern
Name: Alish Medina
Group: SE-2538
Topic: In-Memory Hierarchical File System Visitor

## Role Map
Role | Class | Source
--- | --- | ---
Element Interface | `Element` | `src/main/java/fs/Element.java`
Concrete Element 1 | `FileNode` | `src/main/java/fs/FileNode.java`
Concrete Element 2 | `DirectoryNode` | `src/main/java/fs/DirectoryNode.java`
Visitor Interface | `Visitor` | `src/main/java/fs/Visitor.java`
Visitor Operation 1 | `TotalSizeVisitor` | `src/main/java/fs/TotalSizeVisitor.java`
Visitor Operation 2 | `SearchVisitor` | `src/main/java/fs/SearchVisitor.java`
Visitor Operation 3 | `StatisticsVisitor` | `src/main/java/fs/StatisticsVisitor.java`
Client | `Demo` | `src/main/java/fs/Demo.java`

## Key Locations
*   **Double Dispatch:** `FileNode.accept(Visitor)` and `DirectoryNode.accept(Visitor)` — delegates the call to the matching `visitor.visit(this)` overload.
*   **Traversal Ownership:** The traversal loop is implemented inside the visitors themselves (e.g., `TotalSizeVisitor.visit(DirectoryNode)`), keeping the element classes purely structural.
*   **Integrity Checks:** `DirectoryNode.add(Element)` — rejects duplicate sibling names and self-additions to prevent cycles. `FileNode` constructor rejects negative sizes.
*   **Deterministic Search:** `SearchVisitor.getMatches()` — sorts the resulting file paths lexicographically before returning them. Paths are strictly root-relative.
*   **T1–T8 Demo Check:** The JUnit tests in `src/test/java/fs/` verify all assignment checkpoints. `Demo.main()` provides a visual trace of the total size, search, and statistics operations.

## Build & Run
1. Ensure you have Java JDK 17 (or higher) installed.
2. Open the project in IntelliJ IDEA (or your preferred IDE).
3. Run the tests from the terminal: `mvn clean test`
4. Run the application from the terminal: `mvn exec:java`

## Expected Results (Demo)
```text
Total bytes: 6670
JPG > 100 bytes: [img/photo.jpg, img/thumb.jpg]
Files=5 Dirs=3 MaxDepth=2
