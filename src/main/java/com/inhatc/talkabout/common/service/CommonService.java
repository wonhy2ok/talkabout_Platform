package com.inhatc.talkabout.common.service;

import com.inhatc.talkabout.common.mapper.CommonMapper;
import com.inhatc.talkabout.common.vo.ChatVo;
import com.inhatc.talkabout.common.vo.CommonVo;
import com.inhatc.talkabout.common.vo.KeywordVo;
import com.inhatc.talkabout.common.vo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CommonService {

    @Autowired
    public CommonMapper mapper;

    public List<CommonVo> selectTest() {
        return mapper.selectTest();
    }

    public void addUser(Map<String, String> userData) {
        mapper.addUser(userData);
    }

    /**
     * 사용자 존재여부확인
     * @param userData
     * @return
     */
    public Boolean srhUser1(Map<String, String> userData) {
        return mapper.srhUser1(userData);
    }

    public Boolean srhUser2(Map<String, String> userData) {
        return mapper.srhUser2(userData);
    }

    public void udtScuUser(Map<String, String> userData) {
        mapper.udtScuUser(userData);
    }

    public void addScuUser(Map<String, String> userData) {
        mapper.addScuUser(userData);
    }

    public List<CommonVo> chkUserPass(Map<String, String> userData) {
        return mapper.chkUserPass(userData);
    }

    public void udtUserAuth(Map<String, String> paramMap) {
        mapper.udtUserAuth(paramMap);
    }

    //로그인 사용자 인증
    public UserInfo loginUser(Map<String, String> userData) {
        return (UserInfo) mapper.loginUser(userData);
    }

    public List<ChatVo> srchTitle() {
        return mapper.srchTitle();
    }

    public List<KeywordVo> analGraph() {
        return mapper.analGraph();
    }

    public List<CommonVo> srhUserInfo(Map<String, String> userData) {
        return mapper.srhUserInfo(userData);
    }
}
