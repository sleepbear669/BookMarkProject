package gom.cave.sleep.bookmark.repository;

import com.google.common.collect.Lists;
import gom.cave.sleep.bookmark.model.dto.BookmarkCardDto;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static gom.cave.sleep.bookmark.repository.ConnectionMaker.makeConnection;

/**
 * Created by sleepbear on 2015. 12. 3..
 */
@Repository
public class BookmarkerRepository {

    public void add(long memberId, long cardId) throws SQLException, ClassNotFoundException {

        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO bookmarker(member_id, bookmark_card_id) VALUES (?,?)");
        preparedStatement.setLong(1,memberId);
        preparedStatement.setLong(2,cardId);

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public boolean exists(long memberId, long cardId) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT EXISTS (SELECT * FROM bookmarker where member_id = ? AND bookmark_card_id = ?) as exist;"
        );
        preparedStatement.setLong(1, memberId);
        preparedStatement.setLong(2, cardId);
        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        final long exist = resultSet.getLong("exist");
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return exist == 1 ? true : false;
    }

    public List<BookmarkCardDto> getByMemberId(long memberId) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement(
                "select bc.id, m.nickname, title, tag, phrase from bookmark_card bc join member m on bc.member_id = m.id left outer join book b on bc.book_id = b.id where bc.id in (select bookmark_card_id from bookmarker where member_id = ?);");
        preparedStatement.setLong(1, memberId);
        final ResultSet resultSet = preparedStatement.executeQuery();
        List<BookmarkCardDto> bookmarkCardDtoList = Lists.newArrayList();
        while (resultSet.next()) {
            final long id = resultSet.getLong("id");
            final String nickname = resultSet.getString("nickname");
            final String title = resultSet.getString("title");
            final String phrase = resultSet.getString("phrase");
            final String tag = resultSet.getString("tag");
            bookmarkCardDtoList.add(new BookmarkCardDto(id, nickname, title, phrase, tag));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return bookmarkCardDtoList;
    }
}
