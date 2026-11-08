
import java.util.ArrayList;
import java.util.List;


public class BookSearch {

        public static List<Book> searchByTitle(String title) {
            List<Book> results = new ArrayList<>();

            if (title == null || title.trim().isEmpty()) {
                return results;
            }
            for (Book book : BookStore.load()) {
                if (book.getTitle().toLowerCase()
                        .contains(title.trim().toLowerCase())) {
                    results.add(book);
                }
            }

            return results;
        }
    }


