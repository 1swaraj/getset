package getset.guidance.career.getset;

public enum Country {
    ITALY("Italy"),
    USA("United States"),
    ROK("South Korea");

    private final String country;

    Country(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
