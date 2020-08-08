package com.example.pathology;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import java.util.Calendar;

import mehdi.sakout.aboutpage.AboutPage;
import mehdi.sakout.aboutpage.Element;


public class AboutUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Element adsElement=new Element();
        adsElement.setTitle("Advertise Here:");

        View aboutUsPage = new AboutPage(this)
                .isRTL(false)
                .setImage(R.drawable.logo)
                .setDescription("This is a demo text")
                .addItem(new Element().setTitle("Version 1.0"))
                .addItem(adsElement)
                .addGroup("Connect with us")
                .addEmail("admin@info.com")
                .addWebsite("Website.url")
                .addFacebook("facebook.com")
                .addTwitter("twitter.com")
                .addInstagram("instagram.com")
                .addItem(createCopyRightContent())
                .create();

        setContentView(aboutUsPage);
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
                Toast.makeText(AboutUs.this,copyrightString,Toast.LENGTH_LONG).show();
            }
        });
        return copyrightElement;
    }
}