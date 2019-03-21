package com.example.databinddemo;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private int index = 0;

    private ItemAdapter mItemAdapter;
    private ArrayList<PersonInfo> mItems;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mItems = new ArrayList();
//        for (int i = 0; i < 1; i++) {
//            User u = new User();
//            u.setId(index + "");
//            u.setName("zhangphil @" + index);
//            u.setBlog("blog.csdn.net/zhangphil @" + index);
//            mItems.add(u);
//            index++;
//        }
        for (int i = 0; i < 10; i++) {
            PersonInfo personInfo = new PersonInfo();
            personInfo.setName("john");
            personInfo.setAge("26");
            personInfo.setSex("femal");
            mItems.add(personInfo);
        }


        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        final MyAdapter myAdapter = new MyAdapter(mItems);
        mRecyclerView.setAdapter(myAdapter);


//        mItemAdapter = new ItemAdapter();
//        mRecyclerView.setAdapter(mItemAdapter);
//        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                User u = new User();
//                u.setId(index + "");
//                u.setName("zhangphil @" + index);
//                u.setBlog("blog.csdn.net/zhangphil @" + index);
//                mItems.add(u);
//                mItemAdapter.notifyDataSetChanged();
//                index++;
//            }
//        });
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PersonInfo personInfo = new PersonInfo();
                personInfo.setName("jake");
                personInfo.setAge("26");
                personInfo.setSex("femal");
                mItems.add(personInfo);
                myAdapter.notifyDataSetChanged();

            }
        });
    }
    private class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {

        @Override
        public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.item, viewGroup, false);
            ItemViewHolder holder = new ItemViewHolder(binding);
            return holder;
        }

        @Override
        public void onBindViewHolder(ItemViewHolder viewHolder, final int i) {
            viewHolder.getBinding().setVariable(com.example.databinddemo.BR.user, mItems.get(i));
            viewHolder.getBinding().getRoot().findViewById(R.id.btn_delete).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(i);
                }
            });
            viewHolder.getBinding().executePendingBindings();
        }

        private void deleteItem(int i) {
            mItems.remove(i);
            notifyDataSetChanged();
        }

        @Override
        public int getItemCount() {
            return mItems.size();
        }
    }

    private class ItemViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder {
        private T binding;

        public ItemViewHolder(T binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        public void setBinding(T binding) {
            this.binding = binding;
        }

        public ViewDataBinding getBinding() {
            return this.binding;
        }
    }
}
