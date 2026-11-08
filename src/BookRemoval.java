
import java.util.List;

public class BookRemoval {

    public static boolean removeByTitle(String title) {
        List<Book> books = BookStore.load();

        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getTitle()
                    .equalsIgnoreCase(title.trim())) {

                books.remove(i);
                BookStore.save(books);
                return true;
            }
        }

        return false;
    }
}
