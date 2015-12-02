package gom.cave.sleep.bookmark.repository;

import gom.cave.sleep.bookmark.model.Member;
import org.springframework.stereotype.Repository;

import java.sql.*;

import static gom.cave.sleep.bookmark.repository.ConnectionMaker.makeConnection;

/**
 * Created by sleepbear on 2015. 12. 1..
 */
@Repository
public class MemberRepository {
    public void add(Member member) throws ClassNotFoundException, SQLException {

        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO member(email, password, nickname) VALUES (?,?,?)");
        preparedStatement.setString(1, member.getEmail());
        preparedStatement.setString(2, member.getPassword());
        preparedStatement.setString(3, member.getNickname());

        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    public Member get(String email) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from member where email = ?");
        preparedStatement.setString(1, email);

        final ResultSet resultSet = preparedStatement.executeQuery();
        resultSet.next();
        final long id = resultSet.getLong("id");
        final String email1 = resultSet.getString("email");
        final String password = resultSet.getString("password");
        final String nickname = resultSet.getString("nickname");

        resultSet.close();
        preparedStatement.close();
        connection.close();
        return new Member(id, email1, password, nickname);
    }

    public void delete(String email) throws SQLException, ClassNotFoundException {
        final Connection connection = makeConnection();
        final PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM member WHERE email=?");
        preparedStatement.setString(1, email);
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();

    }


}
