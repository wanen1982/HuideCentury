package youda.component.service;

import java.util.List;

import youda.component.model.Attachment;

public interface AttachmentService {

	public Attachment getNewsThumbnail(String newsId);
	
	public List<Attachment> getPicAttachmentListByNewsId(String newsId);
	
	public List<Attachment> getOtherAttachmentListByNewsId(String newsId);
	
	public int saveAttachment(Attachment attchment);
	
	public void resetAttachment(String newsIdTmp, String newsId);
	
	public void removeAttachmentByNewsId(String newsId);
	
	public Attachment getAttachmentById(int aid);
	
	public int updateAttachment(Attachment attachment);
	
	public void updateThumb(int aid, String newsId);
}
