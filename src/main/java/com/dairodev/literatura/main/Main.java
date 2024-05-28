package com.dairodev.literatura.main;

import com.dairodev.literatura.models.Book;
import com.dairodev.literatura.models.ResponseData;
import com.dairodev.literatura.repository.AuthorRepository;
import com.dairodev.literatura.repository.BookRepository;
import com.dairodev.literatura.services.Guntendex;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Guntendex guntendex = new Guntendex();
    private Scanner sc = new Scanner(System.in);
    private Map<String, String> languageMap = new HashMap<String, String>() {{
        put("af", "Afrikáans");
        put("sq", "Albanés");
        put("am", "Amárico");
        put("ar", "Árabe");
        put("hy", "Armenio");
        put("az", "Azerí");
        put("eu", "Euskera");
        put("be", "Bielorruso");
        put("bn", "Bengalí");
        put("bs", "Bosnio");
        put("bg", "Búlgaro");
        put("ca", "Catalán");
        put("ceb", "Cebuano");
        put("ny", "Chichewa");
        put("zh", "Chino");
        put("co", "Corso");
        put("hr", "Croata");
        put("cs", "Checo");
        put("da", "Danés");
        put("nl", "Neerlandés");
        put("en", "Inglés");
        put("eo", "Esperanto");
        put("et", "Estonio");
        put("tl", "Filipino");
        put("fi", "Finés");
        put("fr", "Francés");
        put("fy", "Frisón");
        put("gl", "Gallego");
        put("ka", "Georgiano");
        put("de", "Alemán");
        put("el", "Griego");
        put("gu", "Guyaratí");
        put("ht", "Criollo haitiano");
        put("ha", "Hausa");
        put("haw", "Hawaiano");
        put("he", "Hebreo");
        put("hi", "Hindi");
        put("hmn", "Hmong");
        put("hu", "Húngaro");
        put("is", "Islandés");
        put("ig", "Igbo");
        put("id", "Indonesio");
        put("ga", "Irlandés");
        put("it", "Italiano");
        put("ja", "Japonés");
        put("jw", "Javanés");
        put("kn", "Canarés");
        put("kk", "Kazajo");
        put("km", "Jemer");
        put("rw", "Kinyarwanda");
        put("ko", "Coreano");
        put("ku", "Kurdo (Kurmanji)");
        put("ky", "Kirguís");
        put("lo", "Lao");
        put("la", "Latín");
        put("lv", "Letón");
        put("lt", "Lituano");
        put("lb", "Luxemburgués");
        put("mk", "Macedonio");
        put("mg", "Malgache");
        put("ms", "Malayo");
        put("ml", "Malayalam");
        put("mt", "Maltés");
        put("mi", "Maorí");
        put("mr", "Maratí");
        put("mn", "Mongol");
        put("my", "Birmano");
        put("ne", "Nepalí");
        put("no", "Noruego");
        put("or", "Odia");
        put("ps", "Pashto");
        put("fa", "Persa");
        put("pl", "Polaco");
        put("pt", "Portugués");
        put("pa", "Panyabí");
        put("ro", "Rumano");
        put("ru", "Ruso");
        put("sm", "Samoano");
        put("gd", "Escocés gaélico");
        put("sr", "Serbio");
        put("st", "Sesotho");
        put("sn", "Shona");
        put("sd", "Sindhi");
        put("si", "Cingalés");
        put("sk", "Eslovaco");
        put("sl", "Esloveno");
        put("so", "Somalí");
        put("es", "Español");
        put("su", "Sudanés");
        put("sw", "Suajili");
        put("sv", "Sueco");
        put("tg", "Tayiko");
        put("ta", "Tamil");
        put("tt", "Tártaro");
        put("te", "Telugu");
        put("th", "Tailandés");
        put("tr", "Turco");
        put("tk", "Turcomano");
        put("uk", "Ucraniano");
        put("ur", "Urdu");
        put("ug", "Uigur");
        put("uz", "Uzbeko");
        put("vi", "Vietnamita");
        put("cy", "Galés");
        put("xh", "Xhosa");
        put("yi", "Yidis");
        put("yo", "Yoruba");
        put("zu", "Zulú");
    }};

    public Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void menu() {

        String menu = """
                                
                     -->  MENU  <--
                                
                1: Search book
                2: Show all books
                3: List of authors
                4: List of living authors
                5: List of books by language
                6: Statistics download books
                7: Top 10 Books, more downloads
                8: Find book by Title
                                
                0: Exit
                                
                -> """;

        int op = -1;
        while (op != 0) {
            System.out.print(menu);
            op = OptionalInt.of(Integer.valueOf(sc.nextLine().strip())).orElse(-1);

            switch (op) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    showAllBooks();
                    break;
                case 3:
                    listAuthors();
                    break;
                case 4:
                    listAuthorsAlive();
                    break;
                case 5:
                    listBooksOfLanguages();
                    break;
                case 6:
                    generateStatistics();
                    break;
                case 7:
                    top10BooksMoreDownloads();
                    break;
                case 8:
                    searchAuthorByName();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
        }
    }

    private void searchAuthorByName() {
        System.out.print("Author name: ");
        String name = sc.nextLine();
        authorRepository.findByNameContainsIgnoreCase(name).forEach(System.out::println);
    }

    private void top10BooksMoreDownloads() {
        bookRepository.findTop10ByOrderByDownloadCountDesc().forEach(System.out::println);
    }

    private void generateStatistics() {
        DoubleSummaryStatistics doubleSummaryStatistics = bookRepository
                .findAll()
                .stream()
                .collect(Collectors.summarizingDouble(Book::getDownloadCount));
        System.out.println(doubleSummaryStatistics);
    }

    private void listBooksOfLanguages() {
        List<Book> books = bookRepository.findAll();
        List<List<String>> languagesList = books
                .stream()
                .map(book -> Arrays.stream(book.getLanguages().split(",")).toList())
                .toList();

        System.out.println();
        System.out.println("Code by language");
        System.out.println();
        languagesList
                .stream()
                .flatMap(List::stream)
                .collect(Collectors.toSet())
                .forEach(language -> {
                    System.out.printf("[%s] : %s\n", language, languageMap.get(language));
                });
        System.out.print("Write the code: ");
        String code = sc.nextLine();
        if (languageMap.containsKey(code.toLowerCase())) {
            bookRepository.findByLanguagesContainsIgnoreCase(code).forEach(System.out::println);
        } else {
            System.out.println("Invalid Code.");
        }

    }

    private void listAuthorsAlive() {
        System.out.print("Year: ");
        int year = Integer.valueOf(sc.nextLine().strip());
        authorRepository.findByDeathYearLessThanEqual(year).forEach(System.out::println);
    }

    private void listAuthors() {
        authorRepository.findAll().forEach(System.out::println);
    }

    private void showAllBooks() {
        bookRepository.findAll().forEach(System.out::println);
    }

    private void searchBook() {
        System.out.print("Book title: ");
        String bookTitle = sc.nextLine();
        ResponseData responseData = guntendex.searchBooks(bookTitle);
        if (responseData.count() == null || responseData.count() == 0) {
            System.out.println("Book  \"" + bookTitle + "\" not found!");
        } else {
            responseData.results().forEach(System.out::println);
            responseData.results().stream().forEach(result -> this.bookRepository.save(new Book(result)));
        }
    }
}
