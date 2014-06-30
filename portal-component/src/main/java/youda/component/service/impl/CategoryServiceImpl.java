package youda.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.dao.CategoryDAO;
import youda.component.dao.CategoryPageDAO;
import youda.component.model.Category;
import youda.component.model.CategoryPage;
import youda.component.service.CategoryService;

@Component
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDAO categoryDao;
	@Autowired
	CategoryPageDAO categoryPageDao;

	@Override
	public Category getCategoryById(int categoryId) {
		return categoryDao.getCategoryById(categoryId);
	}

	@Override
	public List<Category> getCategoryListByType(String categoryType, int status) {
		List<Category> rtn = categoryDao.getCategoryListByType(categoryType, status);
		return rtn;
	}

	@Override
	public List<Category> getChildCategoryList(int parentCategoryId, int status) {
		List<Category> rtn = categoryDao.getChildCategoryList(parentCategoryId, status);
		return rtn;
	}

	@Override
	public int addNewCategory(Category category) {
		int rtn = 0;
		int cid = categoryDao.addNewCategory(category);
		if(cid <= 0){
			return rtn;
		}
		//Update category list order content
		category = this.getCategoryById(cid);
		category.setListOrder(category.getCatId());
		this.updateCategory(category);
		
		//Check category template
		if(category.getType().equals("PAGE") || category.getType().equals("TOUR")){
			CategoryPage tmp = new CategoryPage();
			tmp.setCatId(category.getCatId());
			categoryPageDao.addCategoryPage(tmp);
		}
		
		//Update Parent category have child status
		Category parentCategory = this.getCategoryById(category.getParentId());
		if(parentCategory != null && parentCategory.getHaveChild() <= 0){
			parentCategory.setHaveChild(1);
			parentCategory.setType("CATEGORY");
			rtn = this.updateCategory(parentCategory);
		}
		return rtn;
	}

	@Override
	public int updateCategory(Category category) {
		return categoryDao.updateCategory(category);

	}

	@Override
	public void updateCategoryChild(int categoryId) {
		Category category = this.getCategoryById(categoryId);
		// Update Parent category have child status
		if (category != null){
			if(checkCategoryChild(category.getCatId()) > 0) {
				category.setHaveChild(1);
				category.setType("CATEGORY");
				this.updateCategory(category);
			}
			else{
				category.setHaveChild(0);
				category.setType("NEWS-CATEGORY");
				this.updateCategory(category);
			}
		}
	}

	@Override
	public int delCategory(int categoryId) {
		Category category = this.getCategoryById(categoryId);
		if(category.getHaveChild() > 0){
			List<Category> categoryList = this.getChildCategoryList(category.getCatId(), 1);
			for(Category tmp : categoryList){
				this.delCategory(tmp.getCatId());
			}
		}
		categoryPageDao.delCategoryPageByCatId(categoryId);
		int rtn = categoryDao.delCategory(categoryId);
		return rtn;
	}

	@Override
	public int categoryMoveUp(int categoryId) {
		Category category = this.getCategoryById(categoryId);
		Category preCategory = categoryDao.getPreCategory(category.getParentId(), category.getListOrder());
		if(preCategory == null){
			return 0;
		}
		//Change list order
		int tmp = preCategory.getListOrder();
		preCategory.setListOrder(category.getListOrder());
		this.updateCategory(preCategory);
		
		category.setListOrder(tmp);
		this.updateCategory(category);
		
		return 1;
	}

	@Override
	public int categoryMoveDown(int categoryId) {
		Category category = this.getCategoryById(categoryId);
		Category nextCategory = categoryDao.getNextCategory(category.getParentId(), category.getListOrder());
		if(nextCategory == null){
			return 0;
		}
		//Change list order
		int tmp = nextCategory.getListOrder();
		nextCategory.setListOrder(category.getListOrder());
		this.updateCategory(nextCategory);
		
		category.setListOrder(tmp);
		this.updateCategory(category);
		
		return 1;
	}

	@Override
	public int checkCategoryName(int categoryId, String categoryName) {
		return categoryDao.checkCategoryName(categoryId, categoryName);
	}

	@Override
	public int checkCategoryPathName(int categoryId, String categoryPathName) {
		return categoryDao.checkCategoryPathName(categoryId, categoryPathName);
	}

	@Override
	public void updateParentCategoryStatus(int categoryId) {
		Category category = this.getCategoryById(categoryId);
		if(category == null){
			return;
		}
		List<Category> childCategoryList = this.getChildCategoryList(category.getCatId(), 1);
		if(childCategoryList == null || childCategoryList.size() <= 0){
			category.setHaveChild(0);
			category.setType("NEWS-CATEGORY");
			this.updateCategory(category);
		}
	}

	@Override
	public void generateCategoryTree(int parentId, List<Category> rtn, int level) {
		List<Category> categoryList = this.getChildCategoryList(parentId, 1);
		int size = categoryList.size();
		for(int i = 0; i < size; i++){
			Category category = categoryList.get(i);
			category.setLevel(level);
			if(0 == i){
				category.setStartFlag(1);
			}
			if(1 == (size - i)){
				category.setLastFlag(1);
			}
			rtn.add(category);
			if(category.getHaveChild() > 0){
				generateCategoryTree(category.getCatId(), rtn, level+1);
			}
		}
	}

	@Override
	public CategoryPage getCategoryPageByCatId(int catId) {
		return categoryPageDao.getCategoryPageByCatId(catId);
	}

	@Override
	public int updateCategoryPage(CategoryPage categoryPage) {
		return categoryPageDao.updateCategoryPage(categoryPage);
	}
	
	private int checkCategoryChild(int cid){
		return categoryDao.countCategoryChild(cid);
	}
	
	/**
	 * 读取课程类别
	 */
	public List<Category> getCourseCategory(){
		return this.categoryDao.getCourseCategory();
	}
}
