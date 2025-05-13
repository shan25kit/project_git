
package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.Article;

@Mapper
public interface ArticleDao {
	@Insert("""
			INSERT INTO article
				SET regDate =now()
					,updateDate =now()
					,title = #{title}
					,content = #{content}
			""")
	
	
	public int writeArticle(String title, String content);


	@Select("""
			SELECT *
			FROM article
			ORDER BY id DESC
			""")
	
	public List<Article> getArticles();
	
	@Select("""
			SELECT *
			FROM article
			WHERE id = #{id}
			""")
	public Article getArticleById(Integer id);
	
	
	@Update("""
			<script>
			UPDATE article 
			SET updateDate = NOW()
				<if test="title !=null and title != ''">
						 ,title = #{title}
				</if>
				<if test="content != null and content != ''">
						, content = #{content}
				 </if>
			WHERE id = #{id}
			</script>
			""")
	
	public void modifyArticle(int id, String title, String content);

	@Delete("""
			DELETE FROM article
			WHERE id = #{id}
			""")

	public void deleteArticle(int id);
}
