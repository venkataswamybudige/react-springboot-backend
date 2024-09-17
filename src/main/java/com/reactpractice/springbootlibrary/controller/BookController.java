package com.reactpractice.springbootlibrary.controller;


import com.reactpractice.springbootlibrary.entity.Book;
import com.reactpractice.springbootlibrary.service.BookService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.sound.sampled.BooleanControl;

@CrossOrigin("http://localhost:3000")
@RestController
@RequestMapping("/api")
public class BookController {

    private BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PutMapping("/secure/checkout")
    public Book checkoutBook (@RequestParam Long bookId) throws Exception {
    String userEmail = "venkat@gmail.com";
    return  bookService.checkoutBook(userEmail,bookId);
    }

    @GetMapping("/secure/ischeckedout/byuser")
    public Boolean checkoutBookByUser(@RequestParam Long bookId){
        String userEmail = "venkat@gmail.com";
        return bookService.checkoutByUser(userEmail,bookId);

    }

    @GetMapping("/secure/currentloans/count")
    private int currentLoansCount(){
        String userEmail = "venkat@gmail.com";
        return bookService.currentLoansCount(userEmail);
    }


}












