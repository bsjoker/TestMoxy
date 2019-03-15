package ru.bs.joker.testmoxy.view.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ru.bs.joker.testmoxy.R;
import ru.bs.joker.testmoxy.model.data.Datum;
import ru.bs.joker.testmoxy.model.data.FillData;
import ru.bs.joker.testmoxy.model.data.Variant;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    public ItemClickListener onClickListener;

    private List<Datum> datumList = new ArrayList<>();
    Context ctx;

    public void setList(List<Datum> fillData, Context context, ItemClickListener clickListener) {
        this.datumList = fillData;
        ctx = context;
        onClickListener = clickListener;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_view, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int i) {
        Datum Repo = datumList.get(i);
        switch (Repo.getName()){
            case "hz":
                viewHolder.name.setText(Repo.getData().getText());
                break;
            case "picture":
                Picasso.get().load(Repo.getData().getUrl()).into(viewHolder.ivPic);
                break;
            case "selector":
                if (Repo.getData().getVariants()!=null){
                    List<String> listVar = new ArrayList<>();
                    for (Variant var : Repo.getData().getVariants()) {
                        listVar.add(var.getText());
                    }
                    ArrayAdapter<String> adapter = new ArrayAdapter<>(ctx, R.layout.spinner_item, listVar);
                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    viewHolder.mSpiner.setAdapter(adapter);
                    viewHolder.mSpiner.setSelection(Repo.getData().getSelectedId());
                    viewHolder.mSpiner.setVisibility(View.VISIBLE);

                }
                break;
        }
        viewHolder.llItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.itemViewOnClick(v, i);
            }
        });
    }

    public interface ItemClickListener {
        void itemViewOnClick(View v, int position);
    }

    @Override
    public int getItemCount() {
        return datumList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView name;
        private ImageView ivPic;
        private Spinner mSpiner;
        private LinearLayout llItem;

        public ViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView);
            ivPic = itemView.findViewById(R.id.ivPic);
            mSpiner = itemView.findViewById(R.id.spiner);
            llItem = itemView.findViewById(R.id.llItemRecyclerView);
        }
    }
}