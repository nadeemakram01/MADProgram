package com.example.web.madprogram;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ContactFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContactFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    public static final int PERMISSION_SEND_SMS = 1;
    //Step 1 email) build list of email addresses
    String[] emailAddresses = {"pnikita@stclaircollege.ca"};

    private OnFragmentInteractionListener mListener;

    public ContactFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ContactFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ContactFragment newInstance(String param1, String param2) {
        ContactFragment fragment = new ContactFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_contact, container, false);
       Button emailButton = view.findViewById(R.id.emailPeter);
       Button sendButton = view.findViewById(R.id.sendPromo);
       Button contactButton = view.findViewById(R.id.peterContact);
       Button locationButton = view.findViewById(R.id.location);
       Button callButton = view.findViewById(R.id.callCollege);

       emailButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_SENDTO);
            intent.setData(Uri.parse("mailto:"));
            intent.putExtra(Intent.EXTRA_EMAIL, emailAddresses);
            intent.putExtra(Intent.EXTRA_SUBJECT, "Question From the MAD App");
            intent.putExtra(Intent.EXTRA_TEXT, "I have a question about ...");
            if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                startActivity(intent);
            }
            else{
                Toast.makeText(getContext(),
                        "You do not have the correct software",
                        Toast.LENGTH_SHORT).show();
            }
           }
       });

       callButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(Uri.parse("tel:5199722727"));
               if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                   startActivity(intent);
               }
               else{
                   Toast.makeText(getContext(),
                           "You do not have the correct software",
                           Toast.LENGTH_SHORT).show();
               }
           }
       });

       sendButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //Check to see if we have the permission
              if(ContextCompat.checkSelfPermission(getActivity(),
                      Manifest.permission.SEND_SMS) !=
                      PackageManager.PERMISSION_GRANTED){
                  //if we do not have the permission
                  //have we already asked them for permission?
                  //if so should we show a rationale?
                  if(ActivityCompat.shouldShowRequestPermissionRationale(getActivity(),
                          Manifest.permission.SEND_SMS)){
                      //I should show you a reason as to why I want the permission
                      final AlertDialog alertDialog =
                        new AlertDialog.Builder(getContext()).create();
                        alertDialog.setTitle("SMS Permission");
                        alertDialog.setMessage("We need access to your SMS" +
                                "to be able to send a SMS message");
                        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,
                                "OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        alertDialog.dismiss();
                                        //Request for the permission again
                                        ActivityCompat.requestPermissions(getActivity(),
                                                new String[]{Manifest.permission.SEND_SMS},
                                                PERMISSION_SEND_SMS);
                                    }
                                });
                        alertDialog.show();
                  }
                  else{
                      //if this is the first time asking for the permission
                      //Then ask for permission
                      ActivityCompat.requestPermissions(getActivity(),
                              new String[]{Manifest.permission.SEND_SMS},
                              PERMISSION_SEND_SMS);
                  }
              }
              else{
                  Intent intent = new Intent(Intent.ACTION_SENDTO);
                  intent.setData(Uri.parse("smsto:"));
                  intent.putExtra("sms_body",
                       "Check this out! " +
                               "https://www.youtube.com/watch?v=qdgkUkOIwXk");
                  if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                    startActivity(intent);
                  }
                  else{
                   Toast.makeText(getContext(),
                           "You do not have the correct software",
                           Toast.LENGTH_SHORT).show();
               }
              }
           }
       });

       contactButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent intent = new Intent(Intent.ACTION_INSERT);
               intent.setType(ContactsContract.Contacts.CONTENT_TYPE);
               intent.putExtra(ContactsContract.Intents.Insert.NAME, "Peter Nikita");
               intent.putExtra(ContactsContract.Intents.Insert.EMAIL, "pnikita@stclaircollege.ca");
               intent.putExtra(ContactsContract.Intents.Insert.PHONE, "519 972 2727");
               if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                   startActivity(intent);
               }
               else{
                   Toast.makeText(getContext(),
                           "You do not have the correct software",
                           Toast.LENGTH_SHORT).show();
               }

           }
       });

       locationButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Uri geoLocation = Uri.parse("geo:0,0?q=42.2470072,-83.0149074(Tim Hortons)");
               Intent intent = new Intent(Intent.ACTION_VIEW);
               intent.setData(geoLocation);
               if(intent.resolveActivity(getActivity().getPackageManager()) != null){
                   startActivity(intent);
               }
               else{
                   Toast.makeText(getContext(),
                           "You do not have the correct software",
                           Toast.LENGTH_SHORT).show();
               }

           }
       });


       return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
