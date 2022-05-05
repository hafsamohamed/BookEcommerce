package gov.iti.jets.presentation.dtos;

import gov.iti.jets.persistence.entities.UserType;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.io.Serializable;
import java.time.LocalDate;
import gov.iti.jets.persistence.util.ValidationUtil;

@XmlRootElement
public class UserDto implements Serializable {
    private int id;

    private LocalDate birthDate;
    private UserType userType;
    private String userName;
    private String email;
    private String country;
    private String city;
    private String detailedAddress;
    private String phoneNumber;
    private double wallet;
    private String password;

    public UserDto() {
    }

    public UserDto(int id, LocalDate birthDate, UserType userType, String userName, String email, String country,
            String city, String detailedAddress, String phoneNumber, double wallet, String password) {
        this.id = id;
        this.birthDate = birthDate;
        this.userType = userType;
        this.userName = userName;
        this.email = email;
        this.country = country;
        this.city = city;
        this.detailedAddress = detailedAddress;
        this.phoneNumber = phoneNumber;
        this.wallet = wallet;
        this.password = password;
        ValidationUtil.getInstance().validate(this);

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDetailedAddress() {
        return detailedAddress;
    }

    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public double getWallet() {
        return wallet;
    }

    public void setWallet(double wallet) {
        this.wallet = wallet;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserDto [birthDate=" + birthDate + ", city=" + city + ", country=" + country + ", detailedAddress="
                + detailedAddress + ", email=" + email + ", id=" + id + ", password=" + password + ", phoneNumber="
                + phoneNumber + ", userName=" + userName + ", userType=" + userType + ", wallet=" + wallet + "]";
    }

}
