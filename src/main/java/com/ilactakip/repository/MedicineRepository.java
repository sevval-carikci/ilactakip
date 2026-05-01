package com.ilactakip.repository;

import com.ilactakip.entity.Medicine;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class MedicineRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Tüm ilaçları getir
    public List<Medicine> findAll() {
        TypedQuery<Medicine> query = entityManager.createQuery("SELECT m FROM Medicine m", Medicine.class);
        return query.getResultList();
    }

    // ID'ye göre ilaç getir
    public Medicine findById(Long id) {
        return entityManager.find(Medicine.class, id);
    }

    // Yeni ilaç ekle veya güncelle
    public Medicine save(Medicine medicine) {
        if (medicine.getId() == null) {
            entityManager.persist(medicine); // yeni kayıt
            return medicine;
        } else {
            return entityManager.merge(medicine); // mevcut kaydı güncelle
        }
    }

    // İlaç sil
    public void delete(Long id) {
        Medicine medicine = findById(id);
        if (medicine != null) {
            entityManager.remove(medicine);
        }
    }

    // Mevcut ilacı güncelle
    public void update(Medicine medicine) {
        entityManager.merge(medicine);
    }

    // Toplam ilaç sayısı
    public long count() {
        TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(m) FROM Medicine m", Long.class);
        return query.getSingleResult();
    }
}

