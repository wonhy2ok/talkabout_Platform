package com.inhatc.talkabout.common.mapper;

import com.inhatc.talkabout.common.vo.ChatVo;
import com.inhatc.talkabout.common.vo.CommonVo;
import com.inhatc.talkabout.common.vo.KeywordVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
@Mapper
public interface CommonMapper {
    List<CommonVo> selectTest();

    void addUser(Map<String, String> userData);

    Boolean srhUser1(Map<String, String> userData);

    Boolean srhUser2(Map<String, String> userData);

    void udtScuUser(Map<String, String> userData);

    void addScuUser(Map<String, String> userData);

    List<CommonVo> chkUserPass(Map<String, String> userData);

    void udtUserAuth(Map<String, String> paramMap);

    public List<String> readAuthority(String username);

    Object loginUser(Map<String, String> userData);

    List<ChatVo> srchTitle();

    List<KeywordVo> analGraph();

    void userInfo(Map<String, String> userData);

    List<CommonVo> srhUserInfo(Map<String, String> userData);

    int udtUserInfo(Map<String, String> userData);

    int UdtUserPass(Map<String, String> userData);
}
