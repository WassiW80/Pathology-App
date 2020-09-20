package com.example.pathology;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class AboutUsFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = new AboutPage(getActivity())
                .isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription("It was later started in 1998 with a great vision to provide a Health Care facilities in charitable rate." +
                        " We are a good quality and experience doctor, Technicians on our panel." +
                        " Going beyond the contemporary patient centred care, our ideology lies in taking special care of our patients at all times," +
                        " ensuring the best services.\n\nOur Services:\n" +
                        "| Pathology | \tDigital X-Ray | \tSonography | \tECG | \n| Audiometry | \tOptometry | \t" +
                        "Industrial Medical | \n | Corporate Medical | \tAbroad Pre Medical |")
                .addGroup("Connect with us")
                .addEmail("perfectlab33@gmail.com")
                .addWebsite("https://perfectdiagnostic.co.in/")
                .addItem(createCopyRightContent())
                .create();
        return view;
    }

    private Element createCopyRightContent() {
        final Element copyrightElement = new Element();
        final String copyrightString = String.format("Copyright %d by Express Diagnostic Center", Calendar.getInstance().get(Calendar.YEAR));
        copyrightElement.setTitle(copyrightString);
        copyrightElement.setIcon(R.mipmap.ic_launcher);
        copyrightElement.setGravity(Gravity.CENTER);
        copyrightElement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), copyrightString, Toast.LENGTH_LONG).show();
            }
        });
        return copyrightElement;
    }
}