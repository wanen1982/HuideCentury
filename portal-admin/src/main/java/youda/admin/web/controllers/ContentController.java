package youda.admin.web.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import youda.admin.web.utils.AjaxUtils;
import youda.admin.web.utils.BeanJsonUtils;
import youda.admin.web.utils.StaticParameters;
import youda.component.model.Category;
import youda.component.model.CategoryPage;
import youda.component.model.Crumb;
import youda.component.model.ModelField;
import youda.component.model.News;
import youda.component.model.ResponseJSONBean;
import youda.component.model.Teacher;
import youda.component.service.AttachmentService;
import youda.component.service.CategoryService;
import youda.component.service.ModelService;
import youda.component.service.NewsService;
import youda.component.service.TeacherService;
import youda.component.type.NewsStatus;
import youda.component.type.OrderBy;

@Path("")
public class ContentController {

	@Autowired
	Invocation inv;
	@Autowired
	CategoryService categoryService;
	@Autowired
	AttachmentService attachmentService;
	@Autowired
	NewsService newsService;
	@Autowired
	ModelService modelService;
	@Autowired
	TeacherService teacherService;
	
	
	private final int PAGE_SIZE = 20;
	
	@Get("content/news/list")
	public String newsList(@Param("page") int page){
		//@TODO根据用户查询
		page = (page <= 0 ? 1 : page);
		int totalNum = newsService.getNewsListCountByUser(1);
		int totalPage = (int)Math.ceil(totalNum *1.0 / PAGE_SIZE);
		List<News> newsList = newsService.getAllNewsListByPage(1, page, PAGE_SIZE);
		List<Category> newsCategoryList = categoryService.getCategoryListByType("NEWS-CATEGORY", 1);
		
		inv.addModel("newsCategoryList", newsCategoryList);
		inv.addModel("newsList", newsList);
		inv.addModel("page", page);
		int startPage = (page - 3 > 0 ? page - 3 : 1);
		int endPage = (startPage + 6 > totalPage? totalPage : startPage + 6);
		inv.addModel("startPage", startPage);
		inv.addModel("endPage", endPage);
		inv.addModel("totalPage", totalPage);
		return "content/news-list";
	}
	
	@Post("content/news/list")
	public String newsSearchList(@Param("newsId") String newsId,@Param("newsTitle") String newsTitle,@Param("catId") int catId,
			  					 @Param("newsStatus") int newsStatus,@Param("page") int page){
		// @TODO根据用户查询
		Map<String, Object> search = new HashMap<String, Object>();
		search.put("newsId", newsId);
		search.put("newsTitle", newsTitle);
		search.put("catId", catId);
		search.put("newsStatus", newsStatus);
		
		page = (page <= 0 ? 1 : page);
		
		if(StringUtils.isEmpty(newsId) && StringUtils.isEmpty(newsTitle) && catId <= 0 && newsStatus <= 0){
			return "redirect:/content/news/list?page="+page;
		}
		inv.addModel("search", search);
		
		int totalNum = newsService.countNewsSearchListByStatus(newsId, newsTitle, catId, 0, 0);
		int totalPage = (int) Math.ceil(totalNum * 1.0 / PAGE_SIZE);
		List<News> newsList = newsService.getNewsSearchListByStatus(newsId, newsTitle, catId, 0, newsStatus, page, PAGE_SIZE, OrderBy.UPDATE_TIME, 1);
		List<Category> newsCategoryList = categoryService.getCategoryListByType("NEWS-CATEGORY", 1);
		
		inv.addModel("newsCategoryList", newsCategoryList);
		inv.addModel("newsList", newsList);
		inv.addModel("page", page);
		int startPage = (page - 3 > 0 ? page - 3 : 1);
		int endPage = (startPage + 6 > totalPage? totalPage : startPage + 6);
		inv.addModel("startPage", startPage);
		inv.addModel("endPage", endPage);
		inv.addModel("totalPage", totalPage);
		return "content/news-list";
	}
	
