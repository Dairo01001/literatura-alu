package com.dairodev.literatura.main;

import com.dairodev.literatura.models.Book;
import com.dairodev.literatura.models.ResponseData;
import com.dairodev.literatura.repository.AuthorRepository;
import com.dairodev.literatura.repository.BookRepository;
import com.dairodev.literatura.services.Guntendex;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.Scanner;

public class Main {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Guntendex guntendex = new Guntendex();
    private Scanner sc = new Scanner(System.in);

    public  Main(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    public void menu() {

        String menu = """
                
                -->  MENU  <--
                                
                1: Search Book
                2: Show All books
                3: List authors
                4: List authors alive
                                
                0: Exit
                                
                ->""";

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
                case 0:
                    System.out.println("Coming Out..");
                    break;
                default:
                    System.out.println("Invalid option!");
                    break;
            }
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
