package sunshare;

public class Address {
    private String state;
    private String city;
    private String neighborhood;
    private String street;
    private String cep;

    public Address(String state, String city, String neighborhood, String street, String cep) {
        this.state = state;
        this.city = city;
        this.neighborhood = neighborhood;
        this.street = street;
        this.cep = cep;
    }

    public String formatAdress() {
        StringBuilder str = new StringBuilder();
        str.append("Estado: " + state + "\n");
        str.append("Cidade: " + city  + "\n");
        str.append("Bairro: " + neighborhood  + "\n");
        str.append("Rua: " + street  + "\n");
        str.append("CEP: " + cep  + "\n");

        return str.toString();
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

}
