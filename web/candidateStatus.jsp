<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@page import="java.net.URLEncoder"%>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Applicants Statistics</title>

    <!-- Custom fonts for this template -->
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">

    <!-- Custom styles for this page -->
    <link href="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <style>
        @font-face {
            font-family: 'Pretendard-Regular';
            src: url('https://cdn.jsdelivr.net/gh/Project-Noonnu/noonfonts_2107@1.1/Pretendard-Regular.woff') format('woff');
            font-weight: 400;
            font-style: normal;
        }
        body {
            font-family: 'Pretendard-Regular';
        }
        .col-md-5 {
            flex: 0;
        }
        #dataTable_info {
            visibility: hidden;
        }
        a.page-link {
            color: #1cc88a;
        }
        .page-item.active .page-link {
            color: #fff;
            background: #1cc88a;
            border-color: #1cc88a;
        }
        .page-link:hover {
            color: #1cc88a;
        }
    </style>
</head>

<body id="page-top">

    <!-- Page Wrapper -->
    <div id="wrapper">

        <!-- Sidebar -->
        <ul class="navbar-nav bg-gradient-success sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="candidateStatus">
                <div class="sidebar-brand-icon rotate-n-15">
                    <img src="./resources/img/hana_logo.png" width="39px" height="39px">
                </div>
                <div class="sidebar-brand-text mx-3" style="text-transform: none"> Hana HR </div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Charts -->
            <li class="nav-item">
                <a class="nav-link" href="candidateStatus">
                    <i class="fas fa-fw fa-chart-area"></i>
                    <span style="font-size: 15px; font-weight: 600;"> 지원 현황 </span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Pages Collapse Menu -->
            <li class="nav-item">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseTwo"
                   aria-expanded="true" aria-controls="collapseTwo">
                    <i class="fas fa-fw fa-folder"></i>
                    <span style="font-size: 15px; font-weight: 600;"> 지원자 관리 </span>
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
                    <i class="fas fa-fw fa-wrench"></i>
                    <span style="font-size: 15px; font-weight: 600;"> 지원자 정보 수정 </span></a>
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
                            <!-- Dropdown - Messages -->
                            <div class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown">
                                <form class="form-inline mr-auto w-100 navbar-search">
                                    <div class="input-group">
                                        <input type="text" class="form-control bg-light border-0 small"
                                            placeholder="Search for..." aria-label="Search"
                                            aria-describedby="basic-addon2">
                                        <div class="input-group-append">
                                            <button class="btn btn-primary" type="button">
                                                <i class="fas fa-search fa-sm"></i>
                                            </button>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </li>

                        <!-- Nav Item - User Information -->
                        <li class="nav-item dropdown no-arrow">
                            <a class="nav-link dropdown-toggle" href="#" id="userDropdown" role="button"
                               data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="mr-2 d-none d-lg-inline text-gray-600 small">admin</span>
                                <img class="img-profile rounded-circle"
                                     src="./resources/img/profile.jpg" width="32px" height="32px">
                            </a>
                        </li>
                    </ul>

                </nav>
                <!-- End of Topbar -->

                <!-- Begin Page Content -->
                <div class="container-fluid">

