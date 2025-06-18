package neehru.app.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Username is required")
    private String username;

    @NotNull(message = "Date of birth is required")
    private LocalDate birthday;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 letters long")
    private String password;

    private Boolean visible = true;

    private String profilePicture;

    private String bio;

    // constructors

    public User(){}

    public User(String username, String name, LocalDate  birthday, String password, Boolean visible, String profilePicture, String bio){
        this.id = id;
        this.username = username;
        this.name= name;
        this.birthday = birthday;
        this.password = password;
        this.visible = visible;
        this.profilePicture = profilePicture;
        this.bio = bio;
    }

    // getters and setters

    public Long getId(){
        return this.id;
    }

    public void setId(Long id){
        this.id = id;
    }

    public String getName(){
        return this.name;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDate getBirthday(){
        return this.birthday;
    }

    public void setBirthday(LocalDate birthday){
        this.birthday = birthday;
    }

    public String getPassword(){
        return this.password;
    }

    public void setPassword (String password) {
        this.password = password;
    }

    public Boolean getVisible(){
        return this.visible;
    }

    public void setVisible(Boolean visible){
        this.visible = visible;
    }

    public String getProfilePicture(){
        return this.profilePicture;
    }

    public void setProfilePicture(String profilePicture){
        this.profilePicture = profilePicture;
    }

    public String getBio(){
        return this.bio;
    }

    public void setBio(String bio){
        this.bio = bio;
    }

}
