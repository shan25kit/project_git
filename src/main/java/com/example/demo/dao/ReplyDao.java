
package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.Reply;

@Mapper
public interface ReplyDao {
	@Insert("""
				INSERT INTO reply
			    SET regDate = NOW()
				    , updateDate = NOW()
			        , memberId = #{memberId}
			        , relId = #{relId}
			        , relTypeCode = #{relTypeCode}
				    , content = #{content}
			""")

	void writeReply(int memberId, int relId, String relTypeCode, String content);

	@Select("""
			SELECT r.*, m.loginId AS writerName 
			FROM reply r
			INNER JOIN `member` m
			ON r.memberId = m.id
			WHERE r.relId = #{relId}
			AND r.relTypeCode = #{relTypeCode}
			""")
	
	List<Reply> getReplies(String relTypeCode, int relId);


//	@Select("""
//			<script>
//			SELECT a.*
//					, m.loginId AS writerName
//					, COUNT(l.memberId) AS `likePoint`
//			    FROM article a
//			    INNER JOIN `member` m
//			    ON a.memberId = m.id
//			    LEFT JOIN likePoint l
//			    ON l.relTypeCode = 'article'
//			    AND l.relId = a.id
//			    WHERE a.boardId = #{boardId}
//			    <if test="searchKeyword != ''">
//			    	<choose>
//			    		<when test="searchType == 'title'">
//				    		AND a.title LIKE CONCAT('%', #{searchKeyword}, '%')
//			    		</when>
//					    <when test="searchType == 'content'">
//					    	AND a.content LIKE CONCAT('%', #{searchKeyword}, '%')
//					    </when>
//					    <otherwise>
//					    	AND (
//					    		a.title LIKE CONCAT('%', #{searchKeyword}, '%')
//					    		OR a.content LIKE CONCAT('%', #{searchKeyword}, '%')
//					    	)
//					    </otherwise>
//			    	</choose>
//			    </if>
//			    GROUP BY a.id
//				ORDER BY a.id DESC
//				LIMIT #{limitFrom}, #{articlesInPage}
//			</script>
//			""")
//	public List<Article> getArticles(int boardId, int articlesInPage, int limitFrom, String searchType, String searchKeyword);
//	
//	@Select("""
//			SELECT a.*, m.loginId AS writerName
//			    FROM article a
//			    INNER JOIN `member` m
//			    ON a.memberId = m.id
//				WHERE a.id = #{id}
//			""")
//	public Article getArticleById(int id);
//
//	@Update("""
//			UPDATE article
//			    SET updateDate = NOW()
//			        , title = #{title}
//			        , content = #{content}
//			    WHERE id = #{id}
//			""")
//	public void modifyArticle(int id, String title, String content);
//
//	@Delete("""
//			DELETE FROM article
//				WHERE id = #{id}
//			""")
//	public void deleteArticle(int id);
//
//	@Select("""
//			SELECT LAST_INSERT_ID()
//			""")
//	public int getLastArticleId();
//
//	@Select("""
//			<script>
//			SELECT COUNT(id)
//				FROM article
//				WHERE boardId = #{boardId}
//				<if test="searchKeyword != ''">
//			    	<choose>
//			    		<when test="searchType == 'title'">
//				    		AND title LIKE CONCAT('%', #{searchKeyword}, '%')
//			    		</when>
//					    <when test="searchType == 'content'">
//					    	AND content LIKE CONCAT('%', #{searchKeyword}, '%')
//					    </when>
//					    <otherwise>
//					    	AND (
//					    		title LIKE CONCAT('%', #{searchKeyword}, '%')
//					    		OR content LIKE CONCAT('%', #{searchKeyword}, '%')
//					    	)
//					    </otherwise>
//			    	</choose>
//			    </if>
//			</script>
//			""")
//	public int getArticlesCnt(int boardId, String searchType, String searchKeyword);
//
//	@Update("""
//			UPDATE article
//				SET views = views + 1
//				WHERE id = #{id}
//			""")
//	public void increaseViews(int id);
}
