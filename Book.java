import java.time.Year;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private int publicationYear;
    private String status;

    public Book(String id, String title, String author, String genre, int publicationYear, String status) {
        this.setId(id);
        this.setTitle(title);
        this.setAuthor(author);
        this.setGenre(genre);
        this.setPublicationYear(publicationYear);
        this.setStatus(status);
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        if (id != null && !id.isEmpty()) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Book ID cannot be null or empty.");
        }
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        if (title != null && !title.isEmpty()) {
            this.title = title;
        } else {
            throw new IllegalArgumentException("Title cannot be null or empty.");
        }
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        if (author != null && !author.isEmpty()) {
            this.author = author;
        } else {
            throw new IllegalArgumentException("Author cannot be null or empty.");
        }
    }

    public String getGenre() {
        return this.genre;
    }

    public void setGenre(String genre) {
        if (genre != null && !genre.isEmpty()) {
            this.genre = genre;
        } else {
            throw new IllegalArgumentException("Genre cannot be null or empty.");
        }
    }

    public int getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(int publicationYear) {
        int currentYear = Year.now().getValue();
        if (publicationYear > 0 && publicationYear <= currentYear) {
            this.publicationYear = publicationYear;
        } else {
            throw new IllegalArgumentException("Publication year must be a positive number and not greater than the current year (" + currentYear + ").");
        }
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        if (!status.equalsIgnoreCase("Available") && !status.equalsIgnoreCase("Borrowed")) {
            throw new IllegalArgumentException("Status must be 'Available' or 'Borrowed'.");
        } else {
            this.status = status;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("+-----------------+--------------------------+---------------------+------------------+--------------------+-----------------+\n");
        sb.append("| Book ID        | Title                    | Author              | Genre            | Publication Year   | Status          |\n");
        sb.append("+-----------------+--------------------------+---------------------+------------------+--------------------+-----------------+\n");
        sb.append(String.format("| %-15s | %-24s | %-19s | %-16s | %-18d | %-15s |\n", this.getId(), this.getTitle(), this.getAuthor(), this.getGenre(), this.getPublicationYear(), this.getStatus()));
        sb.append("+-----------------+--------------------------+---------------------+------------------+--------------------+-----------------+\n");
        return sb.toString();
    }
}
