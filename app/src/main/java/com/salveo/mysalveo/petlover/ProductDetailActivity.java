package com.salveo.mysalveo.petlover;

import android.os.Bundle;

import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.salveo.mysalveo.R;

public class ProductDetailActivity extends AppCompatActivity {


        // BottomSheetBehavior variable
        private BottomSheetBehavior bottomSheetBehavior;


        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_product_detail);

            initViews();
            initListeners();


        }

        /**
         * method to initialize the views
         */
        private void initViews () {
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));


            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);


        }


        /**
         * method to initialize the listeners
         */
        private void initListeners () {
            // register the listener for button click

            // Capturing the callbacks for bottom sheet
            bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
                @Override
                public void onStateChanged(@NonNull View bottomSheet, int newState) {
                    switch (newState) {
                        case BottomSheetBehavior.STATE_COLLAPSED:
                            Log.w("Bottom Sheet Behaviour", "STATE_COLLAPSED");
                            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                            break;
                        case BottomSheetBehavior.STATE_DRAGGING:
                            Log.w("Bottom Sheet Behaviour", "STATE_DRAGGING");
                            break;
                        case BottomSheetBehavior.STATE_EXPANDED:
                            Log.w("Bottom Sheet Behaviour", "STATE_EXPANDED");
                            //  bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HALF_EXPANDED);
                            break;
                        case BottomSheetBehavior.STATE_HIDDEN:
                            Log.w("Bottom Sheet Behaviour", "STATE_HIDDEN");
                            break;
                        case BottomSheetBehavior.STATE_SETTLING:
                            Log.w("Bottom Sheet Behaviour", "STATE_SETTLING");
                            break;
                        case BottomSheetBehavior.STATE_HALF_EXPANDED:
                            Log.w("Bottom Sheet Behaviour", "STATE_HALF_EXPANDED");
                            break;
                    }


                }

                @Override
                public void onSlide(@NonNull View bottomSheet, float slideOffset) {


                }


                /**
                 * onClick Listener to capture button click
                 *
                 * @param v
                 */

            });
        }

    }

