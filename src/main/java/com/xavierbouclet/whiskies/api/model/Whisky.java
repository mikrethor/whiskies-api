package com.xavierbouclet.whiskies.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table
public class Whisky implements Persistable<UUID> {

    @Id
    private UUID id;

    @JsonProperty("Bottle")
    private String bottle;

    @JsonProperty("Price")
    private String price;

    @JsonProperty("Rating")
    private String rating;

    @JsonProperty("Region")
    private String region;

    @Transient
    private boolean isNew = false;

    protected Whisky() {
    }

    public Whisky(UUID id, String bottle, String price, String rating, String region) {
        this();
        if (id == null) {
            this.isNew = true;
        }
        this.id = id;
        this.bottle = bottle;
        this.price = price;
        this.rating = rating;
        this.region = region;
    }

    public UUID getId() {
        return id;
    }

    @JsonIgnore
    @Override
    public boolean isNew() {
        return this.isNew;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getBottle() {
        return bottle;
    }

    public void setBottle(String bottle) {
        this.bottle = bottle;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
