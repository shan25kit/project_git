<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="상세보기" />

<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<section class="mt-8">
	<div class="container mx-auto">
		<div class="table-box">
			<table class="w-full">
				<tr>
					<th>번호</th>
					<td>${article.getId() }</td>
				</tr>
				<tr>
					<th>작성일</th>
					<td>${article.getRegDate().substring(2, 16) }</td>
				</tr>
				<tr>
					<th>수정일</th>
					<td>${article.getUpdateDate().substring(2, 16) }</td>
				</tr>
				<tr>
					<th>제목</th>
					<td>${article.getTitle() }</td>
				</tr>
				<tr>
					<th>내용</th>
					<td>${article.getContent() }</td>
				</tr>
			</table>
		</div>

		<div class="mt-3 flex justify-between text-sm btns">
			<div>
				<button onclick="history.back();">뒤로가기</button>
			</div>
			<div class = "flex">
				<div class="flex items-center" >
					<a href="modify?id=${article.getId()  }">수정</a>
				</div>
				<div class="flex items-center ml-1">
					<a onclick="if(confirm('정말로 삭제하시겠습니까?')== false) return false;"
						href="delete?id=${article.getId()  }">삭제</a>
				</div>

			</div>

		</div>
	</div>
</section>

<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>