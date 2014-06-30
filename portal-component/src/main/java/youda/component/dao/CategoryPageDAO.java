package youda.component.dao;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.CategoryPage;

@DAO
public interface CategoryPageDAO {

	final static String categoryPage = "pid,cat_id,page_content";
	
	@SQL("select * from category_page where cat_id = :1 limit 1")
	public CategoryPage getCategoryPageByCatId(int catId);
	
	@SQL("update category_page set page_content=:1.pageContent where pid=:1.pid")
	public int updateCategoryPage(CategoryPage categoryPage);
	
	@SQL("insert into category_page(cat_id,page_content)" +
		 	  "values (:1.catId,:1.page_content)")
	public int addCategoryPage(CategoryPage categoryPage);
	
	@SQL("delete from category_page where cat_id = :1")
	public void delCategoryPageByCatId(int catId);
}
