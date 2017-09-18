package Classes;

import java.util.UUID;

/**
 * Created by Gabriel on 12/09/2017.
 */

public class Usuario {

    private UUID usuarioId;
    private String email;
    private String password;


    public Usuario() {

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
