package com.zti.pantry_portal.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Document("measurement")
public class Measurement {
    @Id
    private String id;

    @Field(name="name")
    private String name;

    @Field(name="weight")
    private Float weight;

    @Field(name="waist")
    private Float waist;

    @Field(name="hips")
    private Float hips;

    @Field(name="thighs")
    private Float thighs;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date dateField;

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Float getWaist() {
        return waist;
    }

    public void setWaist(Float waist) {
        this.waist = waist;
    }

    public Float getHips() {
        return hips;
    }

    public void setHips(Float hips) {
        this.hips = hips;
    }

    public Float getThighs() {
        return thighs;
    }

    public void setThighs(Float thighs) {
        this.thighs = thighs;
    }

    public Date getDateField() {
        return dateField;
    }

    public void setDateField(Date dateField) {
        this.dateField = dateField;
    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}