package com.example.recipes2


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.recipes2.RecipeDetailFragment
class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("DetailActivity", "Before setContentView")
        setContentView(R.layout.activity_detail)
        val recipeId = intent.getLongExtra(EXTRA_recipe_ID,0)

        Log.d("DetailActivity", "Odczytane id: $recipeId")
        val frag = supportFragmentManager.findFragmentById(R.id.detail_frag) as? RecipeDetailFragment
        frag?.setrecipe(recipeId)
    }

    companion object {
        const val EXTRA_recipe_ID = "id"
    }
}