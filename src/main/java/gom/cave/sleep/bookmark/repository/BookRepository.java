package gom.cave.sleep.bookmark.repository;

import gom.cave.sleep.bookmark.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static gom.cave.sleep.bookmark.repository.ConnectionMaker.makeConnection;

/**
 * Created by sleepbear on 2015. 12. 2..
 */
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

    public long getIdByName(String title) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT id FROM book WHERE title = ?");
        preparedStatement.setString(1, title);
        final ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            final long id = resultSet.getLong("id");
            return id;
        }
        return 0;
    }
}
