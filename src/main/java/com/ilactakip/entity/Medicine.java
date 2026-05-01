package com.ilactakip.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "medicines")
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // İlaç Adı
    @Column(name = "name", nullable = false)
    private String name;

    // Doz
    @Column(name = "dosage")
    private String dosage;

    // Kullanım sıklığı
    @Column(name = "frequency")
    private String frequency;

    // ✅ Integer (PATCH uyumlu)
    @Column(name = "stock_quantity")
    private Integer stockQuantity;

    // Hatırlatıcı saatler
    @Column(name = "reminder_times")
    private String reminderTimes;

    // İlacı ekleyen kullanıcı
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_username", nullable = false)
    @JsonIgnore
    private User user;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                CascadeType.PERSIST,
                CascadeType.MERGE
    })
    @JoinTable(
            name = "medicine_categories",
            joinColumns = @JoinColumn(name = "medicine_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private java.util.Set<Category> categories = new java.util.HashSet<>();


    // 🔗 ONE TO ONE → BARCODE
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "barcode_id",
            unique = true
    )
    private Barcode barcode;

    @PreRemove
    private void removeAssociations() {

        if (user != null) {
            user.getMedicines().remove(this);
        }

        categories.clear();
    }



    public Medicine() {
    }

    /* GETTERS */

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDosage() {
        return dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public String getReminderTimes() {
        return reminderTimes;
    }

    public User getUser() {
        return user;
    }

    public Barcode getBarcode() {
        return barcode;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    /* SETTERS */

    public void setName(String name) {
        this.name = name;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void setReminderTimes(String reminderTimes) {
        this.reminderTimes = reminderTimes;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBarcode(Barcode barcode) {
        this.barcode = barcode;
        if (barcode != null) {
            barcode.setMedicine(this);
        }
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}