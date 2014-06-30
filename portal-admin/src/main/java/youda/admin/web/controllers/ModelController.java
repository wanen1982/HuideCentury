package youda.admin.web.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Get;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import youda.admin.web.utils.AntiSpamUtils;
import youda.admin.web.utils.ImageUploadUtils;
import youda.component.constants.Constants;
import youda.component.model.Carousel;
import youda.component.model.Category;
import youda.component.model.Model;
import youda.component.model.ModelField;
import youda.component.model.Partner;
import youda.component.service.CarouselService;
import youda.component.service.CategoryService;
import youda.component.service.ModelService;
import youda.component.service.PartnerService;

@Path("model")
public class ModelController {
	@Autowired
	Invocation inv;
	@Autowired
	ModelService modelService;
	@Autowired
	CategoryService categoryService;
	@Autowired
	CarouselService carouselService;
	@Autowired
	PartnerService partnerService;
	
	@Get("menu/manage")
	public String menuManage(){
		Model model = modelService.getNavigationModel();
		List<ModelField> modelFieldList = modelService.getModelFieldListByModelId(model.getModelId());
		inv.addModel("navigationList", modelFieldList);
		inv.addModel("model", model);
		return "model/model-menu";
	}
	
	@Get("menu/add")
	public String showMenuAddForm(){
		List<Category> selectList = new ArrayList<Category>();
		categoryService.generateCategoryTree(0, selectList, 0);
		inv.addModel("selectList", selectList);
		
		if(!StringUtils.isEmpty(inv.getFlash().get("errorMSG"))){
			inv.addModel("errorMSG", inv.getFlash().get("errorMSG"));
		}
		return "model/menu-add";
	}
	
	@Post("menu/addMenu")
	public String menuAdd(@Param("showName") String showName,@Param("categoryId")int categoryId) throws Exception{
		if(StringUtils.isEmpty(showName)){
			inv.addFlash("errorMSG", "请输入导航栏上需要显示的名称。");
			return "r:/model/menu/add";
		}
		
		Model model = modelService.getNavigationModel();
		ModelField modelField = new ModelField();
		modelField.setModelId(model.getModelId());
		modelField.setCatId(categoryId);
		modelField.setBelong("首页导航");
		modelField.setShowName(showName);
		
		modelService.saveModelField(modelField);
		
		return "r:/model/menu/manage";
	}
	
	@Get("menu/del/{fid:[0-9]+}")
	public String delNavigation(@Param("fid") int fieldId){
		//@Todo  用户权限判定
		Model model = modelService.getNavigationModel();
		modelService.delModelField(fieldId, model.getModelId());
		return "r:/model/menu/manage";
	}
	
	@Get("menu/edit/{fid:[0-9]+}")
	public String editNavigation(@Param("fid") int fieldId){
		ModelField modelField = modelService.getModelFieldById(fieldId);
		inv.addModel("modelField", modelField);
		
		List<Category> selectList = new ArrayList<Category>();
		categoryService.generateCategoryTree(0, selectList, 0);
		inv.addModel("selectList", selectList);
		
		return "model/menu-edit";
	}
	
	@Post("menu/save")
	public String updateMenu(@Param("field")int field, @Param("showName") String showName,@Param("categoryId")int categoryId){
		if(StringUtils.isEmpty(showName)){
			inv.addFlash("errorMSG", "请输入导航栏上需要显示的名称。");
			return "r:/model/menu/add";
		}
		
		ModelField modelField = modelService.getModelFieldById(field);
		modelField.setCatId(categoryId);
		modelField.setShowName(showName);
				
		modelService.updateModelFieldById(modelField);
		
		return "r:/model/menu/manage";
	}
	
	@Get("menu/moveUp/{fid:[0-9]+}")
	public String moveUp(@Param("fid") int fieldId){
		modelService.modelFieldMoveUp(fieldId);
		return "r:/model/menu/manage";
	}
	
	@Get("menu/moveDown/{fid:[0-9]+}")
	public String moveDown(@Param("fid") int fieldId){
		modelService.modelFieldMoveDown(fieldId);
		return "r:/model/menu/manage";
	}
	
	@Get("carousel/manage")
	public String carouselM(){
		List<Carousel> carouselList = carouselService.getCarouselList();
		inv.addModel("carouselList", carouselList);
		return "model/carousel-m";
	}
	
	@Get("carousel/add")
	public String carouselAdd(){
		if(!StringUtils.isEmpty(inv.getFlash().get("errorMSG"))){
			inv.addModel("errorMSG", inv.getFlash().get("errorMSG"));
		}
		return "model/carousel-add";
	}
	
