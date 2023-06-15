<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 2023-06-14
  Time: 오후 5:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1" />
    <title>메일 전송 - 지원자 관리</title>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65"
            crossorigin="anonymous"
    />
</head>

<body class="container" id="page-top">
    <div id="wrapper">
        <form class="row g-3 needs-validation" novalidate>
            <div class="col-md-3">
                <label for="validationCustom04" class="form-label">전형 분류</label>
                <select class="form-select" id="validationCustom04" required>
                    <option selected disabled value="">선택</option>
                    <option>서류전형</option>
                    <option>필기전형</option>
                    <option>면접1차</option>
                    <option>면접2차</option>
                </select>
                <div class="invalid-feedback">선택해주세요.</div>
            </div>
            <br />

            <div class="col-md-3">
                <label for="validationCustom04" class="form-label">지원자 분류</label>

                <select class="form-select" id="validationCustom04" required>
                    <option selected disabled value="">선택</option>
                    <option>합격자</option>
                    <option>불합격자</option>
                </select>
                <div class="invalid-feedback">선택해주세요.</div>
            </div>
            <br />

            <div class="mb-3">
                <label for="exampleFormControlInput1" class="form-label"
                >메일 제목</label
                >
                <input
                        type="text"
                        class="form-control"
                        id="validationCustom03"
                        required
                        placeholder="제목 입력"
                />
                <div class="invalid-feedback">제목을 입력해주세요.</div>
            </div>

            <div class="mb-3">
                <label for="exampleFormControlTextarea1" class="form-label"
                >메일 내용</label
                >
                <textarea
                        class="form-control"
                        id="validationCustom03"
                        required
                        placeholder="내용 입력"
                        rows="3"
                ></textarea>
                <div class="invalid-feedback">제목을 입력해주세요.</div>
            </div>

            <div class="col-12">
                <button type="button" class="btn btn-outline-secondary">
                    일괄 메일 전송
                </button>
            </div>

        </form>
    </div>

<script>
    function validateForm(event) {
        const form = document.querySelector(".needs-validation");
        if (!form.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        form.classList.add("was-validated");
    }

    const submitButton = document.querySelector(".btn");
    submitButton.addEventListener("click", validateForm);
</script>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-kenU1KFdBIe4zVF0s0G1M5b4hcpxyD9F7jL+jjXkk+Q2h455rYXK/7HAuoJl+0I4"
        crossorigin="anonymous"
></script>
</body>
</html>



