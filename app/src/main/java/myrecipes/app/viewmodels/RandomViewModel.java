package myrecipes.app.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import myrecipes.app.models.Recipe;
import myrecipes.app.repositories.DashboardRepository;
import myrecipes.app.repositories.FavouriteRepository;

/**
 * Manages the UI state for the recipe detail screen.
 * Handles both recipe data and favorite status management.
 */
public class RandomViewModel extends ViewModel {
    private final DashboardRepository repository;
    private final FavouriteRepository favouriteRepository;
    // LiveData for the currently displayed recipe
    private final MutableLiveData<Recipe> recipe = new MutableLiveData<>();
    // Tracks favorite status of current recipe
    private final MutableLiveData<Boolean> isFavourite = new MutableLiveData<>();
    private final MutableLiveData<String> error = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    /**
     * Constructor initializes both repositories needed for recipe details
     * and favorite management.
     */
    public RandomViewModel() {
        repository = new DashboardRepository();
        favouriteRepository = new FavouriteRepository();
    }

    public LiveData<Recipe> getRecipe() { return recipe; }
    public LiveData<Boolean> isFavourite() { return isFavourite; }


    public void loadRandomRecipe() {
        repository.getRandomRecipe(recipe);
        recipe.observeForever(recipe -> {
            if (recipe != null) {
                checkIsFavourite(recipe.getId());
            }
        });
    }

    /**
     * Checks if current recipe is in user's favorites
     */
    public void checkIsFavourite(String recipeId) {
        favouriteRepository.checkIsFavorite(recipeId, isFavourite);
    }

    /**
     * Toggles favorite status of current recipe.
     * Updates both repository and local UI state.
     */
    public void toggleFavourite(Recipe recipe) {
        Boolean currentFavouriteStatus = isFavourite.getValue();
        if (currentFavouriteStatus != null) {
            favouriteRepository.toggleFavorite(recipe.getId(), !currentFavouriteStatus);
            isFavourite.setValue(!currentFavouriteStatus);
        }
    }
}
