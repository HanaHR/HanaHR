package com.hanaHR.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import com.hanaHR.web.EmailUtils;

@WebServlet("/emailServlet")
public class emailServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Rq rq = new Rq(req,res);

        //request에 정보를 담는다
        //candidateEmail.jsp에게 나머지 작업을 토스
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/candidateEmail.jsp");
        requestDispatcher.forward(req,res);
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Rq rq = new Rq(req, res);

        // JSP 페이지에서 전송된 데이터를 받아옴
        String selection1 = rq.getParam("selection1"); // 전형 분류
        String selection2 = rq.getParam("selection2"); // 지원자 분류
        String emailTitle = rq.getParam("emailTitle"); // 메일 제목
        String emailContent = rq.getParam("emailContent"); // 메일 내용

        // 조건에 맞는 지원자들의 이메일 주소 가져오기
        List<String> emailList = new ArrayList<>();

        //서류전형 합격자들의 이메일 가져오기
        if (selection1.equals("서류전형") && selection2.equals("합격자")) {
            try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database_name", "your_username", "your_password");
                 Statement statement = connection.createStatement()) {
                String sql = "SELECT memberEmail FROM memberInfo WHERE memberNumber IN (SELECT memberNumber FROM pass WHERE memberPass = 1)";
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    String email = resultSet.getString("memberEmail");
                    emailList.add(email);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // 이메일 전송 로직 구현
        for (String email : emailList) {
            // 이메일 전송 코드 작성
            // email 변수에는 각 지원자의 이메일 주소가 저장되어 있습니다.
            String subject = "메일 제목";
            String content = "메일 내용";
           EmailUtils.sendEmail(email, subject, content);
        }

        // 이메일 전송 후에는 필요한 처리 작업을 수행하고 결과를 JSP 페이지에 전달합니다.
        req.setAttribute("resultMessage", "이메일 전송이 완료되었습니다.");
        req.getRequestDispatcher("/result.jsp").forward(req, res);

        // JSP 페이지로 결과를 전달하여 추가 작업 수행
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/candidateEmail.jsp");
        requestDispatcher.forward(req, res);
    }
}