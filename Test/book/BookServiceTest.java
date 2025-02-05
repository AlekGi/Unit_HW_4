package book;

import org.junit.jupiter.api.*;
import org.mockito.Mock;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookServiceTest {

    @Mock
    private BookService bookService;
    private List<Book> books;
    private BookRepository bookRepo;

    @BeforeEach
    public void setUp() {
        bookRepo = mock(BookRepository.class);
        bookService = new BookService(bookRepo);
    }

    @Test
    void testFindBookById() {
        String id = "1";
        Book book = new Book(id, "Book1", "Author1");
        when(bookRepo.findById(id)).thenReturn(book);
        Book bookMatch = bookService.findBookById(id);
        verify(bookRepo).findById(id);
        assertEquals(book, bookMatch);
    }

    @Test
    void testFindAllBooks() {
        List<Book> books = Arrays.asList(
                new Book("1", "Book1", "Author1"),
                new Book("2", "Book2", "Author2")
        );
        when(bookRepo.findAll()).thenReturn(books);
        List<Book> result = bookService.findAllBooks();
        verify(bookRepo).findAll();
        assertEquals(books, result);
    }
}
