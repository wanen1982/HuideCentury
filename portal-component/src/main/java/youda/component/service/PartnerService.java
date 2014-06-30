package youda.component.service;

import java.util.List;

import youda.component.model.Partner;

public interface PartnerService {

	public List<Partner> getPartnerList();
	
	public Partner getPartnerById(int id);
	
	public int addPartner(Partner partner);
	
	public int updatePartner(Partner partner);
	
	public void delPartner(int id);
	
	public void partnerMoveUp(int id);
	
	public void partnerMoveDown(int id);
}
