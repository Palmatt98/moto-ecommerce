package com._mx.controller;

import com._mx.entity.Brand;
import com._mx.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BrandController {
    @Autowired
    private BrandService brandService;

    @GetMapping("/brands")
    public ResponseEntity<List<Brand>> getBrands() {
        List<Brand> brands = brandService.getAllBrands();
        return new ResponseEntity<>(brands, HttpStatus.OK);
    }

    @GetMapping("/brands/{id}")
    public ResponseEntity<Brand> getBrandById(@PathVariable Long id) {
        Brand brand = brandService.getBrandById(id);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @PostMapping("/brands")
    public ResponseEntity<Brand> createBrand(@RequestBody Brand brand) {
        Brand brandCreated = brandService.saveBrand(brand);
        return new ResponseEntity<>(brandCreated, HttpStatus.CREATED);
    }

    @PutMapping("/brands/{id}")
    public ResponseEntity<Brand> updateBrand(@PathVariable Long id, Brand brand) {
        Brand exsistingBrand = brandService.updateBrand(id, brand);
        return new ResponseEntity<>(brand, HttpStatus.OK);
    }

    @DeleteMapping("/brand/{id}")
    public ResponseEntity<Brand> deleteBrandById(@PathVariable Long id) {
        brandService.deleteBrand(id);
        return new ResponseEntity<>(HttpStatusCode.valueOf(204));

    }
}
