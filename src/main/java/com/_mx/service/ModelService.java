package com._mx.service;

import com._mx.model.Model;
import com._mx.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModelService {
    @Autowired
    private ModelRepository modelRepository;

    public List<Model> getModels() {
        List<Model> modelList = modelRepository.findAll();
        return modelList;
    }

    public Model getModelById(Long id) {
        Model model = modelRepository.findById(id)
                .orElse(null);
        return model;
    }

    public Model updateModel(Long id, Model model) {
        Model exsistingModel = modelRepository.findById(id)
                .orElse(null);
        if (exsistingModel != null) {
            exsistingModel.setName(model.getName());
            exsistingModel.setYear(model.getYear());
            exsistingModel.setEngine_quantity(model.getEngine_quantity());
        }
        return modelRepository.save(exsistingModel);
    }

    public Model saveModel(Model model) {
        Model modelCreated = modelRepository.save(model);
        return modelCreated;
    }

    public void deleteModelById(Long id) {
        modelRepository.deleteById(id);
    }

    public List<Model> getModelByBrandId(Long brandId) {
        return modelRepository.findBrandById(brandId);
    }



}
