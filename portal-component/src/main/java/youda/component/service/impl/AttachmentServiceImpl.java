package youda.component.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.dao.AttachmentDAO;
import youda.component.model.Attachment;
import youda.component.service.AttachmentService;

@Component
public class AttachmentServiceImpl implements AttachmentService {

	@Autowired
	AttachmentDAO attachmentDao;
	
	private final String SAVE_PATH = "/data/img";
	
	@Override
	public Attachment getNewsThumbnail(String newsId) {
		return attachmentDao.getNewsThumbnail(newsId);
	}

	@Override
	public List<Attachment> getPicAttachmentListByNewsId(String newsId) {
		return attachmentDao.getPicAttachmentListByNewsId(newsId);
	}

	@Override
	public List<Attachment> getOtherAttachmentListByNewsId(String newsId) {
		return attachmentDao.getOtherAttachmentListByNewsId(newsId);
	}

	@Override
	public int saveAttachment(Attachment attachment) {
		return attachmentDao.addAttachment(attachment);
	}

	@Override
	public void resetAttachment(String newsIdTmp, String newsId) {
		attachmentDao.resetAttachment(newsIdTmp, newsId);
	}

	@Override
	public void removeAttachmentByNewsId(String newsId) {
		List<Attachment> attachList = new ArrayList<Attachment>();
		attachList.addAll(this.getPicAttachmentListByNewsId(newsId));
		attachList.addAll(this.getOtherAttachmentListByNewsId(newsId));
		//删除文件
		for(Attachment attach : attachList){
			File file = new File(SAVE_PATH + attach.getFilepath());
			if(file.exists()){
				file.delete();
			}
		}
		//删除数据库保存信息
		attachmentDao.removeAttachmentByNewsId(newsId);
	}

	@Override
	public Attachment getAttachmentById(int aid) {
		return attachmentDao.getAttachmentById(aid);
	}

	@Override
	public int updateAttachment(Attachment attachment) {
		return attachmentDao.updateAttachment(attachment);
	}

	@Override
	public void updateThumb(int aid, String newsId) {
		Attachment attachment = this.getNewsThumbnail(newsId);
		if(attachment != null && attachment.getAid() != aid){
			attachmentDao.removeThumbByNewsId(newsId);
			attachmentDao.updateThumb(aid, newsId);
		}
	}
	
}
