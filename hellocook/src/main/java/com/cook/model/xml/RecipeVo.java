package com.cook.model.xml;

import org.springframework.stereotype.Component;

@Component
public class RecipeVo {
	
//	CREATE TABLE IF NOT EXISTS `mrd_db`.`recipe` (
//			  `recipe_text_uid` INT NOT NULL AUTO_INCREMENT,
//			  `recipe_text` VARCHAR(2000) NULL,
//			  `cook_cook_uid` INT NOT NULL,
//			  `recipe_img_link` VARCHAR(300) NULL,
//			  `cook_introduction` VARCHAR(5000) NULL,
//			  `recipecol` VARCHAR(45) NULL, -> 이거 지웠다
//			  PRIMARY KEY (`recipe_text_uid`, `cook_cook_uid`),
//			  INDEX `fk_recipe_cook1_idx` (`cook_cook_uid` ASC) VISIBLE,
//			  CONSTRAINT `fk_recipe_cook1`
//			    FOREIGN KEY (`cook_cook_uid`)
//			    REFERENCES `mrd_db`.`cook` (`cook_uid`)
//			    ON DELETE CASCADE
//			    ON UPDATE CASCADE)
//			ENGINE = InnoDB;
	
	private int recipe_text_uid;
	private String recipe_text;
	private int cook_cook_uid; // 포링키
	private String recipe_img_link; // setp 부분에만 저장할꺼임.
	private String cook_introduction; // cook_cook_uid 에 해당하는걸 조회하면. 맨 앞 자료는 recipe_text 칼럼이 비어있고. 이 칼럼이 채워져있다.
	
	
	public int getRecipe_text_uid() {
		return recipe_text_uid;
	}
	public void setRecipe_text_uid(int recipe_text_uid) {
		this.recipe_text_uid = recipe_text_uid;
	}
	public String getRecipe_text() {
		return recipe_text;
	}
	public void setRecipe_text(String recipe_text) {
		this.recipe_text = recipe_text;
	}
	public int getCook_cook_uid() {
		return cook_cook_uid;
	}
	public void setCook_cook_uid(int cook_cook_uid) {
		this.cook_cook_uid = cook_cook_uid;
	}
	
	public String getCook_introduction() {
		return cook_introduction;
	}
	public void setCook_introduction(String cook_introduction) {
		this.cook_introduction = cook_introduction;
	}
	
	public String getRecipe_img_link() {
		return recipe_img_link;
	}
	public void setRecipe_img_link(String recipe_img_link) {
		this.recipe_img_link = recipe_img_link;
	}
	
	

}
