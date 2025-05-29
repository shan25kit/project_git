
package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.dto.Reply;
import com.example.demo.dto.Req;
import com.example.demo.dto.ResultData;
import com.example.demo.service.ReplyService;

@Controller
public class UsrReplyController {

	private ReplyService replyService;
	private Req req;

	public UsrReplyController(ReplyService replyService, Req req) {
		this.replyService = replyService;
		this.req = req;
	}

	@PostMapping("/usr/reply/doReply")
	@ResponseBody
	public String doReply(int relId, String relTypeCode, String content) {

		this.replyService.writeReply(req.getLoginedMember().getId(), relId, relTypeCode, content);

		return "댓글작성 완료";
	}

	@GetMapping("/usr/reply/getReplies")
	@ResponseBody
	public ResultData<List>getReplies(String relTypeCode, int relId) {

		List<Reply>replies = this.replyService.getReplies(relTypeCode, relId);
		
		System.out.println(replies);
		
		if (replies.isEmpty()) {
			return ResultData.from("F-1", "해당 게시물에 댓글이 없습니다.");
		}

		return ResultData.from("S-1", "댓글 정보 조회 성공", replies);
	}
//	@GetMapping("/usr/article/list")
//	public String list(Model model, int boardId, @RequestParam(defaultValue = "1") int cPage,
//			@RequestParam(defaultValue = "title") String searchType,
//			@RequestParam(defaultValue = "") String searchKeyword) {
//
//		int articlesInPage = 10;
//		int limitFrom = (cPage - 1) * articlesInPage;
//
//		int articlesCnt = this.articleService.getArticlesCnt(boardId, searchType, searchKeyword);
//
//		int totalPagesCnt = (int) Math.ceil(articlesCnt / (double) articlesInPage);
//
//		int begin = ((cPage - 1) / 10) * 10 + 1;
//		int end = (((cPage - 1) / 10) + 1) * 10;
//
//		if (end > totalPagesCnt) {
//			end = totalPagesCnt;
//		}
//
//		Board board = this.boardService.getBoard(boardId);
//		List<Article> articles = this.articleService.getArticles(boardId, articlesInPage, limitFrom, searchType,
//				searchKeyword);
//
//		model.addAttribute("searchType", searchType);
//		model.addAttribute("searchKeyword", searchKeyword);
//		model.addAttribute("cPage", cPage);
//		model.addAttribute("begin", begin);
//		model.addAttribute("end", end);
//		model.addAttribute("totalPagesCnt", totalPagesCnt);
//		model.addAttribute("articlesCnt", articlesCnt);
//		model.addAttribute("articles", articles);
//		model.addAttribute("board", board);
//
//		return "usr/article/list";
//	}
//
//	@GetMapping("/usr/article/detail")
//	public Object detail(HttpServletRequest request, HttpServletResponse response, Model model, int id) {
//
//		Cookie[] cookies = request.getCookies();
//		boolean isViewed = false;
//		
//		if (cookies != null) {
//			for (Cookie cookie : cookies) {
//				if (cookie.getName().equals("viewedArticle_" + id)) {
//					isViewed = true;
//					break;
//				}
//			}
//		}
//		
//		if (!isViewed) {
//			this.articleService.increaseViews(id);
//			Cookie cookie = new Cookie("viewedArticle_" + id, "true");
//			cookie.setMaxAge(60 * 30);
//			response.addCookie(cookie);
//		}
//		
//		Article article = this.articleService.getArticleById(id);
//		
//		model.addAttribute("article", article);
//
//		return "usr/article/detail";
//	}
//
//	@GetMapping("/usr/article/modify")
//	public String modify(Model model, int id) {
//
//		Article article = this.articleService.getArticleById(id);
//
//		model.addAttribute("article", article);
//
//		return "usr/article/modify";
//	}
//
//	@PostMapping("/usr/article/doModify")
//	@ResponseBody
//	public String doModify(int id, String title, String content) {
//
//		this.articleService.modifyArticle(id, title, content);
//
//		return Util.jsReplace(String.format("%d번 게시물을 수정했습니다", id), String.format("detail?id=%d", id));
//	}
//
//	@GetMapping("/usr/article/delete")
//	@ResponseBody
//	public String delete(int id, int boardId) {
//
//		this.articleService.deleteArticle(id);
//
//		return Util.jsReplace(String.format("%d번 게시글이 삭제되었습니다", id), String.format("list?boardId=%d", boardId));
//	}
}
