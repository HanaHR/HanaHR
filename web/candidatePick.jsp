<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@page contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Pick Passer</title>

    <!-- Custom fonts for this template-->
    <link href="${pageContext.request.contextPath}/resources/vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/resources/css/sb-admin-2.min.css" rel="stylesheet">
    <link href="${pageContext.request.contextPath}/resources/vendor/datatables/dataTables.bootstrap4.min.css" rel="stylesheet">
    <style>
        .col-md-5 {
            flex: 0;
        }
        #dataTable_info {
            visibility: hidden;
        }
    </style>
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
</head>

<body id="page-top">

<!-- Page Wrapper -->
<div id="wrapper">

    <!-- Sidebar -->
    <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">

        <!-- Sidebar - Brand -->
        <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.html">
            <div class="sidebar-brand-icon rotate-n-15">
                <i class="fas fa-laugh-wink"></i>
            </div>
            <div class="sidebar-brand-text mx-3"> 하나 인사관리 ERP </div>
        </a>

        <!-- Divider -->
        <hr class="sidebar-divider my-0">

        <!-- Nav Item - Charts -->
        <li class="nav-item">
            <a class="nav-link" href="candidateStatus.jsp">
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

            <!-- Begin Page Content -->
            <div class="container-fluid">

                <!-- Page Heading -->


                <!-- DataTales Example -->
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
<%--                        <h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>--%>
                        <h1 class="h3 mb-2 text-gray-800">합격자 선정</h1>
                        <p class="mb-4">진행중인 전형을 선택하고, 선발 인원을 입력하여 합격자를 추가합니다.</p>
                        <hr>
                        <form action="/pick" method="post" id="picker">
                            <label>
                                전형분류
                                <select name="process" class="custom-select custom-select-sm form-control form-control-sm" id="processSelector">
                                    <option value="memberPaperScore"> 서류전형 </option>
                                    <option value="memberWrittenScore"> 필기전형 </option>
                                    <option value="memberInterview1Score"> 1차면접전형 </option>
                                    <option value="memberInterview2Score"> 2차면접전형 </option>
                                </select>
                            </label>
                            <label> 선발인원
                                <input type="text" name="headCount" class="form-control form-control-sm" id="headCountInput">
                            </label>
                            <input type="submit" id="search" value="검색" class="page-link" style="display: inline-block" onclick="saveValue()">
                            <input type="submit" id="appendPasser" value="합격자 추가" class="page-link" style="display: inline-block" onclick="removeValue()">
                            <script>
                                window.onload = function() {
                                    var selectedProcess = document.getElementById("processSelector");
                                    var headCountInput = document.getElementById("headCountInput");
                                    var storedProcess = localStorage.getItem("process");
                                    var storedCount = localStorage.getItem("count");
                                    if (storedProcess){
                                        selectedProcess.value = storedProcess;
                                    }
                                    if (storedCount){
                                        headCountInput.value = storedCount;
                                    }
                                }
                            </script>
                        </form>
                    </div>
                    <script>
                        function saveValue() {
                            var selectedProcess = document.getElementById("processSelector");
                            var headCountInput = document.getElementById("headCountInput");

                            var selectedVal = selectedProcess.value;
                            var inputCount = headCountInput.value;

                            if (inputCount === ""){
                                alert("선발 인원을 입력해주세요");
                            }

                            localStorage.setItem("process", selectedVal);
                            localStorage.setItem("count", inputCount);
                        }
                        function removeValue() {
                            localStorage.removeItem("process");
                            localStorage.removeItem("count");
                        }

                    </script>
                    <div class="card-body">
                        <div class="table-responsive">
                            <% List<Map<String, String>> passer = (List<Map<String, String>>)request.getAttribute("passer"); %>
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>지원자번호</th>
                                    <th>이름</th>
                                    <th>전공유무</th>
                                    <th>전화번호</th>
                                    <th>이메일</th>
                                    <th>서류점수</th>
                                    <th>서류합격여부</th>
                                    <th>필기점수</th>
                                    <th>필기합격여부</th>
                                    <th>1차면접점수</th>
                                    <th>1차면접합격여부</th>
                                    <th>2차면접점수</th>
                                    <th>2차면접합격여부</th>
                                </tr>
                                </thead>
<%--                                <tfoot>--%>
<%--                                <tr>--%>
<%--                                    <th>Name</th>--%>
<%--                                    <th>Position</th>--%>
<%--                                    <th>Office</th>--%>
<%--                                    <th>Age</th>--%>
<%--                                    <th>Start date</th>--%>
<%--                                    <th>Salary</th>--%>
<%--                                </tr>--%>
<%--                                </tfoot>--%>
                                <tbody>
                                    <% for ( Map<String, String> psr : passer) { %>
                                    <tr>
                                        <td><%= psr.get("지원자번호") %></td>
                                        <td><%= psr.get("이름") %></td>
                                        <td><%= psr.get("전공유무").equals("0") ? "비전공자" : "전공자" %></td>
                                        <td><%= psr.get("전화번호") %></td>
                                        <td><%= psr.get("이메일") %></td>
                                        <td><%= psr.get("서류점수") %></td>
                                        <td><%= psr.get("서류합격여부").equals("0") ? "불합격" : psr.get("서류합격여부").equals("1") ? "합격" : "미진행" %></td>
                                        <td><%= psr.get("필기점수") %></td>
                                        <td><%= psr.get("필기합격여부").equals("0") ? "불합격" : psr.get("필기합격여부").equals("1") ? "합격" : "미진행" %></td>
                                        <td><%= psr.get("1차면접점수") %></td>
                                        <td><%= psr.get("1차면접합격여부").equals("0") ? "불합격" : psr.get("1차면접합격여부").equals("1") ? "합격" : "미진행" %></td>
                                        <td><%= psr.get("2차면접점수") %></td>
                                        <td><%= psr.get("2차면접합격여부").equals("0") ? "불합격" : psr.get("2차면접합격여부").equals("1") ? "합격" : "미진행" %></td>
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
                    <span>Copyright &copy; Your Website 2020</span>
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



</body>

</html>