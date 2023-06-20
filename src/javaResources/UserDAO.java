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

public class UserDAO {
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
            statement.setString(1, "%" + name + "%");  // 입력받은 이름으로 부분 일치 검색 수행

            // 쿼리 실행 결과를 ResultSet으로 받아와서 처리
            ResultSet resultSet = statement.executeQuery();

            // 결과 처리
            while (resultSet.next()) {
                int memberNumber = resultSet.getInt("memberNumber");
                String memberName = resultSet.getString("memberName");
                String memberEmail = resultSet.getString("memberEmail");
                String memberGender = resultSet.getString("memberGender");
                Date memberBirth = resultSet.getDate("memberBirth");
                String memberAddress = resultSet.getString("memberAddress");
                boolean memberCareer = resultSet.getBoolean("memberCareer");
                String memberPhone = resultSet.getString("memberPhone");
                boolean memberMajor = resultSet.getBoolean("memberMajor");

                // score 테이블에서 정보 가져오기
                String scoreQuery = "SELECT * FROM score WHERE memberNumber = ?";
                PreparedStatement scoreStatement = connection.prepareStatement(scoreQuery);
                scoreStatement.setInt(1, memberNumber);
                ResultSet scoreResultSet = scoreStatement.executeQuery();

                int memberPaperScore = 0;
                int memberWrittenScore = 0;
                int memberInterview1Score = 0;
                int memberInterview2Score = 0;
                boolean memberPaperPass = false;
                boolean memberInterview1Pass = false;
                boolean memberInterview2Pass = false;
                boolean memberWrittenPass = false;

                if (scoreResultSet.next()) {
                    memberPaperScore = scoreResultSet.getInt("memberPaperScore");
                    memberWrittenScore = scoreResultSet.getInt("memberWrittenScore");
                    memberInterview1Score = scoreResultSet.getInt("memberInterview1Score");
                    memberInterview2Score = scoreResultSet.getInt("memberInterview2Score");
                    memberPaperPass = scoreResultSet.getBoolean("memberPaperPass");
                    memberInterview1Pass = scoreResultSet.getBoolean("memberInterview1Pass");
                    memberInterview2Pass = scoreResultSet.getBoolean("memberInterview2Pass");
                    memberWrittenPass = scoreResultSet.getBoolean("memberWrittenPass");
                }
                User user = new User(memberName, memberNumber, memberEmail, memberGender, memberBirth,
                        memberAddress, memberCareer, memberPhone, memberMajor, memberPaperScore,
                        memberWrittenScore, memberInterview1Score, memberInterview2Score, memberPaperPass,
                        memberInterview1Pass, memberInterview2Pass, memberWrittenPass);
                searchResults.add(user);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException |ClassNotFoundException | RuntimeException e) {
            e.printStackTrace();
        }
        System.out.println("Search results: " + searchResults);
        return searchResults;
    }
}

