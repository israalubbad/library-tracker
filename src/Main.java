import java.util.List;
import java.util.Scanner;

public class Main {

    public static void printBanner() {
        System.out.println("=== Library Tracker ===");
    }

    public static void main(String[] args) {
        printBanner();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n1) Add book");
            System.out.println("2) List books");
            System.out.println("0) Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    addBook(scanner);
                    break;
                case "2":
                    listBooks();
                    break;
                case "0":
                    System.out.println("Bye!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void addBook(Scanner scanner) {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Year: ");
        int year;
        try {
            year = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Year must be a number.");
            return;
        }
        BookStore.addBook(new Book(title, author, year));
        System.out.println("Book added.");
    }

    private static void listBooks() {
        List<Book> books = BookStore.load();
        if (books.isEmpty()) {
            System.out.println("No books yet.");
            return;
        }
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
}