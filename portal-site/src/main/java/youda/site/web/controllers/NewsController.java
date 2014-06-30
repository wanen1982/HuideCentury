package youda.site.web.controllers;

import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;

import org.springframework.beans.factory.annotation.Autowired;

import youda.component.model.Category;
import youda.component.model.News;
import youda.component.service.CategoryService;
import youda.component.service.NewsService;
import youda.site.web.annotation.Navigation;

@Path("")
public class NewsController {

	@Autowired
	Invocation inv;
	@Autowired
	CategoryService categoryService;
	@Autowired
	NewsService newsService;
	
	@Navigation
	@Get("news/{newsId:[a-zA-Z0-9]+}")
	public String news(@Param("newsId") String newsId){
		News news = newsService.getNewsById(newsId);
		Category newsCategory = categoryService.getCategoryById(news.getCatId());
		Category parCategory = categoryService.getCategoryById(newsCategory.getParentId());
		
		List<Category> categoryList = categoryService.getChildCategoryList(parCategory.getCatId(), 1);
		
		inv.addModel("parentCat", parCategory);
		inv.addModel("currentCat", newsCategory);
		inv.addModel("catList", categoryList);
		inv.addModel("news", news);
		return "page-news";
	}
}
