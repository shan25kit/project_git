package com.example.demo.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.example.demo.dto.Member;

@Mapper
public interface MemberDao {
	
	
	
	@Insert("""
			INSERT INTO `member`
				SET regDate =NOW()
					,updateDate =NOW()
					,loginId = #{loginId}
					,loginPw = #{loginPw}
					,`name`=#{name}
			""")

	public void doJoin(String loginId, String loginPw, String name);

	
	@Select("""
			SELECT * 
			FROM `member`
			WHERE loginId = #{loginId}
			""")
	
	public Member getMemberByLoginId(String loginId);
}
