package Classes.Entity;


import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

import Classes.Entity.Item;


/**
 * Created by gabriel.bur on 11/09/2017.
 */

public class Pedido implements Serializable{

    private String PedidoId;
    @Nullable
    private ArrayList<Item> itensPedidos;

    public Pedido() {
    }

    public String getPedidoId() {
        return PedidoId;
    }
    public void setPedidoId(String pedidoId) {
        PedidoId = pedidoId;
    }


    @Nullable
    public ArrayList<Item> getItensPedidos() {
        return itensPedidos;
    }
    public void setItensPedidos(@Nullable ArrayList<Item> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }
}
