package com.example.AwemanyBooks.Presentation.RecyclerView;

import com.example.AwemanyBooks.Business.CommentList;
import com.example.AwemanyBooks.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder>{

//    ArrayList<Comment> comments;
    CommentList comments;
    public CommentAdapter( CommentList comments ){
        this.comments = comments;

    }
    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_item, parent, false );

        return new CommentViewHolder(view);
    }

    public void onBindViewHolder(@NonNull CommentViewHolder holder, int position) {

        TextView uidTextView = (TextView)holder.itemView.findViewById(R.id.item_comment_uid_txt);
        uidTextView.setText(comments.get(position).getUid());
        TextView commentTextView = (TextView)holder.itemView.findViewById(R.id.item_comment_txt);
        commentTextView.setText(comments.get(position).getComment());
        TextView timeTextView = (TextView)holder.itemView.findViewById((R.id.item_comment_time_txt));
        timeTextView.setText(comments.get(position).getTime());

        holder.uid = uidTextView;
        holder.comment = commentTextView;
        holder.time = timeTextView;
        holder.ratingBar.setRating((float)comments.get(position).getRating());
    }

    public int getItemCount(){
        return comments.size();
    }

    public void sortByDate(){
//        Collections.sort(comments, new Comparator<Comment>() {
//            @Override
//            public int compare(Comment o1, Comment o2) {
//                return o1.getTime().compareTo(o2.getTime());
//            }
//        });
    }
    public void sortByDateDes(){
//        Collections.sort(comments, new Comparator<Comment>() {
//            @Override
//            public int compare(Comment o1, Comment o2) {
//                return -( o1.getTime().compareTo(o2.getTime()) );
//            }
//        });
    }
    public void sortByRating(){
//        Collections.sort(comments, new Comparator<Comment>() {
//            @Override
//            public int compare(Comment o1, Comment o2) {
//                return (int)(o1.getRating()-o2.getRating());
//            }
//        });
    }
    public void sortByRatingDes(){
//        Collections.sort(comments, new Comparator<Comment>() {
//            @Override
//            public int compare(Comment o1, Comment o2) {
//                return -( (int)(o1.getRating()-o2.getRating()) );
//            }
//        });
    }



    class CommentViewHolder extends RecyclerView.ViewHolder{

        private TextView uid;
        private TextView comment;
        private TextView time;
        private RatingBar ratingBar;

        public CommentViewHolder(@NonNull View itemView){
            super(itemView);
            uid = itemView.findViewById(R.id.item_comment_uid_txt);
            comment = itemView.findViewById(R.id.item_comment_txt);
            time = itemView.findViewById(R.id.item_comment_time_txt);
            ratingBar = itemView.findViewById(R.id.item_comment_ratingBar);
        }
    }
}
