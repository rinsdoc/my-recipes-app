/**
 * Displays detailed information about a specific recipe.
 * Demonstrates dynamic UI creation and data binding with ViewModel.
 * Shows how to handle complex UI updates with LiveData observation.
 */
package myrecipes.app.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.bumptech.glide.Glide;
import java.util.List;
import java.util.Random;

import myrecipes.app.R;
import myrecipes.app.databinding.FragmentDetailBinding;
import myrecipes.app.databinding.FragmentRandomBinding;
import myrecipes.app.models.Recipe;
import myrecipes.app.viewmodels.DetailViewModel;
import myrecipes.app.viewmodels.RandomViewModel;

public class RandomFragment extends Fragment {
    private RandomViewModel viewModel;
    private FragmentRandomBinding binding;



    /**
     * Inflates the fragment layout using View Binding.
     * Shows proper fragment initialization with view binding.
     */

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRandomBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    /**
     *
     * @param view The View returned by {@link #onCreateView(LayoutInflater, ViewGroup, Bundle)}.
     * @param savedInstanceState If non-null, this fragment is being re-constructed
     * from a previous saved state as given here.
     */
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setupViewModel();
        setupFavouriteFab();
        setupRandomButton();
    }

    private void setupRandomButton() {
        binding.randomButton.setOnClickListener(v -> viewModel.loadRandomRecipe());
    }

    private void setupViewModel() {
        viewModel = new ViewModelProvider(this).get(RandomViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        viewModel.loadRandomRecipe();
        viewModel.getRecipe().observe(getViewLifecycleOwner(), this::displayRecipeDetails);

        viewModel.getRecipe().observe(getViewLifecycleOwner(), recipe -> {
            displayRecipeDetails(recipe);
            if (recipe != null) {
                viewModel.checkIsFavourite(recipe.getId());
            }
        });
    }

    private void setupFavouriteFab() {
        binding.fabFavourite.setOnClickListener(v -> {
            Recipe currentRecipe = viewModel.getRecipe().getValue();
            if (currentRecipe != null) {
                viewModel.toggleFavourite(currentRecipe);
            }
        });

        viewModel.isFavourite().observe(getViewLifecycleOwner(), isFavourite -> {
            binding.fabFavourite.setImageResource(
                    isFavourite ? R.drawable.ic_favourite : R.drawable.ic_favourite_border
            );
        });
    }
    private void displayRecipeDetails(Recipe recipe) {
        // Load recipe image using Glide
        Glide.with(requireContext())
                .load(recipe.getImageUrl())
                .into(binding.recipeImageView);

        // Display ingredients dynamically
        binding.ingredientsContainer.removeAllViews();
        for (List<Object> ingredient : recipe.getIngredients()) {
            TextView ingredientTextView = new TextView(requireContext());
            String ingredientText = "• " + ingredient.get(0) + " (" + ingredient.get(2) + ")";
            ingredientTextView.setText(ingredientText);
            ingredientTextView.setTextSize(18);
            ingredientTextView.setTextAppearance(R.style.CustomEditText);
            binding.ingredientsContainer.addView(ingredientTextView);
        }

        // Display cooking steps
        binding.stepsContainer.removeAllViews();
        for (int i = 0; i < recipe.getSteps().size(); i++) {
            TextView stepTextView = new TextView(requireContext());
            stepTextView.setText((i + 1) + ". " + recipe.getSteps().get(i));
            stepTextView.setTextSize(18);
            stepTextView.setTextAppearance(R.style.CustomEditText);
            binding.stepsContainer.addView(stepTextView);
        }

        // Display calorie information
        binding.caloriesContainer.removeAllViews();
        TextView caloriesTextView = new TextView(requireContext());
        int calories = recipe.getCalories();
        caloriesTextView.setText(calories + " Calorias totales");
        caloriesTextView.setTextSize(18);
        caloriesTextView.setTextAppearance(R.style.CustomEditText);
        binding.caloriesContainer.addView(caloriesTextView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}