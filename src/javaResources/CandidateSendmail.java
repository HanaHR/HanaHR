//package javaResources;
//
//import javaDB.DB1;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.io.OutputStreamWriter;
//import java.io.PrintWriter;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//
//@WebServlet("/emailServlet")
//public class CandidateSendmail extends HttpServlet {
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        RequestUtils requestUtils = new RequestUtils(req,res);
//
//        //request에 정보를 담는다
//        //candidateEmail.jsp에게 나머지 작업을 토스
//        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/candidateEmail.jsp");
//        requestDispatcher.forward(req,res);
//    }
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//        RequestUtils requestUtils = new RequestUtils(req, res);
//
//        req.setCharacterEncoding("UTF-8");
//
//
//        // JSP 페이지에서 전송된 데이터를 받아옴
//        String selection1 = requestUtils.getParam("selection1"); // 전형 분류
//        String selection2 = requestUtils.getParam("selection2"); // 지원자 분류
//        String emailSubject = requestUtils.getParam("emailSubject"); // 메일 제목
//        String emailContent = requestUtils.getParam("emailContent"); // 메일 내용
//
//        res.setCharacterEncoding("UTF-8");
//        res.setContentType("text/plain; charset=UTF-8");
//
//        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);
//        out.println(selection1);
//        out.println(selection2);
//        out.println(emailSubject);
//        out.println(emailContent);
//
//
//
//        // 조건에 맞는 지원자들의 이메일 주소 가져오기
//        List<String> emailList = new ArrayList<>();
//        Connection connection = DB1.getConnection();
//
//        //서류전형 합격자들의 이메일 가져오기
//        if (selection1.equals("paper") && selection2.equals("pass")) {
//            try {
//                Statement statement = connection.createStatement();
//                String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberPaperPass=1";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    String email = resultSet.getString("memberEmail");
//                    emailList.add(email);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //서류전형 불합격자들의 이메일 가져오기
//        if (selection1.equals("paper") && selection2.equals("fail")) {
//            try {
//                Statement statement = connection.createStatement();
//                String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberPaperPass=0";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    String email = resultSet.getString("memberEmail");
//                    emailList.add(email);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //필기전형 합격자들의 이메일 가져오기
//        if (selection1.equals("written") && selection2.equals("pass")) {
//            try {
//
//                Statement statement = connection.createStatement();
//                String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberWrittenPass=1";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    String email = resultSet.getString("memberEmail");
//                    emailList.add(email);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //필기전형 불합격자들의 이메일 가져오기
//        if (selection1.equals("written") && selection2.equals("fail")) {
//            try {
//
//                Statement statement = connection.createStatement();
//                String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberWrittenPass=0";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    String email = resultSet.getString("memberEmail");
//                    emailList.add(email);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //면접1차 합격자들의 이메일 가져오기
//        if (selection1.equals("interview1") && selection2.equals("pass")) {
//            try {
//
//                Statement statement = connection.createStatement();
//                String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview1Pass=1";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    String email = resultSet.getString("memberEmail");
//                    emailList.add(email);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //면접1차 불합격자들의 이메일 가져오기
//        if (selection1.equals("interview1") && selection2.equals("fail")) {
//            try {
//
//                Statement statement = connection.createStatement();
//                String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview1Pass=0";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    String email = resultSet.getString("memberEmail");
//                    emailList.add(email);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //면접2차 합격자들의 이메일 가져오기
//        if (selection1.equals("interview2") && selection2.equals("pass")) {
//            try {
//
//                Statement statement = connection.createStatement();
//                String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview2Pass=1";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    String email = resultSet.getString("memberEmail");
//                    emailList.add(email);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        //면접2차 합격자들의 이메일 가져오기
//        if (selection1.equals("interview2") && selection2.equals("fail")) {
//            try {
//
//                Statement statement = connection.createStatement();
//                String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview2Pass=0";
//                ResultSet resultSet = statement.executeQuery(sql);
//
//                while (resultSet.next()) {
//                    String email = resultSet.getString("memberEmail");
//                    emailList.add(email);
//                }
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        out.println(emailList);
//
//        // 이메일 전송 로직 구현
//        for (String email : emailList) {
//            // 이메일 전송 코드 작성
//            // email 변수에는 각 지원자의 이메일 주소가 저장되어 있습니다.
//            String subject = "메일 제목";
//            String content = "메일 내용";
//           MailUtils.sendEmail(email, subject, content);
//        }
//
//        // 이메일 전송 후에는 필요한 처리 작업을 수행하고 결과를 JSP 페이지에 전달합니다.
//        req.setAttribute("resultMessage", "이메일 전송이 완료되었습니다.");
//        req.getRequestDispatcher("/candidateEmail.jsp").forward(req, res);
//
////        // JSP 페이지로 결과를 전달하여 추가 작업 수행
////        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/candidateEmail.jsp");
////        requestDispatcher.forward(req, res);
//    }
//}