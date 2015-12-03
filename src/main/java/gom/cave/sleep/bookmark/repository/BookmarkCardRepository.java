package gom.cave.sleep.bookmark.repository;

import com.google.common.collect.Lists;
import gom.cave.sleep.bookmark.model.BookmarkCard;
import gom.cave.sleep.bookmark.model.dto.BookmarkCardDto;
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

    public List<BookmarkCardDto> getByMemberId(long memberId) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("select bc.id, m.nickname, title, tag, phrase from bookmark_card bc join member m on bc.member_id = m.id left outer join book b on bc.book_id = b.id where bc.member_id = ?; ");
        preparedStatement.setLong(1, memberId);
        final ResultSet resultSet = preparedStatement.executeQuery();

        List<BookmarkCardDto> bookmarkCardDtoList = getBookmarkCardDtos(resultSet);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return bookmarkCardDtoList;
    }

    private List<BookmarkCardDto> getBookmarkCardDtos(ResultSet resultSet) throws SQLException {
        List<BookmarkCardDto> bookmarkCardDtoList = Lists.newArrayList();
        while (resultSet.next()) {
            final long id = resultSet.getLong("id");
            final String nickname = resultSet.getString("nickname");
            final String title = resultSet.getString("title");
            final String phrase = resultSet.getString("phrase");
            final String tag = resultSet.getString("tag");
            bookmarkCardDtoList.add(new BookmarkCardDto(id, nickname, title, phrase, tag));
        }
        return bookmarkCardDtoList;
    }

    public List<BookmarkCardDto> findAll() throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("select bc.id, m.nickname, title, tag, phrase from bookmark_card bc join member m on bc.member_id = m.id left outer join book b on bc.book_id = b.id ");
        final ResultSet resultSet = preparedStatement.executeQuery();

        List<BookmarkCardDto> bookmarkCardDtoList = getBookmarkCardDtos(resultSet);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return bookmarkCardDtoList;
    }


}
