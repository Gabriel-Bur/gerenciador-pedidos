package Classes;

import android.support.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by gabriel.bur on 11/09/2017.
 */

public class Mesa  {


    private String MesaId;
    private String Nome;
    @Nullable
    private Pedido pedido;
    private Boolean statusPedido;

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

    @Nullable
    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(@Nullable Pedido pedido) {
        this.pedido = pedido;
    }

    public Boolean getStatusPedido() {
        return statusPedido;
    }

    public void setStatusPedido(Boolean statusPedido) {
        this.statusPedido = statusPedido;
    }
}
