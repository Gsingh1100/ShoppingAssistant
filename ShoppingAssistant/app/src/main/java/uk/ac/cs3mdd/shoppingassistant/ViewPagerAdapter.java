package uk.ac.cs3mdd.shoppingassistant;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0: return new MealPlanTab();
            case 1: return new ShoppingListTab();
            default:return new MealPlanTab();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
