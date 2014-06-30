package youda.component.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.Partner;

@DAO
public interface PartnerDAO {

	final static String partner = "id,name,iconPath,linkUrl,listorder";
	
	@SQL("select " + partner + " from partner order by listorder")
	public List<Partner> getPartnerList();
	
	@SQL("select " + partner + " from partner where id = :1")
	public Partner getPartnerById(int id);
	
	@ReturnGeneratedKeys
	@SQL("insert into partner(name,iconPath,linkUrl,listorder) values (:1.name,:1.iconPath,:1.linkUrl,:1.listorder)")
	public int addPartner(Partner partner);
	
	@SQL("update partner set name=:1.name,iconPath=:1.iconPath,linkUrl=:1.linkUrl,listorder=:1.listorder where id=:1.id")
	public int updatePartner(Partner partner);
	
	@SQL("delete from partner where id = :1")
	public void delPartner(int id);
	
	@SQL("select " + partner + " from partner where listorder < :1 order by listorder desc limit 1")
	public Partner getPrePartner(int listorder);
	
	@SQL("select " + partner + " from partner where listorder > :1 order by listorder limit 1")
	public Partner getNextPartner(int listorder);
}
