package javaDB;

import javaBeans.User;
import javaResources.MailUtils;
import javax.servlet.annotation.WebServlet;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/servlet")
public class UserDAO {
    Connection connection = DB1.getConnection();

    public List<Map<String, String>> pickPasser(String process, String headCount){
        List<Map<String, String>> findResult = new ArrayList<>();
        PreparedStatement pstmt = null;
//        PreparedStatement infoPstmt = null;
        ResultSet resultSet = null;
        try {
            String query = "";
            queryTime.getStartTime();
            if (process.equals("memberPaperScore")) {
                query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberPaperScore > 0 order by s.memberPaperScore desc limit ?";
            } else if (process.equals("memberWrittenScore")) {
                query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberWrittenScore > 0 order by s.memberWrittenScore desc limit ?";
            } else if (process.equals("memberInterview1Score")) {
                query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview1Score > 0 order by s.memberInterview1Score desc limit ?";
            } else {
                query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview2Score > 0 order by s.memberInterview2Score desc limit ?";
            }

            pstmt = connection.prepareStatement(query);
            pstmt.setInt(1, Integer.parseInt(headCount));

            resultSet = pstmt.executeQuery();

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
            queryTime.getStopTime();
            System.out.println("전형별 지원자 검색 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

                // 조인하지 않고 쿼리문 나눠서 작성하는 방법
//            String passQuery = "";
//            queryTime.getStartTime();
//
//            if (process.equals("memberPaperScore")) {
//                passQuery = "select memberNumber, memberPaperScore, memberPaperPass, memberWrittenScore, memberWrittenPass, memberInterview1Score, memberInterview1Pass, memberInterview2Score, memberInterview2Pass from score where memberPaperScore > 0 order by memberPaperScore desc limit ?";
//            } else if (process.equals("memberWrittenScore")) {
//                passQuery = "select memberNumber, memberPaperScore, memberPaperPass, memberWrittenScore, memberWrittenPass, memberInterview1Score, memberInterview1Pass, memberInterview2Score, memberInterview2Pass from score where memberWrittenScore > 0 order by memberWrittenScore desc limit ?";
//            } else if (process.equals("memberInterview1Score")) {
//                passQuery = "select memberNumber, memberPaperScore, memberPaperPass, memberWrittenScore, memberWrittenPass, memberInterview1Score, memberInterview1Pass, memberInterview2Score, memberInterview2Pass from score where memberInterview1Score > 0 order by memberInterview1Score desc limit ?";
//            } else {
//                passQuery = "select memberNumber, memberPaperScore, memberPaperPass, memberWrittenScore, memberWrittenPass, memberInterview1Score, memberInterview1Pass, memberInterview2Score, memberInterview2Pass from score where memberInterview2Score > 0 order by memberInterview2Score desc limit ?";
//            }
//
//            pstmt = connection.prepareStatement(passQuery);
//            pstmt.setInt(1, Integer.parseInt(headCount));
//
//            resultSet = pstmt.executeQuery();
//
//            while (resultSet.next()) {
//
//                int num = resultSet.getInt("memberNumber");
//
//                Map<String, String> hm = new HashMap<>();
//                hm.put("지원자번호", Integer.toString(num));
//                hm.put("서류점수", Integer.toString(resultSet.getInt("memberPaperScore")));
//                hm.put("서류합격여부", Integer.toString(resultSet.getInt("memberPaperPass")));
//                hm.put("필기점수", Integer.toString(resultSet.getInt("memberWrittenScore")));
//                hm.put("필기합격여부", Integer.toString(resultSet.getInt("memberWrittenPass")));
//                hm.put("1차면접점수", Integer.toString(resultSet.getInt("memberInterview1Score")));
//                hm.put("1차면접합격여부", Integer.toString(resultSet.getInt("memberInterview1Pass")));
//                hm.put("2차면접점수", Integer.toString(resultSet.getInt("memberInterview2Score")));
//                hm.put("2차면접합격여부", Integer.toString(resultSet.getInt("memberInterview2Pass")));
//
//                String infoQuery = "";
//                infoQuery = "";
//                infoPstmt = connection.prepareStatement(infoQuery);
//                infoPstmt.setInt(1, num);
//                ResultSet infoResultset = infoPstmt.executeQuery();
//                while (infoResultset.next()) {
//                    hm.put("이름", infoResultset.getString("memberName"));
//                    hm.put("전공유무", Integer.toString(infoResultset.getInt("memberMajor")));
//                    hm.put("전화번호", infoResultset.getString("memberPhone"));
//                    hm.put("이메일", infoResultset.getString("memberEmail"));
//                }
//                findResult.add(hm);
//            }
//            queryTime.getStopTime();
//            System.out.println("전형별 지원자 검색 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 + "초");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return findResult;
    }

    public void updatePasser(List<Map<String, String>> selectedPasser, String process, String headCount) {
        PreparedStatement pstmt3 = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmt2 = null;
        try {
            // 합격자 score 테이블 각 전형별 Pass 컬럼 값 변경
            String query = "";
            // 합격자 pass 테이블에 합격자 id 추가
            String query3 = "";
            queryTime.getStartTime();
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

            pstmt = connection.prepareStatement(query);
            for (int i = 0; i < selectedPasser.size(); i++) {
                Map<String, String> candidate = selectedPasser.get(i);
                int id = Integer.parseInt(candidate.get("지원자번호"));
                pstmt.setInt(1, id);
                pstmt.executeUpdate();

                if (query3 != "") {
                    pstmt3 = connection.prepareStatement(query3);
                    pstmt3.setInt(1, id);
                    pstmt3.executeUpdate();
                }
            }
            queryTime.getStopTime();
            System.out.println("합격자 선정 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

            // 불합격자 score 테이블의 각 전형 Pass 컬럼 값 변경
            String query2 = "";
            queryTime.getStartTime();
            if (process.equals("memberPaperScore")) {
                query2 = "update score as s left join (select memberNumber from score order by memberPaperScore desc limit ?) as p on s.memberNumber = p.memberNumber set s.memberPaperPass=0 where p.memberNumber is null";
            } else if (process.equals("memberWrittenScore")) {
                query2 = "update score as s left join (select memberNumber from score order by memberWrittenScore desc limit ?) as p on s.memberNumber = p.memberNumber set s.memberWrittenPass=0 where p.memberNumber is null";
            } else if (process.equals("memberInterview1Score")) {
                query2 = "update score as s left join (select memberNumber from score order by memberInterview1Score desc limit ?) as p on s.memberNumber = p.memberNumber set s.memberInterview1Pass=0 where p.memberNumber is null";
            } else {
                query2 = "update score as s left join (select memberNumber from score order by memberInterview2Score desc limit ?) as p on s.memberNumber = p.memberNumber set s.memberInterview2Pass=0 where p.memberNumber is null";
            }

            pstmt2 = connection.prepareStatement(query2);
            pstmt2.setInt(1, Integer.parseInt(headCount));
            pstmt2.executeUpdate();
            queryTime.getStopTime();
            System.out.println("불합격자 지정 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            if (pstmt3 != null) {
                try {
                    pstmt3.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (pstmt2 != null) {
                try {
                    pstmt2.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public void outputPasser() {
        try {
            Connection connection = DB1.getConnection();
            queryTime.getStartTime();

            // Check if pass table has values
            String checkQuery = "SELECT COUNT(memberNumber) FROM `pass`";
            PreparedStatement checkStmt = connection.prepareStatement(checkQuery);
            ResultSet checkResult = checkStmt.executeQuery();
            queryTime.getStopTime();
            System.out.println("합격자 DB 유무 확인 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");
            checkResult.next();
            int count = checkResult.getInt(1);

            // Execute the try block only if there are values in the pass table
            if (count > 0) {
                queryTime.getStartTime();
                String query = "SELECT memberNumber FROM `pass` INTO OUTFILE 'C:/ProgramData/MySQL/MySQL Server 8.0/Uploads/Passer.csv' FIELDS TERMINATED BY ',' ENCLOSED BY '' LINES TERMINATED BY '\n'";
                PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();
                queryTime.getStopTime();
                System.out.println("합격자 file 생성 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");
            } else {
                System.out.println("No values in the 'pass' table.");

            }
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
    }


    public List<User> searchUsersByName(String name) {

        List<User> searchResults = new ArrayList<>();

        try {
            queryTime.getStartTime();
            String query = "SELECT * FROM memberinfo WHERE memberName LIKE ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, name + "%");  // 입력받은 이름으로 부분 일치 검색 수행
            ResultSet resultSet = statement.executeQuery();
            queryTime.getStopTime();
            System.out.println("지원자 이름 검색(memberinfo) query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

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

                queryTime.getStartTime();
                // score 테이블에서 정보 가져오기
                String scoreQuery = "SELECT * FROM score WHERE memberNumber = ?";
                PreparedStatement scoreStatement = connection.prepareStatement(scoreQuery);
                scoreStatement.setInt(1, memberNumber);
                ResultSet scoreResultSet = scoreStatement.executeQuery();
                queryTime.getStopTime();
                System.out.println("지원자 이름 검색(score) query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

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

    public void CandidateDelete(String memberNumber) {
        System.out.println(memberNumber);
        // 데이터베이스 연결 및 삭제 작업 수행
        PreparedStatement deleteScoreStatement = null;
        PreparedStatement deleteParentStatement = null;

        try {
            // 데이터베이스 연결 설정

            // 삭제 쿼리 Score
            queryTime.getStartTime();
            String deleteScoreQuery = "DELETE FROM score WHERE memberNumber = ?";
            deleteScoreStatement = connection.prepareStatement(deleteScoreQuery);
            deleteScoreStatement.setString(1, memberNumber);
            deleteScoreStatement.executeUpdate();
            queryTime.getStopTime();
            System.out.println("지원자 삭제(score) query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

            // 삭제 쿼리 memberinfo
            queryTime.getStartTime();
            String deleteParentQuery = "DELETE FROM memberinfo WHERE memberNumber = ?";
            deleteParentStatement = connection.prepareStatement(deleteParentQuery);
            deleteParentStatement.setString(1, memberNumber);
            deleteParentStatement.executeUpdate();
            queryTime.getStopTime();
            System.out.println("지원자 삭제(memberinfo) query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");
            // 삭제 작업 완료 후 응답

        } catch (Exception e) {
            e.printStackTrace();
            // 삭제 작업 실패 시 응답

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


    protected void CandidateSendmail(String selection1,String selection2,String emailSubject,String emailContent) throws UnsupportedEncodingException, SQLException {




        PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out, "UTF-8"), true);
        out.println(selection1);
        out.println(selection2);
        out.println(emailSubject);
        out.println(emailContent);



        // 조건에 맞는 지원자들의 이메일 주소 가져오기
        List<String> emailList = new ArrayList<>();

        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();

            //서류전형 합격자들의 이메일 가져오기
            if (selection1.equals("paper") && selection2.equals("pass")) {
                try {
                    statement = connection.createStatement();
                    queryTime.getStartTime();
                    String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberPaperPass=1";
                    resultSet = statement.executeQuery(sql);
                    queryTime.getStopTime();
                    System.out.println("합격자 이메일 찾기 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

                    while (resultSet.next()) {
                        String email = resultSet.getString("memberEmail");
                        emailList.add(email);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //서류전형 불합격자들의 이메일 가져오기
            if (selection1.equals("paper") && selection2.equals("fail")) {
                try {
                    statement = connection.createStatement();
                    queryTime.getStartTime();
                    String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberPaperPass=0";
                    resultSet = statement.executeQuery(sql);
                    queryTime.getStopTime();
                    System.out.println("불합격자 이메일 찾기 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");


                    while (resultSet.next()) {
                        String email = resultSet.getString("memberEmail");
                        emailList.add(email);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //필기전형 합격자들의 이메일 가져오기
            if (selection1.equals("written") && selection2.equals("pass")) {
                try {

                    statement = connection.createStatement();
                    String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberWrittenPass=1";
                    resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        String email = resultSet.getString("memberEmail");
                        emailList.add(email);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //필기전형 불합격자들의 이메일 가져오기
            if (selection1.equals("written") && selection2.equals("fail")) {
                try {

                    statement = connection.createStatement();
                    String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberWrittenPass=0";
                    resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        String email = resultSet.getString("memberEmail");
                        emailList.add(email);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //면접1차 합격자들의 이메일 가져오기
            if (selection1.equals("interview1") && selection2.equals("pass")) {
                try {

                    statement = connection.createStatement();
                    String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview1Pass=1";
                    resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        String email = resultSet.getString("memberEmail");
                        emailList.add(email);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //면접1차 불합격자들의 이메일 가져오기
            if (selection1.equals("interview1") && selection2.equals("fail")) {
                try {

                    statement = connection.createStatement();
                    String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview1Pass=0";
                    resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        String email = resultSet.getString("memberEmail");
                        emailList.add(email);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //면접2차 합격자들의 이메일 가져오기
            if (selection1.equals("interview2") && selection2.equals("pass")) {
                try {

                    statement = connection.createStatement();
                    String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview2Pass=1";
                    resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        String email = resultSet.getString("memberEmail");
                        emailList.add(email);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            //면접2차 합격자들의 이메일 가져오기
            if (selection1.equals("interview2") && selection2.equals("fail")) {
                try {

                    statement = connection.createStatement();
                    String sql = "SELECT m.memberEmail from memberInfo as m join score as s on m.memberNumber = s.memberNumber where s.memberInterview2Pass=0";
                    resultSet = statement.executeQuery(sql);

                    while (resultSet.next()) {
                        String email = resultSet.getString("memberEmail");
                        emailList.add(email);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            out.println(emailList);

            // 이메일 전송 로직 구현
            for (String email : emailList) {
                // 이메일 전송 코드 작성
                // email 변수에는 각 지원자의 이메일 주소가 저장되어 있습니다.
                String subject = "메일 제목";
                String content = "메일 내용";
                MailUtils.sendEmail(email, subject, content);
            }
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }



    }

    public void CandidateApply(String name,String email,String gender,String temp_career,String temp_major,String birth,String address,String phone ) throws SQLException {
        Integer career = 0;
        Integer major = 0;
        Connection connection = DB1.getConnection();


        if (temp_career.equals("yes")) {
            career = 1;
        } else {
            career = 0;
        }

        if (temp_major.equals("yes")) {
            major = 1;
        } else {
            major = 0;
        }
        PreparedStatement pstmt = null;
        try {
            queryTime.getStartTime();
            String query = "INSERT into memberinfo (memberName, memberGender , memberBirth, memberAddress , memberCareer,memberPhone , memberEmail, memberMajor) values (?,?,?,?,?,?,?,?)";

            pstmt = null;
            pstmt = connection.prepareStatement(query);

            pstmt.setString(1, name);
            pstmt.setString(2, gender);
            pstmt.setDate(3, Date.valueOf(birth));
            pstmt.setString(4, address);
            pstmt.setInt(5, career);
            pstmt.setString(6, phone);
            pstmt.setString(7, email);
            pstmt.setInt(8, major);

            pstmt.execute();
            queryTime.getStopTime();
            System.out.println("지원서 정보 DB에 넣기 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");
        }catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }

    }
//    public static void ApplyDao(String name, String gender, String birth, String address, Integer career, String phone, String email, Integer major) throws SQLException {
//
//
//        System.out.println("Apply DB success");
//
//        String query = "INSERT into memberinfo (memberName, memberGender , memberBirth, memberAddress , memberCareer,memberPhone , memberEmail, memberMajor) values (?,?,?,?,?,?,?,?)";
//
//        PreparedStatement pstmt = null;
//        pstmt = connection.prepareStatement(query);
//
//        pstmt.setString(1,name);
//        pstmt.setString(2,gender);
//        pstmt.setDate(3, Date.valueOf(birth));
//        pstmt.setString(4,address);
//        pstmt.setInt(5,career);
//        pstmt.setString(6,phone);
//        pstmt.setString(7,email);
//        pstmt.setInt(8,major);
//
//        pstmt.execute();
//    }


    public int getCandidatesCount() {
        int totalCount = 0;
        Connection connection = null;
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            connection = DB1.getConnection();
            queryTime.getStartTime();
            String query = "SELECT COUNT(memberNumber) AS totalCandidate FROM memberInfo;";
            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();
            queryTime.getStopTime();
            System.out.println("지원자 수 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

            while (resultSet.next()) {
                totalCount = resultSet.getInt("totalCandidate");
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }finally {
            // 리소스 해제
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return totalCount;
    }
    public List<Map<String, String>> viewCandidate() {
        List<Map<String, String>> viewResult = new ArrayList<>();
        ResultSet resultSet = null;
        PreparedStatement pstmt = null;
        try {
            queryTime.getStartTime();
            String query = "SELECT m.memberNumber, m.memberName, m.memberMajor, m.memberPhone, m.memberEmail, s.memberPaperScore, s.memberPaperPass, s.memberWrittenScore, s.memberWrittenPass, s.memberInterview1Score, s.memberInterview1Pass, s.memberInterview2Score, s.memberInterview2Pass " +
                    "from memberInfo as m join score as s on m.memberNumber = s.memberNumber";

            pstmt = connection.prepareStatement(query);
            resultSet = pstmt.executeQuery();
            queryTime.getStopTime();
            System.out.println("지원자 DB view query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

            while (resultSet.next()) {
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

                viewResult.add(hm);
            }
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        } finally {
            // 리소스 해제
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    System.out.println("리소스 해제 중 에러: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
        return viewResult;
    }
    public void CandidateUpdate(String memberNumber, String memberName,boolean memberMajor,String memberPhone,String memberEmail,int memberPaperScore,boolean memberPaperPass,int memberWrittenScore,boolean memberWrittenPass,int memberInterview1Score,boolean memberInterview1Pass,int memberInterview2Score,boolean memberInterview2Pass ) {

        PreparedStatement updateStatement = null;
        try {
            // 데이터베이스 연결 설정
            System.out.println("------------------");
            // 업데이트 쿼리 작성 score테이블
            queryTime.getStartTime();
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
            queryTime.getStopTime();
            System.out.println("지원자 정보 수정(score) query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

            // 업데이트 쿼리 작성 memberinfo테이블
            queryTime.getStartTime();
            String updateParentQuery = "UPDATE memberinfo set memberName = ?, memberMajor = ?, memberPhone = ?, memberEmail = ? WHERE memberNumber = ?";
            System.out.println("updateParentQuery: " + updateParentQuery);
            updateStatement = connection.prepareStatement(updateParentQuery);
            updateStatement.setString(1, memberName);
            updateStatement.setBoolean(2, memberMajor);
            updateStatement.setString(3, memberPhone);
            updateStatement.setString(4, memberEmail);
            updateStatement.setString(5, memberNumber);
            updateStatement.executeUpdate();
            queryTime.getStopTime();
            System.out.println("지원자 정보 수정(memberinfo) query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

            // 업데이트 작업 완료 후 candidateEdit.jsp로 이동
            System.out.println("Search results: " + updateParentQuery);
            System.out.println("Search results: " + updateScoreQuery);


        } catch (Exception e) {
            e.printStackTrace();
            // 업데이트 작업 실패 시 응답

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



    public List<Map<String, String>> AvgScore() {
        List<Map<String, String>> avgResult = new ArrayList<>();
        try {
            Connection connection = DB1.getConnection();
            queryTime.getStartTime();
            String query = "SELECT AVG(memberPaperScore) as avgPaperScore, AVG(memberWrittenScore) as avgrWrittenScore,  " +
                    "AVG(memberInterview1Score) as avgInterview1Score, AVG(memberInterview2Score) as avgInterview2Score FROM score;";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            queryTime.getStopTime();
            System.out.println("지원자 평균 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

            while (resultSet.next()) {
                Map<String, String> hm = new HashMap<>();
                hm.put("서류전형", Float.toString(resultSet.getFloat("avgPaperScore")));
                hm.put("필기전형", Float.toString(resultSet.getFloat("avgrWrittenScore")));
                hm.put("1차면접점수", Float.toString(resultSet.getFloat("avgInterview1Score")));
                hm.put("2차면접점수", Float.toString(resultSet.getFloat("avgInterview2Score")));
                avgResult.add(hm);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
        return avgResult;
    }

    public List<Integer> getCutLine() {
        int totalCut = 0;
        List<Integer> cutResult = new ArrayList<Integer>();
        String[] columnName1 = {"memberPaperScore", "memberWrittenScore", "memberInterview1Score", "memberInterview2Score"};
        String[] columnName2 = {"memberPaperPass", "memberWrittenPass", "memberInterview1Pass", "memberInterview2Pass"};
        String[] columnName3 = {"minPaperPass", "minWrittenPass", "minInterview1Pass", "minInterview2Pass"};
        try {
            Connection connection = DB1.getConnection();
            for(int i=0;i<4;i++) {
                queryTime.getStartTime();
                String query = "select min(" + columnName1[i] + ") as "+columnName3[i]+" from memberInfo as m join score " +
                        "as s on m.memberNumber = s.memberNumber where s."+columnName2[i]+"=1;";
                PreparedStatement pstmt = connection.prepareStatement(query);
                ResultSet resultSet = pstmt.executeQuery();
                queryTime.getStopTime();
                System.out.println("지원자 커트라인 " + i + "번째 컬럼 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

                while (resultSet.next()) {
                    totalCut = resultSet.getInt(columnName3[i]);
                }
                cutResult.add(totalCut);
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
        return cutResult;
    }
    public List<Float> getPassRate() {
        List<Float> passResult = new ArrayList<Float>();
        String[] columnVarName = {"memberPaperPass", "memberWrittenPass", "memberInterview1Pass", "memberInterview2Pass"};
        int totalCandidate = 0;
        int passCandidate = 0;
        try {
            Connection connection = DB1.getConnection();
            String query = "SELECT COUNT(memberNumber) AS totalCandidate FROM memberInfo;";
            PreparedStatement pstmt = connection.prepareStatement(query);
            ResultSet resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                totalCandidate = resultSet.getInt("totalCandidate");
            }

            for(int i=0;i<4;i++) {
                queryTime.getStartTime();
                String query2 = "SELECT count(memberNumber) AS countPass from score where "+ columnVarName[i] + "=1;";
                PreparedStatement pstmt2 = connection.prepareStatement(query2);
                ResultSet resultSet2 = pstmt2.executeQuery();
                queryTime.getStopTime();
                System.out.println("지원자 합격률 " + i + "번째 컬럼 query: " + ((double) queryTime.getElapsedTime()) / 1000000000 +"초");

                while (resultSet2.next()) {
                    passCandidate = resultSet2.getInt("countPass");
                }
                passResult.add(new Float(passCandidate*100 / totalCandidate));
            }
            connection.close();
        } catch (SQLException e) {
            System.out.println("에러: " + e.getMessage());
            e.printStackTrace();
        }
        return passResult;
    }
    public boolean AdminLogin(String adminId, String adminPw) throws SQLException {
        String query = "select * from admin where adminId=? and adminPassword=?";

        PreparedStatement pstmt = connection.prepareStatement(query);

        try {
            pstmt = connection.prepareStatement(query);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pstmt.setString(1,adminId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            pstmt.setString(2,adminPw);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            ResultSet resultSet = pstmt.executeQuery();


            // 로그인 실패 시

            if(resultSet.next() == true){
                return true;
            }

            // 로그인 성공 시

            else{
                System.out.println("login success");

            }


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false;
    }

}





