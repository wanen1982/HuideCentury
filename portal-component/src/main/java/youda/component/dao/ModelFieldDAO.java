package youda.component.dao;

import java.util.List;

import net.paoding.rose.jade.annotation.DAO;
import net.paoding.rose.jade.annotation.ReturnGeneratedKeys;
import net.paoding.rose.jade.annotation.SQL;
import youda.component.model.ModelField;

@DAO
public interface ModelFieldDAO {

	final static String modelField = "field_id,model_id,cat_id,belong,show_name,list_order,editable,locked";
	
	@SQL("select " + modelField + " from model_field where field_id = :1")
	public ModelField getModelFieldById(int modelFieldId);
	
	@SQL("select " + modelField + " from model_field where model_id = :1 order by list_order")
	public List<ModelField> getModelFieldList(int modelId);
	
	@SQL("delete from model_field where model_id = :1 and editable = 1")
	public void deleteModelFieldByModelId(int modelId);
	
	@ReturnGeneratedKeys
	@SQL("insert into model_field (`model_id`,`cat_id`,`belong`,`show_name`,`list_order`,`editable`) values (:1.modelId,:1.catId,:1.belong,:1.showName,:1.listOrder,:1.editable)")
	public int saveModelField(ModelField modelField);
	
	@SQL("select count(*) from model_field where model_id = :1 and cat_id = -1")
	public int getModelManualNums(int modelId);
	
	@SQL("select count(*) from model_field where model_id = :1 and cat_id = :2")
	public int checkCategoryInModelField(int modelId, int catId);
	
	@SQL("update model_field set model_id=:1.modelId,cat_id=:1.catId,belong=:1.belong,show_name=:1.showName," +
			"list_order=:1.listOrder where field_id=:1.fieldId")
	public int updateModelField(ModelField modelField);
	
	@SQL("delete from model_field where field_id=:1 and model_id=:2 and editable = 1")
	public void delModelField(int fieldId, int modelId);
	
	@SQL("select " + modelField + " from model_field where model_id = :1 and list_order < :2 order by list_order desc limit 1")
	public ModelField getPreModelField(int modelId, int listOrder);
	
	@SQL("select " + modelField + " from model_field where model_id = :1 and list_order > :2 order by list_order limit 1")
	public ModelField getNextModelField(int modelId, int listOrder);
	
}
