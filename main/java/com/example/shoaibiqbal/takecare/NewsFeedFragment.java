package com.example.shoaibiqbal.takecare;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shoaib Iqbal on 13-Mar-16.
 */
public class NewsFeedFragment extends Fragment{

    private final int IMG_HEIGHT = 100;
    private final int IMG_WIDTH = 100;
    ArticlesArrayAdapter arrayListAdapter;

    public class ArticlesArrayAdapter extends ArrayAdapter<ArticleCard> {
        private final Context context;
        private final List<ArticleCard> articles;
        //later articles input can be used for finding specific photos
        public ArticlesArrayAdapter(Context context, List<ArticleCard> articles) {
            super(context, R.layout.articles_row_layout, articles);
            this.context = context;
            this.articles = articles;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = myInflater.inflate(R.layout.articles_row_layout, parent, false);
            }

            TextView text = (TextView) convertView.findViewById(R.id.article_title_text);
            text.setText(articles.get(position).getTitle());

            ImageView image = (ImageView) convertView.findViewById(R.id.article_img);
            //image.setImageResource(articles.get(position).getImage());
            image.setImageBitmap(
                    IntelligentBitmap.decodeSampledBitmapFromResource(getResources(), articlesArray.get(position).getImage(), IMG_WIDTH, IMG_HEIGHT));
            return convertView;
        }
    }

    private ListView newsItemsListView;
    // list to store articles from database
    private List<ArticleCard> articlesArray = new ArrayList<ArticleCard>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.newsfeed_frg, container, false);
        fetchArticles();

            newsItemsListView = (ListView) currentView.findViewById(R.id.listViewTips);

            newsItemsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    //int itemNo = (int) parent.getItemAtPosition(position);
                    Intent intent = new Intent(getActivity(), PgFullArticle.class);
                    intent.putExtra("articleImageID", articlesArray.get(position).getImage());
                    intent.putExtra("articleTitle", articlesArray.get(position).getTitle());
                    startActivity(intent);
                }
            });


//        ArrayAdapter<String> arrayListAdapter = new ArrayAdapter<String>(
//               this.getActivity(),
//                android.R.layout.simple_expandable_list_item_1,
//                articlesArray);

            ArticlesArrayAdapter arrayListAdapter = new ArticlesArrayAdapter(this.getContext(), articlesArray);

            newsItemsListView.setAdapter(arrayListAdapter);

        return currentView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (arrayListAdapter != null) {
            arrayListAdapter.clear();
        }
    }

    private void fetchArticles() {

        // fetch images from database using this
        // int resId = context.getResources().getIdentifier("picture1","drawable",context.getPackageName());
        // image.setImageResource(resId);;
        // if image was R.drawable.picture1

        articlesArray.add(new ArticleCard("10 Ways to Reduce Stress", (R.drawable.tenwaysstress)));
        articlesArray.add(new ArticleCard("The Benefits of Running", R.drawable.runningphoto));
        articlesArray.add(new ArticleCard("Getting Rid of Back Pain"));
        articlesArray.add(new ArticleCard("Suffering from Frequent Headaches?"));
        articlesArray.add(new ArticleCard("Tips to Deal with Anxiety"));
        articlesArray.add(new ArticleCard("Diet Plan to Keep you Healthy"));
        articlesArray.add(new ArticleCard("Feeling Lazy? Here's what you do"));
        articlesArray.add(new ArticleCard("Home Remedies to Keep your Skin Fresh"));
        articlesArray.add(new ArticleCard("Controlling Sugar Level"));
    }

}
