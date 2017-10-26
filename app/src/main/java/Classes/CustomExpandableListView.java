package Classes;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import com.infnet.android.gerenciador_pedidos.R;
import java.util.List;
import java.util.Map;

/**
 * Created by Gabriel on 17/09/2017.
 */

public class CustomExpandableListView extends BaseExpandableListAdapter {

    private Activity context;
    private List<String> itens;
    private Map<String,List<Item>> itensCollection;

    public CustomExpandableListView(){}
    public CustomExpandableListView(Activity context, List<String> itens, Map<String, List<Item>> itensCollection) {
        this.context = context;
        this.itens = itens;
        this.itensCollection = itensCollection;
    }

    @Override
    public int getGroupCount() {
        return itens.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return itensCollection.get(itens.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return itens.get(groupPosition);
    }

    @Override
    //pega o nome do objetos
    public Object getChild(int groupPosition, int childPosition) {
        return itensCollection.get(itens.get(groupPosition)).get(childPosition);
    }
    public Item getChildItem(int groupPosition, int childPosition) {
        return itensCollection.get(itens.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
     public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
     }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
                if (convertView == null){
                    LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                    convertView = inflater.inflate(R.layout.list_group,null);
                }
        TextView lblListHeader = (TextView)convertView.findViewById(R.id.lblListHeader);
        lblListHeader.setText(headerTitle);
        return convertView;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Item item = getChildItem(groupPosition,childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_item,null);
        }
        TextView txtListChild = (TextView)convertView.findViewById(R.id.lblListItem);
        txtListChild.setText(item.getNome());
        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

}
