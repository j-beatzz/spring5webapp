package irab.springframework.spring5webapp.bootstrap;

import irab.springframework.spring5webapp.model.Author;
import irab.springframework.spring5webapp.model.Book;
import irab.springframework.spring5webapp.model.Publisher;
import irab.springframework.spring5webapp.repositories.AuthorRepository;
import irab.springframework.spring5webapp.repositories.BookRepository;
import irab.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class DevBootstrap implements ApplicationListener<ContextRefreshedEvent> {
    private AuthorRepository authorRepository;
    private BookRepository bookRepository;
    private PublisherRepository publisherRepository;

    public DevBootstrap(AuthorRepository authorRepository,
                        BookRepository bookRepository,
                        PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    private void initData() {
        // Publishers
        Publisher hc = new Publisher("Harper Collins");
        Publisher worx = new Publisher("Worx");
        Publisher mit = new Publisher("The MIT Press");
        // Eric
        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "1234", hc);
        eric.getBooks().add(ddd);

        // Rod
        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB", "23444", worx);
        rod.getBooks().add(noEJB);

        // Ngozi
        Author ngozi = new Author("Ngozi", "Okonjo-Iweala");
        Book cor = new Book("Fighting Corroption is Hard", "9780", mit);
        ngozi.getBooks().add(cor);


        publisherRepository.save(hc);
        publisherRepository.save(worx);
        publisherRepository.save(mit);

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
