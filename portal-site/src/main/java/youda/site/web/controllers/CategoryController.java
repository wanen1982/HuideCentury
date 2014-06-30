package youda.site.web.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import youda.component.model.Category;
import youda.component.model.CategoryPage;
import youda.component.model.News;
import youda.component.service.CategoryService;
import youda.component.service.NewsService;
import youda.component.type.NewsStatus;
import youda.component.type.OrderBy;
import youda.site.web.annotation.Navigation;

@Path("")
public class CategoryController {
	@Autowired
	Invocation inv;
	@Autowired
	CategoryService categoryService;
	@Autowired
	NewsService newsService;
	
	private final int NEWSLIST_PAGE_LIMIT = 25;
	
	@Navigation
	@Get("/c/{cid:[0-9]+}")
	public String category(@Param("cid") int catId, @Param("page") int page){
		Category show = categoryService.getCategoryById(catId);
		Category parentCat = categoryService.getCategoryById(show.getParentId());
		List<Category> categoryList = categoryService.getChildCategoryList(parentCat.getCatId(), 1);
		inv.addModel("parentCat", parentCat);
		inv.addModel("currentCat", show);
		inv.addModel("catList", categoryList);
		
		if(show.getType().equals("PAGE")){
			CategoryPage catPage = categoryService.getCategoryPageByCatId(show.getCatId());
			inv.addModel("pageContent", catPage.getPageContent());
			return "page-content";
		}
		if(show.getType().equals("NEWS-CATEGORY")){
			page = (page < 1 ? 1 : page);
			int totalNum = newsService.countPublishNewsList(show.getCatId());
			int totalPage = (int)Math.ceil(totalNum *1.0 / NEWSLIST_PAGE_LIMIT);
			List<News> newsList = newsService.getNewsListByCategoryId(show.getCatId(), NewsStatus.PUBLISHED, OrderBy.PUBLISH_TIME, page, NEWSLIST_PAGE_LIMIT);
			inv.addModel("newsList", newsList);
			inv.addModel("page", page);
			int startPage = (page - 3 > 0 ? page - 3 : 1);
			int endPage = (startPage + 6 > totalPage? totalPage : startPage + 6);
			inv.addModel("startPage", startPage);
			inv.addModel("endPage", endPage);
			inv.addModel("totalPage", totalPage);
			return "page-newsList";
		}
		
		return "r:/index";
	}
	
	@Navigation
	@Get("/navi/{cid:[0-9]+}")
	public String navigation(@Param("cid") int catId, @Param("page") int page){
		Category parentCat = categoryService.getCategoryById(catId);
		List<Category> categoryList = categoryService.getChildCategoryList(catId, 1);
		Category show;
		if(categoryList.size() > 0){
			show = categoryList.get(0);
			inv.addModel("parentCat", parentCat);
		}
		else{
			show = parentCat;
		}
		
		inv.addModel("currentCat", show);
		inv.addModel("catList", categoryList);
		
		if(show.getType().equals("PAGE")){
			CategoryPage catPage = categoryService.getCategoryPageByCatId(show.getCatId());
			inv.addModel("pageContent", catPage.getPageContent());
			return "page-content";
		}
		if(show.getType().equals("NEWS-CATEGORY")){
			page = (page < 1 ? 1 : page);
			int totalNum = newsService.countPublishNewsList(show.getCatId());
			int totalPage = (int)Math.ceil(totalNum *1.0 / NEWSLIST_PAGE_LIMIT);
			List<News> newsList = newsService.getNewsListByCategoryId(show.getCatId(), NewsStatus.PUBLISHED, OrderBy.PUBLISH_TIME, page, NEWSLIST_PAGE_LIMIT);
			inv.addModel("newsList", newsList);
			inv.addModel("page", page);
			int startPage = (page - 3 > 0 ? page - 3 : 1);
			int endPage = (startPage + 6 > totalPage? totalPage : startPage + 6);
			inv.addModel("startPage", startPage);
			inv.addModel("endPage", endPage);
			inv.addModel("totalPage", totalPage);
			return "page-newsList";
		}
		
		return "r:/index";
	}
}
