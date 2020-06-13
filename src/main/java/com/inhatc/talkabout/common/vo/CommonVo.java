package com.inhatc.talkabout.common.vo;
//import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class CommonVo {
    private String userName;
    private String userId;
    private String userPw;
    private String nick;
    private String birth;
    private String contact;
    private String userState;
    private String userRole;
    private String scuPass;
    private boolean isAccountNonExpired;
    private boolean isAccountNonLocked;
    private boolean isCredentialsNonExpired;
    private boolean isEnabled;

    //private Collection<? extends GrantedAuthority> authorities;

    public String getUserPw() {  return userPw; }

    public void setUserPw(String userPw) {
        this.userPw = userPw;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getUserState() {
        return userState;
    }

    public void setUserState(String userState) {
        this.userState = userState;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getScuPass() {
        return scuPass;
    }

    public void setScuPass(String scuPass) {
        this.scuPass = scuPass;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isAccountNonExpired() {
        return isAccountNonExpired;
    }

    public void setAccountNonExpired(boolean isAccountNonExpired) {
        this.isAccountNonExpired = isAccountNonExpired;
    }

    public boolean isAccountNonLocked() {
        return isAccountNonLocked;
    }

    public void setAccountNonLocked(boolean isAccountNonLocked) {
        this.isAccountNonLocked = isAccountNonLocked;
    }

    public boolean isCredentialsNonExpired() {
        return isCredentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean isCredentialsNonExpired) {
        this.isCredentialsNonExpired = isCredentialsNonExpired;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    //public Collection<? extends GrantedAuthority> getAuthorities() {
    //    return authorities;
   // }

//    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
    //    this.authorities = authorities;
  //  }
}
