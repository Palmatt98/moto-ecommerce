package com._mx.service;

import com._mx.model.Brand;
import com._mx.repository.BrandRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandRepository brandRepository;

    public List<Brand> getAllBrands() {
        List<Brand> brands = brandRepository.findAll();
        return brands;
    }

    public Brand getBrandById(Long id) {
        //ricordiamo che per l'id serve optional e cosi facendo ce lo da.
        return brandRepository.findById(id).orElse(null);
    }

    public Brand saveBrand(Brand brand) {
        Brand brandSaved = brandRepository.save(brand);
        return brandSaved;
    }

    public Brand updateBrand(Long id, Brand brand) {
        Brand existingBrand = brandRepository.findById(id)
                .orElse(null);
        if (existingBrand != null) {
            existingBrand.setName(brand.getName());
        }
        return existingBrand;

    }

    public void deleteBrand(Long id) {
        brandRepository.deleteById(id);
    }
}
