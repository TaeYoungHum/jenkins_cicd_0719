package com.cook.controller;

import org.springframework.ui.Model;

public interface RecipeController {
	public String viewMain(Model model) throws Exception; //홈페이지 
	public String callSubPage(Model model) throws Exception; //서브페이지 
	public String callRecipePage(Model model, String cookUid) throws Exception; //레시피페이지
	public String callWritePage() throws Exception; //작성페이지 
	public String callMyPage() throws Exception; //마이페이지
	public String callLoginPage() throws Exception; //로그인페이지
	public String callSignUpPage() throws Exception; //테스 찾기 페이지
	public String viewSearch(String  cookName) throws Exception; //회원가입페이지
}
