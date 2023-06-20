package com.xavierbouclet.whiskies.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Whisky {

    @Id
    @JsonProperty("id")
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

    public UUID id() {
        return id;
    }

    public void id(UUID id) {
        this.id = id;
    }

    public String bottle() {
        return bottle;
    }

    public void bottle(String bottle) {
        this.bottle = bottle;
    }

    public String price() {
        return price;
    }

    public void price(String price) {
        this.price = price;
    }

    public String rating() {
        return rating;
    }

    public void rating(String rating) {
        this.rating = rating;
    }

    public String region() {
        return region;
    }

    public void region(String region) {
        this.region = region;
    }
}
