package youda.component.service;

import java.util.List;

import youda.component.model.Category;
import youda.component.model.CategoryPage;

public interface CategoryService {

	public Category getCategoryById(int categoryId);
	
	public List<Category> getCategoryListByType(String categoryType, int status);
	
	public List<Category> getChildCategoryList(int parentCategoryId, int status);
	
	public int addNewCategory(Category category);
	
	public int updateCategory(Category category);
	
	public int delCategory(int categoryId);
	
	public int categoryMoveUp(int categoryId);
	
	public int categoryMoveDown(int categoryId);
	
	public int checkCategoryName(int categoryId, String categoryName);
	
	public int checkCategoryPathName(int categoryId, String categoryPathName);
	
	public void updateParentCategoryStatus(int categoryId);
	
	public void generateCategoryTree(int parentId, List<Category> rtn, int level);
	
	public CategoryPage getCategoryPageByCatId(int catId);
	
	public int updateCategoryPage(CategoryPage categoryPage);
	
	public void updateCategoryChild(int categoryId);
	
	/**
	 * 读取课程类别
	 */
	public List<Category> getCourseCategory();
	
}
