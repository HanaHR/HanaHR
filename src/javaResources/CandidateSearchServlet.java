package javaResources;

import javaBeans.User;

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

@WebServlet("/candidateSearch")
public class CandidateSearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private Connection connection;

    @Override
    public void init() throws ServletException {
        super.init();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hanadb?useUnicode=true&characterEncoding=utf8", "root", "0000");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchName = request.getParameter("searchName");
        List<User> searchResults = searchUsersByName(searchName);
        request.setAttribute("searchResults", searchResults);

        RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
        dispatcher.forward(request, response);
    }

    private List<User> searchUsersByName(String name) {
        List<User> searchResults = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/hanadb?useUnicode=true&characterEncoding=utf8", "root", "0000");

            String query = "SELECT * FROM memberinfo WHERE memberName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String memberName = resultSet.getString("memberName");
                System.out.println("Search results: " + memberName);
                String email = resultSet.getString("memberEmail");
                User user = new User(memberName, email);

                searchResults.add(user);
            }
            System.out.println("Executed SQL query: " + query);
            System.out.println("Search results: " + searchResults);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
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
