<%--
  Created by IntelliJ IDEA.
  User: 하나로H014
  Date: 2023-06-13
  Time: 오후 3:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<style>
  .input-box{
    position:relative;
    margin:10px 0;
  }

  .input-box > input{
    background:transparent;
    border:none;
    border-bottom: solid 1px #ccc;
    padding:20px 0px 5px 0px;
    font-size:14pt;
    width:100%;
  }

  input::placeholder{
    color:transparent;
  }

  input:placeholder-shown + label{
    color:#aaa;
    font-size:14pt;
    top:15px;
  }

  input:focus + label, label{
    color:#8aa1a1;
    font-size:10pt;
    pointer-events: none;
    position: absolute;
    left:0px;
    top:0px;
    transition: all 0.2s ease ;
    -webkit-transition: all 0.2s ease;
    -moz-transition: all 0.2s ease;
    -o-transition: all 0.2s ease;
  }

  input:focus, input:not(:placeholder-shown){
    border-bottom: solid 1px #8aa1a1;
    outline:none;
  }




</style>
<head>
  <title>Title</title>
</head>
<body>
<html>
<head>
  <meta name="viewport" content="width=device-width, height=device-height, minimum-scale=1.0, maximum-scale=1.0, initial-scale=1.0">
</head>
<body>
<header>
  <h2>Login</h2>
</header>

<form action="login" method="POST">

  <div class="input-box">
    <input id="username" type="text" name="adminId" placeholder="아이디">
    <label for="username">아이디</label>
  </div>

  <div class="input-box">
    <input id="password" type="password" name="adminPw" placeholder="비밀번호">
    <label for="password">비밀번호</label>
  </div>

  <input type="submit" value="로그인">

</form>

</body>
</html>

</body>
</html>
