package javaResources;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/deleteCandidate")
public class deleteCandidateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String memberNumber = request.getParameter("memberNumber");
        System.out.println(memberNumber);
        // 데이터베이스 연결 및 삭제 작업 수행
        Connection connection = null;
        PreparedStatement deleteScoreStatement = null;
        PreparedStatement deleteParentStatement = null;

        try {
            // 데이터베이스 연결 설정
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://172.16.20.89:3306/hanahr?useUnicode=true&characterEncoding=utf8", "hanaro", "hanaro6666!");

            // 삭제 쿼리 Score

            String deleteScoreQuery = "DELETE FROM score WHERE memberNumber = ?";
            deleteScoreStatement = connection.prepareStatement(deleteScoreQuery);
            deleteScoreStatement.setString(1, memberNumber);
            deleteScoreStatement.executeUpdate();

            // 삭제 쿼리 memberinfo
            String deleteParentQuery = "DELETE FROM memberinfo WHERE memberNumber = ?";
            deleteParentStatement = connection.prepareStatement(deleteParentQuery);
            deleteParentStatement.setString(1, memberNumber);
            deleteParentStatement.executeUpdate();
            // 삭제 작업 완료 후 응답
            response.sendRedirect("candidateEdit.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // 삭제 작업 실패 시 응답
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } finally {
            // 리소스 해제
            try {
                if (deleteScoreStatement != null)
                    deleteScoreStatement.close();
                if (deleteParentStatement != null)
                    deleteParentStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}

