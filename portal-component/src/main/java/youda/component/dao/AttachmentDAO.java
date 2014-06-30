package youda.component.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.Attachment;

@DAO
public interface AttachmentDAO {

	final static String attachment = "aid, cat_id, news_id,filename,filepath,filesize,fileext," +
			"is_image,is_thumb,downloads_count,user_id,upload_time,upload_ip,status";
	
	@SQL("select " + attachment + " from attachment where news_id = :1 and is_image = 1 and is_thumb = 1 order by upload_time desc limit 1")
	public Attachment getNewsThumbnail(String newsId);
	
	@SQL("select " + attachment + " from attachment where news_id = :1 and is_image = 1 order by upload_time desc")
	public List<Attachment> getPicAttachmentListByNewsId(String newsId);
	
	@SQL("select " + attachment + " from attachment where news_id = :1 and is_image = 0 order by upload_time desc")
	public List<Attachment> getOtherAttachmentListByNewsId(String newsId);
	
	@ReturnGeneratedKeys
	@SQL("insert into attachment(cat_id,news_id,filename,filepath,filesize,fileext," +
		 "is_image,is_thumb,downloads_count,user_id,upload_time,upload_ip,status)" +
 	     "values (:1.catId,:1.newsId,:1.filename,:1.filepath,:1.filesize,:1.fileext," +
		 ":1.isImage,:1.isThumb,:1.downloadsCount,:1.userId,now(),:1.uploadIp,:1.status)")
	public int addAttachment(Attachment attachment);
	
	@SQL("update attachment set news_id=:2 where news_id=:1")
	public void resetAttachment(String newsIdTmp, String newsId);
	
	@SQL("delete from attachment where news_id = :1")
	public void removeAttachmentByNewsId(String newsId);
	
	@SQL("select " + attachment + " from attachment where aid = :1")
	public Attachment getAttachmentById(int aid);
	
	@SQL("update attachment set cat_id=:1.catId,news_id=:1.newsId,filename=:1.filename,is_image=:1.isImage,is_thumb=:1.isThumb," +
		 "status=:1.status where aid=:1.aid")
	public int updateAttachment(Attachment attachment);
	
	@SQL("update attachment set is_thumb = 0 where news_id = :1")
	public void removeThumbByNewsId(String newsId);
	
	@SQL("update attachment set is_thumb = 1 where aid = :1 and news_id = :2 and is_image = 1")
	public void updateThumb(int aid, String newsId);
}
