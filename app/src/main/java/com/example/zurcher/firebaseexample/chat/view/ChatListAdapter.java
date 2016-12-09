package com.example.zurcher.firebaseexample.chat.view;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.zurcher.firebaseexample.R;
import com.example.zurcher.firebaseexample.chat.model.ChatMessage;

import java.util.ArrayList;

/**
 * Created by az on 09/12/16.
 */

public class ChatListAdapter extends RecyclerView.Adapter<ChatListAdapter.ChatViewHolder> {

    public static class ChatViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView chatSenderName;
        TextView chatMessage;
        //ImageView userNameProfilePic;

        ChatViewHolder(View itemView) {
            super(itemView);
            cv = (CardView) itemView.findViewById(R.id.cv);
            chatSenderName = (TextView) itemView.findViewById(R.id.chat_sender_name);
            chatMessage = (TextView) itemView.findViewById(R.id.chat_message);
            //userNameProfilePic = (ImageView) itemView.findViewById(R.id.sender_profile_pic);
        }
    }

    ArrayList<ChatMessage> mCurrentChatMessage;

    ChatListAdapter(ArrayList<ChatMessage> currentChatMessage) {
        mCurrentChatMessage = currentChatMessage;
    }

    @Override
    public int getItemCount() {
        return mCurrentChatMessage.size();
    }

    @Override
    public ChatViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.chat_message_layout, viewGroup, false);
        ChatViewHolder pvh = new ChatViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(ChatViewHolder personViewHolder, int i) {
        personViewHolder.chatSenderName.setText(mCurrentChatMessage.get(i).getSenderUserName());
        personViewHolder.chatMessage.setText(mCurrentChatMessage.get(i).getMessage());
        //personViewHolder.userNameProfilePic.setImageResource(mCurrentChatMessage.get(i).photoId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}


