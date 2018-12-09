package com.bharatdodeja.mycv.detail.model.data

import com.google.gson.annotations.SerializedName

data class CVDataModel(
    @SerializedName("basics")
    val basics: Basics,
    @SerializedName("education")
    val education: List<Education>,
    @SerializedName("references")
    val references: List<Reference>,
    @SerializedName("skills")
    val skills: List<Skill>,
    @SerializedName("work")
    val work: List<Work>
)

data class Education(
    @SerializedName("area")
    val area: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("institution")
    val institution: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("studyType")
    val studyType: String
)

data class Skill(
    @SerializedName("keywords")
    val keywords: List<String>,
    @SerializedName("name")
    val name: String
)

data class Basics(
    @SerializedName("email")
    val email: String,
    @SerializedName("label")
    val label: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("name")
    val name: String,
    @SerializedName("profiles")
    val profiles: List<Profile>,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("website")
    val website: String
)

data class Location(
    @SerializedName("city")
    val city: String,
    @SerializedName("countryCode")
    val countryCode: String
)

data class Profile(
    @SerializedName("network")
    val network: String,
    @SerializedName("url")
    val url: String,
    @SerializedName("username")
    val username: String
)

data class Reference(
    @SerializedName("name")
    val name: String,
    @SerializedName("reference")
    val reference: String
)

data class Work(
    @SerializedName("company")
    val company: String,
    @SerializedName("endDate")
    val endDate: String,
    @SerializedName("highlights")
    val highlights: List<String>,
    @SerializedName("position")
    val position: String,
    @SerializedName("startDate")
    val startDate: String,
    @SerializedName("summary")
    val summary: String,
    @SerializedName("website")
    val website: String
)