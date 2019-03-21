package com.example.databinddemo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private final ArrayList<PersonInfo> data;

    public MyAdapter(ArrayList<PersonInfo> data) {
        this.data = data;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //创建view
        ViewDataBinding inflate = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.info, parent, false);
        MyViewHolder<ViewDataBinding> viewDataBindingMyViewHolder = new MyViewHolder<>(inflate);
        return viewDataBindingMyViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        //绑定视图
        holder.getView().setVariable(com.example.databinddemo.BR.personinfo, data.get(position));
        holder.getView().getRoot().findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteItem(position);

            }
        });
        holder.getView().executePendingBindings();

    }

    private void deleteItem(int position) {
        data.remove(position);
        notifyDataSetChanged();
    }


    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private T view;

        public MyViewHolder(T binding) {
            super(binding.getRoot());
            this.view = binding;
        }

        public T getView() {
            return view;
        }

        public void setView(T view) {
            this.view = view;
        }
    }
}
