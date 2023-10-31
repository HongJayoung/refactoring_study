package book.chapter06;

public class TestApplication {
    public static void main(String[] args){
        Data data = new Data("김용휘", "KR");
        Organization organization = new Organization(data);
    }
}

class Data {
    private String title;
    private String country;

    public Data(String title, String country) {
        this.title = title;
        this.country = country;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}

class Organization {
    private String title;
    private String country;

    public Organization(Data data) {
        this.title = data.getTitle();
        this.country = data.getCountry();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}