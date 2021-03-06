package Classes.Entity;



import java.io.Serializable;


/**
 * Created by gabriel.bur on 11/09/2017.
 */

public class Mesa implements Serializable {


    private String MesaId;
    private String Nome;
    private Pedido pedido;
    private Conta conta;


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

    public Conta getConta() {
        return conta;
    }
    public void setConta(Conta conta) {
        this.conta = conta;
    }


    @Override
    public String toString() {
        return Nome;

    }
}
