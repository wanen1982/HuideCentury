
package youda.admin.web.controllers;

import java.util.HashMap;
import java.util.Map;

import net.paoding.rose.web.Invocation;
import net.paoding.rose.web.annotation.Param;
import net.paoding.rose.web.annotation.Path;
import net.paoding.rose.web.annotation.rest.Post;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import youda.admin.web.utils.AjaxUtils;
import youda.admin.web.utils.ImageUploadUtils;
import youda.component.constants.Constants;
import youda.component.model.Attachment;
import youda.component.service.AttachmentService;

@Path("")
public class ImageController {
	@Autowired
	Invocation inv;
	@Autowired
	AttachmentService attachmentService;
	
	@Post("/Editer/imgUpload/{nid:[A-Za-z0-9]+}")
	public String uploadEditImg(@Param("nid") String newsIdTmp,@Param("upfile") MultipartFile mf, @Param("pictitle") String picTitle) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String originalFileName = mf.getOriginalFilename();
		String extension = FilenameUtils.getExtension(mf.getOriginalFilename().toLowerCase());
		//服务器根的真实路径
		String rootRealPath = inv.getServletContext().getRealPath("/").replace("\\","/");
		String filePath = ImageUploadUtils.uploadImg(mf, rootRealPath+Constants.IMG_PATH , "img_", 0);
		//图片保存后替换成虚拟路径
		filePath = filePath.replace("\\","/");
		
		filePath = "/"+StringUtils.substringAfter(filePath, rootRealPath);
//		filePath = StringUtils.substringAfter(filePath, Constants.ROOT_PATH);

		// 保存数据库信息
		Attachment attach = new Attachment();
		attach.setNewsId(newsIdTmp);
		attach.setFilename(picTitle);
		attach.setFilepath(filePath);
		attach.setFileext(extension);
		attach.setFilesize(mf.getSize());
		attach.setIsImage(1);
		attach.setUploadIp(inv.getRequest().getRemoteAddr());

		//返回生成的ID
		int aid = attachmentService.saveAttachment(attach);
		result.put("aid", aid);
		
		if(picTitle.equals(originalFileName)){
			picTitle = "";
		}
		
		result.put("url", Constants.URL_IMG + filePath);
		result.put("title", picTitle);
		result.put("original", originalFileName);
		result.put("state", "success");

		return AjaxUtils.printJson(result, inv);
	}
	
	@Post("/Editer/imgUpload/category/{cid:[0-9]+}")
	public String uploadCategoryImg(@Param("cid") int cid,@Param("upfile") MultipartFile mf, @Param("pictitle") String picTitle) throws Exception {
		Map<String, Object> result = new HashMap<String, Object>();
		
		String originalFileName = mf.getOriginalFilename();
		String extension = FilenameUtils.getExtension(mf.getOriginalFilename().toLowerCase());
		String filePath = ImageUploadUtils.uploadImg(mf, Constants.IMG_PATH, "img_", 0);
		filePath = StringUtils.substringAfter(filePath, Constants.ROOT_PATH);

		// 保存数据库信息
		Attachment attach = new Attachment();
		attach.setCatId(cid);
		attach.setFilename(picTitle);
		attach.setFilepath(filePath);
		attach.setFileext(extension);
		attach.setFilesize(mf.getSize());
		attach.setIsImage(1);
		attach.setUploadIp(inv.getRequest().getRemoteAddr());

		//返回生成的ID
		int aid = attachmentService.saveAttachment(attach);
		result.put("aid", aid);
		
		if(picTitle.equals(originalFileName)){
			picTitle = "";
		}
		
		result.put("url", Constants.URL_IMG + filePath);
		result.put("title", picTitle);
		result.put("original", originalFileName);
		result.put("state", "success");

		return AjaxUtils.printJson(result, inv);
	}
}
