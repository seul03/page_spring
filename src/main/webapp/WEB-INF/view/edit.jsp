<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>수정</title>
</head>
<body>
      <form:form commandName="boardVO" method="POST">
            <table border="1">
                   <tr>
                          <th>제목</th>
                          <td><input name="title" />
                          <form:errors path="title" /> </td>
                   </tr>
                   <tr>
                          <th>내용</th>
                          <td><input name="command" />
                          <form:errors path="command" /> </td>
                   </tr>
                   <tr>
                          <th>작성자</th>
                          <td><input name="writer" />
                          <form:errors path="writer" /> </td>
                   </tr>
                   <tr>
                          <th>비밀번호</th>
                          <td><input type="password" id="pwd" name="pwd"
                          />${msg}</td>
                          <tr>
               </table>
               <div>
                      <input type="submit" value="등록">
                      <a href="<c:url value="list" />">목록</a>
               </div>
               </form:form>
</body>
</html>