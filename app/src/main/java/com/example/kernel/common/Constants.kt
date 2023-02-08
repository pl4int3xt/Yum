package com.example.kernel.common

object Constants {
    private const val BASE_URL = "https://www.themealdb.com/api/json/v1/1"
    const val GET_CATEGORIES_URL = "$BASE_URL/categories.php"
    const val GET_MEALS_URL = "$BASE_URL/filter.php"
    const val GET_MEAL_DETAILS_URL = "$BASE_URL/search.php"
    const val GET_AREAS_URL = "$BASE_URL/list.php"
}