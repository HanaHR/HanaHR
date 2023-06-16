package javaResources;

import javaBeans.UserDTO;

import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserDAO {
    private String url = "jdbc:mysql://localhost:3306/hanahr?useUnicode=true&characterEncoding=utf8";
    private String username = "root";
    private String password = "1234";

    public static Integer getInteger(ResultSet rs, String colName) throws SQLException {
        int n = rs.getInt(colName);
        return rs.wasNull() ? null : n;
    }

    public List<Map<String, String>> pickPasser(String process, String headCount){
        List<Map<String, String>> findResult = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            String query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber order by ? desc limit ?";
//            String query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where ? <> 0 order by ? desc limit ?";

            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setString(1, process);
            pstmt.setInt(2, Integer.parseInt(headCount));

            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()){
                Map<String, String> hm = new HashMap<>();
                hm.put("지원자번호", Integer.toString(resultSet.getInt("memberNumber")));
                hm.put("이름", resultSet.getString("memberName"));
                hm.put("전공유무", Integer.toString(resultSet.getInt("memberMajor")));
                hm.put("전화번호", resultSet.getString("memberPhone"));
                hm.put("이메일", resultSet.getString("memberEmail"));
                hm.put("서류점수", Integer.toString(resultSet.getInt("memberPaperScore")));
                hm.put("서류합격여부", Integer.toString(resultSet.getInt("memberPaperPass")));
                hm.put("필기점수", Integer.toString(resultSet.getInt("memberWrittenScore")));
                hm.put("필기합격여부", Integer.toString(resultSet.getInt("memberWrittenPass")));
                hm.put("1차면접점수", Integer.toString(resultSet.getInt("memberInterview1Score")));
                hm.put("1차면접합격여부", Integer.toString(resultSet.getInt("memberInterview1Pass")));
                hm.put("2차면접점수", Integer.toString(resultSet.getInt("memberInterview2Score")));
                hm.put("2차면접합격여부", Integer.toString(resultSet.getInt("memberInterview2Pass")));
                findResult.add(hm);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return findResult;
    }
}
