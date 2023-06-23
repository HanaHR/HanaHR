//package javaResources;
//
//import javaDB.ApplyDao;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//import java.sql.*;
//
//@WebServlet("/apply")
//public class CandidateApply extends HttpServlet {
//
//
//    @Override
//    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        response.sendRedirect("apply.jsp");
//    }
//
//    @Override
//    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html; charset=utf-8");
//        response.setCharacterEncoding("utf-8");
//        String name = request.getParameter("name");
//        String email = request.getParameter("email");
//        String gender = request.getParameter("gender");
//        String temp_career = request.getParameter("career");
//        String temp_major = request.getParameter("major");
//
//        // option 값에 따라 0,1 로 구별해주기
//
//        Integer career = 0;
//        Integer major = 0;
//
//        if(temp_career.equals("yes")){
//            career = 1;
//        }
//        else{
//            career = 0;
//        }
//
//        if(temp_major.equals("yes")){
//            major = 1;
//        }
//        else{
//            major = 0;
//        }
//        String birth = request.getParameter("birth");
//
//
//        String address = request.getParameter("address");
//        String phone = request.getParameter("phone");
//
//        try {
//            ApplyDao applyDao = new ApplyDao(name,gender,birth,address,career,phone,email,major);
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
//
//        response.sendRedirect("apply.jsp");
//    }
//}
//
