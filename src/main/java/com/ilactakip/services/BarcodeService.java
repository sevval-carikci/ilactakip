package com.ilactakip.services;

import com.ilactakip.entity.Barcode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import com.ilactakip.dao.Dao;


@Service("barcodeService")
@Transactional
public class BarcodeService {

    private final Dao dao;

    public BarcodeService(Dao dao) {
        this.dao = dao;
    }

    /* ================= GET ALL ================= */
    public List<Barcode> getAllBarcodes() {
        return dao.findAllBarcodes();
    }

    /* ================= GET BY ID ================= */
    public Barcode getBarcodeById(Long id) {
        Barcode barcode = dao.findBarcodeById(id);
        if (barcode == null) {
            throw new RuntimeException("Barkod bulunamadı. ID: " + id);
        }
        return barcode;
    }

    /* ================= GET BY CODE ================= */
    public Barcode getBarcodeByCode(String code) {
        Barcode barcode = dao.findBarcodeByCode(code);
        if (barcode == null) {
            throw new RuntimeException("Barkod bulunamadı: " + code);
        }
        return barcode;
    }

    /* ================= CREATE ================= */
    public Barcode createBarcode(Barcode barcode) {
        return dao.saveBarcode(barcode);
    }

    /* ================= UPDATE ================= */
    public Barcode updateBarcode(Long id, Barcode barcode) {
        Barcode existing = getBarcodeById(id);
        existing.setCode(barcode.getCode());
        return dao.updateBarcode(existing);
    }

    /* ================= PARTIAL UPDATE ================= */
    public Barcode partialUpdateBarcode(Long id, Barcode barcode) {
        Barcode existing = getBarcodeById(id);
        if (barcode.getCode() != null) existing.setCode(barcode.getCode());
        return dao.updateBarcode(existing);
    }

    /* ================= DELETE ================= */
    public void deleteBarcode(Long id) {
        Barcode existing = getBarcodeById(id);
        dao.deleteBarcode(existing);
    }
}


