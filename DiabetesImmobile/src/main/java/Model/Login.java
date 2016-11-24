package Model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by fishe on 24.11.2016.
 */
@Entity
@Table(name="login")
public class Login {
    @Id
    @Column(name="id")
    private int id;
    @Column(name="Login")
    private String login;
    @Column(name="Password")
    private String passwd;

    public Login(){}

    public Login(int id, String login, String passwd){
        this.id = id;
        this.login = login;
        this.passwd = passwd;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }
}
