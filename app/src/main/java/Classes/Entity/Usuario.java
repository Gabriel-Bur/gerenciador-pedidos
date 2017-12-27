package Classes.Entity;

import java.util.UUID;

/**
 * Created by gabriel.bur on 18/09/2017.
 */

public class Usuario {

    private UUID usuarioId;
    private String email;
    private String password;

    public Usuario() {
    }

    public UUID getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UUID usuarioId) {
        this.usuarioId = usuarioId;
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
