<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Tables</title>
    <style>
        .col-md-5 {
            flex: 0;
        }
        #dataTable_info {
            display: none;
        }
        div.dataTables_wrapper div.dataTables_paginate ul.pagination {

            display: none;
        }
        div.dataTables_wrapper div.dataTables_filter {
            display: none;
        }
        div.dataTables_wrapper div.dataTables_length label {
            display: none;
        }

        input{
            background-color: #fff;
            border-radius: 0.8rem;
            border: 0.1rem solid #ededed;
            display: inline-block;
            padding-left: 1.4rem;
            margin-bottom: 3.5rem;
            padding-right: 1.4rem;
            font-size: 1.2rem;
            height: 5rem;
            color: #141414;
            box-shadow: none;
            transition: border 0.1s ease-in;
            word-break: normal;
            word-wrap: normal;
            width: 180px;
            height: 50px;
        }
        select{
            background-color: #fff;
            border-radius: 0.8rem;
            border: 0.1rem solid #ededed;
            display: inline-block;
            padding-left: 1.4rem;
            margin-bottom: 3.5rem;
            padding-right: 1.4rem;
            font-size: 1.2rem;
            height: 5rem;
            color: #141414;
            box-shadow: none;
            transition: border 0.1s ease-in;
            word-break: normal;
            word-wrap: normal;
            width: 180px;
            height: 50px;
        }
        select:hover{
            text-decoration: none;
            border-width: 1px;
            border-style: solid;
            border-color: #007e7b;
        }
        input:hover {
            text-decoration: none;
            border-width: 1px;
            border-style: solid;
            border-color: #007e7b;
        }
    </style>

    <!-- Custom fonts for this template -->
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">

</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="candidateStatus">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3"> 하나 인사관리 ERP </div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="candidateStatus">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right" viewBox="0 0 16 16">
                    <path d="M6 12.796V3.204L11.481 8 6 12.796zm.659.753 5.48-4.796a1 1 0 0 0 0-1.506L6.66 2.451C6.011 1.885 5 2.345 5 3.204v9.592a1 1 0 0 0 1.659.753z"/>
                </svg>
                <span>지원 현황</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Pages Collapse Menu -->
        <li class="nav-item">
            <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
               aria-expanded="true" aria-controls="collapseTwo">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right" viewBox="0 0 16 16">
                    <path d="M6 12.796V3.204L11.481 8 6 12.796zm.659.753 5.48-4.796a1 1 0 0 0 0-1.506L6.66 2.451C6.011 1.885 5 2.345 5 3.204v9.592a1 1 0 0 0 1.659.753z"/>
                </svg>
                <span>지원자 관리</span>
            </a>
            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo" data-parent="#accordionSidebar">
                <div class="bg-white py-2 collapse-inner rounded">
                    <a class="collapse-item" href="./candidatePick.jsp">합격자 선정</a>
                    <a class="collapse-item" href="./candidateEmail.jsp">메일 전송</a>
                </div>
            </div>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="./candidateEdit.jsp">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-caret-right" viewBox="0 0 16 16">
                    <path d="M6 12.796V3.204L11.481 8 6 12.796zm.659.753 5.48-4.796a1 1 0 0 0 0-1.506L6.66 2.451C6.011 1.885 5 2.345 5 3.204v9.592a1 1 0 0 0 1.659.753z"/>
                </svg>
                <span>지원자 정보 수정</span></a>
        </li>

        <!-- Divider -->
        <hr class="sidebar-divider d-none d-md-block">

        <!-- Sidebar Toggler (Sidebar) -->
        <div class="text-center d-none d-md-inline">
            <button class="rounded-circle border-0" id="sidebarToggle"></button>
        </div>

    </ul>
    <!-- End of Sidebar -->

    <!-- Content Wrapper -->
    <div id="content-wrapper" class="d-flex flex-column">

        <!-- Main Content -->
        <div id="content">

            <!-- Topbar -->
            <nav class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow">

                <!-- Sidebar Toggle (Topbar) -->
                <form class="form-inline">
                    <button id="sidebarToggleTop" class="btn btn-link d-md-none rounded-circle mr-3">
                        <i class="fa fa-bars"></i>
                    </button>
                </form>

                <!-- Topbar Navbar -->
                <ul class="navbar-nav ml-auto">

                    <!-- Nav Item - Search Dropdown (Visible Only XS) -->
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <a class="nav-link dropdown-toggle" href="#" id="searchDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-search fa-fw"></i>
                        </a>
                    </li>



                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <span class="mr-2 d-none d-lg-inline text-gray-600 small">admin</span>
                            <img class="img-profile rounded-circle"
                                 src="${pageContext.request.contextPath}/resources/img/undraw_profile.svg">
                        </a>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <%@ page import="javaBeans.User" %>
                <%@ page import="java.util.List" %>
                <%@ page import="java.util.ArrayList" %>

                <div class="card shadow mb-4">
                    <div class="card-header py-3"> <!-- 회색 부분 -->
                        <div class="d-flex align-items-center">
                            <form action="/candidateSearch.jsp" method="post" accept-charset="UTF-8">
                                <label for="searchName">Search by Name:</label>
                                <input type="text" id="searchName" name="searchName">
                                <button type="submit" class="btn btn-outline-secondary" >검색</button>
                            </form>
                        </div>
                        <br>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr style="font-size: 14px">
