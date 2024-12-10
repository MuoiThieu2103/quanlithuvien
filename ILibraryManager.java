import java.util.List;

    public interface ILibraryManager {
        void addBook(Book var1);

        boolean removeBook(String var1);

        List<Book> searchBooks(String var1);

        void displayBooks();
    }

