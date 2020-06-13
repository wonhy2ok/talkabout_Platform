package com.inhatc.talkabout.common.vo;

import jdk.nashorn.internal.objects.annotations.Getter;
import jdk.nashorn.internal.objects.annotations.Setter;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import java.io.Serializable;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)

public class UserInfo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String userId;
    private String userNm;
    private String userRole;
    private String userState;

    public String getUserRole() {
        return userRole;
    }
    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    public String getUserState() {
        return userState;
    }
    public void setUserState(String userState) {
        this.userState = userState;
    }
    public String getUserId() {
        return userId;
    }
    public void setUserId(String userId) {
        this.userId = userId;
    }
    public String getUserNm() {
        return userNm;
    }
    public void setUserNm(String userNm) {
        this.userNm = userNm;
    }
    @Override
    public String toString() {
        return "UserInfo{" +
                "userId=" + userId +
                ", userNm='" + userNm + '\'' +
                ", userRole='" + userRole + '\'' +
                ", userState='" + userState + '\'' +
                '}';
    }

    public Boolean isEmpty() {
        String str = this.userId;
        if(this.userId.isEmpty()){
            return true;
        }else return false;
    }
}