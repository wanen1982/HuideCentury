package youda.component.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import youda.component.dao.ModelDAO;
import youda.component.dao.ModelFieldDAO;
import youda.component.model.Model;
import youda.component.model.ModelField;
import youda.component.service.CategoryService;
import youda.component.service.ModelService;

@Component
public class ModelServiceImpl implements ModelService {

	@Autowired
	ModelDAO modelDao;
	@Autowired
	ModelFieldDAO modelFieldDao;
	@Autowired
	CategoryService categoryService;
	
	@Override
	public Model getModeById(int modelId) {
		return modelDao.getModelById(modelId);
	}

	@Override
	public ModelField getModelFieldById(int fieldId) {
		return modelFieldDao.getModelFieldById(fieldId);
	}

	@Override
	public List<ModelField> getModelFieldListByModelId(int modelId) {
		List<ModelField> modelFieldList = modelFieldDao.getModelFieldList(modelId);
		for(ModelField modelField : modelFieldList){
			modelField.setLinkCategory(categoryService.getCategoryById(modelField.getCatId()));
		}		
		return modelFieldList;
	}
	
	@Override
	public Model getNavigationModel() {
		return modelDao.getNavigationModel();
	}

	@Override
	public List<ModelField> getNavigationList() {
		List<ModelField> modelFieldList = modelFieldDao.getModelFieldList(1);
		for(ModelField modelField : modelFieldList){
			modelField.setChildCategoryList(categoryService.getChildCategoryList(modelField.getCatId(), 1));
		}
		return modelFieldList;
	}

	@Override
	public int updateModelField(int modelId, List<Integer> categoryIdList) {
		modelFieldDao.deleteModelFieldByModelId(modelId);
		Model model = this.getModeById(modelId);
		for(int categoryId : categoryIdList){
			if(categoryId == 0){
				continue;
			}
			
			if(categoryId != -1 && modelFieldDao.checkCategoryInModelField(modelId, categoryId) > 0){
				continue;
			}
			
			ModelField modelField = new ModelField();
			modelField.setCatId(categoryId);
			modelField.setModelId(modelId);
			modelField.setBelong("东盟模板 -&gt " + model.getName().replace(" ", ""));
			
			int fieldId = modelFieldDao.saveModelField(modelField);
			
			//设置ModelField 的 List Order
			modelField = this.getModelFieldById(fieldId);
			modelField.setListOrder(fieldId);
			modelFieldDao.updateModelField(modelField);
		}
		return 1;
	}

	@Override
	public int saveModelField(ModelField modelField) {
		modelField.setEditable(1);
		int fieldId = modelFieldDao.saveModelField(modelField);
		modelField.setFieldId(fieldId);
		modelField.setListOrder(fieldId);
		modelFieldDao.updateModelField(modelField);
		return 1;
	}

	@Override
	public void delModelField(int fieldId, int modelId) {
		modelFieldDao.delModelField(fieldId, modelId);
	}

	@Override
	public int updateModelFieldById(ModelField modelField) {
		return modelFieldDao.updateModelField(modelField);
	}

	@Override
	public int modelFieldMoveUp(int fieldId) {
		ModelField modelField = this.getModelFieldById(fieldId);
		ModelField preModelField = modelFieldDao.getPreModelField(modelField.getModelId(), modelField.getListOrder());
		if(preModelField == null){
			return 0;
		}
		//Change list order
		int tmp = preModelField.getListOrder();
		preModelField.setListOrder(modelField.getListOrder());
		this.updateModelFieldById(preModelField);
		
		modelField.setListOrder(tmp);
		this.updateModelFieldById(modelField);
		
		return 1;
	}

	@Override
	public int modelFieldMoveDown(int fieldId) {
		ModelField modelField = this.getModelFieldById(fieldId);
		ModelField nextModelField = modelFieldDao.getNextModelField(modelField.getModelId(), modelField.getListOrder());
		if(nextModelField == null){
			return 0;
		}
		//Change list order
		int tmp = nextModelField.getListOrder();
		nextModelField.setListOrder(modelField.getListOrder());
		this.updateModelFieldById(nextModelField);
		
		modelField.setListOrder(tmp);
		this.updateModelFieldById(modelField);
		return 1;
	}
}
