import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookStore {
    private static final Path FILE = Paths.get("books.json");

    public static List<Book> load() {
        List<Book> books = new ArrayList<>();
        if (!Files.exists(FILE)) {
            return books;
        }
        try {
            String json = Files.readString(FILE);
            Pattern p = Pattern.compile(
                    "\\{\\s*\"title\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"author\"\\s*:\\s*\"(.*?)\"\\s*,\\s*\"year\"\\s*:\\s*(\\d+)\\s*\\}");
            Matcher m = p.matcher(json);
            while (m.find()) {
                books.add(new Book(m.group(1), m.group(2), Integer.parseInt(m.group(3))));
            }
        } catch (IOException e) {
            System.out.println("Could not read books.json: " + e.getMessage());
        }
        return books;
    }

    public static void save(List<Book> books) {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            sb.append("  {\"title\": \"").append(clean(b.getTitle()))
                    .append("\", \"author\": \"").append(clean(b.getAuthor()))
                    .append("\", \"year\": ").append(b.getYear()).append("}");
            if (i < books.size() - 1) {
                sb.append(",");
            }
            sb.append("\n");
        }
        sb.append("]\n");
        try {
            Files.writeString(FILE, sb.toString());
        } catch (IOException e) {
            System.out.println("Could not write books.json: " + e.getMessage());
        }
    }

    public static void addBook(Book book) {
        List<Book> books = load();
        books.add(book);
        save(books);
    }

    private static String clean(String s) {
        return s.replace("\"", "'").replace("\\", "");
    }
}