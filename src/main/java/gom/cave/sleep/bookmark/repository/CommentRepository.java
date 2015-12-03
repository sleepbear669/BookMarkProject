package gom.cave.sleep.bookmark.repository;

import com.google.common.collect.Lists;
import gom.cave.sleep.bookmark.model.Comment;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static gom.cave.sleep.bookmark.repository.ConnectionMaker.makeConnection;

/**
 * Created by sleepbear on 2015. 12. 3..
 */
@Repository
public class CommentRepository {

    public void add(Comment comment) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO comments(bookmark_card_id, comment) VALUES (?,?)");
        preparedStatement.setLong(1, comment.getBookMarkCardId());
        preparedStatement.setString(2, comment.getComment());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public List<Comment> getById(long cardId) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM comments WHERE bookmark_card_id = ?");
        preparedStatement.setLong(1, cardId);
        final ResultSet resultSet = preparedStatement.executeQuery();
        final ArrayList<Comment> comments = Lists.newArrayList();
        while (resultSet.next()) {
            final long id = resultSet.getLong("id");
            final long bookmarkCardId = resultSet.getLong("bookmark_card_id");
            final String comment = resultSet.getString("comment");
            comments.add(new Comment(id, bookmarkCardId, comment));
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return comments;
    }
}
