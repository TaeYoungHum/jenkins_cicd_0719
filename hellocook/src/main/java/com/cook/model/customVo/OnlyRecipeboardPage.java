package com.cook.model.customVo;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;


@Component
public class OnlyRecipeboardPage {
	
	private String CookTitle;
	private String introduceCook;
	private String tags;
	
	private String number_of_people;
	private String cook_time;
	private String preparation_time;
	
	private List<String> products;
	private List<String> grams;
	private List<String> recipeFullTexts;
	
	private List<MultipartFile> images; 
	
	
	
	public List<MultipartFile> getImages() {
		return images;
	}
	public void setImages(List<MultipartFile> images) {
		this.images = images;
	}
	public String getCookTitle() {
		return CookTitle;
	}
	public void setCookTitle(String cookTitle) {
		CookTitle = cookTitle;
	}
	public String getIntroduceCook() {
		return introduceCook;
	}
	public void setIntroduceCook(String introduceCook) {
		this.introduceCook = introduceCook;
	}
	public String getTags() {
		return tags;
	}
	public void setTags(String tags) {
		this.tags = tags;
	}
	
	public List<String> getProducts() {
		return products;
	}
	public void setProducts(List<String> products) {
		this.products = products;
	}
	public List<String> getGrams() {
		return grams;
	}
	public void setGrams(List<String> grams) {
		this.grams = grams;
	}
	public List<String> getRecipeFullTexts() {
		return recipeFullTexts;
	}
	public void setRecipeFullTexts(List<String> recipeFullTexts) {
		this.recipeFullTexts = recipeFullTexts;
	}
	public String getNumber_of_people() {
		return number_of_people;
	}
	public void setNumber_of_people(String number_of_people) {
		this.number_of_people = number_of_people;
	}
	public String getCook_time() {
		return cook_time;
	}
	public void setCook_time(String cook_time) {
		this.cook_time = cook_time;
	}
	public String getPreparation_time() {
		return preparation_time;
	}
	public void setPreparation_time(String preparation_time) {
		this.preparation_time = preparation_time;
	}
	
	

}
