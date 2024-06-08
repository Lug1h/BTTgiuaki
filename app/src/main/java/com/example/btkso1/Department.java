package com.example.btkso1;

public class Department {
    private int id;
    private String name;
    private String email;
    private String website;
    private String logoPath;
    private String address;
    private String phone;
    private int parentId;

    public Department(int id, String name, String email, String website, String logoPath, String address, String phone, int parentId) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.website = website;
        this.logoPath = logoPath;
        this.address = address;
        this.phone = phone;
        this.parentId = parentId;
    }

    // Các phương thức getter và setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getWebsite() { return website; }
    public void setWebsite(String website) { this.website = website; }
    public String getLogoPath() { return logoPath; }
    public void setLogoPath(String logoPath) { this.logoPath = logoPath; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }
    public int getParentId() { return parentId; }
    public void setParentId(int parentId) { this.parentId = parentId; }
}
