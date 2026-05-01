package com.ilactakip.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "barcodes",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "code")
        }
)
public class Barcode {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code;

    // inverse side
    @OneToOne(
            mappedBy = "barcode",
            fetch = FetchType.LAZY,
            cascade = CascadeType.PERSIST
    )
    private Medicine medicine;

    public Barcode() {}

    public Barcode(String code) {
        this.code = code;
    }

    @PreRemove
    private void breakRelation() {
        if (medicine != null) {
            medicine.setBarcode(null);
        }
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }
}


