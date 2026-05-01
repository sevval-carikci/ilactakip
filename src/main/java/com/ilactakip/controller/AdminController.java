package com.ilactakip.controller;

import com.ilactakip.dto.MedicineDto;
import com.ilactakip.entity.Medicine;
import com.ilactakip.services.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/medicines")
public class AdminController {

    @Autowired
    @Qualifier("medicineService")  
    private MedicineService medicineService;

    @GetMapping("/list")
    public List<Medicine> getAllMedicines() {
        return medicineService.getAllMedicines();
    }

    @GetMapping("/{id}")
    public Medicine getMedicineById(@PathVariable Long id) {
        return medicineService.getMedicineById(id);
    }

    @PostMapping
    public Medicine addMedicine(@RequestBody MedicineDto dto,
                                Authentication authentication) {
        String username = authentication.getName();
        return medicineService.addMedicine(dto, username);
    }

    @PutMapping("/{id}")
    public Medicine updateMedicine(@PathVariable Long id,
                                   @RequestBody MedicineDto dto) {
        return medicineService.updateMedicine(id, dto);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Medicine> partialUpdateMedicine(
            @PathVariable Long id,
            @RequestBody MedicineDto dto
    ) {
        Medicine updated = medicineService.partialUpdateMedicine(id, dto);
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public void deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
    }
}

