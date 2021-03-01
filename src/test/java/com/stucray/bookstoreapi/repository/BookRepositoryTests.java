package com.stucray.bookstoreapi.repository;

import com.stucray.bookstore.common.model.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

/**
 * Book repository unit tests.
 */
@DataMongoTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;

    @Test
    @DisplayName("When a book is added it should be retrieved from getAll method")
    public void testGetAllBooks() {
        Book book = new Book();
        book.setTitle("A Title");

        Mono<Book> setup = this.bookRepository
                .deleteAll()
                .then(bookRepository.save(book));

        Flux<Book> findAll = bookRepository.findAll();

        var composite = Flux
                .from(setup)
                .thenMany(findAll);

        StepVerifier
                .create(composite)
                .assertNext(b -> {
                    assertNotNull(b.getId());
                    assertEquals("A Title", b.getTitle());
                })
                .verifyComplete();
    }
}
