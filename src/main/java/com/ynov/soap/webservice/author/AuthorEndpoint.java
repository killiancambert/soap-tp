package com.ynov.soap.webservice.author;

import com.ynov.soap.webservice.author.*;
import com.ynov.soap.webservice.author.Author;
import com.ynov.soap.webservice.book.Book;
import com.ynov.soap.webservice.author.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import javax.annotation.PostConstruct;
import java.util.Optional;

public class AuthorEndpoint {

    private static final String NAMESPACE_URI = "http://soap.ynov.com/webservice/author";

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorEndpoint(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @PostConstruct
    public void initData() {
        Author author1 = new Author();
        author1.setFirstName("Bob");
        author1.setLastName("Lecrivain");

        Author author2 = new Author();
        author2.setFirstName("John");
        author2.setLastName("Doe");
        authorRepository.save(author2);

        Author author3 = new Author();
        author3.setFirstName("Simon");
        author3.setLastName("Minter");
        authorRepository.save(author3);
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorByIdRequest")
    @ResponsePayload
    public GetAuthorByIdResponse getAuthorById(@RequestPayload GetAuthorByIdRequest request) {
        GetAuthorByIdResponse response = new GetAuthorByIdResponse();
        Optional<Author> author = authorRepository.findById(request.getId());
        response.setAuthor(author.get());
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAuthorsRequest")
    @ResponsePayload
    public GetAllAuthorsResponse getAuthors() {
        GetAllAuthorsResponse response = new GetAllAuthorsResponse();
        response.getAuthor().addAll(authorRepository.findAll());
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addAuthorRequest")
    @ResponsePayload
    public AddAuthorRequest addAuthor(@RequestPayload AddAuthorRequest request) {
        return null;
    }
}
