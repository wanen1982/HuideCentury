package youda.component.service.impl;

import java.io.File;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.constants.Constants;
import youda.component.dao.PartnerDAO;
import youda.component.model.Partner;
import youda.component.service.PartnerService;

@Component
public class PartnerServiceImpl implements PartnerService {

	@Autowired
	PartnerDAO partnerDao;
	
	@Override
	public List<Partner> getPartnerList() {
		return partnerDao.getPartnerList();
	}

	@Override
	public Partner getPartnerById(int id) {
		return partnerDao.getPartnerById(id);
	}

	@Override
	public int addPartner(Partner partner) {
		int id = partnerDao.addPartner(partner);
		partner.setId(id);
		partner.setListorder(id);
		this.updatePartner(partner);
		return id;
	}

	@Override
	public int updatePartner(Partner partner) {
		return partnerDao.updatePartner(partner);
	}

	@Override
	public void delPartner(int id) {
		Partner partner = this.getPartnerById(id);
		File file = new File(Constants.ROOT_PATH + partner.getIconPath());
		if(file.exists()){
			file.delete();
		}
		partnerDao.delPartner(id);
	}

	@Override
	public void partnerMoveUp(int id) {
		Partner partner = this.getPartnerById(id);
		Partner prePartner = partnerDao.getPrePartner(partner.getListorder());
		
		if(prePartner == null){
			return ;
		}
		
		int tmp = prePartner.getListorder();
		prePartner.setListorder(partner.getListorder());
		this.updatePartner(prePartner);
		
		partner.setListorder(tmp);
		this.updatePartner(partner);
	}

	@Override
	public void partnerMoveDown(int id) {
		Partner partner = this.getPartnerById(id);
		Partner nextPartner = partnerDao.getNextPartner(partner.getListorder());
		
		if(nextPartner == null){
			return ;
		}
		
		int tmp = nextPartner.getListorder();
		nextPartner.setListorder(partner.getListorder());
		this.updatePartner(nextPartner);
		
		partner.setListorder(tmp);
		this.updatePartner(partner);
	}

}
