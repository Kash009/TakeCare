package com.example.shoaibiqbal.takecare;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.util.Linkify;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shoaibiqbal.takecare.data.Reader;

import org.w3c.dom.Node;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;

/**
 * Created by BoboMan2 on 09/04/2016.
 */
public class PgDoctorProfile extends AppCompatActivity {

    private Node docNode;
    private Reader myXMLreader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activity_doctor_profile);

        Bundle data = getIntent().getExtras();


        InputStream filepath = getResources().openRawResource(R.raw.doctors_db);
        try {
            myXMLreader = new Reader(filepath);
            docNode = myXMLreader.getDoctorNode(data.getString("doctorID"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XPathExpressionException e) {
            e.printStackTrace();
        }

        Toolbar toolbar = (Toolbar) findViewById(R.id.docProfileToolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }


        setQualifications();
        setSpecialities();
        setFee();
        setClinicDetails();
        setClinicContacts();
        setDocProfile();
    }

    private void setDocProfile() {
        TextView docName = (TextView) findViewById(R.id.tv_prof_doctor_name);
        docName.setText("Dr. " + myXMLreader.getDoctorName(docNode));

        TextView docPost = (TextView) findViewById(R.id.tv_prof_doctor_post);
        docPost.setText(myXMLreader.getDoctorPos(docNode));

        TextView docExp = (TextView) findViewById(R.id.tv_prof_doctor_exp);
        docExp.setText(myXMLreader.getDoctorExp(docNode) + " Years Experience");


    }


    private View getSubDividerLine() {
        View divLine = new View(this);
        divLine.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 1));
        divLine.setBackgroundColor(Color.rgb(200, 200, 200));
        return divLine;
    }

    private TextView getSimpleTextView(String text) {
        TextView temp = new TextView(this);
        temp.setText(text);
        temp.setTextColor(Color.rgb(33, 33, 33));
        return temp;
    }

    private TextView getContactTextView(String number) {
        TextView temp = new TextView(this);
        temp.setTextColor(Color.rgb(33, 33, 33));
        temp.setAutoLinkMask(Linkify.PHONE_NUMBERS);
        temp.setText(number);
        temp.setPadding(0, 5, 0, 5);
        return temp;
    }

    private void setQualifications() {
        String[] qualifications = myXMLreader.getDoctorQual(docNode);
        int strLen = qualifications.length;
        LinearLayout layoutQualifications = (LinearLayout) findViewById(R.id.listQualifications);

        for (int i=0; i<strLen; i++) {
            layoutQualifications.addView(getSimpleTextView(qualifications[i]));
            if (i != strLen-1) {
                layoutQualifications.addView(getSubDividerLine());
            }
        }
    }

    private void setSpecialities() {
        String[] specialities = myXMLreader.getDoctorSpec(docNode);
        int strLen = specialities.length;

        LinearLayout layoutSpecialities = (LinearLayout) findViewById(R.id.listDocSpecialities);
        for (int i=0; i<strLen; i++) {
            layoutSpecialities.addView(getSimpleTextView(specialities[i]));
            if (i != strLen-1) {
                layoutSpecialities.addView(getSubDividerLine());
            }
        }
//        layoutSpecialities.addView(getSimpleTextView("General Paediatrics"));
//        layoutSpecialities.addView(getSubDividerLine());
//        layoutSpecialities.addView(getSimpleTextView("Gastrointestinal Surgery"));
//        layoutSpecialities.addView(getSubDividerLine());
//        layoutSpecialities.addView(getSimpleTextView("Laparoscopic Hernia Surgery"));
//        layoutSpecialities.addView(getSubDividerLine());
//        layoutSpecialities.addView(getSimpleTextView("Gastrointestinal Oncological Surgery"));
    }

    private void setFee() {
        TextView fee = (TextView) findViewById(R.id.text_fee_value);
        fee.setText("Rs 600");
    }

    private void setClinicDetails() {
        TextView clinicName = (TextView) findViewById(R.id.heading_clinic_name);
        clinicName.setText("Aga Khan University Hospital");
        TextView clinicArea = (TextView) findViewById(R.id.heading_clinic_area);
        clinicArea.setText("Stadium Road, Karachi");

        LinearLayout clinicSchedule = (LinearLayout) findViewById(R.id.listDocSchedule);
        clinicSchedule.addView(getSimpleTextView("Mon-Thurs: 08:00 - 12:00"));
        clinicSchedule.addView(getSubDividerLine());
        clinicSchedule.addView(getSimpleTextView("Sat: 10:00 - 13:00"));
    }

    private void setClinicContacts() {
        LinearLayout clinicContact = (LinearLayout) findViewById(R.id.listClinicContacts);
        clinicContact.addView(getContactTextView("+923333835001"));
        clinicContact.addView(getSubDividerLine());
        clinicContact.addView(getContactTextView("+922134663299"));
    }
}
