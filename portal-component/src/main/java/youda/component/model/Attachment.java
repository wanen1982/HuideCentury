package youda.component.model;

import java.io.Serializable;
import java.util.Date;

public class Attachment implements Serializable {

	private static final long serialVersionUID = 7519935519551933940L;
	
	private int aid;
	
	private int catId;
	
	private String newsId;
	
	private String filename;
	
	private String filepath;
	
	private long filesize;
	
	private String fileext;
	
	private int isImage;
	
	private int isThumb;
	
	private int downloadsCount;
	
	private int userId;
	
	private Date uploadTime;
	
	private String uploadIp;
	
	private int status;

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getCatId() {
		return catId;
	}

	public void setCatId(int catId) {
		this.catId = catId;
	}

	public String getNewsId() {
		return newsId;
	}

	public void setNewsId(String newsId) {
		this.newsId = newsId;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public long getFilesize() {
		return filesize;
	}

	public void setFilesize(long filesize) {
		this.filesize = filesize;
	}

	public String getFileext() {
		return fileext;
	}

	public void setFileext(String fileext) {
		this.fileext = fileext;
	}

	public int getIsImage() {
		return isImage;
	}

	public void setIsImage(int isImage) {
		this.isImage = isImage;
	}

	public int getIsThumb() {
		return isThumb;
	}

	public void setIsThumb(int isThumb) {
		this.isThumb = isThumb;
	}

	public int getDownloadsCount() {
		return downloadsCount;
	}

	public void setDownloadsCount(int downloadsCount) {
		this.downloadsCount = downloadsCount;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadIp() {
		return uploadIp;
	}

	public void setUploadIp(String uploadIp) {
		this.uploadIp = uploadIp;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
