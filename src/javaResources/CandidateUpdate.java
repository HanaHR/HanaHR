package javaResources;

import javaDB.DB1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


@WebServlet("/updateCandidate")
public class CandidateUpdate extends HttpServlet {

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

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");

        // HTML이 UTF-8 형식이라는 것을 브라우저에게 전달
        response.setContentType("text/html; charset=utf-8");

        // 서블릿을 통해 생성되는 HTML 파일의 인코딩을 UTF-8로 설정
        response.setCharacterEncoding("utf-8");

        String memberNumber = request.getParameter("memberNumber");
        String memberName = request.getParameter("memberName");
        boolean memberMajor = Boolean.parseBoolean(request.getParameter("memberMajor"));
        String memberPhone = request.getParameter("memberPhone");
        String memberEmail = request.getParameter("memberEmail");
        String memberPaperScoreStr = request.getParameter("memberPaperScore");
        int memberPaperScore = Integer.parseInt(memberPaperScoreStr);
        boolean memberPaperPass = Boolean.parseBoolean(request.getParameter("memberPaperPass"));
        String memberWrittenScoreStr = request.getParameter("memberWrittenScore");
        int memberWrittenScore =Integer.parseInt(memberWrittenScoreStr);
        boolean memberWrittenPass = Boolean.parseBoolean(request.getParameter("memberWrittenPass"));
        String memberInterview1ScoreStr = request.getParameter("memberInterview1Score");
        int memberInterview1Score = Integer.parseInt(memberInterview1ScoreStr);
        boolean memberInterview1Pass = Boolean.parseBoolean(request.getParameter("memberInterview1Pass"));
        String memberInterview2ScoreStr = request.getParameter("memberInterview2Score");
        int memberInterview2Score =Integer.parseInt(memberInterview2ScoreStr) ;
        boolean memberInterview2Pass = Boolean.parseBoolean(request.getParameter("memberInterview2Pass"));

        // 데이터베이스 연결 및 업데이트 작업 수행
        Connection connection = DB1.getConnection();
        PreparedStatement updateStatement = null;

        try {
            // 데이터베이스 연결 설정
            System.out.println("------------------");
            // 업데이트 쿼리 작성 score테이블
            String updateScoreQuery = "UPDATE score set memberPaperScore = ?, memberPaperPass = ?, memberWrittenScore = ?, memberWrittenPass = ?, memberInterview1Score = ?, memberInterview1Pass = ?, memberInterview2Score = ?, memberInterview2Pass = ? WHERE memberNumber = ?";
            updateStatement = connection.prepareStatement(updateScoreQuery);
            updateStatement.setInt(1, memberPaperScore);
            updateStatement.setBoolean(2, memberPaperPass);
            updateStatement.setInt(3, memberWrittenScore);
            updateStatement.setBoolean(4, memberWrittenPass);
            updateStatement.setInt(5, memberInterview1Score);
            updateStatement.setBoolean(6, memberInterview1Pass);
            updateStatement.setInt(7, memberInterview2Score);
            updateStatement.setBoolean(8, memberInterview2Pass);
            updateStatement.setString(9,memberNumber);
            System.out.println("updateScoreQuery: " + updateScoreQuery);
            System.out.println(memberNumber);

            updateStatement.executeUpdate();

            // 업데이트 쿼리 작성 memberinfo테이블
            String updateParentQuery = "UPDATE memberinfo set memberName = ?, memberMajor = ?, memberPhone = ?, memberEmail = ? WHERE memberNumber = ?";
            System.out.println("updateParentQuery: " + updateParentQuery);
            updateStatement = connection.prepareStatement(updateParentQuery);
            updateStatement.setString(1, memberName);
            updateStatement.setBoolean(2, memberMajor);
            updateStatement.setString(3, memberPhone);
            updateStatement.setString(4, memberEmail);
            updateStatement.setString(5, memberNumber);
            updateStatement.executeUpdate();

            // 업데이트 작업 완료 후 candidateEdit.jsp로 이동
            System.out.println("Search results: " + updateParentQuery);
            System.out.println("Search results: " + updateScoreQuery);

            response.sendRedirect("candidateEdit.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            // 업데이트 작업 실패 시 응답
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            log("Error occurred during updateCandidateServlet execution: " + e.getMessage());
        } finally {
        // 리소스 해제
        try {
            if (updateStatement != null)
                updateStatement.close();
            if (connection != null)
                connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
}