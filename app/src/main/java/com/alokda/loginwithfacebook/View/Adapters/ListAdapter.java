package com.alokda.loginwithfacebook.View.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.alokda.loginwithfacebook.Model.ListModel;
import com.alokda.loginwithfacebook.R;
import com.alokda.loginwithfacebook.View.Interface.ListListener;

import java.util.List;


public class ListAdapter extends RecyclerView.Adapter<ListAdapter.RecycleViewHolder> {

    List<ListModel> modelBuildList;
    ListListener listListener;
    Context context;

    public ListAdapter(List<ListModel> flatList, Context context, ListListener listListener) {
        this.modelBuildList = flatList;
        this.listListener = listListener;
        this.context = context;

    }

    @NonNull
    @Override
    public RecycleViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.single_item_list, viewGroup, false);
        return new RecycleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecycleViewHolder holder, int position) {

        final ListModel model = modelBuildList.get(position);

        if (!model.getTitle().equals("") && model.getTitle() != null) {
            holder.tv_title.setText(model.getTitle());
        } else {
            holder.tv_title.setText(context.getString(R.string.noData));
        }

        if (!model.getDate().equals("") && model.getDate() != null) {
            holder.tv_date.setText(model.getDate());
        } else {
            holder.tv_date.setText(context.getString(R.string.noDate));
        }

        if (model.getStar()) {

            holder.iv_star.setBackgroundResource(R.drawable.star_color);
        } else {
            holder.iv_star.setBackgroundResource(R.drawable.star);

        }


    }


    @Override
    public int getItemCount() {
        return modelBuildList.size();
    }


    public class RecycleViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_delete, iv_edit, iv_star;
        private TextView tv_title, tv_date;

        public RecycleViewHolder(View view) {
            super(view);
            iv_delete = (ImageView) view.findViewById(R.id.iv_singleItemList_delete);
            iv_edit = (ImageView) view.findViewById(R.id.iv_singleItemList_edit);
            iv_star = (ImageView) view.findViewById(R.id.iv_singleItemList_star);
            tv_title = (TextView) view.findViewById(R.id.tv_singleItemList_title);
            tv_date = (TextView) view.findViewById(R.id.tv_singleItemList_date);

            //Init Listener
            iv_star.setOnClickListener(this);
            iv_edit.setOnClickListener(this);
            iv_delete.setOnClickListener(this);
            itemView.setOnClickListener(this);

            //Init Tag
            iv_star.setTag(0);
            iv_edit.setTag(1);
            iv_delete.setTag(2);
            itemView.setTag(3);
        }

        @Override
        public void onClick(View v) {
            switch ((Integer) v.getTag()) {
                case 0:
                    changeStar();
                    break;
                case 1:
                    edit();
                    break;
                case 2:
                    delete();
                    break;
                case 3:
                    goToDetail(getLayoutPosition());
                    break;
                default:
                    break;
            }

        }

        private void changeStar() {
            if (modelBuildList.get(getLayoutPosition()).getStar() != null && modelBuildList.get(getLayoutPosition()).getStar()) {
                iv_star.setBackgroundResource(R.drawable.star);
                modelBuildList.get(getLayoutPosition()).setStar(false);
                Toast.makeText(context, context.getString(R.string.unFavourite), Toast.LENGTH_SHORT).show();
            } else {
                iv_star.setBackgroundResource(R.drawable.star_color);
                modelBuildList.get(getLayoutPosition()).setStar(true);
                Toast.makeText(context, context.getString(R.string.favourite), Toast.LENGTH_SHORT).show();

            }
        }

        private void edit() {
            Toast.makeText(context, context.getString(R.string.edit), Toast.LENGTH_SHORT).show();
        }

        private void delete() {
            Toast.makeText(context, context.getString(R.string.delete), Toast.LENGTH_SHORT).show();
        }

        private void goToDetail(int position) {
            listListener.onClickItem(position);
        }

    }


}