	@Post("carousel/add")
	public String carouselAddNew(@Param("title") String title, @Param("desc") String description, @Param("pic") MultipartFile pic,@Param("linkUrl") String linkUrl){
		if(pic.getSize() < 0){
			inv.addFlash("errorMSG", "请选择需要上传的图片。");
			return "r:/model/carousel/add";
		}
		if(ImageUploadUtils.isValidFormats(pic)){
			inv.addFlash("errorMSG", "系统仅支持上传png、jpg、jpeg、gif格式图片。");
			return "r:/model/carousel/add";
		}
		
		if(StringUtils.isEmpty(linkUrl)){
			inv.addFlash("errorMSG", "请输入轮播图片跳转地址。");
			return "r:/model/carousel/add";
		}
		//服务器根的真实路径
		String rootRealPath = inv.getServletContext().getRealPath("/").replace("\\","/");
		String picpath = ImageUploadUtils.uploadImg(pic, rootRealPath+Constants.CAROUSEL_PATH, "img_", 0);
		
		//图片保存后替换成虚拟路径
		picpath = picpath.replace("\\","/");
		picpath = "/"+StringUtils.substringAfter(picpath, rootRealPath);
		
		Carousel carousel = new Carousel();
		carousel.setTitle(AntiSpamUtils.safeText(title));
		carousel.setDescription(AntiSpamUtils.safeText(description));
//		carousel.setPicpath(StringUtils.substringAfter(picpath, Constants.ROOT_PATH));
		carousel.setPicpath(picpath);
		carousel.setLinkUrl(linkUrl);
		
		carouselService.addCarousel(carousel);
		return "r:/model/carousel/manage";
	}
	
	@Get("carousel/edit/{id:[0-9]+}")
	public String carouselEdit(@Param("id") int id){
		if(!StringUtils.isEmpty(inv.getFlash().get("errorMSG"))){
			inv.addModel("errorMSG", inv.getFlash().get("errorMSG"));
		}
		
		Carousel carousel = carouselService.getCarouselById(id);
		inv.addModel("carousel", carousel);		
		return "model/carousel-edit";
	}
	
	@Post("carousel/edit")
	public String carouselUpdate(@Param("id") int id, @Param("title") String title, @Param("desc") String description, @Param("pic") MultipartFile pic,@Param("linkUrl") String linkUrl){
		Carousel carousel = carouselService.getCarouselById(id);
		if(pic.getSize() > 0){
			if(ImageUploadUtils.isValidFormats(pic)){
				inv.addFlash("errorMSG", "系统仅支持上传png、jpg、jpeg、gif格式图片。");
				return "r:/model/carousel/edit/" + id;
			}
			//服务器根的真实路径
			String rootRealPath = inv.getServletContext().getRealPath("/").replace("\\","/");
			String picpath = ImageUploadUtils.uploadImg(pic, rootRealPath+Constants.CAROUSEL_PATH, "img_", 0);
			//图片保存后替换成虚拟路径
			picpath = picpath.replace("\\","/");
			picpath = "/"+StringUtils.substringAfter(picpath, rootRealPath);
			
			//删除以前图片
			File file = new File(rootRealPath+Constants.ROOT_PATH + carousel.getPicpath());
			if(file.exists()){
				file.delete();
			}
			carousel.setPicpath(picpath);
//			carousel.setPicpath(StringUtils.substringAfter(picpath, Constants.ROOT_PATH));
		}
		
		if(StringUtils.isEmpty(linkUrl)){
			inv.addFlash("errorMSG", "请输入轮播图片跳转地址。");
			return "r:/model/carousel/edit/" + id;
		}
		
		carousel.setTitle(AntiSpamUtils.safeText(title));
		carousel.setDescription(AntiSpamUtils.safeText(description));
		carousel.setLinkUrl(linkUrl);
		carouselService.updateCarousel(carousel);
		
		return "r:/model/carousel/manage";
	}
	
	@Get("carousel/delete/{id:[0-9]+}")
	public String carouselDel(@Param("id") int id){
		carouselService.delCarousel(id);
		return "r:/model/carousel/manage";
	}
	
	@Get("carousel/moveUp/{id:[0-9]+}")
	public String carouselMoveUp(@Param("id") int id){
		carouselService.carouselMoveUp(id);
		return "r:/model/carousel/manage";
	}
	
	@Get("carousel/moveDown/{id:[0-9]+}")
	public String carouselMoveDown(@Param("id") int id){
		carouselService.carouselMoveDown(id);
		return "r:/model/carousel/manage";
	}
	
