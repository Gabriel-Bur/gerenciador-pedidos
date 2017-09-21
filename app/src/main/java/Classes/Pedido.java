package Classes;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

/**
 * Created by gabriel.bur on 11/09/2017.
 */

public class Pedido implements Serializable{

    private String PedidoId;
    private ArrayList<Item> itensPedidos;

    public Pedido() {
    }

    public String getPedidoId() {
        return PedidoId;
    }
    public void setPedidoId(String pedidoId) {
        PedidoId = pedidoId;
    }

    public ArrayList<Item> getItensPedidos() {
        return itensPedidos;
    }
    public void setItensPedidos(ArrayList<Item> itensPedidos) {
        this.itensPedidos = itensPedidos;
    }
}
