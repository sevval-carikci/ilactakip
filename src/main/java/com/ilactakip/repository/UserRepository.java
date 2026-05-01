package com.ilactakip.repository;

import com.ilactakip.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    // Tüm kullanıcıları getir
    public List<User> findAll() {
        return entityManager.createQuery("SELECT u FROM User u", User.class).getResultList();
    }

    // ID’ye göre kullanıcı getir
    public User findById(Long id) {
        return entityManager.find(User.class, id);
    }

    // Kullanıcı adından kullanıcıyı bul
    public User findByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    // Yeni kullanıcı ekle veya güncelle
    public User save(User user) {
        return entityManager.merge(user);
    }

    // Kullanıcı sil
    public void delete(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    public long count() {
    TypedQuery<Long> query = entityManager.createQuery("SELECT COUNT(u) FROM User u", Long.class);
    return query.getSingleResult();
    }
}