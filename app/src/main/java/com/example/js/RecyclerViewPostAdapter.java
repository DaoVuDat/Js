package com.example.js;

import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.js.Model.Post;

import java.util.ArrayList;
//
//public class RecyclerViewPostAdapter extends RecyclerView.Adapter<RecyclerViewPostAdapter.ViewHolder> {
//
//    private PostItemListener mPostItemListener;
//    private List<Post> mPostItem;
//    private Context mContext;
//
//    // this view holder will bind the view and RecyclerViewAdapter takes this ViewHolder to set data
//    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
//        private TextView tvId;
//        private TextView tvTitle;
//        PostItemListener mPostItemListener;
//
//        public ViewHolder(@NonNull View itemView, PostItemListener mPostItemListener) {
//            super(itemView);
//            this.tvId = itemView.findViewById(R.id.tv_id);
//            this.tvTitle = itemView.findViewById(R.id.tv_title);
//            this.mPostItemListener = mPostItemListener;
//            itemView.setOnClickListener(this);
//        }
//
//        @Override
//        public void onClick(View v) {
//            Post item = getItem(getAdapterPosition());
////            Toast.makeText(this, item.getId(),Toast.LENGTH_SHORT).show();
//            this.mPostItemListener.onPostClick(item.getId());
//            notifyDataSetChanged();
//        }
//    }
//
//    public RecyclerViewPostAdapter(Context mContext, List<Post> mPostItem, PostItemListener mPostItemListener) {
//        this.mPostItemListener = mPostItemListener;
//        this.mPostItem = mPostItem;
//        this.mContext = mContext;
//    }
//
//    // any adapter needs overriding getItemCount and onBindViewHolder (for RecyclerViewAdapter)
//    @Override
//    public int getItemCount() {
//        return mPostItem.size();
//    }
//
//    @NonNull
//    @Override
//    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//
//        Context context = parent.getContext();
//
//        LayoutInflater layoutInflater = LayoutInflater.from(context);
//
//        View postView = layoutInflater.inflate(R.layout.browse, parent, false);
//        ViewHolder viewHolder = new ViewHolder(postView, this.mPostItemListener);
//
//        return viewHolder;
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
//        PostItem item = mPostItem.get(position);
//        TextView tvId = holder.tvId;
//        TextView tvTitle = holder.tvTitle;
//        tvId.setText(item.getId().toString());
//        tvTitle.setText(item.getTitle());
//    }
//
//    private PostItem getItem(int adapterPostion){
//        return mPostItem.get(adapterPostion);
//    }
//
//    public void updateList(List<PostItem> items){
//        mPostItem = items;
//
//        notifyDataSetChanged();
//
//    }
//
//
//
//    public interface PostItemListener{
//        void onPostClick(long id);
//    }
//}


public class RecyclerViewPostAdapter extends RecyclerView.Adapter<RecyclerViewPostAdapter.BindingViewHolder>{
    private ArrayList<Post> mListPost;
    // Constructor
    RecyclerViewPostAdapter(ArrayList<Post> mListPost) {
        this.mListPost = mListPost;
    }


    // After creating constructor, we need to create class BindingViewHolder
    // and override onCreateViewHolder with LayoutInflater from parent
    class BindingViewHolder extends RecyclerView.ViewHolder{
        private TextView tv_id;
        private TextView tv_title;

        BindingViewHolder(@NonNull View itemView) {
            super(itemView);
            this.tv_id = itemView.findViewById(R.id.tv_id);
            this.tv_title = itemView.findViewById(R.id.tv_title);
        }

    }

    @NonNull
    @Override
    public BindingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.browse, parent,false);

        // return the new binding view holder with View holder
        return new BindingViewHolder(view);
    }


    // this method will bind data into the view holder
    @Override
    public void onBindViewHolder(@NonNull BindingViewHolder holder, int position) {

        // get position data
        Post item = mListPost.get(position);

        // binding current data to holder
        holder.tv_id.setText(item.getId().toString()); // convert int number to String
        holder.tv_title.setText(item.getTitle());
    }

    // one of the methods needs overriding
    @Override
    public int getItemCount() {
        return mListPost.size();
    }

    // update list from outside
    void updateListData(ArrayList<Post> mData){
        mListPost = mData;
        notifyDataSetChanged();
    }

}



















