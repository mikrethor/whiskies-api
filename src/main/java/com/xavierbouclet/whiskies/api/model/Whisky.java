package com.xavierbouclet.whiskies.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

@Entity
public class Whisky {

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

    protected Whisky() {
    }

    public Whisky(UUID id, String bottle, String price, String rating, String region) {
        this();
        this.id = id;
        this.bottle = bottle;
        this.price = price;
        this.rating = rating;
        this.region = region;
    }

    public UUID getId() {
        return id;
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
