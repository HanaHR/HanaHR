package javaResources;

import javaResources.DB1;

import java.sql.*;

public class ApplyDao {

    Connection connection = DB1.getConnection();
    Statement stmt = null;
    ResultSet rs = null;
    public ApplyDao(String name, String gender, String birth, String address, Integer career, String phone, String email, Integer major) throws SQLException {

        System.out.println("Apply DB success");

        String query = "INSERT into memberinfo (memberName, memberGender , memberBirth, memberAddress , memberCareer,memberPhone , memberEmail, memberMajor) values (?,?,?,?,?,?,?,?)";

        PreparedStatement pstmt = null;
        pstmt = connection.prepareStatement(query);

        pstmt.setString(1,name);
        pstmt.setString(2,gender);
        pstmt.setDate(3, Date.valueOf(birth));
        pstmt.setString(4,address);
        pstmt.setInt(5,career);
        pstmt.setString(6,phone);
        pstmt.setString(7,email);
        pstmt.setInt(8,major);

        pstmt.execute();
    }
}