<%--                    <!-- Page Heading -->--%>
<%--                    <h1 class="h3 mb-2 text-gray-800">Tables</h1>--%>
<%--                    <p class="mb-4">DataTables is a third party plugin that is used to generate the demo table below.--%>
<%--                        For more information about DataTables, please visit the <a target="_blank"--%>
<%--                            href="https://datatables.net">official DataTables documentation</a>.</p>--%>
                    <!-- DataTales Example -->
                    <div class="card shadow mb-4">
                        <div class="card-header py-3"> <!-- 회색 부분 -->
                            <div class="d-flex align-items-center">
                                <h6 class="h6 mb-0 text-gray-800">총 지원자 수</h6>
                                &nbsp;
                                <input type="text" id="StatusBox" name="StatusBox" class="form-control" style="width: 10rem;" value="<%=request.getAttribute("rst1")%>">
                            </div>
                            <br>

                            <% List<Map<String, String>> result2 = (List<Map<String, String>>)request.getAttribute("rst3"); %>
                            <table class="table table-bordered">
                                <thead>
                                <tr>
                                    <th scope="col" class="text-success">지원자 통계</th>
                                    <th scope="col">서류전형</th>
                                    <th scope="col">필기전형</th>
                                    <th scope="col">1차면접점수</th>
                                    <th scope="col">2차면접점수</th>
                                </tr>
                                </thead>
                                <tbody>
                                <% for ( Map<String, String> rs2 : result2) { %>
                                <tr>
                                    <th scope="row">평균 점수</th>
                                    <td> <%= rs2.get("서류전형") %> </td>  <!--서류 평균점수-->
                                    <td> <%= rs2.get("필기전형") %> </td>
                                    <td> <%= rs2.get("1차면접점수") %> </td>
                                    <td> <%= rs2.get("2차면접점수") %> </td>
                                </tr>
                                <% } %>
                                <tr>
                                    <th scope="row">커트라인</th>
                                    <td> <%=request.getAttribute("result4-1")%> </td>
                                    <td> <%=request.getAttribute("result4-2")%> </td>
                                    <td> <%=request.getAttribute("result4-3")%> </td>
                                    <td> <%=request.getAttribute("result4-4")%> </td>
                                </tr>
                                <tr>
                                    <th scope="row">합격률</th>
                                    <td> <%= request.getAttribute("result5-1") %> </td>  <!--서류 평균점수-->
                                    <td> <%= request.getAttribute("result5-2") %> </td>
                                    <td> <%= request.getAttribute("result5-3") %> </td>
                                    <td> <%= request.getAttribute("result5-4") %> </td>
                                </tr>

                                </tbody>
                            </table>
                        </div>
                        <div class="card-body">
                            <div class="table-responsive">
                                <% List<Map<String, String>> result = (List<Map<String, String>>)request.getAttribute("rst2"); %>
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                    <thead>
                                        <tr style="font-size: 10px">
                                            <th>지원자 번호</th>
                                            <th>이름</th>
                                            <th>전공 유무</th>
                                            <th>전화번호</th>
                                            <th>이메일</th>
                                            <th>서류점수</th>
                                            <th>서류합격여부</th>
                                            <th>필기점수</th>
                                            <th>필기합격여부</th>
                                            <th>1차면접점수</th>
                                            <th>1차합격여부</th>
                                            <th>2차면접점수</th>
                                            <th>최종합격유무</th>
                                        </tr>
                                    </thead>
                                    <tfoot>
                                        <tr style="font-size: 10px">
                                            <th>지원자 번호</th>
                                            <th>이름</th>
                                            <th>전공 유무</th>
                                            <th>전화번호</th>
                                            <th>이메일</th>
                                            <th>서류점수</th>
                                            <th>서류합격여부</th>
                                            <th>필기점수</th>
                                            <th>필기합격여부</th>
                                            <th>1차면접점수</th>
                                            <th>1차합격여부</th>
                                            <th>2차면접점수</th>
                                            <th>최종합격유무</th>
                                        </tr>
                                    </tfoot>
                                    <tbody>

                                    <% for ( Map<String, String> rs : result) { %>
                                    <tr>
                                        <td><%= rs.get("지원자번호") %></td>
                                        <td><%= rs.get("이름") %></td>
                                        <td><%= rs.get("전공유무").equals("0") ? "비전공자" : "전공자" %></td>
                                        <td><%= rs.get("전화번호") %></td>
                                        <td><%= rs.get("이메일") %></td>
                                        <td><%= rs.get("서류점수") %></td>
                                        <td><%= rs.get("서류합격여부").equals("0") ? "불합격" : rs.get("서류합격여부").equals("1") ? "합격" : "미진행" %></td>
                                        <td><%= rs.get("필기점수") %></td>
                                        <td><%= rs.get("필기합격여부").equals("0") ? "불합격" : rs.get("필기합격여부").equals("1") ? "합격" : "미진행" %></td>
                                        <td><%= rs.get("1차면접점수") %></td>
                                        <td><%= rs.get("1차면접합격여부").equals("0") ? "불합격" : rs.get("1차면접합격여부").equals("1") ? "합격" : "미진행" %></td>
                                        <td><%= rs.get("2차면접점수") %></td>
                                        <td><%= rs.get("2차면접합격여부").equals("0") ? "불합격" : rs.get("2차면접합격여부").equals("1") ? "합격" : "미진행" %></td>
                                    </tr>
                                    <% } %>
                                    </tbody>
                                </table>
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