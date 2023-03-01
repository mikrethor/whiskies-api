package com.xavierbouclet.whiskies.api.model

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.persistence.Entity
import jakarta.persistence.Id
import java.util.*

@Entity
data class Whisky(
    @Id
    @JsonProperty("id")
    var id: UUID?=null,

    @JsonProperty("Bottle")
    val bottle: String="",

    @JsonProperty("Price")
    val price: String="",

    @JsonProperty("Rating")
    val rating: String="",

    @JsonProperty("Region")
    val region: String=""
)