package javaResources;


import java.net.ConnectException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PasserDAO {
    private String url = "jdbc:mysql://localhost:3306/hanadb?useUnicode=true&characterEncoding=utf8";
    private String username = "root";
    private String password = "0000";

    public List<Map<String, String>> pickPasser(String process, String headCount){
        List<Map<String, String>> findResult = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);
            String query = "";
            if (process.equals("memberPaperScore")) {
                query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberPaperScore > 0 order by ? desc limit ?";
            } else if (process.equals("memberWrittenScore")) {
                query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberWrittenScore > 0 order by ? desc limit ?";
            } else if (process.equals("memberInterview1Score")) {
                query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview1Score > 0 order by ? desc limit ?";
            } else {
                query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview2Score > 0 order by ? desc limit ?";
            }

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
    public void updatePasser(List<Map<String, String>> selectedPasser, String process, String headCount) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url, username, password);

            // 합격자 score 테이블 각 전형별 Pass 컬럼 값 변경
            String query = "";
            // 합격자 pass 테이블에 합격자 id 추가
            String query3 = "";
            if (process.equals("memberPaperScore")) {
                query = "update score set memberPaperPass=1 where memberNumber = ?";
            } else if (process.equals("memberWrittenScore")) {
                query = "update score set memberWrittenPass=1 where memberNumber = ?";
            } else if (process.equals("memberInterview1Score")) {
                query = "update score set memberInterview1Pass=1 where memberNumber = ?";
            } else {
                query = "update score set memberInterview2Pass=1 where memberNumber = ?";
                query3 = "insert into pass(memberNumber) values (?)";
            }

            PreparedStatement pstmt = connection.prepareStatement(query);
            for (int i = 0; i < selectedPasser.size(); i++) {
                Map<String, String> candidate = selectedPasser.get(i);
                int id = Integer.parseInt(candidate.get("지원자번호"));
                pstmt.setInt(1, id);
                pstmt.executeUpdate();

                if (query3 != "") {
                    PreparedStatement pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setInt(1, id);
                    pstmt3.executeUpdate();
                }
            }

            // 불합격자 score 테이블의 각 전형 Pass 컬럼 값 변경
            String query2 = "";
            if (process.equals("memberPaperScore")) {
                query2 = "update score as s left join (select memberNumber from score order by memberPaperScore desc limit ?) as p on s.memberNumber = p.memberNumber set s.memberPaperPass=0 where p.memberNumber is null";
            } else if (process.equals("memberWrittenScore")) {
                query2 = "update score as s left join (select memberNumber from score order by memberWrittenScore desc limit ?) as p on s.memberNumber = p.memberNumber set s.memberWrittenPass=0 where p.memberNumber is null";
            } else if (process.equals("memberInterview1Score")) {
                query2 = "update score as s left join (select memberNumber from score order by memberInterview1Score desc limit ?) as p on s.memberNumber = p.memberNumber set s.memberInterview1Pass=0 where p.memberNumber is null";
            } else {
                query2 = "update score as s left join (select memberNumber from score order by memberInterview2Score desc limit ?) as p on s.memberNumber = p.memberNumber set s.memberInterview2Pass=0 where p.memberNumber is null";
            }

            PreparedStatement pstmt2 = connection.prepareStatement(query2);
            pstmt2.setInt(1, Integer.parseInt(headCount));
            pstmt2.executeUpdate();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}