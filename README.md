# HanaHR
<img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white"> <img src="https://img.shields.io/badge/apache tomcat-F8DC75?style=for-the-badge&logo=apachetomcat&logoColor=white"> <img src="https://img.shields.io/badge/github-181717?style=for-the-badge&logo=github&logoColor=white"> <img src="https://img.shields.io/badge/bootstrap-7952B3?style=for-the-badge&logo=bootstrap&logoColor=white"> 
<br />
디지털 하나로 두번째 과제 - JSP, MYSQL, APACHE TOMCAT으로 ERP 만들기

## 1. 프로젝트 배경

#### 1-1. 프로젝트 주제 선정 및 배경

채용 과정에서의 지원자 관리를 위한 하나은행의 신입 개발자 채용 관리 erp 프로그램을 주제로 선정하였습니다. 

취업준비생에게 가장 익숙한 소재인 ‘채용’에서 단순 페이지 구현이 주가 아닌 서버 부하 줄이기, 쿼리 최적화 등 도전적인 과제를 추가하고자 했습니다. 지원 마감일에 다수의 지원자가 특정 시간에 몰리는 상황과 많은 지원자의 데이터를 조회하는 상황이 이러한 과제를 추가하기에 적합하다고 판단하여 채용 관리 erp로 선정하게 되었습니다.


#### 1-2. 프로젝트 목적
지원 현황
진행 중인 채용의 총 지원자 수와 각 전형별 평균 점수,  커트라인, 합격률 등 지원자 통계 정보를 확인할 수 있습니다.

합격자 선정
서류전형, 필기전형, 1차면접전형, 2차면접전형 중 해당 전형을 선택하고 선발 인원을 입력하여 합격자를 검색합니다. 검색 결과를 확인 후 합격자를 선정할 수 있습니다. 또한 최종합격자(2차면접전형 합격자)의 경우에는, 최종합격자의 정보가 담긴 파일을 다운로드할 수 있습니다.

메일 전송
각 전형 결과에 따라 합격자/불합격자에게 적합한 메일 제목과 내용을 입력 후, 일괄적으로 메일을 전송할 수 있습니다.

지원자 정보 수정
지원자가 입력한 정보를 개명, 오기입 등 수정이 필요한 상황에 관리자가 지원자의 이름으로 검색 후, 지원자의 정보를 수정할 수 있습니다.


#### 1-3. 프로젝트 개요
JSP, MYSQL, APACHE TOMCAT를 이용해 만들 예정입니다

<br />
## 2. 팀 구성 및 역할

이름 | 맡은 역할
------------|------|
[강민경](https://github.com/LaNiMk) | 1. 이메일 전송 페이지 구축<br />2. 로드 밸런싱<br /> 3. 서버 부하 테스트 |
[노유빈](https://github.com/YOOBINNOH) | 1. 로그인 <br />2. 지원서 작성 페이지 구축<br />3. 로드 밸런싱<br />4. ERD 설계 |
[박민재](https://github.com/Cfctor9) | 1. 지원자 정보 수정 페이지 구축<br />2. 로드 밸런싱<br />3. MVC 구조<br />4. 페이지 디자인 |
[서예진b](https://github.com/YEJIN325) | 1. 합격자 선정 페이지 구축<br />2. 효율적 쿼리<br />3. 데이터 생성<br />4. MVC 구조<br />5. 페이지 디자인 |
[이현주](https://github.com/hhyunjooo) | 1. 지원자 통계 페이지 구축<br />2. 합격자 파일 추출 기능<br />3. 효율적 쿼리<br />4. 코드 리팩토링 |

## 3. 프로젝트 수행 절차 및 방법
#### 3-1. 일정 계획

<table>
    <thead>
    <tr>
        <th>개발 기간</th>
        <th colspan="2">2023.6.05. ~ 2022.6.30.</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td>프로젝트명</td>
        <td colspan="2">HanaHR</td>
    </tr>
    <tr>
        <td rowspan="5">프로젝트<br><br>개발기간</td>
        <td>작업내용</td>
        <td>개발 기간</td>
    </tr>
    <tr>
        <td>주제, 요구사항, 화면 설계, DB 설계</td>
        <td>2023-06-05 ~ 2023-06-11</td>
    </tr>
    <tr>
        <td>화면 구현, DB 구현</td>
        <td>2023-06-12 ~ 2023-06-19</td>
    </tr>
    <tr>
        <td>서버 부하 줄이기 챌린지 - 로드 밸런싱</td>
        <td>2023-06-19 ~ 2023-06-26</td>
    </tr>
    <tr>
        <td>최종 점검 및 발표</td>
        <td>2022-07-03</td>
    </tr>
    <tr>
        <td>작업 순서</td>
        <td colspan="2">주제선정→ 계획 → 설계 → 구현 → 시험 및 유지 보수</td>
    </tr>
    <tr>
        <td>필요 자원</td>
        <td colspan="2">Intellij(jsp), MySQL, Github</td>
    </tr>
    </tbody>
</table>

<br />
