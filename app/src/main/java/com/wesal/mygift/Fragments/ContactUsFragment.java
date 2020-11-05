package com.wesal.mygift.Fragments;

import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wesal.mygift.R;

public class ContactUsFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View parentView = inflater.inflate(R.layout.fragment_contact_us, container, false);

        TextView email = parentView.findViewById(R.id.tvMail);
        email.setText(Html.fromHtml("<a href=\"mailto:alwesal145@gmail.com\">Email: alwesal145@gmail.com</a>"));
        email.setMovementMethod(LinkMovementMethod.getInstance());
        return parentView;
    }
}
