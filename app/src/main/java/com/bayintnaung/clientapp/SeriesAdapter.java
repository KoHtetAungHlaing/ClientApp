package com.bayintnaung.clientapp;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import io.grpc.internal.InternalServer;

public class SeriesAdapter extends RecyclerView.Adapter<SeriesAdapter.MovieHolder> {
    ArrayList<SeriesModel> seriesModels=new ArrayList<>();
    Context context;
    FragmentManager fm;
    ArrayList<String> seriesIds=new ArrayList<String>();
    InterstitialAd interstitialAd;

    public SeriesAdapter(ArrayList<SeriesModel> seriesModels, final Context context, FragmentManager fm) {
        this.seriesModels = seriesModels;
        this.context = context;
        this.fm=fm;
        GoogleAds googleAds=new GoogleAds();
        interstitialAd=googleAds.loadInterstitialAds(context);
    }

    public SeriesAdapter(ArrayList<SeriesModel> seriesModels, final Context context, FragmentManager fm, ArrayList<String> seriesIds) {
        this.seriesModels = seriesModels;
        this.context = context;
        this.fm=fm;
        this.seriesIds=seriesIds;
        GoogleAds googleAds=new GoogleAds();
        interstitialAd=googleAds.loadInterstitialAds(context);
    }

    @NonNull
    @Override
    public MovieHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View myView= LayoutInflater.from(parent.getContext()).inflate(R.layout.movieitem,parent,false);
        return new MovieHolder(myView);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieHolder holder, final int position) {
        Glide.with(context)
                .load(seriesModels.get(position).seriesImage)
                .into(holder.movieImage);
        holder.movieImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              if(interstitialAd.isLoaded()){
                    interstitialAd.show();
                    goToNext(position);
                    SeriesFragment.setHeader(seriesModels.get(position).seriesName);
              }else{
                    goToNext(position);
                  SeriesFragment.setHeader(seriesModels.get(position).seriesName);
              }

            }
        });
    }

    @Override
    public int getItemCount() {
        return seriesModels.size();
    }

    public class MovieHolder extends RecyclerView.ViewHolder{
        ImageView movieImage;

        public MovieHolder(@NonNull View itemView) {
            super(itemView);
            movieImage=itemView.findViewById(R.id.image);

        }
    }
    public void setFragment(Fragment detfrag){
        FragmentTransaction ft=fm.beginTransaction();
        ft.replace(R.id.navContent,detfrag);
        ft.commit();
    }
    public void goToNext(int position){
        try {
            if (MainActivity.preFrag.equals("Search")) {
                MainActivity.preFrag = "Search";
            } else {
                MainActivity.preFrag = MainActivity.currentFrag;
            }
            MainActivity.currentFrag = "Series_Detail";
            SeriesDetailFragment detfrag = new SeriesDetailFragment();
            detfrag.model = seriesModels.get(position);
            detfrag.seriesId = seriesIds.get(position);
            setFragment(detfrag);
        }
        catch(Exception ex)
        {
            Log.e("TAG","Main Activity Pre Frag");
        }
    }
}