	@Get("content/news/add")
	public String newsAdd(){
		//获取内容目录List
		List<Category> newsCategoryList = categoryService.getCategoryListByType("NEWS-CATEGORY", 1);
		inv.addModel("newsCategoryList", newsCategoryList);
		inv.addModel("newsIdTmp", newsService.generateNewsId());
		//课程类型选择列表
		inv.addModel("courseTypeMap", StaticParameters.USER_ROLE);
		inv.addModel("courseTypeKeySet",StaticParameters.USER_ROLE.keySet());
		return "content/news-add";
	}
	
	/**
	 * 通过课程类型查找老师列表
	 */
	@Get("content/news/getTeachers/{courseType:[0-9]+}")
	public String getTeachers(@Param("courseType")int courseType){
		ResponseJSONBean<Teacher> jsonBean = new ResponseJSONBean<Teacher>();
		try{
			jsonBean.setReultBeans(this.teacherService.findByCourseType(courseType));
			jsonBean.setMessage("success");
			jsonBean.setSuccess(true);
		}catch(Exception e){
			e.printStackTrace();
			jsonBean.setSuccess(false);
			jsonBean.setMessage("查询数据时发生错误,请联系管理员查看日志信息!");
			jsonBean.setReultBeans(new ArrayList<Teacher>());
		}
		return "@json:"+BeanJsonUtils.convertToJson(jsonBean);
	}
	
	@Get("content/news/edit/{newsId:[A-Za-z0-9]+}")
	public String newsEdit(@Param("newsId") String newsId){
		//获取内容目录List
		List<Category> newsCategoryList = categoryService.getCategoryListByType("NEWS-CATEGORY", 1);
		News news = newsService.getNewsById(newsId);
		inv.addModel("newsCategoryList", newsCategoryList);
		inv.addModel("news", news);
		//课程类型选择列表
		inv.addModel("courseTypeMap", StaticParameters.USER_ROLE);
		inv.addModel("courseTypeKeySet",StaticParameters.USER_ROLE.keySet());
		return "content/news-edit";
	}
	
	@Post("content/news/save")
	public String newsSave(@Param("catid") int catid,@Param("newsIdTmp") String newsIdTmp,@Param("info[title]") String title,
			@Param("info[source]") String source,@Param("info[author]") String author,@Param("info[editor]") String editor,
			@Param("info[summary]") String summary,@Param("info[content]") String content,@Param("newsId") String newsId,
			@Param("thumb") int picId,News param){
		News news = null;
		boolean newFlag = false;
		if(StringUtils.isEmpty(newsId)){
			news = new News();
			newFlag = true;
		}
		else{
			news = newsService.getNewsById(newsId);
			if(news == null){
				news = new News();
			}
		}
		
		Category category = categoryService.getCategoryById(catid);
		
		
		news.setCatId(catid);
		news.setCatName(category.getCatName());
		news.setTitle(title);
		news.setSource(source);
		news.setContent(content);
		news.setEditorName(editor);
		news.setStatus(NewsStatus.PUBLISHED);
		news.setPublishTime(new Date());
		news.setCreateTime(new Date(System.currentTimeMillis()));
		news.setUpdateTime(new Date(System.currentTimeMillis()));
		BeanUtils.copyProperties(param, news,new String[]{"newsId","catId","catName","title","source","content","editorId","editorName",
				"status","template","isTop","listOrder","attachment","createTime","updateTime","updateBy","publishTime","publishBy",
				"thumbnail","picAttachmentList","attachmentList"});
		/*********** 暂未处理 ******************/
		news.setEditorId(1);
		if(picId > 0){
			news.setTemplate("IMAGE");
		}
		else{
			news.setTemplate("TEXT");
		}
		
		news.setAttachment(0);
		/*****************************/
		
		if(StringUtils.isEmpty(news.getNewsId())){
			news.setCreateTime(new Date());
		}
		
		if(newFlag){
			newsId = newsService.newsSave(news);
			attachmentService.resetAttachment(newsIdTmp, newsId);
			if(picId > 0){
				attachmentService.updateThumb(picId, newsId);
			}
		}
		else{
			newsService.updateNews(news);
			if(picId > 0){
				attachmentService.updateThumb(picId, news.getNewsId());
			}
		}
		
		return "content/save-success";
	}
	
