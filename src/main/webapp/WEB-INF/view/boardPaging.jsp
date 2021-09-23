<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>게시판 목록</title>
</head>

<script>
	function selChange() {
		var sel = document.getElementById('cntPage').value;
		location.href="boardList?now=${paging.now}&cntPage="+sel;
	}
</script>
<body>
<h2>게시판</h2>

<div id="outter">
	<div style="float: right;">
		<select id="cntPage" name="sel" onchange="selChange()">
			<option value="5"
				<c:if test="${paging.cntPage == 5}">selected</c:if>>5줄 보기</option>
			<option value="10"
				<c:if test="${paging.cntPage == 10}">selected</c:if>>10줄 보기</option>
			<option value="15"
				<c:if test="${paging.cntPage == 15}">selected</c:if>>15줄 보기</option>
			<option value="20"
				<c:if test="${paging.cntPage == 20}">selected</c:if>>20줄 보기</option>
		</select>
	</div> <!-- 옵션선택 끝 -->
	<table border="1">
		<tr>
			<td>No.</td>
			<td width="50%">제목</td>
			<td>작성자</td>
			<td>등록일</td>
			<td>조회수</td>		
		</tr>
		<c:forEach items="${viewAll }" var="list">
			<tr>
				<td>${list.seq }</td>
				<td><a href='detail?seq=${list.seq }'>${list.title }</a></td>
				<td>${list.writer }</td>
				<td><fmt:formatDate value="${list.regdate }" pattern="yyyy.MM.dd"/> </td>
				<td>${list.cnt }</td>
			</tr>
		</c:forEach>
	</table>
	<input type="button" value="글쓰기" style="float: right;" onclick="location.href='/write'"><br>
	
	<div style="display: block; text-align: center;">		
		<c:if test="${paging.startPage != 1 }">
			<a href="/boardList?now=${paging.startPage - 1 }&cntPerPage=${paging.cntPage}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage }" end="${paging.endPage }" var="p">
			<c:choose>
				<c:when test="${p == paging.now }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.now }">
					<a href="/boardList?now=${p }&cntPage=${paging.cntPage}">${p }</a>
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="/boardList?now=${paging.endPage+1 }&cntPage=${paging.cntPage}">&gt;</a>
		</c:if>
	</div>
</div>
</body>
</html>