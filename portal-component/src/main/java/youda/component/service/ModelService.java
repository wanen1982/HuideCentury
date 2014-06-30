package youda.component.service;

import java.util.List;

import youda.component.model.Model;
import youda.component.model.ModelField;

public interface ModelService {

	public Model getModeById(int modelId);
	
	public ModelField getModelFieldById(int fieldId);
	
	public List<ModelField> getModelFieldListByModelId(int modelId);
	
	public Model getNavigationModel();
	
	public List<ModelField> getNavigationList();
	
	public int updateModelField(int modelId, List<Integer> categoryIdList);
	
	public int saveModelField(ModelField modelField);
	
	public void delModelField(int fieldId, int modelId);
	
	public int updateModelFieldById(ModelField modelField);
	
	public int modelFieldMoveUp(int fieldId);
	
	public int modelFieldMoveDown(int fieldId);
}
