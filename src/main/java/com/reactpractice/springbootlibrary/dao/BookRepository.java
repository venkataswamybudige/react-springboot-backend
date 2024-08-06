package com.reactpractice.springbootlibrary.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.reactpractice.springbootlibrary.entity.Book;

public interface BookRepository extends JpaRepository<Book, Long> {

}
