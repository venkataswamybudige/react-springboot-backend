package com.reactpractice.springbootlibrary.dao;

import com.reactpractice.springbootlibrary.entity.Book;
import com.reactpractice.springbootlibrary.entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long> {

    Page<Review> findByBookId(@RequestParam("book_id") Long id,Pageable pageable);

}
