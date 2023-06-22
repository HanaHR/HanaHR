package javaResources;

import javaBeans.User;
import javaDB.DB1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.ArrayList;

@WebServlet("/candidateSearch.jsp")
public class CandidateSearch extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private  Connection connection = DB1.getConnection();


    @Override
    public void init() throws ServletException {
        super.init();
        Connection connection = DB1.getConnection();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // HTML이 UTF-8 형식이라는 것을 브라우저에게 전달
        response.setContentType("text/html; charset=utf-8");

        // 서블릿을 통해 생성되는 HTML 파일의 인코딩을 UTF-8로 설정
        response.setCharacterEncoding("utf-8");

        RequestDispatcher dispatcher = request.getRequestDispatcher("candidateEdit.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        // HTML이 UTF-8 형식이라는 것을 브라우저에게 전달
        response.setContentType("text/html; charset=utf-8");

        // 서블릿을 통해 생성되는 HTML 파일의 인코딩을 UTF-8로 설정
        response.setCharacterEncoding("utf-8");

        String searchName = request.getParameter("searchName");
        request.setCharacterEncoding("UTF-8");
        List<User> searchResults = searchUsersByName(searchName);
        request.setAttribute("searchResults", searchResults);

        RequestDispatcher dispatcher = request.getRequestDispatcher("candidateEdit.jsp");
        dispatcher.forward(request, response);
    }

    private List<User> searchUsersByName(String name) {

        List<User> searchResults = new ArrayList<>();
        try {
            String query = "SELECT * FROM memberinfo WHERE memberName LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, "%" + name + "%");  // 입력받은 이름으로 부분 일치 검색 수행
            ResultSet resultSet = statement.executeQuery();

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

                scoreResultSet.close();
                scoreStatement.close();

                // User 객체 생성 및 결과에 추가
                User user = new User(memberName, memberNumber, memberEmail, memberGender, memberBirth,
                        memberAddress, memberCareer, memberPhone, memberMajor, memberPaperScore,
                        memberWrittenScore, memberInterview1Score, memberInterview2Score, memberPaperPass,
                        memberInterview1Pass, memberInterview2Pass, memberWrittenPass);
                searchResults.add(user);
            }

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Search results: " + searchResults);
        return searchResults;
    }


    @Override
    public void destroy() {
        super.destroy();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
