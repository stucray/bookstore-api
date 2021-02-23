package com.stucray.bookstoreapi.handler;

import com.stucray.bookstore.common.model.Book;
import com.stucray.bookstoreapi.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

/**
 * Handler for Book REST endpoints.
 */
@Component
@RequiredArgsConstructor
public class BookHandler {

    private final BookService bookService;

    /**
     * Retrieves all books.
     * @param request
     * @return All books.
     */
    public Mono<ServerResponse> getAllBooks(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.getAllBooks(), Book.class);
    }

    /**
     * Retrieves a single book by its id.
     * @param request
     * @return The Book with the specified id.
     */
    public Mono<ServerResponse> getBookById(ServerRequest request) {
       String id = request.pathVariable("id");
       if(id == null) {
           return ServerResponse.badRequest().bodyValue("No id supplied");
       }
       return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(bookService.getBook(id), Book.class);
    }
}
