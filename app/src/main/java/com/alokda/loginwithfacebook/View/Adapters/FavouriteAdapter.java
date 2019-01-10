package com.alokda.loginwithfacebook.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alokda.loginwithfacebook.Model.ListModel;
import com.alokda.loginwithfacebook.R;

import java.util.List;


public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.RecycleViewHolder> {

    List<ListModel> modelBuildList;
    Context context;

    public FavouriteAdapter(List<ListModel> flatList, Context context) {
        this.modelBuildList = flatList;
        this.context = context;

    }

    @NonNull
    @Override
    public FavouriteAdapter.RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_single_favourite, viewGroup, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewHolder holder, int position) {

        final ListModel model = modelBuildList.get(position);

        if (!model.getTitle().equals("") && model.getTitle() != null) {
            holder.tv_title.setText(model.getTitle());
        } else {
            holder.tv_title.setText(context.getString(R.string.noData));
        }

        if (!model.getDetail().equals("") && model.getDetail() != null) {
            holder.tv_desc.setText(model.getDetail());
        } else {
            holder.tv_desc.setText(context.getString(R.string.noData));
        }


    }


    @Override
    public int getItemCount() {
        return modelBuildList.size();
    }


    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


        private TextView tv_title, tv_desc;
        private Button btn_favourite;

        public RecycleViewHolder(View view) {
            super(view);

            tv_title = (TextView) view.findViewById(R.id.tv_singleItemFavourite_title);
            tv_desc = (TextView) view.findViewById(R.id.tv_singleItemFavourite_desc);
            btn_favourite = (Button) view.findViewById(R.id.btn_singleItemFavourite_favourite);

            //Init Listener
            btn_favourite.setOnClickListener(this);

            //Init Tag

            btn_favourite.setTag(0);
        }

        @Override
        public void onClick(View v) {
            switch ((Integer) v.getTag()) {
                case 0:
                    unFavourite();
                    break;
                default:
                    break;
            }

        }

        private void unFavourite() {
            Toast.makeText(context, context.getString(R.string.unFavourite), Toast.LENGTH_SHORT).show();
        }

    }


}