package com.dairodev.literatura.main;

import com.dairodev.literatura.models.Book;
import com.dairodev.literatura.models.ResponseData;
import com.dairodev.literatura.services.Guntendex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private Guntendex guntendex = new Guntendex();
    private Scanner sc = new Scanner(System.in);
    private List<Book> books = new ArrayList<>();

    public void menu() {

        String menu = """
                -->  MENU  <--
                
                1: Search Book
                2: Show All books
                
                0: Exit
                
                ->""";

        int op = -1;
        while (op != 0) {
            System.out.print(menu);
            op = Integer.valueOf(sc.nextLine().strip());

            switch (op) {
                case 1:
                    searchBook();
                    break;
                case 2:
                    showAllBooks();
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

    private void showAllBooks() {
        books.forEach(System.out::println);
    }

    private void searchBook() {
        System.out.print("Book title: ");
        String bookTitle = sc.nextLine();
        ResponseData responseData =  guntendex.searchBooks(bookTitle);
        if(responseData.count() == null || responseData.count() == 0){
            System.out.println("Book  \"" + bookTitle + "\" not found!");
        } else {
            responseData.results().forEach(System.out::println);
            responseData.results().stream().forEach(result -> books.add(new Book(result)));
        }
    }
}
