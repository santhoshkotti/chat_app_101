package com.example.chatapp101;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {

    private ArrayList<Message> messages;
    private String senderImg,receiverImg;
    private Context context;

    public MessageAdapter(ArrayList<Message> messages, String my_img, String senderImg, Context context) {
        this.messages = messages;
        this.senderImg = senderImg;
        this.receiverImg = receiverImg;
        this.context = context;
    }

    @NonNull
    @Override
    public MessageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.message_holder,parent,false);
        return new MessageHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageHolder holder, int position) {
        holder.txtMessage.setText(messages.get(position).getContent());

        ConstraintLayout constraintLayout = holder.ccll;

        if(messages.get(position).getSender().equals(FirebaseAuth.getInstance().getCurrentUser().getEmail())){
            Glide.with(context).load(senderImg).error(R.drawable.account_img).placeholder(R.drawable.account_img).into(holder.profImage);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.txt_message_content,constraintSet.LEFT);
            constraintSet.clear(R.id.profile_cardView,constraintSet.LEFT);
            constraintSet.connect(R.id.profile_cardView,constraintSet.RIGHT,R.id.ccLayout,constraintSet.RIGHT,0);
            constraintSet.connect(R.id.txt_message_content,constraintSet.RIGHT,R.id.profile_cardView,constraintSet.LEFT,0);
            constraintSet.applyTo(constraintLayout);
        }else {
            Glide.with(context).load(receiverImg).error(R.drawable.account_img).placeholder(R.drawable.account_img).into(holder.profImage);
            ConstraintSet constraintSet = new ConstraintSet();
            constraintSet.clone(constraintLayout);
            constraintSet.clear(R.id.txt_message_content,constraintSet.RIGHT);
            constraintSet.clear(R.id.profile_cardView,constraintSet.RIGHT);
            constraintSet.connect(R.id.profile_cardView,constraintSet.LEFT,R.id.ccLayout,constraintSet.LEFT,0);
            constraintSet.connect(R.id.txt_message_content,constraintSet.LEFT,R.id.profile_cardView,constraintSet.RIGHT,0);
            constraintSet.applyTo(constraintLayout);
        }
    }

    @Override
    public int getItemCount() {
        return messages.size();
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        ConstraintLayout ccll;
        TextView txtMessage;
        ImageView profImage;
        public MessageHolder(@NonNull View itemView) {
            super(itemView);

            ccll=itemView.findViewById(R.id.ccLayout);
            txtMessage=itemView.findViewById(R.id.txt_message_content);
            profImage=itemView.findViewById(R.id.small_profile_img);
        }
    }
}