<%--                                    <th>지원자 번호</th>--%>
                                    <th>이름</th>
                                    <th>전공 유무</th>
                                    <th>전화번호</th>
                                    <th>이메일</th>
                                    <th>서류전형</th>
                                    <th>서류합격자</th>
                                    <th>필기전형</th>
                                    <th>필기합격유무</th>
                                    <th>1차면접점수</th>
                                    <th>1차합격유무</th>
                                    <th>2차면접점수</th>
                                    <th>최종합격유무</th>
                                    <th>Action</th> <!-- delete -->
                                </tr>
                                </thead>
                                <tbody>
                                <% List<User> searchResults = (List<User>) request.getAttribute("searchResults"); %>
                                <% if (searchResults != null && !searchResults.isEmpty()) { %>
                                <% for (User user : searchResults) { %>
                                <tr>

<%--                                    <td><%= user.getMemberNumber() %></td>--%>
                                    <td><%= user.getMemberName() %></td>
                                    <td><%= user.isMemberMajor() ? "전공" : "비전공" %></td>
                                    <td><%= user.getMemberPhone() %></td>
                                    <td><%= user.getMemberEmail() %></td>
                                    <td><%= user.getMemberPaperScore() %></td>
                                    <td><%= user.isMemberPaperPass() ? "서류합격" : "서류탈락" %></td>
                                    <td><%= user.getMemberWrittenScore() %></td>
                                    <td><%= user.isMemberWrittenPass() ? "합격" : "불합격" %></td>
                                    <td><%= user.getMemberInterview1Score() %></td>
                                    <td><%= user.isMemberInterview1Pass() ? "합격" : "불합격" %></td>
                                    <td><%= user.getMemberInterview2Score() %></td>
                                    <td><%= user.isMemberInterview2Pass() ? "합격" : "불합격" %></td>
                                    <td>
                                        <!-- 삭제 버튼 -->
                                        <form action="/deleteCandidate" method="POST">
                                            <input type="hidden" name="memberNumber" value="<%= user.getMemberNumber() %>">
                                            <button class="btn btn-danger" type="submit" onclick="return confirm('정말로 삭제하시겠습니까?')">삭제</button>
                                        </form>
                                    </td>
                                </tr>
                                <% } %>
                                <% } else { %>
                                <tr>
                                    <td colspan="13">검색 결과가 없습니다.</td>
                                </tr>
                                <% } %>

                                </tbody>
                            </table>

                            <% if (searchResults != null && !searchResults.isEmpty()) { %>
                            <% for (User user : searchResults) { %>

                            <form action="/updateCandidate" method="post" accept-charset="UTF-8">
                            <table >
                                <tr>
                                        <td>
                                            <form>
                                                <input type="text"  name="memberName" value="<%= user.getMemberName() %>">
                                                <select  name="memberMajor">
                                                <option value="true" <%= user.isMemberMajor() ? "selected" : "" %>>전공</option>
                                                <option value="false" <%= !user.isMemberMajor() ? "selected" : "" %>>비전공</option>
                                            </select>
                                                <input type="text"  name="memberPhone" value="<%= user.getMemberPhone() %>">
                                                <input type="text"  name="memberEmail" value="<%= user.getMemberEmail() %>"> </br>
                                                <input type="text"  name="memberPaperScore" value="<%= user.getMemberPaperScore() %>">
                                                <select name="memberPaperPass">
                                                    <option value="true" <%= user.isMemberPaperPass() ? "selected" : "" %>>서류합격</option>
                                                    <option value="false" <%= !user.isMemberPaperPass() ? "selected" : "" %>>서류탈락</option>
                                                </select>
                                                <input type="text"  name="memberWrittenScore" value="<%= user.getMemberWrittenScore() %>">
                                                <select  name="memberWrittenPass">
                                                    <option value="true" <%= user.isMemberWrittenPass() ? "selected" : "" %>>필기합격</option>
                                                    <option value="false" <%= !user.isMemberWrittenPass() ? "selected" : "" %>>불합격</option>
                                                </select></br>
                                                <input type="text" name="memberInterview1Score" value="<%= user.getMemberInterview1Score() %>">
                                                <select name="memberInterview1Pass">
                                                    <option value="true" <%= user.isMemberInterview1Pass() ? "selected" : "" %>>1차면접합격</option>
                                                    <option value="false" <%= !user.isMemberInterview1Pass() ? "selected" : "" %>>불합격</option>
                                                </select>
                                                <input type="text"  name="memberInterview2Score" value="<%= user.getMemberInterview2Score() %>">
                                                <select name="memberInterview2Pass">
                                                    <option value="true" <%= user.isMemberInterview2Pass() ? "selected" : "" %>>최종합격</option>
                                                    <option value="false" <%= !user.isMemberInterview2Pass() ? "selected" : "" %>>불합격</option>
                                                </select></br>
                                                <input type="hidden" name="memberNumber" value="<%= user.getMemberNumber() %>">
                                                <button class="btn btn-primary btn-sm" type="submit" style="margin: 20px 0px; padding: 7px 360px"; onclick="return confirm('수정되었습니다')">edit</button>
                                            </form>
                                        </td>

                                </tr>
                                <% } %>
                            </table>
                            </form>
                            <% } %>
                        </div>
                    </div>
                </div>
            </div>
            <!-- /.container-fluid -->
        </div>

        <!-- End of Main Content -->

        <!-- Footer -->
        <footer class="sticky-footer bg-white">
            <div class="container my-auto">
                <div class="copyright text-center my-auto">
                    <span>Copyright hanaHR 2023</span>
                </div>
            </div>
        </footer>
        <!-- End of Footer -->

    </div>
    <!-- End of Content Wrapper -->
</div>
<!-- End of Page Wrapper -->

<!-- Scroll to Top Button-->
<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Logout Modal-->
<div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
     aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
            <div class="modal-footer">
                <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<!-- Bootstrap core JavaScript-->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${pageContext.request.contextPath}/resources/vendor/jquery-easing/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/resources/js/sb-admin-2.min.js"></script>

<!-- Page level plugins -->
<script src="${pageContext.request.contextPath}/resources/vendor/datatables/jquery.dataTables.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.js"></script>

<!-- Page level custom scripts -->
<script src="${pageContext.request.contextPath}/resources/js/demo/datatables-demo.js"></script>

</body>

</html>