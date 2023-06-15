# 하나은행 지원자 관리 ERP

테이블 구성
1. admin (관리자)
   - adminNum INT NOT NULL PRIMARY KEY
   - adminId VARCHAR(45) NOT NULL
   - adminPassword VARCHAR(45) NOT NULL
  
    추가한 데이터 <br>
   1 admin 1234 <br>
   2 scott tiger <br>
   3 hanaro 6666 <br>

3. admin_log (관리자 로그)
   - adminNum INT NOT NULL FOREIGN KEY(admin)
   - adminLoginDate DATE NOT NULL
   - adminIP VARCHAR(45) NOT NULL
  
4. memberInfo (지원자 정보)
   - memberNumber INT NOT NULL PRIMARY KEY
   - memberName VARCHAR(45) NOT NULL
   - memberGender VARCHAR(45) NOT NULL
   - memberBirth DATE NOT NULL
   - memberAddress VARCHAR(45) NOT NULL
   - memberCareer TINYINT NOT NULL
   - memberPhone VARCHAR(13) NOT NULL
   - memberEmail VARCHAR(45) NOT NULL
   - memberMajor TINYINT NOT NULL
  
    추가한 데이터(있으면 1) <br>
   1 김하나 여 1997-01-01 서울특별시 성동구 아차산로 111 0 010-1234-1234 apple@naver.com 1 <br>
   2 이두리 여 1997-01-02 서울특별시 성동구 아차산로 111 1 010-1234-5678 banana@naver.com 1 <br>
   3 박삼이 남 1997-01-03 서울특별시 성동구 아차산로 111 1 010-1234-9876 candy@naver.com 0 <br>

6. certificate (자격증)
   - memberNumber INT NOT NULL FOREIGN KEY(memberInfo)
   - memberTOEIC TINYINT
   - memberEngineer TINYINT
   - memberSQLD TINYINT
   - memberCertificationScore INT
  
    추가한 데이터(있으면 1, 각 10점씩 계산) <br>
   1 1 1 1 30 <br>
   2 1 1 0 20 <br>
   3 1 0 0 10 <br>
  
8. score (점수)
   - memberNumber INT NOT NULL FOREIGN KEY(memberInfo)
   - memberPaperScore INT
   - memberWrittenScore INT
   - memberInterview1Score INT
   - memberInterview2Score INT
   - memberPaperPass TINYINT
   - memberWrittenPass TINYINT
   - memberInterview1Pass TINYINT
   - memberInterview2Pass TINYINT
  
   추가한 데이터 (1차면접 전형까지 진행된 상태라고 가정) <br>
   1 90 85 88 NULL 1 1 1 NULL (1차면접까지 합격) <br>
   2 70 80 55 NULL 1 1 0 NULL (1차면접 탈락, 필기까지 합격) <br>
   3 50 NULL NULL NULL 0 NULL NULL NULL (서류 탈락) <br>
  
10. pass (합격자)
   - memberNumber INT NOT NULL FOREIGN KEY(memberInfo)
   - memberPass TINYINT
  
