package com.example.compc.wallpaper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.squareup.picasso.Picasso;

import java.util.List;


public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ImageViewHolder> {
    private Context mContext;
    private List<Upload> mUploads;
    private OnItemClickListener mListener;



    public  ImageAdapter(Context context, List<Upload> uploads) {
        mContext = context;
        mUploads = uploads;
    }


    @Override
    public ImageViewHolder onCreateViewHolder( ViewGroup parent , int ViewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.image_item, parent, false);
        return new ImageViewHolder(v);

    }

    @Override
    public void onBindViewHolder( ImageViewHolder holder, int position) {
        Upload uploadCurrent = mUploads.get(position);
        holder.textViewName.setText(uploadCurrent.getName());

        Picasso.with(mContext)
                .load(uploadCurrent.getImageUrl())
                .placeholder(R.mipmap.ic_launcher)
                .fit()
                .centerCrop()
                .into(holder.imageView);

    }



    @Override
    public int getItemCount() {
        return mUploads.size();
    }

    public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,
            View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener {
        public TextView textViewName;
      //  public TextView textViewName1;
        public ImageView imageView;



        public ImageViewHolder(View itemView){
            super(itemView);

            textViewName = itemView.findViewById(R.id.text_view_name);
          //  textViewName1 = itemView.findViewById(R.id.text_view_name1);
            imageView = itemView.findViewById(R.id.image_view_upload);


            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mListener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("เลือกการกระทำ");

            MenuItem delete = menu.add(Menu.NONE, 2, 2, "ลบรูปภาพ");
            MenuItem download = menu.add(Menu.NONE, 3, 3, "ดาวน์โหลด");


            delete.setOnMenuItemClickListener(this);
            download.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if (mListener != null){
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION){

                switch (item.getItemId()){
                  //  case 1:
                 //       mListener.onWhatEverClick(position);
                 //       return true;
                    case 2:
                        mListener.onDeleteClick(position);
                        return true;
                    case 3:
                        mListener.onDownloadClick(position);
                        return true;
                }
            }
        }
            return false;
        }
    }

    public  interface OnItemClickListener{
        void onItemClick(int position);

        void onWhatEverClick(int position);

        void onDeleteClick(int position);

        void onDownloadClick(int position);
    }

    public  void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }

}
