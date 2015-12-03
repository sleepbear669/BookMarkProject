package gom.cave.sleep.bookmark.repository;

import com.google.common.collect.Lists;
import gom.cave.sleep.bookmark.model.Book;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static gom.cave.sleep.bookmark.repository.ConnectionMaker.makeConnection;

/**
 * Created by sleepbear on 2015. 12. 2..
 */
@Repository
public class BookRepository {
    public Book getById(long bookId) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book WHERE id = ?");
        preparedStatement.setLong(1, bookId);
        final ResultSet resultSet = preparedStatement.executeQuery();
        Book book = null;
        if (resultSet.next()) {
            final long id1 = resultSet.getLong("id");
            final String title = resultSet.getString("title");
            final String writer = resultSet.getString("writer");
            final String publisher = resultSet.getString("publisher");
            final String introduce = resultSet.getString("introduce");
            book = new Book(id1, title, writer, publisher, introduce);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return book;
    }

    public Long getIdByName(String title) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM book WHERE title = ?");
        preparedStatement.setString(1, title);
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            final long id = resultSet.getLong("id");
            resultSet.close();
            preparedStatement.close();
            connection.close();
            return id;
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return null;
    }

    public List<Book> findAll() throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM book");
        final ResultSet resultSet = preparedStatement.executeQuery();
        final ArrayList<Book> bookList = Lists.newArrayList();
        while(resultSet.next()) {
            final long id = resultSet.getLong("id");
            final String title = resultSet.getString("title");
            final String writer = resultSet.getString("writer");
            final String publisher = resultSet.getString("publisher");
            final String introduce = resultSet.getString("introduce");
            bookList.add(new Book(id, title, writer, publisher, introduce));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return bookList;
    }
}
