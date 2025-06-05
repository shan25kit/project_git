
package com.example.demo.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.example.demo.dto.Reply;

@Mapper
public interface ReplyDao {

	@Insert("""
			INSERT INTO reply
			    SET regDate = NOW()
			        , updateDate = NOW()
			        , memberId = #{memberId}
			        , relTypeCode = #{relTypeCode}
			        , relId = #{relId}
			        , content = #{content}
			""")
	void writeReply(int memberId, String relTypeCode, int relId, String content);

	@Select("""
			SELECT r.*, m.loginId `writerName`
				FROM reply r
			    INNER JOIN `member` m
			    ON r.memberId = m.id
				WHERE relTypeCode = #{relTypeCode}
				AND relId = #{relId}
			""")
	List<Reply> getReplies(String relTypeCode, int relId);

	@Select("""
			SELECT LAST_INSERT_ID()
			""")
	int getLastInsertReplyId();

	@Select("""
			SELECT r.*, m.loginId `writerName`
				FROM reply r
			    INNER JOIN `member` m
			    ON r.memberId = m.id
				WHERE r.id = #{id}
			""")
	Reply getReplyById(int id);

	@Delete("""
			DELETE FROM reply
				WHERE id = #{id}
			""")
	void deleteReply(int id);

	@Update("""
			UPDATE reply
				SET updateDate = NOW()
					, content = #{content}
				WHERE id = #{id}
			""")
	void modifyReply(int id, String content);

}