	@Get("content/news/delete/{newsId:[a-zA-Z0-9]+}")
	public String deleteNews(@Param("newsId") String newsId, @Param("page") int page){
		newsService.delNews(newsId);
		return "r:/content/news/list?page="+page;
	}
	
	@Get("content/news/preview/{nid:[a-zA-Z0-9]+}")
	public String showNews(@Param("nid") String newsId){
		News news = newsService.getNewsById(newsId);
		
		Category category = categoryService.getCategoryById(news.getCatId());
		Category pcategory = null;
		
		//目录面包屑
		List<Crumb> crumbList = new ArrayList<Crumb>();
		crumbList.add(new Crumb(category));
		if(category.getParentId() > 0){
			pcategory = categoryService.getCategoryById(category.getParentId());
			crumbList.add(new Crumb(pcategory));
			while(pcategory.getParentId() > 0){
				pcategory = categoryService.getCategoryById(pcategory.getParentId());
				crumbList.add(new Crumb(pcategory));
			}
		}
		Collections.reverse(crumbList);
		inv.addModel("crumbList", crumbList);
		inv.addModel("category", category);
		inv.addModel("news", news);
		
		return "content/news-preview";
	}
	
	@Get("content/category/manage")
	public String categoryManage(){
		List<Category> categoryList = new ArrayList<Category>();
		generateCategoryTree(0, categoryList, 0);
		inv.addModel("categoryLlist", categoryList);
		return "content/category-list";
	}
	
	@Get("content/category/add")
	public String categoryAdd(@Param("cid") int categoryId){
		if(categoryId > 0){
			Category parentCategory = categoryService.getCategoryById(categoryId);
			inv.addModel("parentCategory", parentCategory);
		}
		List<Category> selectList = new ArrayList<Category>();
		generateCategoryTree(0, selectList, 0);
		inv.addModel("selectList", selectList);
		return "content/category-add";
	}
	
	@Get("content/category/edit")
	public String categoryEdit(@Param("cid") int categoryId){
		Category category = categoryService.getCategoryById(categoryId);
		List<Category> selectList = new ArrayList<Category>();
		generateCategoryTree(0, selectList, 0);
		inv.addModel("edit", 1);
		inv.addModel("category", category);
		inv.addModel("selectList", selectList);
		return "content/category-edit";
	}
	
	@Get("content/category/page/edit")
	public String categoryPageEdit(@Param("cid") int categoryId){
		Category category = categoryService.getCategoryById(categoryId);
		CategoryPage catPage = categoryService.getCategoryPageByCatId(category.getCatId());
		inv.addModel("category", category);
		inv.addModel("catPage", catPage);
		return "content/category-page-edit";
	}
	
	@Post("content/category/page/save")
	public String categoryPageSave(@Param("cid") int categoryId,@Param("page_content") String content,@Param("template") String template){
		CategoryPage catPage = categoryService.getCategoryPageByCatId(categoryId);
		catPage.setPageContent(content);
		categoryService.updateCategoryPage(catPage);
		return "r:/content/category/manage";
	}
	
	@Post("/content/category/page/preview")
	public String previewCategory(@Param("content") String content,@Param("cid") int catId){
		Category cat = categoryService.getCategoryById(catId);
		Category parentCat =  categoryService.getCategoryById(cat.getParentId());
		inv.addModel("currentCat", cat);
		inv.addModel("parentCat", parentCat);
		inv.addModel("pageContent", content);
		
		List<ModelField> naviModelFieldList = modelService.getNavigationList();
		inv.addModel("navigationList", naviModelFieldList);
		List<Category> categoryList = categoryService.getChildCategoryList(cat.getParentId(), 1);
		inv.addModel("catList", categoryList);
		return "content/preview-page-content";
	}
	
