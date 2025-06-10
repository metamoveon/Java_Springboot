package com.app.my_project.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.my_project.entity.ProductionEntity;
import com.app.my_project.entity.SaleTempEntity;
import com.app.my_project.repository.ProductionRepository;
import com.app.my_project.repository.SaleTempRepository;
import com.app.my_project.service.UserService;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/SaleTemp")
public class SaleTempApiController {
    @Autowired
    private SaleTempRepository saleTempRepository;

    @Autowired
    private ProductionRepository productionRepository;

    @PostMapping
    public SaleTempEntity create(
            @RequestBody SaleTempEntity saleTemp,
            @RequestHeader("Authorization") String token) {
        UserService userService = new UserService();
        String userId = userService.getUserIdFromToken(token).toString();

        Long productionId = saleTemp.getProduction().getId();

        ProductionEntity productionEntity = productionRepository.findById(productionId)
                .orElseThrow(() -> new RuntimeException("production not found"));

        Long userIdValue = Long.parseLong(userId);

        // old SaleTemp
        SaleTempEntity oldSaleTemp = saleTempRepository.findByProductionIdAndUserId(
                productionId,
                userIdValue);

        // update record
        if (oldSaleTemp != null) {
            oldSaleTemp.setQty(oldSaleTemp.getQty() + 1);
            return saleTempRepository.save(oldSaleTemp);
        }

        // new record
        saleTemp.setQty(1);
        saleTemp.setUserId(Integer.parseInt(userId));
        saleTemp.setPrice(productionEntity.getPrice());
        saleTemp.setProduction(productionEntity);

        return saleTempRepository.save(saleTemp);
    }

    @GetMapping
    public List<SaleTempEntity> list(@RequestHeader("Authorization") String token) {
        UserService userService = new UserService();
        Long userId = userService.getUserIdFromToken(token);

        return saleTempRepository.findAllByUserIdOrderByIdDesc(userId);
    }

    @DeleteMapping("/{id}")
    public void delete(@RequestHeader("Authorization") String token, @PathVariable Long id) {
        UserService userService = new UserService();
        Long userId = userService.getUserIdFromToken(token);

        if (userId == null) {
            throw new RuntimeException("user not found");
        }

        saleTempRepository.deleteById(id);
    }

    @PutMapping("/{id}")
    public void update(@PathVariable Long id, @RequestBody SaleTempEntity saleTemp) {
        SaleTempEntity saleTempEntity = saleTempRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("sale temp not found"));

        saleTempEntity.setQty(saleTemp.getQty());
        saleTempRepository.save(saleTempEntity);
    }
}