package com.reactpractice.springbootlibrary.service;

import com.reactpractice.springbootlibrary.dao.BookRepository;
import com.reactpractice.springbootlibrary.dao.CheckoutRepository;
import com.reactpractice.springbootlibrary.entity.Book;
import com.reactpractice.springbootlibrary.entity.Checkout;
import jakarta.transaction.Transactional;
import org.hibernate.annotations.Check;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class BookService {

    private BookRepository bookRepository;

    private CheckoutRepository checkoutRepository;

    public BookService(BookRepository bookRepository,CheckoutRepository checkoutRepository) {
        this.bookRepository = bookRepository;
        this.checkoutRepository = checkoutRepository;
    }

    public Book checkoutBook (String userEmail, Long bookId) throws Exception {

        Optional<Book> book = bookRepository.findById(bookId);

        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail, bookId);

        if (!book.isPresent() || validateCheckout != null || book.get().getCopiesAvailable() <= 0) {
            throw new Exception("Book doesn't exist or already checked out by user");
        }

        book.get().setCopiesAvailable(book.get().getCopiesAvailable() - 1);
        bookRepository.save(book.get());

        Checkout checkout = new Checkout(
                userEmail,
                LocalDate.now().toString(),
                LocalDate.now().plusDays(7).toString(),
                book.get().getId()
        );

        checkoutRepository.save(checkout);

        return book.get();
    }

    public Boolean checkoutByUser(String userEmail,Long bookId){
        Checkout validateCheckout = checkoutRepository.findByUserEmailAndBookId(userEmail,bookId);
        if(validateCheckout!=null){
            return true;
        }
        else {
            return false;
        }
    }

    public int currentLoansCount(String userEmail){
        return  checkoutRepository.findBooksByUserEmail(userEmail).size();
    }
}
