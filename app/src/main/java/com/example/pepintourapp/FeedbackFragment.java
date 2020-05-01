package com.example.pepintourapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FeedbackFragment extends Fragment {


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_feedback, container, false);
        Button button = (Button) view.findViewById(R.id.send);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                EditText name = getView().findViewById(R.id.name);
                EditText email = getView().findViewById(R.id.email);
                EditText subject = getView().findViewById(R.id.subject);
                EditText message = getView().findViewById(R.id.message);

                if(name.getText().toString().equals("")){
                    name.setError("Please enter name");
                } else if(email.getText().toString().equals("")){
                    email.setError("Please enter email");
                }else if(subject.getText().toString().equals("")){
                    email.setError("Please enter subject");
                }else if(message.getText().toString().equals("")){
                    email.setError("Please enter message");
                }else {
                    Intent i = new Intent(Intent.ACTION_SENDTO);
                    i.setData(Uri.parse("mailto:"));
                    i.putExtra(Intent.EXTRA_EMAIL, new String[]{"myemail@gmail.com"});
                    i.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
                    i.putExtra(Intent.EXTRA_TEXT, "dear pepintour app, \n" + message.getText().toString() + "\n regards, " + email.getText().toString());

                    try {
                        startActivity(Intent.createChooser(i, "send mail"));

                    } catch (android.content.ActivityNotFoundException exception) {
                        Toast.makeText(getActivity(), "no mail app found", Toast.LENGTH_SHORT).show();
                    } catch (Exception ex) {
                        Toast.makeText(getActivity(), "unexpected error" + ex.toString(), Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
        return view;
    }

//    public void send(View v){
//        EditText name = getView().findViewById(R.id.name);
//        EditText email = getView().findViewById(R.id.email);
//        EditText subject = getView().findViewById(R.id.subject);
//        EditText message = getView().findViewById(R.id.message);
//
//        if(name.getText().toString().equals("")){
//            name.setError("Please enter name");
//        } else if(email.getText().toString().equals("")){
//            email.setError("Please enter email");
//        }else if(subject.getText().toString().equals("")){
//            email.setError("Please enter subject");
//        }else if(message.getText().toString().equals("")){
//            email.setError("Please enter message");
//        }else{
//            Intent i = new Intent(Intent.ACTION_SENDTO);
//            i.setData(Uri.parse("mailto:"));
//            i.putExtra(Intent.EXTRA_EMAIL, new String[]{"myemail@gmail.com"});
//            i.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
//            i.putExtra(Intent.EXTRA_TEXT, "dear pepintour app, \n" + message.getText().toString() + "\n regards, " + email.getText().toString());
//
//            try{
//                startActivity(Intent.createChooser(i, "send mail"));
//
//            }catch(android.content.ActivityNotFoundException exception){
//                Toast.makeText(getActivity(), "no mail app found", Toast.LENGTH_SHORT).show();
//            }catch(Exception ex){
//                Toast.makeText(getActivity(), "unexpected error"+ex.toString(),Toast.LENGTH_SHORT).show();
//            }
//
//
//        }






}