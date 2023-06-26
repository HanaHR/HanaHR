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
            // 합격자 추가 폼의 input에 value 저장
            var hiddenProcess = document.getElementById("hiddenProcess");
            var hiddenHeadCount = document.getElementById("hiddenHeadCount");

            hiddenProcess.value = localStorage.getItem("process");
            hiddenHeadCount.value = localStorage.getItem("count");
            if (hiddenProcess.value !== "" && hiddenHeadCount.value !== "") {
                alert("합격자 추가가 완료되었습니다.");
            }
            localStorage.removeItem("process");
            localStorage.removeItem("count");
        }


    </script>
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
                                 src="${pageContext.request.contextPath}/resources/img/undraw_profile.svg">
                        </a>
                    </li>

                </ul>

            </nav>
            <!-- End of Topbar -->
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
                        <form action="/pick" method="post" id="picker" style="display: inline-block">
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
                        </form>
                        <form action="/updatePasser" method="post" style="display: inline-block">
                            <input type="hidden" name="process" value="" id="hiddenProcess">
                            <input type="hidden" name="headCount" value="" id="hiddenHeadCount">
                            <input type="submit" id="appendPasser" value="합격자 추가" class="page-link" style="display: inline-block" onclick="removeValue()">
                        </form>
                        <form action="/outputPasser" method="post" style="display: inline-block">
                            <input type="submit" id="outPasser" value="최종합격자 파일 다운로드" class="page-link" style="display: inline-block">
                        </form>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <% List<Map<String, String>> passer = (List<Map<String, String>>)request.getAttribute("passer"); %>
                            <% if (passer != null && !passer.isEmpty()) { %>
                            <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0" style="font-size: 14px;">
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
                                <% } else { %>
                                <tr>
                                    <td colspan="13">검색 결과가 없습니다.</td>
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



</body>

</html>