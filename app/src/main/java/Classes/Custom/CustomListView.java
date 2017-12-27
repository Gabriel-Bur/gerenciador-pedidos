package Classes.Custom;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.infnet.android.gerenciador_pedidos.R;

import java.util.List;

import Classes.Entity.Item;

/**
 * Created by Gabriel on 04/10/2017.
 */

public class CustomListView extends ArrayAdapter<Item> {



    public CustomListView(@NonNull Context context, @LayoutRes int resource) {
        super(context, resource);
    }

    public CustomListView(@NonNull Context context, @LayoutRes int resource, @NonNull List<Item> itens) {
        super(context, resource, itens);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        if (v==null) {
            LayoutInflater vi;
            vi = LayoutInflater.from(getContext());
            v = vi.inflate(R.layout.list_row, null);
        }
        Item p = getItem(position);

        if(p!=null) {
            TextView qtd = (TextView) v.findViewById(R.id.qtd_list);
            TextView title = (TextView) v.findViewById(R.id.item);
            TextView obs = (TextView) v.findViewById(R.id.description);

            title.setText(p.getNome());
            obs.setText(p.getObs());
            qtd.setText(String.valueOf(p.getQuantidade()));

        }
        return v;
    }
}

