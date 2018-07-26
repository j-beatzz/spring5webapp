package irab.springframework.spring5webapp.bootstrap;

import irab.springframework.spring5webapp.model.Author;
import irab.springframework.spring5webapp.model.Book;
import irab.springframework.spring5webapp.repositories.AuthorRepository;
import irab.springframework.spring5webapp.repositories.BookRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;

    public DevBootstrap(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    private void initData() {
        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", "Harper Collins");
        eric.getBooks().add(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", "Worx");
        rod.getBooks().add(noEJB);

        // Ngozi
        Author ngozi = new Author("Ngozi", "Okonjo-Iweala");
        Book cor = new Book("Fighting Corroption is Hard", "9780", "The MIT Press");
        ngozi.getBooks().add(cor);

        authorRepository.save(eric);
        authorRepository.save(rod);
        authorRepository.save(ngozi);

        bookRepository.save(ddd);
        bookRepository.save(noEJB);
        bookRepository.save(cor);
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initData();
    }
}
