package gom.cave.sleep.bookmark.repository;

import gom.cave.sleep.bookmark.model.Book;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

/**
 * Created by sleepbear on 2015. 12. 2..
 */
public class BookRepositoryTest {

    @Test
    public void testGetBook() throws Exception {
        // Given
        final Book book = new Book();
        book.setTitle("폴라리스 랩소디");
        book.setWriter("이영도");
        book.setPublisher("황금가지");
        book.setIntroduce("노스윈드");
        final BookRepository bookRepository = new BookRepository();
        // When
        final long bookId = bookRepository.getIdByName(book.getTitle());
        final Book fetchedBook = bookRepository.getById(bookId);
        // Then
        assertThat(bookId, is(fetchedBook.getId()));
        assertThat( fetchedBook.getTitle(), is(book.getTitle()) );
        assertThat( fetchedBook.getWriter(), is(book.getWriter()) );
        assertThat( fetchedBook.getPublisher(), is(book.getPublisher()) );
        assertThat( fetchedBook.getIntroduce(), is(book.getIntroduce()) );
    }
}