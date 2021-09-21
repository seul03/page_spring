<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>글 작성</title>
</head>
<body>
       <form action="<c:url value="/board/write" /> " method="POST" enctype="multipart/form-data" />
              <table border="1">
                     <tr>
                            <th><form:label path="title">제목</form:label><th>
                            <td><form:input path="title" />
                            <form:errors path="title" /> </td>
                     </tr>
                     <tr>
                            <th><form:label path="content">내용</form:label></th>
                            <td><form:input path="content"/>
                            <form:errors path="content"/></td>
                     </tr>
                     <tr>
                            <th><form:label path="writer">작성자</form:label></th>
                            <td><form:input path="writer"/>
                            <form:errors path="writer"/></td>
                     </tr>
                     <tr>
                            <th><form:label path="password">비밀번호</form:label></th>
                            <td><form:input path="password"/>
                            <form:errors path="password" /></td>
                     </tr>
                     <tr>
                            <td>
                            <input type="file" name="file">
                            </td>
                     </tr>                                
               </table>
               <div>
                       <input type="submit" value="등록">
                       <a href ="<c:url value="/board/list" />" >목록</a>
               </div>
               </form>
</body>
</html>