	@Get("partner/manage")
	public String partnerM(){
		List<Partner> partnerList = partnerService.getPartnerList();
		inv.addModel("partnerList", partnerList);
		return "model/partner-m";
	}
	
	@Get("partner/add")
	public String partnerAdd(){
		if(!StringUtils.isEmpty(inv.getFlash().get("errorMSG"))){
			inv.addModel("errorMSG", inv.getFlash().get("errorMSG"));
		}
		return "model/partner-add";
	}
	
	@Post("partner/add")
	public String partnerAddNew(@Param("name") String name, @Param("icon") MultipartFile icon,@Param("linkUrl") String linkUrl){
		if(StringUtils.isEmpty(name)){
			inv.addFlash("errorMSG", "公司名称不能为空!");
			return "r:/model/partner/add";
		}
		if(icon.getSize() < 0){
			inv.addFlash("errorMSG", "请选择需要上传的公司图标。");
			return "r:/model/partner/add";
		}
		if(ImageUploadUtils.isValidFormats(icon)){
			inv.addFlash("errorMSG", "系统仅支持上传png、jpg、jpeg、gif格式图片。");
			return "r:/model/partner/add";
		}
		//服务器根的真实路径
		String rootRealPath = inv.getServletContext().getRealPath("/").replace("\\","/");
		String iconpath = ImageUploadUtils.uploadImg(icon, rootRealPath+Constants.PARTNER_ICON_PATH, "icon_", 0);
		//图片保存后替换成虚拟路径
		iconpath = iconpath.replace("\\","/");
				
		iconpath = "/"+StringUtils.substringAfter(iconpath, rootRealPath);
		
		Partner partner = new Partner();
		partner.setName(name);
//		partner.setIconPath(StringUtils.substringAfter(iconpath, Constants.ROOT_PATH));
		partner.setIconPath(iconpath);
		partner.setLinkUrl(linkUrl);
		partnerService.addPartner(partner);
		return "r:/model/partner/manage";
	}
	
	@Get("partner/edit/{id:[0-9]+}")
	public String partnerEdit(@Param("id") int id){
		if(!StringUtils.isEmpty(inv.getFlash().get("errorMSG"))){
			inv.addModel("errorMSG", inv.getFlash().get("errorMSG"));
		}
		Partner partner = partnerService.getPartnerById(id);
		inv.addModel("partner", partner);
		return "model/partner-edit";
	}
	
	@Post("partner/update")
	public String partnerUpdate(@Param("id") int id, @Param("name") String name, @Param("icon") MultipartFile icon,@Param("linkUrl") String linkUrl){
		Partner partner = partnerService.getPartnerById(id);
		if(StringUtils.isEmpty(name)){
			inv.addFlash("errorMSG", "公司名称不能为空!");
			return "r:/model/partner/edit/" + id;
		}
		if(icon.getSize() > 0){
			if(ImageUploadUtils.isValidFormats(icon)){
				inv.addFlash("errorMSG", "系统仅支持上传png、jpg、jpeg、gif格式图片。");
				return "r:/model/partner/edit/" + id;
			}
			//服务器根的真实路径
			String rootRealPath = inv.getServletContext().getRealPath("/").replace("\\","/");
			String iconPath = ImageUploadUtils.uploadImg(icon, rootRealPath+Constants.PARTNER_ICON_PATH, "icon_", 0);
			//图片保存后替换成虚拟路径
			iconPath = iconPath.replace("\\","/");
			
			iconPath = "/"+StringUtils.substringAfter(iconPath, rootRealPath);
			//删除以前图片
			File file = new File(rootRealPath+Constants.ROOT_PATH + partner.getIconPath());
			if(file.exists()){
				file.delete();
			}
//			partner.setIconPath(StringUtils.substringAfter(iconPath, Constants.ROOT_PATH));
			partner.setIconPath(iconPath);
		}
		
		partner.setName(AntiSpamUtils.safeText(name));
		partner.setLinkUrl(linkUrl);
		partnerService.updatePartner(partner);
		return "r:/model/partner/manage";
	}
	
	@Get("partner/delete/{id:[0-9]+}")
	public String partnerDel(@Param("id") int id){
		partnerService.delPartner(id);
		return "r:/model/partner/manage";
	}
	
	@Get("partner/moveUp/{id:[0-9]+}")
	public String partnerMoveUp(@Param("id") int id){
		partnerService.partnerMoveUp(id);
		return "r:/model/partner/manage";
	}
	
	@Get("partner/moveDown/{id:[0-9]+}")
	public String partnerMoveDown(@Param("id") int id){
		partnerService.partnerMoveDown(id);
		return "r:/model/partner/manage";
	}
}
