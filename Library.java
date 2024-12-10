import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

    public class Library implements ILibraryManager {
        private String id;
        private String name;
        private List<Book> books;
        private static int bookCounter = 1;

        public Library() {
            this.books = new ArrayList();
        }

        public Library(String id, String name, List<Book> books) {
            this.setId(id);
            this.setName(name);
            this.books = books;
        }

        public String getId() {
            return this.id;
        }

        public void setId(String id) {
            try {
                if (id == null || id.trim().isEmpty()) {
                    System.out.println("Library ID cannot be null or empty.");
                }

                this.id = id;
            } catch (IllegalArgumentException var3) {
                IllegalArgumentException e = var3;
                System.out.println("Error setting ID: " + e.getMessage());
            }

        }

        public String getName() {
            return this.name;
        }

        public void setName(String name) {
            try {
                if (name == null || name.trim().isEmpty()) {
                    System.out.println("Library name cannot be null or empty.");
                }

                this.name = name;
            } catch (IllegalArgumentException var3) {
                IllegalArgumentException e = var3;
                System.out.println("Error setting name: " + e.getMessage());
            }

        }

        public List<Book> getBooks() {
            return this.books;
        }

        public void setBooks(List<Book> books) {
            try {
                if (books == null) {
                    System.out.println("Book list cannot be null.");
                }

                this.books = books;
            } catch (IllegalArgumentException var3) {
                IllegalArgumentException e = var3;
                System.out.println("Error setting books: " + e.getMessage());
            }

        }

        public void addBook(Book book) {
            String bookId = String.format("B%04d", bookCounter++);
            book.setId(bookId);
            this.books.add(book);
            System.out.println("Book added successfully with ID: " + bookId);
        }

        public boolean removeBook(String bookId) {
            return this.books.removeIf((book) -> {
                return book.getId().equals(bookId);
            });
        }

        public List<Book> searchBooks(String keyword) {
            List<Book> result = new ArrayList();
            if (keyword != null && !keyword.trim().isEmpty()) {
                String keywordLower = keyword.toLowerCase();

                for(int i = 0; i < this.books.size(); ++i) {
                    Book book = (Book)this.books.get(i);
                    String title = book.getTitle() != null ? book.getTitle().toLowerCase() : "";
                    String author = book.getAuthor() != null ? book.getAuthor().toLowerCase() : "";
                    if (title.contains(keywordLower) || author.contains(keywordLower)) {
                        result.add(book);
                    }
                }

                return result;
            } else {
                System.out.println("Keyword cannot be null or empty.");
                return result;
            }
        }

        public void displayBooks() {
            StringBuilder sb = new StringBuilder();
            sb.append("+-----------------+--------------------------+---------------------+------------------+--------------------+-----------------+\n");
            sb.append("| Book ID        | Title                    | Author              | Genre            | Publication Year   | Status          |\n");
            sb.append("+-----------------+--------------------------+---------------------+------------------+--------------------+-----------------+\n");
            Iterator var2 = this.books.iterator();

            while(var2.hasNext()) {
                Book book = (Book)var2.next();
                sb.append(String.format("| %-15s | %-24s | %-19s | %-16s | %-18d | %-15s |\n", book.getId(), book.getTitle(), book.getAuthor(), book.getGenre(), book.getPublicationYear(), book.getStatus()));
            }

            sb.append("+-----------------+--------------------------+---------------------+------------------+--------------------+-----------------+\n");
            System.out.println(sb.toString());
        }

        public String toString() {
            String var10000 = this.id;
            return "Library{id='" + var10000 + "', name='" + this.name + "', books=" + String.valueOf(this.books) + "}";
        }

        public void sortBooks(Comparator<Book> comparator) {
            if (this.books != null && this.books.size() > 1) {
                this.books = this.mergeSort(this.books, comparator);
                System.out.println("Books sorted.");
            }
        }

        private List<Book> mergeSort(List<Book> list, Comparator<Book> comparator) {
            if (list.size() <= 1) {
                return list;
            } else {
                int middle = list.size() / 2;
                List<Book> left = new ArrayList(list.subList(0, middle));
                List<Book> right = new ArrayList(list.subList(middle, list.size()));
                left = this.mergeSort(left, comparator);
                right = this.mergeSort(right, comparator);
                return this.merge(left, right, comparator);
            }
        }

        private List<Book> merge(List<Book> left, List<Book> right, Comparator<Book> comparator) {
            List<Book> result = new ArrayList();
            int i = 0;
            int j = 0;

            while(i < left.size() && j < right.size()) {
                if (comparator.compare((Book)left.get(i), (Book)right.get(j)) <= 0) {
                    result.add((Book)left.get(i));
                    ++i;
                } else {
                    result.add((Book)right.get(j));
                    ++j;
                }
            }

            while(i < left.size()) {
                result.add((Book)left.get(i));
                ++i;
            }

            while(j < right.size()) {
                result.add((Book)right.get(j));
                ++j;
            }

            return result;
        }

        public void sortBooksByYear() {
            Comparator<Book> byYear = Comparator.comparingInt(Book::getPublicationYear);
            this.sortBooks(byYear);
            System.out.println("Books sorted by publication year.");
        }

        public void sortBooksByTitle() {
            Comparator<Book> byTitle = Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER);
            this.sortBooks(byTitle);
            System.out.println("Books sorted by title (A-Z).");
        }

        public void sortBooksByTitleDescending() {
            Comparator<Book> byTitleDescending = (book1, book2) -> {
                return book2.getTitle().compareToIgnoreCase(book1.getTitle());
            };
            this.sortBooks(byTitleDescending);
            System.out.println("Books sorted by title (Z-A).");
        }
    }

