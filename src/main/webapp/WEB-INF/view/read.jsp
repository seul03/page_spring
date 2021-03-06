<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>%{boardBP.title}&nbsp내용</title>
</head>
<body>
       <table border="1">
              <tr>
                     <th>제목</th>
                     <td>${boardVO.title}</td>
              </tr>
              <tr>
                     <th>내용</th>
                     <td>${boardVO.content}</td>
              </tr>
              <tr>
                     <th>작성자</th>
                     <td>${boardVO.writer}</td>
              </tr>
              <tr>
                     <th>작성일</th>
                     <td>${boardVO.regDate}</td>
              </tr>
              <tr>
                     <th>조회수</th>
                     <td>${boardVO.cnt}</td>
              </tr>       
              <tr>
                    <th>첨부파일</th>
                    <td><a method="POST" href="<%= request.getContextPath()%>/fileDown/${boardVO.seq}" ><download>${boardVO.fileName}</a></td>
              </tr>
       </table>
       <div>
              <a href="<c:url value="edit/${boardVO.seq}"/>">수정</a>
              <a href="<c:url value="delete/${boardVO.seq}"/>">삭제</a>
              <a href="<C:url value="list"/>">목록</a>
       </div>
       <div id="reply">
       <ol class="replyList">
           <c:forEach items="${replyList}" var="replyList"/>
                <li>
                    <p>
                                              작성자 : ${replyList.write}<br/>
                    </p>
                    
                    <p>${replyList.con}</p>
</body>
</html>