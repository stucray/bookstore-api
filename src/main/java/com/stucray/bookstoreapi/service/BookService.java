package com.stucray.bookstoreapi.service;

import com.stucray.bookstore.common.model.Book;
import com.stucray.bookstoreapi.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    public Flux<Book> getAllBooks() {
        return repository.findAll();
    }

    public Mono<Book> getBook(String id) {
        return repository.findById(id);
    }
}
