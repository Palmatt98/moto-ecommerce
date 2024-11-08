package com._mx.controller;

import com._mx.model.Model;
import com._mx.service.ModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ModelController {
    @Autowired
    private ModelService modelService;

    @GetMapping("/models")
    public ResponseEntity<List<Model>> getModels() {
        List<Model> models = modelService.getModels();
        return new ResponseEntity<>(models, HttpStatus.OK);
    }

    @GetMapping("models/{id}")
    public ResponseEntity<Model> getModelById(@PathVariable Long id) {
        Model modelById = modelService.getModelById(id);
        return new ResponseEntity<>(modelById, HttpStatus.OK);
    }

    @PutMapping("/models/{id}")
    public ResponseEntity<Model> updateModelById(@PathVariable Long id, @RequestBody Model model) {
        Model exsitingModel = modelService.updateModel(id, model);
        return new ResponseEntity<>(exsitingModel, HttpStatus.OK);
    }

    @PostMapping("/models")
    public ResponseEntity<Model> createModel(@RequestBody Model model) {
        Model modelCreated = modelService.saveModel(model);
        return new ResponseEntity<>(modelCreated, HttpStatus.CREATED);

    }

    @DeleteMapping("/models/{id}")
    public ResponseEntity<Model> deleteModelById(@PathVariable Long id) {
        modelService.deleteModelById(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));
    }

    @GetMapping("/models/brand/{brandId}")
    public ResponseEntity<List<Model>> getModelByBrandId(@PathVariable Long brandId) {
        List<Model> modelByBrandId = modelService.getModelByBrandId(brandId);
        return new ResponseEntity<>(modelByBrandId, HttpStatus.OK);
    }


}
