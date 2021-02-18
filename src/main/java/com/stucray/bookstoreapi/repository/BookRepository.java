package com.stucray.bookstoreapi.repository;

import com.stucray.bookstore.common.model.Book;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends ReactiveCrudRepository<Book, String> {
}
