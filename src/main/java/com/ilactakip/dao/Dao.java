package com.ilactakip.dao;

import com.ilactakip.entity.*;

import java.util.List;

public interface Dao {

    // ===== User işlemleri =====
    List<User> findAllUsers();
    User findUserById(Long id);
    User findUserByUsername(String username);
    User saveUser(User user);
    User updateUser(User user);
    void deleteUser(User user);

    // ===== Medicine işlemleri =====
    List<Medicine> findAllMedicines();
    Medicine findMedicineById(Long id);
    Medicine saveMedicine(Medicine medicine);
    Medicine updateMedicine(Medicine medicine);
    void deleteMedicine(Medicine medicine);

    // ===== Role işlemleri =====
    List<Role> findAllRoles();
    Role findRoleById(Long id);
    Role findRoleByName(String name);
    Role saveRole(Role role);
    Role updateRole(Role role);
    void deleteRole(Role role);

    // Category işlemleri
    List<Category> findAllCategories();
    Category findCategoryById(Long id);
    Category saveCategory(Category category);
    Category updateCategory(Category category);
    void deleteCategory(Category category);

    // ================= BARCODE =================
    List<Barcode> findAllBarcodes();
    Barcode findBarcodeById(Long id);
    Barcode findBarcodeByCode(String code);
    Barcode saveBarcode(Barcode barcode);
    Barcode updateBarcode(Barcode barcode);
    void deleteBarcode(Barcode barcode);

}