	@Get("content/category/del/{cid:[0-9]+}")
	public String categoryDel(@Param("cid") int categoryId){
		//@Todo  用户权限判定
		Category category = categoryService.getCategoryById(categoryId);
		categoryService.delCategory(categoryId);
		categoryService.updateParentCategoryStatus(category.getParentId());
		return "r:/content/category/manage";
	}
	
	@Get("content/category/moveup/{cid:[0-9]+}")
	public String categoryMoveUp(@Param("cid") int categoryId){
		categoryService.categoryMoveUp(categoryId);
		return "r:/content/category/manage";
	}
	
	@Get("content/category/movedown/{cid:[0-9]+}")
	public String categoryMoveDown(@Param("cid") int categoryId){
		categoryService.categoryMoveDown(categoryId);
		return "r:/content/category/manage";
	}
	
	@Post("/content/category/addNew")
	public String categoryAddNew(@Param("categoryType") String categoryType,@Param("parentCategory") int parentCategoryId,
								@Param("categoryName") String categoryName,@Param("categoryPathName") String categoryPathName,
								@Param("workflow") int workflow){
		
		if(StringUtils.isEmpty(categoryName) || StringUtils.isEmpty(categoryType) || StringUtils.isEmpty(categoryPathName)){
			inv.addModel("errorMSG", "相关参数输入不完整，请核对输入信息后再提交。");
			return "r:/content/category/add";
		}
		
		Category category = new Category();
		category.setCatName(categoryName);
		category.setType(categoryType);
		category.setParentId(parentCategoryId);
		category.setPathName(categoryPathName);
		categoryService.addNewCategory(category);
		return "r:/content/category/manage";
	}
	
	@Post("/content/category/update")
	public String categoryUpdate(@Param("cid") int categoryId,@Param("parentCategory") int parentCategoryId,
								@Param("categoryName") String categoryName,@Param("categoryPathName") String categoryPathName,
								@Param("workflow") int workflow){
		if(categoryId == parentCategoryId){
			return null;
		}
		Category category = categoryService.getCategoryById(categoryId);
		int cid = category.getParentId();
		category.setCatName(categoryName);
		category.setParentId(parentCategoryId);
		category.setPathName(categoryPathName);
		categoryService.updateCategory(category);
		categoryService.updateCategoryChild(cid);
		return "r:/content/category/manage";
	}
	
	@Post("/content/category/checkName")
	public String checkCategoryName(@Param("cid") int categoryId, @Param("catName") String categoryName){
		Map<String, Object> rtn = new HashMap<String, Object>();
		if(StringUtils.isEmpty(categoryName.trim())){
			rtn.put("status", -1);
			return AjaxUtils.printJson(rtn, inv);
		}
		rtn.put("status", categoryService.checkCategoryName(categoryId, categoryName));
		return AjaxUtils.printJson(rtn, inv);
	}
	
	@Post("/content/category/checkPathName")
	public String checkCategoryPathName(@Param("cid") int categoryId, @Param("catPathName") String categoryPathName){
		Map<String, Object> rtn = new HashMap<String, Object>();
		if(StringUtils.isEmpty(categoryPathName.trim())){
			rtn.put("status", -1);
			return AjaxUtils.printJson(rtn, inv);
		}
		rtn.put("status", categoryService.checkCategoryPathName(categoryId, categoryPathName));
		return AjaxUtils.printJson(rtn, inv);
	}
	
	private void generateCategoryTree(int parentId, List<Category> rtn, int level){
		List<Category> categoryList = categoryService.getChildCategoryList(parentId, 1);
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
}
