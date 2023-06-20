<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>지원서 작성</title>


  <!-- Bootstrap CSS -->
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

  <style>
    body {
      min-height: 100vh;

      background: -webkit-gradient(linear, left bottom, right top, from(#92b5db), to(#1d466c));
      background: -webkit-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -moz-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: -o-linear-gradient(bottom left, #92b5db 0%, #1d466c 100%);
      background: linear-gradient(to top right, #92b5db 0%, #1d466c 100%);
    }

    .input-form {
      max-width: 680px;

      margin-top: 80px;
      padding: 32px;

      background: #fff;
      -webkit-border-radius: 10px;
      -moz-border-radius: 10px;
      border-radius: 10px;
      -webkit-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      -moz-box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15);
      box-shadow: 0 8px 20px 0 rgba(0, 0, 0, 0.15)
    }
  </style>
</head>


<form action="apply" method="post">

<body>
<div class="container">
  <div class="input-form-backgroud row">
    <div class="input-form col-md-12 mx-auto">
      <h4 class="mb-3">지원서 작성</h4>
      <form class="validation-form" novalidate>
        <div class="row">
          <div class="col-md-6 mb-3">
            <label for="name">이름</label>
            <input type="text" class="form-control" id="name" placeholder="홍길동" value="" required name = "name">
            <div class="invalid-feedback">
              이름을 입력해주세요.
            </div>
          </div>

          <div class="mb-3">
            <label for="email">이메일</label>
            <input type="email" class="form-control" id="email" placeholder="you@example.com" required name="email">
            <div class="invalid-feedback">
              이메일을 입력해주세요.
            </div>
          </div>

          <div class="col-md-6 mb-3">
            <label for="nickname">성별</label>
            <input type="text" class="form-control" id="nickname" placeholder="" value="남/여" required name="gender">
            <div class="invalid-feedback">
              성별을 입력해주세요.
            </div>
          </div>
        </div>

        <div class="row">
          <div class="col-md-8 mb-3">
            <label for="root">경력</label>
            <select class="custom-select d-block w-100" id="root" name = "career">

              <option value="yes">있음</option>
              <option value="no">없음</option>
            </select>

          </div>
        </div>

        <div class="row">
          <div class="col-md-8 mb-3">
            <label for="root">전공 여부</label>
            <select class="custom-select d-block w-100" name = "major">

              <option value="yes">전공</option>
              <option value="no">비전공</option>
            </select>

          </div>
        </div>




        <div class="mb-3">
          <label for="address">생년월일</label>
          <input type="text" class="form-control" placeholder="199x-xx-xx" required name = "birth">
          <div class="invalid-feedback">
            생년월일을 입력해주세요
          </div>
        </div>



        <div class="mb-3">
          <label for="address">주소</label>
          <input type="text" class="form-control" id="address" placeholder="xx시 xx구" required name = "address">
          <div class="invalid-feedback">
            주소를 입력해주세요.
          </div>
        </div>


        <div class="mb-3">
          <label for="address">휴대폰 번호</label>
          <input type="text" class="form-control"  placeholder="010-xxxx-xxxx" required name = "phone">
          <div class="invalid-feedback">
            휴대폰 번호를 입력해주세요
          </div>
        </div>


        <hr class="mb-4">

        <div class="mb-4"></div>
        <input type="submit" value="지원서 제출"></button>
      </form>
    </div>
  </div>
  <footer class="my-3 text-center text-small">
    <p class="mb-1"></p>
  </footer>
</div>


</body>

</form>
</html>