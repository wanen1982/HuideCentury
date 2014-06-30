package youda.site.web.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.springframework.beans.factory.annotation.Autowired;

import youda.component.model.Carousel;
import youda.component.model.Category;
import youda.component.model.CategoryPage;
import youda.component.model.News;
import youda.component.model.Partner;
import youda.component.service.CarouselService;
import youda.component.service.CategoryService;
import youda.component.service.NewsService;
import youda.component.service.PartnerService;
import youda.component.type.NewsStatus;
import youda.component.type.OrderBy;
import youda.site.web.annotation.Navigation;
import youda.site.web.utils.AjaxUtils;


@Path("")
public class HomeController {

	@Autowired
	Invocation inv;
	@Autowired
	CategoryService categoryService;
	@Autowired
	NewsService newsService;
	@Autowired
	CarouselService carouselService;
	@Autowired
	PartnerService partnerService;
	
	private final int NEWSLIST_HOME_LIMIT = 5;
	
	@Get("")
	public String none(){
		return "r:/index";
	}
	
	@Navigation
	@Get("index")
	public String index(){
		//加载轮播图片数据
		List<Carousel> carouselList = carouselService.getCarouselList();
		List<Partner> partnerList = partnerService.getPartnerList();
		inv.addModel("carouselList", carouselList);
		inv.addModel("partnerList", partnerList);
		return "index";
	}
	
	@Post("home/newslist")
	public String newsList(){
		List<News> companyNewsList = newsService.getNewsListByCategoryId(43, NewsStatus.PUBLISHED, OrderBy.PUBLISH_TIME, 1, NEWSLIST_HOME_LIMIT);
		List<News> industryNewsList = newsService.getNewsListByCategoryId(45, NewsStatus.PUBLISHED, OrderBy.PUBLISH_TIME, 1, NEWSLIST_HOME_LIMIT);
		inv.addModel("companyNewsList", companyNewsList);
		inv.addModel("industryNewsList", industryNewsList);
		return "home-newslist";
	}
	
	@Post("home/solution")
	public String solutionList(){
		List<Category> categoryList = categoryService.getChildCategoryList(46, 1);
		inv.addModel("solutionList", categoryList);
		return "home-solution";
	}
	
	@Post("home/aboutus")
	public String aboutus(){
		Map<String, String> rtn = new HashMap<String, String>();
		CategoryPage page = categoryService.getCategoryPageByCatId(56);
		rtn.put("content", page.getPageContent());
		return AjaxUtils.printJson(rtn, inv);
	}
}
