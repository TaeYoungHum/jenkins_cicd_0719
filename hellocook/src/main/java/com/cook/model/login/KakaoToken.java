package com.cook.model.login;

public class KakaoToken {
	
	String token_type;
    String access_token;
    Integer expires_in;
    String refresh_token;
    Integer refresh_token_expires_in;
    String scope;
	
    public String getToken_type() {
		return token_type;
	}
	public void setToken_type(String token_type) {
		this.token_type = token_type;
	}
	public String getAccess_token() {
		return access_token;
	}
	public void setAccess_token(String access_token) {
		this.access_token = access_token;
	}
	public Integer getExpires_in() {
		return expires_in;
	}
	public void setExpires_in(Integer expires_in) {
		this.expires_in = expires_in;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public Integer getRefresh_token_expires_in() {
		return refresh_token_expires_in;
	}
	public void setRefresh_token_expires_in(Integer refresh_token_expires_in) {
		this.refresh_token_expires_in = refresh_token_expires_in;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
    
    
    

}
