package accounts;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "users")
public class UserProfile implements Serializable {
    @Id
    @Column(name = "idusers")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public UserProfile(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public UserProfile(){
        //This is for hibernate
    }

    public long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}
