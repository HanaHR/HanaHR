package javaResources;

import javaBeans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {
    private String url = "jdbc:mysql://localhost:3306/hanadb?useUnicode=true&characterEncoding=utf8";
    private String username = "root";
    private String password = "0000";

    public List<User> searchMembersByName(String name) {
        List<User> searchResults = new ArrayList<>();

        try {
            // MySQL JDBC 드라이버 로드
            Class.forName("com.mysql.cj.jdbc.Driver");

            // MySQL 데이터베이스에 연결
            Connection connection = DriverManager.getConnection(url, username, password);

            // 쿼리 작성
            String query = "SELECT * FROM members WHERE name = ?";

            // PreparedStatement를 사용하여 쿼리 실행
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name); // 파라미터 설정

            // 쿼리 실행 결과를 ResultSet으로 받아와서 처리
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            while (resultSet.next()) {

                String memberName = resultSet.getString("name");
                String email = resultSet.getString("email");

                User member = new User(memberName, email);
                searchResults.add(member);
            }

            // 리소스 해제
            resultSet.close();
            statement.close();
            connection.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return searchResults;
    }
}
