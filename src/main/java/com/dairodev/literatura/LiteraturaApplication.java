package com.dairodev.literatura;

import com.dairodev.literatura.main.Main;
import com.dairodev.literatura.repository.AuthorRepository;
import com.dairodev.literatura.repository.BookRepository;
import com.dairodev.literatura.services.Fetch;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraturaApplication implements CommandLineRunner {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private AuthorRepository authorRepository;

    public static void main(String[] args) {
        SpringApplication.run(LiteraturaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Main main = new Main(bookRepository, authorRepository);
        main.menu();
    }
}
