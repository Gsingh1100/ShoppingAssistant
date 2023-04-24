package uk.ac.cs3mdd.shoppingassistant;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.LayoutTransition;
import android.os.Bundle;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MyNutrition extends AppCompatActivity {

    TextView softBoiledEggsText;
    TextView porridgeText;
    TextView pancakesText;
    TextView frenchToastText;
    TextView salmonBagelText;
    TextView wrapsText;
    TextView jacketPotatoText;
    TextView chickRiceText;
    TextView chickPadThaiText;
    TextView chickSaladText;
    TextView pastaBakeText;
    TextView fishChipsText;
    TextView spagBolText;
    TextView lambCurryText;


    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    LinearLayout layout5;
    LinearLayout layout6;
    LinearLayout layout7;
    LinearLayout layout8;
    LinearLayout layout9;
    LinearLayout layout10;
    LinearLayout layout11;
    LinearLayout layout12;
    LinearLayout layout13;
    LinearLayout layout14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_nutrition);


        Button back = findViewById(R.id.button);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        softBoiledEggsText = findViewById(R.id.softBoiledEggsInfo);
        layout1 = findViewById(R.id.sbeLayout);
        layout1.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        porridgeText = findViewById(R.id.porridgeInfo);
        layout2 = findViewById(R.id.pobLayout);
        layout2.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        pancakesText = findViewById(R.id.pancakesInfo);
        layout3 = findViewById(R.id.pancakesLayout);
        layout3.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        frenchToastText = findViewById(R.id.frenchToastInfo);
        layout4 = findViewById(R.id.frenchToastLayout);
        layout4.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        salmonBagelText = findViewById(R.id.salmonBagelInfo);
        layout5 = findViewById(R.id.salmonBagelLayout);
        layout5.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        wrapsText = findViewById(R.id.wrapsInfo);
        layout6 = findViewById(R.id.wrapsLayout);
        layout6.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        jacketPotatoText = findViewById(R.id.jacketPotatoInfo);
        layout7 = findViewById(R.id.jacketPotatoLayout);
        layout7.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        chickRiceText = findViewById(R.id.chickRiceInfo);
        layout8 = findViewById(R.id.chickRiceLayout);
        layout8.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        chickPadThaiText = findViewById(R.id.padThaiInfo);
        layout9 = findViewById(R.id.padThaiLayout);
        layout9.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        chickSaladText = findViewById(R.id.chickSaladInfo);
        layout10 = findViewById(R.id.chickSaladLayout);
        layout10.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        pastaBakeText = findViewById(R.id.pastaBakeInfo);
        layout11 = findViewById(R.id.pastaBakeLayout);
        layout11.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

        fishChipsText = findViewById(R.id.fishChipsInfo);
        layout12 = findViewById(R.id.fishChipsLayout);
        layout12.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        spagBolText = findViewById(R.id.spagBolInfo);
        layout13 = findViewById(R.id.spagBolLayout);
        layout13.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


        lambCurryText = findViewById(R.id.lambCurryInfo);
        layout14 = findViewById(R.id.lambCurryLayout);
        layout14.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);

    }


    public void expandable (View view){

        int visible = (softBoiledEggsText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout1, new AutoTransition());
        softBoiledEggsText.setVisibility(visible);

    }

    public void expandable2(View view) {

        int visible = (porridgeText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout2, new AutoTransition());
        porridgeText.setVisibility(visible);
    }

    public void expandable3(View view) {

        int visible = (pancakesText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout3, new AutoTransition());
        pancakesText.setVisibility(visible);

    }

    public void expandable4(View view) {

        int visible = (frenchToastText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout4, new AutoTransition());
        frenchToastText.setVisibility(visible);
    }

    public void expandable5(View view) {

        int visible = (salmonBagelText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout5, new AutoTransition());
        salmonBagelText.setVisibility(visible);

    }

    public void expandable6(View view) {

        int visible = (wrapsText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout6, new AutoTransition());
        wrapsText.setVisibility(visible);
    }

    public void expandable7(View view) {

        int visible = (jacketPotatoText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout7, new AutoTransition());
        jacketPotatoText.setVisibility(visible);
    }

    public void expandable8(View view) {

        int visible = (chickRiceText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout8, new AutoTransition());
        chickRiceText.setVisibility(visible);
    }

    public void expandable9(View view) {

        int visible = (chickPadThaiText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout9, new AutoTransition());
        chickPadThaiText.setVisibility(visible);
    }

    public void expandable10(View view) {

        int visible = (chickSaladText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout10, new AutoTransition());
        chickSaladText.setVisibility(visible);
    }

    public void expandable11(View view) {

        int visible = (pastaBakeText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout11, new AutoTransition());
        pastaBakeText.setVisibility(visible);
    }

    public void expandable12(View view) {

        int visible = (fishChipsText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout12, new AutoTransition());
        fishChipsText.setVisibility(visible);
    }

    public void expandable13(View view) {

        int visible = (spagBolText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout13, new AutoTransition());
        spagBolText.setVisibility(visible);
    }

    public void expandable14(View view) {

        int visible = (lambCurryText.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

        TransitionManager.beginDelayedTransition(layout14, new AutoTransition());
        lambCurryText.setVisibility(visible);
    }
}