package com.springframework.spring5webapp.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.springframework.spring5webapp.domain.Author;
import com.springframework.spring5webapp.domain.Book;
import com.springframework.spring5webapp.domain.Publisher;
import com.springframework.spring5webapp.repositories.AuthorRepository;
import com.springframework.spring5webapp.repositories.BookRepository;
import com.springframework.spring5webapp.repositories.PublisherRepository;

@Component
public class BootStrapData implements CommandLineRunner{
	
	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;
	private final PublisherRepository publisherRepository;
	
	public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
		this.publisherRepository = publisherRepository;
	}
	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Started in BootStrap");
		
		Publisher publisher1 = new Publisher();
		publisher1.setName("SFG Publishing");
		publisher1.setAddressLine1("1403 Winterset Pkwy");
		publisher1.setCity("Marietta");
		publisher1.setState("GA");
		publisher1.setZip("30067");
		
		
		publisherRepository.save(publisher1);
		
		System.out.println("Number of publishers stored " + publisherRepository.count());
		
		
		Author eric = new Author("Eric", "Evans");
		Book book1 = new Book("Domain Driven Design", "1");
		eric.getBooks().add(book1);
		book1.getAuthors().add(eric);
		book1.setPublisher(publisher1);
		
		publisher1.getBooks().add(book1);
		
		authorRepository.save(eric);
		bookRepository.save(book1);
		
		Author rod = new Author("Rod", "Johnson");
		Book book2 = new Book("J2EE Development without EJB", "2");
		rod.getBooks().add(book2);
		book2.getAuthors().add(rod);
		book2.setPublisher(publisher1);
		
		publisher1.getBooks().add(book2);
		
		authorRepository.save(rod);
		bookRepository.save(book2);
		
		
		System.out.println("Number of publishers stored " + publisherRepository.count());
		System.out.println("Number of books stored " + bookRepository.count());
		System.out.println("Number of books published by publisher1 " + publisher1.getBooks().size());
		
	}
	
	

}
