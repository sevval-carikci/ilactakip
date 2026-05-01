package com.ilactakip.services;

import com.ilactakip.dao.Dao;
import com.ilactakip.dto.MedicineDto;
import com.ilactakip.entity.Medicine;
import com.ilactakip.entity.User;
import com.ilactakip.exception.MedicineNotFoundException;
import com.ilactakip.exception.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MedicineService {

    private final Dao dao; // artık tek Dao üzerinden işlemler
    // Constructor injection
    public MedicineService(Dao dao) {
        this.dao = dao;
    }

    /* ================== GET ALL ================== */
    public List<Medicine> getAllMedicines() {
        return dao.findAllMedicines();
    }

    /* ================== GET BY ID ================== */
    public Medicine getMedicineById(Long id) {
        Medicine medicine = dao.findMedicineById(id);
        if (medicine == null) {
            throw new MedicineNotFoundException("İlaç bulunamadı. ID: " + id);
        }
        return medicine;
    }

    /* ================== ADD ================== */
    public Medicine addMedicine(MedicineDto dto, String username) {

        User user = dao.findUserByUsername(username);
        if (user == null) {
            throw new NotFoundException("Kullanıcı bulunamadı: " + username);
        }

        Medicine medicine = new Medicine();
        medicine.setName(dto.getName());
        medicine.setDosage(dto.getDosage());
        medicine.setFrequency(dto.getFrequency());
        medicine.setStockQuantity(dto.getStockQuantity());
        medicine.setReminderTimes(dto.getReminderTimes());
        medicine.setUser(user);

        return dao.saveMedicine(medicine);
    }


    /* ================== UPDATE ================== */
    public Medicine updateMedicine(Long id, MedicineDto dto) {
        Medicine existing = getMedicineById(id);

        existing.setName(dto.getName());
        existing.setDosage(dto.getDosage());
        existing.setFrequency(dto.getFrequency());
        existing.setStockQuantity(dto.getStockQuantity());
        existing.setReminderTimes(dto.getReminderTimes());

        return dao.updateMedicine(existing);
    }


    /* ================== PARTIAL UPDATE ================== */
    public Medicine partialUpdateMedicine(Long id, MedicineDto dto) {
        Medicine existing = getMedicineById(id);

        if (dto.getName() != null) existing.setName(dto.getName());
        if (dto.getDosage() != null) existing.setDosage(dto.getDosage());
        if (dto.getFrequency() != null) existing.setFrequency(dto.getFrequency());
        if (dto.getReminderTimes() != null) existing.setReminderTimes(dto.getReminderTimes());
        if (dto.getStockQuantity() != 0) existing.setStockQuantity(dto.getStockQuantity());

        return dao.updateMedicine(existing);
    }


    /* ================== DELETE ================== */
    public void deleteMedicine(Long id) {
        Medicine existing = getMedicineById(id);
        dao.deleteMedicine(existing);
    }
}
