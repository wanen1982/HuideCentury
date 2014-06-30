package youda.component.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.Category;

@DAO
public interface CategoryDAO {
	
	final static String category = "cat_id, parent_id, have_child,cat_name,type,status,path_name," +
									"list_order,need_approve,create_time,update_time,create_by,update_by";
	
	@SQL("select " + category + " from category where cat_id = :1")
	public Category getCategoryById(int categoryId);
	
	@SQL("select " + category + " from category where type=:1 and status=:2 order by list_order, create_time")
	public List<Category> getCategoryListByType(String categoryType,int status);
	
	@SQL("select " + category + " from category where parent_id=:1 and status=:2 order by list_order, create_time")
	public List<Category> getChildCategoryList(int parentCategoryId, int status);
	
	@ReturnGeneratedKeys
	@SQL("insert  into `category`(`parent_id`,`cat_name`,`type`,`path_name`,`create_time`,`update_time`,`create_by`,`update_by`) " +
			"values (:1.parentId,:1.catName,:1.type,:1.pathName,now(),now(),:1.createBy,:1.updateBy)")
	public int addNewCategory(Category category);
	
	@SQL("update category set parent_id=:1.parentId,have_child=:1.haveChild,cat_name=:1.catName,type=:1.type,status=:1.status,path_name=:1.pathName," +
			"list_order=:1.listOrder,create_time=:1.createTime,update_time=now(),create_by=:1.createBy,update_by=:1.updateBy where cat_id=:1.catId")
	public int updateCategory(Category category);
	
	@SQL("delete from category where cat_id = :1")
	public int delCategory(int categoryId);
	
	@SQL("select " + category + " from category where parent_id = :1 and list_order < :2 order by list_order desc limit 1")
	public Category getPreCategory(int parentId, int listOrder);
	
	@SQL("select " + category + " from category where parent_id = :1 and list_order > :2 order by list_order limit 1")
	public Category getNextCategory(int parentId, int listOrder);
	
	@SQL("select count(*) from category where cat_name = :2 and cat_id <> :1")
	public int checkCategoryName(int categoryId, String categoryName);
	
	@SQL("select count(*) from category where path_name = :2 and cat_id <> :1")
	public int checkCategoryPathName(int categoryId, String categoryPathName);
	
	@SQL("select count(*) from category where parent_id = :1")
	public int countCategoryChild(int categoryId);
	
	/**
	 * 读取课程类别
	 */
	@SQL("SELECT a.cat_id,a.parent_id,a.have_child,a.cat_name,a.type,a.status,a.path_name,a.list_order,a.need_approve,"
			+ "a.create_time,a.update_time,a.create_by,a.update_by"
			+ " FROM category a,category b WHERE a.parent_id=b.cat_id AND b.cat_name='课程科目'")
	public List<Category> getCourseCategory();
}
