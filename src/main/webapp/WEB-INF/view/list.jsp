<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>목록</title>
</head>
<body>

       <table boarder="1">
              <tr>
                     <th>NO</th>
                     <th>제목</th>
                     <th>작성자</th>
                     <th>작성일</th>
                     <th>조회수</th>
              </tr>
              <c:forEach var="boardVO" items="${list}" varStatus="loop">
                     <tr>
                            <td>${boardVO.seq}</td>
                            <td><a href="<c:url value="/read/${boardVO.seq}" /> ">
                                   ${boardVO.title}</a></td>
                            <td>${boardVO.writer}</td>
                            <td>${boardVO.regDate}</td>
                            <td>${boardVO.cnt}</td>
                     </tr>  
              </c:forEach>
       </table>
       <a href="<c:url value="write" />">새글</a>
       <div>
		<ul>
			<c:if test="${pagingVO.prev}">
				<li><a href="<%= request.getContextPath()%>/list/${pagingVO.makeQuery(pagingVO.startPage - 1)}">이전</a></li>
			</c:if>
			
			 <c:forEach begin="${pagingVO.startPage}" end="${pagingVO.endPage}" var="idx">
    	<li><a href="list${pagingVO.makeQuery(idx)}">${idx}</a></li>
   			 </c:forEach>
			
			<c:if test="${pagingVO.next && pagingVO.endPage > 0}">
				<li><a href="<%= request.getContextPath()%>/list/${pagingVO.makeQuery(pagingVO.endPage + 1)}">다음</a></li>
			</c:if>
		</ul>
	</div>
</body>
</html>