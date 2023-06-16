package com.example.recipes2

//import android.R
import com.example.recipes2.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


class RecipeDetailFragment : Fragment() {
    private var recipeId: Long = 0;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            val stoper = StoperFragment()
            val ft = childFragmentManager.beginTransaction()
            ft.add(R.id.stoper_container, stoper)
            ft.addToBackStack(null)
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
            ft.commit()
        } else {
            recipeId = savedInstanceState.getLong("recipeId")
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        Log.d("recipeDetailFragment", "onCreateView")
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    fun setrecipe(Id: Long)
    {
        this.recipeId = Id
        Log.d("recipeDetailFragment", "Received recipe ID: $recipeId")
        super.onResume()
    }

    override fun onResume() {
        super.onResume()
        val view = view
        Log.d("recipeDetailFragment", "OnStart ready: $recipeId")
        if (view != null) {
            Log.d("recipeDetailFragment", "--Received recipe ID: $recipeId")
            val title = view.findViewById<View>(R.id.textTitle) as TextView
            val recipe = Recipe.recipes[recipeId.toInt()]
            title.text = recipe.fetchName()
            Log.d("recipeDetailFragment", "Title: ${title.text}")
            val description = view.findViewById<View>(R.id.textDescription) as TextView
            Log.d("recipeDetailFragment", "Description: ${description.text}")
            description.text = recipe.getRecipe()
        }
        else{
            Log.d("recipeDetailFragment", "View = null")
        }
    }
}