package uk.ac.cs3mdd.shoppingassistant;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.app.Activity;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import uk.ac.cs3mdd.shoppingassistant.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        //App opens to this page once logged in and selected a store:
        replaceFragment(new HomeFragment());


        //User selects items(icons)
        //Listen to that event and go to selected fragment
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            //Switch cases - get the id of the selected item
            //Calls on replaceFragment method
            switch (item.getItemId()) {

                case R.id.home:
                    replaceFragment(new HomeFragment());
                    break;
                case R.id.mealPlan:
                    replaceFragment(new MealPlanFragment());
                    break;
                case R.id.route:
                    replaceFragment(new RouteFragment());
                    break;
                case R.id.profile:
                    replaceFragment(new ProfileFragment());
                    break;
            }
            return true;
        });
    }



    //Method to replace the framelayout with the selected fragment
    public void replaceFragment (Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        //Replaces framelayout with the fragment that has been passed
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }
}