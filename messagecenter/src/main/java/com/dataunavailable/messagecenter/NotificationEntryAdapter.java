package com.dataunavailable.messagecenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;

/**
 * Created by lordo on 7/19/2017.
 */

public class NotificationEntryAdapter extends FirebaseRecyclerAdapter<SyncData, NotificationEntryAdapter.ViewHolder> {

    @Override
    protected void populateViewHolder(ViewHolder viewHolder, SyncData model, int position) {
        byte[] imageArray = Base64.decode(model.getSmallIcon(), Base64.NO_WRAP | Base64.URL_SAFE);
        Bitmap bitmap = BitmapFactory.decodeByteArray(imageArray, 0, imageArray.length);
        ((ImageView)viewHolder.itemView).setImageBitmap(bitmap);
    }

    public NotificationEntryAdapter(Context context, DatabaseReference data) {
        super(SyncData.class, R.layout.imageview, ViewHolder.class, data);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
