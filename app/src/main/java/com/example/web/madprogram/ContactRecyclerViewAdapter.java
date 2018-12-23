package com.example.web.madprogram;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.web.madprogram.JavaBean.Contact;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactRecyclerViewAdapter extends RecyclerView.Adapter {
    private ArrayList<Contact> contacts;
    private Context context;


    public ContactRecyclerViewAdapter(ArrayList<Contact> contacts, Context context){
        this.contacts = contacts;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.contact_recycle_row, null);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        view.setLayoutParams(layoutParams);
        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Contact contact = contacts.get(position);
        ((CustomViewHolder) holder).profile.setImageResource(contact.getPicture());
        ((CustomViewHolder) holder).name.setText(contact.getfName() + " " + contact.getlName());
        ((CustomViewHolder) holder).position.setText(contact.getPosition());
        ((CustomViewHolder) holder).office.setText("Office Number: " + contact.getOfficeNumber());
        ((CustomViewHolder) holder).description.setText(contact.getDescription());
        ((CustomViewHolder) holder).phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        ((CustomViewHolder) holder).email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] emailAddresses = {contact.getEmailAddress()};
                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
                intent.putExtra(Intent.EXTRA_SUBJECT, "Question From the MAD App");
                intent.putExtra(Intent.EXTRA_TEXT, "I have a question about ...");
                if(intent.resolveActivity(context.getPackageManager()) != null){
                    context.startActivity(intent);
                }
                else{
                    Toast.makeText(context,
                            "You do not have the correct software",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        if(contacts != null){
            return contacts.size();
        }
        return 0;
    }

    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected CircleImageView profile;
        protected TextView name;
        protected TextView position;
        protected TextView office;
        protected TextView description;
        protected ImageView phone;
        protected ImageView email;

        public CustomViewHolder(View view){
            super(view);
            this.profile = view.findViewById(R.id.profile);
            this.description = view.findViewById(R.id.description);
            this.name = view.findViewById(R.id.name);
            this.position = view.findViewById(R.id.position);
            this.phone = view.findViewById(R.id.phone);
            this.email = view.findViewById(R.id.email);
            this.office = view.findViewById(R.id.office);
        }

    }


}
