package Classes;

import android.support.annotation.Nullable;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Gabriel on 22/10/2017.
 */

public class Conta implements Serializable {

    private String ContaId;
    @Nullable
    private ArrayList<Item> itensConta;

    public Conta() {
    }


    public String getContaId() {
        return ContaId;
    }
    public void setContaId(String contaId) {
        ContaId = contaId;
    }

    @Nullable
    public ArrayList<Item> getItensConta() {
        return itensConta;
    }
    public void setItensConta(@Nullable ArrayList<Item> itensConta) {
        this.itensConta = itensConta;
    }
}
