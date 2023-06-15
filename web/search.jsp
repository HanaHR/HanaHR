<%--
  Created by IntelliJ IDEA.
  User: 하나로H015
  Date: 2023-06-15
  Time: 오후 5:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Member Search</title>
</head>
<body>
<h1>Member Search</h1>
<form action="/candidateSearch" method="post">
    <label for="searchName">Search by Name:</label>
    <input type="text" id="searchName" name="searchName">
    <button type="submit">Search</button>
</form>

<%@ page import="javaBeans.User" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>

<% List<User> searchResults = (List<User>) request.getAttribute("searchResults"); %>
<% if (searchResults != null && !searchResults.isEmpty()) { %>
<h2>Search Results:</h2>
<table>
    <thead>
    <tr>
        <th>ID안녕</th>
        <th>Name</th>
        <th>Email</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <% for (User user : searchResults) { %>
    <tr>

        <td><%= user.getMemberName() %></td>
        <td><%= user.getEmail() %></td>

    </tr>
    <% } %>
    </tbody>
</table>
<% } else { %>
<p>No results found.</p>
<% } %>
</body>
</html>