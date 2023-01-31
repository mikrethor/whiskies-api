package com.xavierbouclet;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Whisky extends PanacheEntityBase implements Serializable {

    @Id
    public UUID id;
    @JsonProperty("Bottle")
    public String bottle;
    @JsonProperty("Price")
    public String price;

    @JsonProperty("Rating")
    public String rating;
    @JsonProperty("Region")
    public String region;

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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Whisky whisky = (Whisky) o;

        return bottle.equals(whisky.bottle);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottle);
    }
}
