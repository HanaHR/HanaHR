<!DOCTYPE html>
<html lang="ko">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>회원가입</title>
  <link rel="stylesheet" href="./test.css">

  <style>

    div {

      position: absolute;

      left: 50%;
      top: 50%;
      transform: translate(-50%, -50%);
      width: 670px;
      height: 960px;
      background: #FFFFFF;
      border: 1px solid #AACDFF;
      box-shadow: 7px 7px 39px rgba(0, 104, 255, 0.25);
      border-radius: 20px;

      margin: 0px;
      padding: 100px;
      box-sizing: border-box;
      display: flex;
      flex-direction: column;
      flex-wrap: nowrap;
    }

    h2 {
      width: 466px;
      height: 94px;
      left: 725px;
      top: 132px;

      font-family: 'Noto Sans CJK KR';
      font-style: normal;
      font-weight: 700;
      font-size: 32px;
      line-height: 47px;

      color: #0068FF;
      justify-content: space-evenly;


    }

    button {
      width: 400px;
      height: 50px;
      left: 725px;
      top: 875px;
      background-color: #FFFFFF;
      color: royalblue;
      border-radius: 8px;
      border: #0068FF solid 1px;
    }

    input {
      padding: 0px;
      border: none;
      border-bottom: 1px solid #CFCFCF;
      width: 466px;
      height: 30px;
    }

    label {
      color: lightgrey;
    }

    .radio {
      align-items: center;
      font-size: 20pt;
      width: 15px;
      height: 15px;
    }

    input.agree {
      align-items: center;
      font-size: 20pt;
      width: 15px;
      height: 15px;
    }
  </style>
</head>

<body>
<div>
  <div class="container">
    <h2>회원가입을 위해<br>정보를 입력해주세요.</h2>

    <label for="email">* 이메일<br>
      <input type="text" id="email"><br><br>
    </label>
    <label for="name">* 이름<br>
      <input type="text" id="name"><br><br>
    </label>
    <label for="password1">* 비밀번호<br>
      <input class="pw" id="password1" type="password"><br><br>
    </label>
    <label for="password2">* 비밀번호 확인<br>
      <input class="pw" id="password2" type="password"><br><br> </label>
    <form>
      <input type="radio" class="radio" name="gender">&nbsp 여성
      <input type="radio" class="radio" name="gender">&nbsp 남성
    </form>
    <br><br>
    <form>
      <input type="checkbox" class="agree">&nbsp 이용약관 개인정보 수집 및 정보이용에 동의합니다.
    </form>
    <hr>
    <hr>
    <button>가입하기</button>
  </div>
</div>
</body>

</html>

