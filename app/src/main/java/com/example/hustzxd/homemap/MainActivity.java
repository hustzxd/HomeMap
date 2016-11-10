package com.example.hustzxd.homemap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hustzxd.homemap.util.AssetsHelper;
import com.svgmapview.SVGMapView;
import com.svgmapview.SVGMapViewListener;
import com.svgmapview.core.data.SVGPicture;
import com.svgmapview.core.helper.ImageHelper;
import com.svgmapview.core.helper.map.SVGBuilder;
import com.svgmapview.overlay.SVGMapLocationOverlay;


public class MainActivity extends AppCompatActivity {

    SVGMapView mSVGMapView;
    TextView mTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSVGMapView = (SVGMapView) findViewById(R.id.map_view);
        mTextView = (TextView) findViewById(R.id.tv);
        mSVGMapView.registerMapViewListener(new SVGMapViewListener() {
            @Override
            public void onMapLoadComplete() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"onMapLoadComplete",Toast.LENGTH_LONG).show();
                        SVGMapLocationOverlay locationOverlay = new SVGMapLocationOverlay(mSVGMapView);
                        locationOverlay.setPosition(new PointF(400, 500));
                        locationOverlay.setMode(SVGMapLocationOverlay.MODE_NORMAL);
                        mSVGMapView.getOverLays().add(locationOverlay);
                        mSVGMapView.refresh();
                    }
                });
            }

            @Override
            public void onMapLoadError() {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"onMapLoadError",Toast.LENGTH_LONG).show();
                    }
                });
            }

            @Override
            public void onGetCurrentMap(Bitmap bitmap) {
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"onGetCurrentMap",Toast.LENGTH_LONG).show();
                    }
                });
            }
        });

        mSVGMapView.setBrandBitmap(ImageHelper.drawableToBitmap(
                new SVGBuilder().readFromString(SVGPicture.ICON_TOILET).build().getDrawable(), 1.0f));

        mSVGMapView.loadMap(AssetsHelper.getContent(this,"map1.svg"));

        mTextView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                Toast.makeText(MainActivity.this,"dddd",Toast.LENGTH_LONG).show();
                return true;
            }
        });
//        mSVGMapView.getController().sparkAtPoint(
//                new PointF(400, 400), 100, Color.BLUE, 10);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSVGMapView.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSVGMapView.onResume();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSVGMapView.onDestroy();
    }


}
