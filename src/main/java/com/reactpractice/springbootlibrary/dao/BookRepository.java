package com.reactpractice.springbootlibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.reactpractice.springbootlibrary.entity.Book;
@CrossOrigin(origins = "http://localhost:3000")  // Replace with your client’s origin
public interface BookRepository extends JpaRepository<Book, Long> {

}
