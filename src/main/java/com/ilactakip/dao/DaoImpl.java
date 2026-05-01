package com.ilactakip.dao;

import com.ilactakip.dao.Dao;
import com.ilactakip.entity.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class DaoImpl implements Dao {

    @PersistenceContext
    private EntityManager entityManager;

    /* ================= USER ================= */
    @Override
    public List<User> findAllUsers() {
        TypedQuery<User> query = entityManager.createQuery("SELECT u FROM User u", User.class);
        return query.getResultList();
    }

    @Override
    public User findUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User findUserByUsername(String username) {
        TypedQuery<User> query = entityManager.createQuery(
                "SELECT u FROM User u WHERE u.username = :username", User.class);
        query.setParameter("username", username);
        List<User> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public User saveUser(User user) {
        entityManager.persist(user);
        return user;
    }

    @Override
    public User updateUser(User user) {
        return entityManager.merge(user);
    }

    @Override
    public void deleteUser(User user) {
        entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
    }

    /* ================= MEDICINE ================= */
    @Override
    public List<Medicine> findAllMedicines() {
        TypedQuery<Medicine> query = entityManager.createQuery("SELECT m FROM Medicine m", Medicine.class);
        return query.getResultList();
    }

    @Override
    public Medicine findMedicineById(Long id) {
        return entityManager.find(Medicine.class, id);
    }

    @Override
    public Medicine saveMedicine(Medicine medicine) {
        entityManager.persist(medicine);
        return medicine;
    }

    @Override
    public Medicine updateMedicine(Medicine medicine) {
        return entityManager.merge(medicine);
    }

    @Override
    public void deleteMedicine(Medicine medicine) {
        entityManager.remove(entityManager.contains(medicine) ? medicine : entityManager.merge(medicine));
    }

    /* ================= ROLE ================= */
    @Override
    public List<Role> findAllRoles() {
        TypedQuery<Role> query = entityManager.createQuery("SELECT r FROM Role r", Role.class);
        return query.getResultList();
    }

    @Override
    public Role findRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public Role findRoleByName(String name) {
        TypedQuery<Role> query = entityManager.createQuery(
                "SELECT r FROM Role r WHERE r.name = :name", Role.class);
        query.setParameter("name", name);
        List<Role> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Role saveRole(Role role) {
        entityManager.persist(role);
        return role;
    }

    @Override
    public Role updateRole(Role role) {
        return entityManager.merge(role);
    }

    @Override
    public void deleteRole(Role role) {
        entityManager.remove(entityManager.contains(role) ? role : entityManager.merge(role));
    }

    /* ================= CATEGORY ================= */
    @Override
    public List<Category> findAllCategories() {
        TypedQuery<Category> query = entityManager.createQuery("SELECT c FROM Category c", Category.class);
        return query.getResultList();
    }

    @Override
    public Category findCategoryById(Long id) {
        return entityManager.find(Category.class, id);
    }

    @Override
    public Category saveCategory(Category category) {
        entityManager.persist(category);
        return category;
    }

    @Override
    public Category updateCategory(Category category) {
        return entityManager.merge(category);
    }

    @Override
    public void deleteCategory(Category category) {
        entityManager.remove(entityManager.contains(category) ? category : entityManager.merge(category));
    }

    /* ================= BARCODE ================= */
    @Override
    public List<Barcode> findAllBarcodes() {
        TypedQuery<Barcode> query = entityManager.createQuery("SELECT b FROM Barcode b", Barcode.class);
        return query.getResultList();
    }

    @Override
    public Barcode findBarcodeById(Long id) {
        return entityManager.find(Barcode.class, id);
    }

    @Override
    public Barcode findBarcodeByCode(String code) {
        TypedQuery<Barcode> query = entityManager.createQuery(
                "SELECT b FROM Barcode b WHERE b.code = :code", Barcode.class);
        query.setParameter("code", code);
        List<Barcode> result = query.getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Barcode saveBarcode(Barcode barcode) {
        entityManager.persist(barcode);
        return barcode;
    }

    @Override
    public Barcode updateBarcode(Barcode barcode) {
        return entityManager.merge(barcode);
    }

    @Override
    public void deleteBarcode(Barcode barcode) {
        entityManager.remove(entityManager.contains(barcode) ? barcode : entityManager.merge(barcode));
    }


}

