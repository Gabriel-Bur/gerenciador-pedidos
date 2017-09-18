package Classes;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by gabriel.bur on 11/09/2017.
 */

public class Mesa {


    private UUID MesaId;
    private String Numero;
    private ArrayList<Pedido> Pedidos;

    public Mesa() {
    }

    public UUID getMesaId() {
        return MesaId;
    }

    public void setMesaId(UUID mesaId) {
        MesaId = mesaId;
    }

    public String getNumero() {
        return Numero;
    }

    public void setNumero(String numero) {
        Numero = numero;
    }

    public ArrayList<Pedido> getPedidos() {
        return Pedidos;
    }

    public void setPedidos(ArrayList<Pedido> pedidos) {
        Pedidos = pedidos;
    }
}
