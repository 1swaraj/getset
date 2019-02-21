package com.getset.career.guidance;

public class AthleticModel {

    private String name;
    private Country country;
    private long score;

    public AthleticModel(String name, Country country, long score) {
        this.name = name;
        this.country = country;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }

    public long getScore() {
        return score;
    }
}
