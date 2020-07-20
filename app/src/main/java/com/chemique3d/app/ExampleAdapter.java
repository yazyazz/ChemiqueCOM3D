package com.chemique3d.app;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    public static ArrayList<Integer> marked = new ArrayList<>();
    private ArrayList<ExampleItem> mExampleList;
    private OnItemClickListener mListener;

    public ExampleAdapter(ArrayList<ExampleItem> exampleList) {
        mExampleList = exampleList;
    }

    public static int correctResponses() {
        return marked.size();
    }

//    public void setOnItemClickListener(OnItemClickListener listener) {
//        mListener =  listener;
//    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mListener = listener;
    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.example_item, parent, false);
        ExampleViewHolder evh = new ExampleViewHolder(v, mListener);
        return evh;
    }

    @Override
    public void onBindViewHolder(@NonNull ExampleViewHolder holder, int position) {

        ExampleItem currentItem = mExampleList.get(position);

        holder.mImageView.setImageResource(currentItem.getImageResource());
        holder.mTextView1.setText(currentItem.getText1());
        holder.mTextView2.setText(currentItem.getText2());

        holder.mA1.setText(currentItem.getA1());
        holder.mA2.setText(currentItem.getA2());
        holder.mA3.setText(currentItem.getA3());
        holder.mA4.setText(currentItem.getA4());

    }

    @Override
    public int getItemCount() {

        return mExampleList.size();

    }

    public interface OnItemClickListener {
//        void onItemClick(int position);

        void onDeleteClick(int position);
    }

    public static class ExampleViewHolder extends RecyclerView.ViewHolder {

        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;
        public RadioGroup radioGroup;
        public RadioButton mA1, mA2, mA3, mA4;


        public ExampleViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mTextView1 = itemView.findViewById(R.id.textView);
            mTextView2 = itemView.findViewById(R.id.textView2);
            radioGroup = itemView.findViewById(R.id.radioGroup);
            mA1 = itemView.findViewById(R.id.radioButton);
            mA2 = itemView.findViewById(R.id.radioButton2);
            mA3 = itemView.findViewById(R.id.radioButton3);
            mA4 = itemView.findViewById(R.id.radioButton4);

            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            listener.onDeleteClick(position);
                        }
                    }
                }
            });


            radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    // checkedId is the RadioButton selected
                    if (checkedId == R.id.radioButton2) {
                        System.out.println("selected");
                        marked.add(1);
                    }
                }
            });
        }
    }
}
