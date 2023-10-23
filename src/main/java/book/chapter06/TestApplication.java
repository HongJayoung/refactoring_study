package book.chapter06;

public class TestApplication {
    public static void main(String[] args){
        Data data = new Data("김용휘", "KR");
        Organization organization = new Organization(data);
    }
}

class Data {
    private String name;
    private String country;

    public Data(String name, String country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

class Organization {
    private String name;
    private String country;

    public Organization(Data data) {
        this.name = data.getName();
        this.country = data.getCountry();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}