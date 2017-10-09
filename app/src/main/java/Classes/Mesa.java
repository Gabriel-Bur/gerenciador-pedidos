package Classes;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by gabriel.bur on 11/09/2017.
 */

public class Mesa implements Serializable {


    private String MesaId;
    private String Nome;
    private Pedido pedido;


    public Mesa() {
    }

    public String getMesaId() {
        return MesaId;
    }
    public void setMesaId(String mesaId) {
        MesaId = mesaId;
    }


    public String getNome() {
        return Nome;
    }
    public void setNome(String nome) {
        Nome = nome;
    }


    public Pedido getPedido() {
        return pedido;
    }
    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return Nome.toUpperCase();

    }
}
