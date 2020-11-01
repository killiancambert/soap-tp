package com.ynov.soap.webservice.book;

import com.ynov.soap.webservice.author.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.PostConstruct;
import java.util.Optional;

public class BookEndpoint {

    public static final String NAMESPACE_URI = "http://soap.ynov.com/webservice/book";

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookEndpoint(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void initData() {
        Book book1 = new Book();
        book1.setTitle("L'Odysée");
        book1.setIsbn("123");
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBookById")
    @ResponsePayload
    public GetBookByIdResponse getBookById(@RequestPayload GetBookByIdResponse request) {
        GetBookByIdResponse response = new GetBookByIdResponse();
        Optional<Book> book = bookRepository.findById(request.getBook().getId());
        response.setBook(book.get());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getBooksRequest")
    @ResponsePayload
    public GetAllBooksResponse getBooks(@RequestPayload GetAllBooksResponse request) {
        GetAllBooksResponse response = new GetAllBooksResponse();
        response.getBook().addAll(bookRepository.findAll());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addBookRequest")
    @ResponsePayload
    public AddBookResponse addBook(@RequestPayload AddBookRequest request) {
        return null;
    }
}
