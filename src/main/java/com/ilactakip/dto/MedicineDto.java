package com.ilactakip.dto;

public class MedicineDto {
    
    
    private Long id;
      
    private String name;

    private String dosage;

    private String frequency;

    private Integer stockQuantity;

    private String reminderTimes;


    public MedicineDto() {
    }

    public MedicineDto(Long id, String name, String dosage, String frequency, int stockQuantity, String reminderTimes, String userId) {
        this.id = id;
        this.name = name;
        this.dosage = dosage;
        this.frequency = frequency;
        this.stockQuantity = stockQuantity;
        this.reminderTimes = reminderTimes;
    }

    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getReminderTimes() {
        return reminderTimes;
    }

    public void setReminderTimes(String reminderTimes) {
        this.reminderTimes = reminderTimes;
    }

}