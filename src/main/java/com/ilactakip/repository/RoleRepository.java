package com.ilactakip.repository;

import com.ilactakip.entity.Role;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.ilactakip.repository.RoleRepository;


import java.util.List;

@Repository
@Transactional
public class RoleRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Tüm rolleri getir
    public List<Role> findAll() {
        TypedQuery<Role> query =
                entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    // ID ile rol bul
    public Role findById(Long id) {
        return entityManager.find(Role.class, id);
    }

    // Rol adına göre bul (SECURITY için çok önemli)
    public Role findByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);

        List<Role> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    // Kaydet / güncelle
    public Role save(Role role) {
        return entityManager.merge(role);
    }

    // Sil
    public void delete(Role role) {
        entityManager.remove(entityManager.contains(role) ? role : entityManager.merge(role));
    }

    public long count() {
        TypedQuery<Long> query =
                entityManager.createQuery("SELECT COUNT(r) FROM Role r", Long.class);
        return query.getSingleResult();
    }
}
