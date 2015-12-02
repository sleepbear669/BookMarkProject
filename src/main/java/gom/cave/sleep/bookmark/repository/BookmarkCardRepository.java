package gom.cave.sleep.bookmark.repository;

import com.google.common.collect.Lists;
import gom.cave.sleep.bookmark.model.BookmarkCard;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static gom.cave.sleep.bookmark.repository.ConnectionMaker.makeConnection;

/**
 * Created by sleepbear on 2015. 12. 2..
 */
@Repository
public class BookmarkCardRepository {

    public void add(BookmarkCard bookmarkCard) throws SQLException, ClassNotFoundException {

        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bookmark_card(member_id, book_id, phrase, tag) VALUES (?,?,?,?)");
        preparedStatement.setLong(1,bookmarkCard.getMemberId() );
        if (bookmarkCard.getBookId() == null) preparedStatement.setString(2, null);
        else preparedStatement.setLong(2, bookmarkCard.getBookId());

        preparedStatement.setString(3, bookmarkCard.getPhrase());
        preparedStatement.setString(4, bookmarkCard.getTag());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public List<BookmarkCard> getByMemberId(long memberId) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM bookmark_card WHERE member_id = ?");
        preparedStatement.setLong(1, memberId);
        final ResultSet resultSet = preparedStatement.executeQuery();
        List<BookmarkCard> bookmarkCardList = Lists.newArrayList();
        while (resultSet.next()) {
            final long id = resultSet.getLong("id");
            final long fetchedMemberId = resultSet.getLong("member_id");
            final long bookId = resultSet.getLong("book_id");
            final String tag = resultSet.getString("tag");
            final String phrase = resultSet.getString("phrase");
            final BookmarkCard bookmarkCard = new BookmarkCard(id, fetchedMemberId, bookId, phrase, tag);
            bookmarkCardList.add(bookmarkCard);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return bookmarkCardList;
    }
}
