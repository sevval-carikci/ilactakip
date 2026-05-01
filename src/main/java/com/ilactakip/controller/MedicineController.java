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
@RequestMapping("/api/user/medicines")
public class MedicineController {

    @Autowired
    @Qualifier("medicineService")
    private MedicineService medicineService;


    @GetMapping("/list")
    public ResponseEntity<List<Medicine>> getAllMedicines() {
        List<Medicine> medicines = medicineService.getAllMedicines();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Medicine> getMedicineById(@PathVariable Long id) {
        Medicine medicine = medicineService.getMedicineById(id);
        return new ResponseEntity<>(medicine, HttpStatus.OK);
    }


    @PostMapping
    public ResponseEntity<Medicine> addMedicine(
            @RequestBody MedicineDto dto,
            Authentication authentication
    ) {
        String username = authentication.getName();
        Medicine newMedicine = medicineService.addMedicine(dto, username);
        return new ResponseEntity<>(newMedicine, HttpStatus.CREATED);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteMedicine(@PathVariable Long id) {
        medicineService.deleteMedicine(id);
        return new ResponseEntity<>("İlaç başarıyla silindi.", HttpStatus.NO_CONTENT);
    }
}
