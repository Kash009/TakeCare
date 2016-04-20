package com.example.shoaibiqbal.takecare;

import android.app.ActionBar;
import android.app.Activity;
        import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by BoboMan2 on 23/03/2016.
 */
public class PgFullArticle extends AppCompatActivity {
//    private int imageID;
//    private String title;
    private final int IMG_HEIGHT = 150;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.activty_full_article);
        Toolbar toolbar = (Toolbar) findViewById(R.id.articleToolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        if (savedInstanceState == null) {
            Bundle data = getIntent().getExtras();

            TextView heading = (TextView) findViewById(R.id.article_title);
            heading.setText(data.getString("articleTitle"));
            ImageView photo = (ImageView) findViewById(R.id.article_full_image);

            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            int width = displayMetrics.widthPixels -50 ;

            photo.setImageBitmap(
                    IntelligentBitmap.decodeSampledBitmapFromResource(getResources(), data.getInt("articleImageID"), width, IMG_HEIGHT));

        }

        TextView body = (TextView) findViewById(R.id.article_body);
        body.setText("Water (chemical formula: H2O) is a transparent fluid which forms the world's streams, lakes, oceans and rain, and is the major constituent of the fluids of organisms. As a chemical compound, a water molecule contains one oxygen and two hydrogen atoms that are connected by covalent bonds. Water is a liquid at standard ambient temperature and pressure, but it often co-exists on Earth with its solid state, ice; and gaseous state, steam (water vapor). It also exists as snow, fog, dew and cloud.\n" +
                "\n" +
                "Water covers 71% of the Earth's surface.[1] It is vital for all known forms of life. On Earth, 96.5% of the planet's crust water is found in seas and oceans, 1.7% in groundwater, 1.7% in glaciers and the ice caps of Antarctica and Greenland, a small fraction in other large water bodies, and 0.001% in the air as vapor, clouds (formed of ice and liquid water suspended in air), and precipitation.[2][3] Only 2.5% of this water is freshwater, and 98.8% of that water is in ice (excepting ice in clouds) and groundwater. Less than 0.3% of all freshwater is in rivers, lakes, and the atmosphere, and an even smaller amount of the Earth's freshwater (0.003%) is contained within biological bodies and manufactured products.[2] A greater quantity of water is found in the earth's interior.[4]\n" +
                "\n" +
                "Water on Earth moves continually through the water cycle of evaporation and transpiration (evapotranspiration), condensation, precipitation, and runoff, usually reaching the sea. Evaporation and transpiration contribute to the precipitation over land. Water used in the production of a good or service is known as virtual water.\n" +
                "\n" +
                "Safe drinking water is essential to humans and other lifeforms even though it provides no calories or organic nutrients. Access to safe drinking water has improved over the last decades in almost every part of the world, but approximately one billion people still lack access to safe water and over 2.5 billion lack access to adequate sanitation.[5] There is a clear correlation between access to safe water and gross domestic product per capita.[6] However, some observers have estimated that by 2025 more than half of the world population will be facing water-based vulnerability.[7] A report, issued in November 2009, suggests that by 2030, in some developing regions of the world, water demand will exceed supply by 50%.[8] Water plays an important role in the world economy, as it functions as a solvent for a wide variety of chemical substances and facilitates industrial cooling and transportation. Approximately 70% of the freshwater used by humans goes to agriculture.[9]");
    }
}
