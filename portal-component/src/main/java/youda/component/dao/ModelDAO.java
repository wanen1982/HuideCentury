package youda.component.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.Model;

@DAO
public interface ModelDAO {

	final static String model = "model_id,name,description,min_categorynums," +
								"max_categorynums,isNavigation,update_time,update_by";
	
	@SQL("select " + model + " from model where model_id = :1")
	public Model getModelById(int modelId);
	
	@SQL("select " + model + " from model where template_id = :1 and isNavigation = 0 order by model_id;")
	public List<Model> getModelList(int templateId);
	
	@SQL("select " + model + " from model where isNavigation = 1")
	public Model getNavigationModel();
	
}
