package com.example.recipes2

class Recipe(val name: String, private val recipe: String) {
    companion object {
        val recipes = arrayOf(
            Recipe("Jajecznica", "Składniki: \n 3 jajka \n 10 g masła" +
                    "\n\n Sposób przygotowania: \n Jajka wymieszać, posolić." +
                    "Roztopić masło na patelni, wlać jajka i smażyć kilka minut, do pożądanego"+
                    "stopnia ścięcia."),
            Recipe("Woda", "Składniki: \n 250 ml wody" +
            "Sposób przygotowania: \n Wodę wlej do szklanki i wypij")
        )
    }

    fun getRecipe(): String {
        return recipe
    }

    fun fetchName(): String {
        return name
    }

    override fun toString(): String {
        return name
    }
}
