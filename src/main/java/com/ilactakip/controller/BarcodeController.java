package com.ilactakip.controller;

import com.ilactakip.entity.Barcode;
import com.ilactakip.services.BarcodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barcodes")
public class BarcodeController {

    @Autowired
    private BarcodeService barcodeService;

    /* ================= GET ALL ================= */
    @GetMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<List<Barcode>> getAllBarcodes() {
        return ResponseEntity.ok(barcodeService.getAllBarcodes());
    }

    /* ================= GET BY ID ================= */
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<Barcode> getBarcodeById(@PathVariable Long id) {
        return ResponseEntity.ok(barcodeService.getBarcodeById(id));
    }

    /* ================= GET BY CODE ================= */
    @GetMapping("/code/{code}")
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER','USER')")
    public ResponseEntity<Barcode> getBarcodeByCode(@PathVariable String code) {
        return ResponseEntity.ok(barcodeService.getBarcodeByCode(code));
    }

    /* ================= CREATE ================= */
    @PostMapping
    @PreAuthorize("hasAnyRole('ADMIN','MANAGER')")
    public ResponseEntity<Barcode> createBarcode(@RequestBody Barcode barcode) {
        return ResponseEntity.ok(barcodeService.createBarcode(barcode));
    }

    /* ================= UPDATE ================= */
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Barcode> updateBarcode(@PathVariable Long id, @RequestBody Barcode barcode) {
        return ResponseEntity.ok(barcodeService.updateBarcode(id, barcode));
    }

    /* ================= PARTIAL UPDATE ================= */
    @PatchMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Barcode> partialUpdateBarcode(@PathVariable Long id, @RequestBody Barcode barcode) {
        return ResponseEntity.ok(barcodeService.partialUpdateBarcode(id, barcode));
    }

    /* ================= DELETE ================= */
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteBarcode(@PathVariable Long id) {
        barcodeService.deleteBarcode(id);
        return ResponseEntity.noContent().build();
    }
}

