package com.proyecto.albanbassante.espelapp;

/**
 * Created by gonzaloalban on 6/5/15.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.qozix.tileview.TileView;
import com.qozix.tileview.graphics.BitmapDecoderAssets;

public class Tab3 extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.tab3,container,false);
        final double posX;
        final double posY;
        final Bundle args = getArguments();
        final String id = args.getString("id");
        final TileView tileView = new TileView(getActivity());
        tileView.setDecoder(new BitmapDecoderAssets());





        int width = 6259;
        int height = 3716;

        tileView.setSize(width, height);

        tileView.addDetailLevel(1f, "tiles1/1000_%col%_%row%.png", "downsamples1/primerpiso.png");
        tileView.addDetailLevel(0.5f, "tiles1/500_%col%_%row%.png", "downsamples1/primerpiso.png");
        tileView.addDetailLevel(0.25f, "tiles1/250_%col%_%row%.png", "downsamples1/primerpiso.png");
        tileView.addDetailLevel(0.125f, "tiles1/125_%col%_%row%.png", "downsamples1/primerpiso.png");

        //tileView.moveToAndCenter(6259, 3716);


        tileView.setScaleLimits(0, 4);
        tileView.setScale(1);
        //tileView.setScale(1);

        //tileView.defineRelativeBounds(-0.9361351607367396, -78.61079947091639, -0.9347531525418162, -78.61034827306867);
        //tileView.defineRelativeBounds(-0.93663769, -78.61052442, -0.93477397, -78.61018396);

        ImageView markerA = new ImageView(this.getActivity());
        markerA.setImageResource(R.drawable.marker);
        markerA.setTag("Nice");

        ImageView markerB = new ImageView(this.getActivity());
        markerB.setImageResource(R.drawable.marker);
        markerB.setTag("Nice");


        if (id.equals("1")) {
            tileView.post(new Runnable() {
                @Override
                public void run() {
                    tileView.slideToAndCenter(1442, 2535);
                }
            });
            tileView.addMarker(markerA, 1442, 2535, -0.5f, -1.0f);
        }
        if (id.equals("2")) {
            tileView.post(new Runnable() {
                @Override
                public void run() {
                    tileView.slideToAndCenter(1384, 1912);
                }
            });
            tileView.addMarker(markerA, 1384, 1912, -0.5f, -1.0f);
        }
        if (id.equals("3")) {
            tileView.post(new Runnable() {
                @Override
                public void run() {
                    tileView.slideToAndCenter(1382, 2899);
                }
            });
            tileView.addMarker(markerA, 1382, 2899, -0.5f, -1.0f);
        }
        if (id.equals("4")) {
            tileView.post(new Runnable() {
                @Override
                public void run() {
                    tileView.slideToAndCenter(1442, 2660);
                }
            });
            tileView.addMarker(markerA, 1442, 2660, -0.5f, -1.0f);
        }
        if (id.equals("5")) {
            tileView.post(new Runnable() {
                @Override
                public void run() {
                    tileView.slideToAndCenter(398, 2311);
                }
            });
            tileView.addMarker(markerA, 398, 2311, -0.5f, -1.0f);
        }



        //tileView.addMarker(markerB, -0.9357831525418162 , -78.61051827306867);
        //tileView.addMarker(markerB, -0.9357531525418162 , -78.61034827306867);

        //tileView.addMarker(markerA, -0.93663769 , -78.61052442);
        //tileView.addMarker(markerB, -0.93663769 , -78.61018396);




        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        ((LinearLayout) v).addView(tileView, lp);

        return v;
    }

}
