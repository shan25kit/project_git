
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<c:set var="pageTitle" value="상세보기" />

<%@ include file="/WEB-INF/jsp/common/header.jsp"%>

<script>
		$(function(){
			getLikePoint();
			getReplies();
		})
	
		const clickLikePoint = async function () {
			let likePointBtn = $('#likePointBtn > i').hasClass('fa-solid');
			
			await $.ajax({
				url : '/usr/likePoint/clickLikePoint',
				type : 'GET',
				data : {
					relTypeCode : 'article',
					relId : ${article.getId() },
					likePointBtn : likePointBtn
				},
			})
			await getLikePoint();
		}
		
		const getLikePoint = function () {
			$.ajax({
				url : '/usr/likePoint/getLikePoint',
				type : 'GET',
				data : {
					relTypeCode : 'article',
					relId : ${article.getId() },
				},
				dataType : 'json',
				success : function (data) {
					$('#likePointCnt').html(data.rsData);
					
					
					if (data.success) {
						$('#likePointBtn').html(`<i class="fa-solid fa-thumbs-up"></i>`);
					} else {
						$('#likePointBtn').html(`<i class="fa-regular fa-thumbs-up"></i>`);
					}
				},
				error : function (xhr, status, error) {
					console.log(error);
				}
			})
		}
		const writeReply = function () {
			let replyContent = $('#replyContent');
			
			if (replyContent.val().length == 0) {
				alert('내용이 없는 댓글은 작성할 수 없습니다');
				replyContent.focus();
				return;
			}
			$.ajax({
				url: '/usr/reply/doReply',
				type: 'POST',
				data: {
					relId: ${article.getId()},
					relTypeCode: 'article',
					content: replyContent
				},
				dataType : 'text',
				success : function (data) {
					console.log(data);
					$('#reply-content').val('');
				},
				error: function (xhr, status, error) {
					console.log(error);
				}
			});
		}
		
		const getReplies = function () {
			$.ajax({
				url: '/usr/reply/getReplies',
				type: 'GET',
				data: {
					relTypeCode: 'article',
					relId: ${article.getId()}
				},
				dataType: 'json',
				success: function (data) {
						$.each(data.rsData, function (index, Reply) {
							$('#reply-list').append(`
									<li>${Reply.writerName}</li>
									<li>${Reply.content}</li>
									<li>${Reply.updateDate}</li>
									`);
						});
					},
				error: function (xhr, status, error) {
					console.error("댓글 불러오기 실패:", error);
					$('#reply-list').html("<li class='text-red-500'>댓글을 불러오지 못했습니다.</li>");
				}
			});
		};
	</script>

<section class="mt-8">
	<div class="container mx-auto">
		<div class="table-box">
			<table class="table">
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
					<th>추천수</th>
					<td><c:if test="${req.getLoginedMember().getId() == 0 }">
							<span id="likePointCnt"></span>
						</c:if> <c:if test="${req.getLoginedMember().getId() != 0 }">
							<button class="btn btn-neutral btn-outline btn-xs"
								onclick="clickLikePoint();">
								<span id="likePointCnt"></span> <span id="likePointBtn"></span>
							</button>
						</c:if></td>
				</tr>
				<tr>
					<th>조회수</th>
					<td>${article.getViews() }</td>
				</tr>
				<tr>
					<th>작성자</th>
					<td>${article.getWriterName() }</td>
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
		<div class="bg-white p-6 flex justify-between">
			<div>
				<button class="btn btn-neutral btn-outline btn-xs"
					onclick="history.back();">뒤로가기</button>
			</div>
			<c:if
				test="${article.getMemberId() == req.getLoginedMember().getId() }">
				<div class="flex">
					<div class="mr-2">
						<a class="btn btn-neutral btn-outline btn-xs"
							href="modify?id=${article.getId() }">수정</a>
					</div>
					<div>
						<a class="btn btn-neutral btn-outline btn-xs"
							href="delete?id=${article.getId() }&boardId=${article.getBoardId() }"
							onclick="if(confirm('정말로 삭제하시겠습니까?') == false) return false;">삭제</a>
					</div>
				</div>
			</c:if>
		</div>
		<section class="mt-4">
			<div class="container mx-auto ">
				<div id="reply-list">댓글</div>
				<div id="reply-form">
					<c:if test="${req.getLoginedMember().getId() != 0}">
						<div class="mt-3 mb-2 font-semibold text-sm">${req.getLoginedMember().getLoginId() }</div>
						<textarea id="reply-content" style="width: 100%; resize: none;"
							placeholder="댓글을 입력하세요"></textarea>

						<%-- <c:if>
				test="${reply.getMemberId() == req.getLoginedMember().getId() }">
				<div class="flex">
					<div class="mr-2">
						<a class="btn btn-neutral btn-outline btn-xs"
							href="modify?id=${reply.getId() }">수정</a>
					</div>
					<div>
						<a class="btn btn-neutral btn-outline btn-xs"
							href="delete?id=${reply.getId() }}"
							onclick="if(confirm('정말로 삭제하시겠습니까?') == false) return false;">삭제</a>
					</div>
				</div>
			</c:if> --%>
						<div class="flex justify-end my-2">
							<button id="submit-reply"
								class="btn btn-neutral btn-outline btn-xs"
								onclick="submitReply();">등록</button>
						</div>
					</c:if>
				</div>


				<%-- <div class="table-box">
			<form action="doReply" method="post" onsubmit="return doReply(this);">
				<div class="table-box">
					<table class="table">
						<tr>
							<td><textarea class="input input-neutral" name="content"  
									onblur="doReply(this);"></textarea>
								<button class="btn btn-square btn-ghost">
									<svg xmlns="http://www.w3.org/2000/svg" fill="none"viewBox="0 0 24 24"
										class="inline-block h-5 w-5 stroke-current"> 
										<path stroke-linecap="round" stroke-linejoin="round" stroke-width="2"d="M5 12h.01M12 12h.01M19 12h.01M6 12a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0zm7 0a1 1 0 11-2 0 1 1 0 012 0z"></path> </svg>
								</button></td>
							<td><button class="btn btn-neutral btn-outline btn-sm btn-wide">등록</button></td>
						</tr>
					</table>
				</div>
			</form>
			<table>
				<tr>
					<td>${reply.getWriterName() }</td>
				</tr>
				<tr>
					<td>${reply.getTitle() }</td>
				</tr>
				<tr>
					<td>${reply.getContent() }</td>
				</tr>
			</table>
		</div>
 --%>
			</div>
		</section>

		<%@ include file="/WEB-INF/jsp/common/footer.jsp"%>