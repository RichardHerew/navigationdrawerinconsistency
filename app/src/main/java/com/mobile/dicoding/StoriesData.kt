package com.mobile.dicoding

object StoriesData {
    private val storyNames = arrayOf(
        "The Bogey Beast",
        "The Tortoise and the Hare",
        "The Tale of Johnny Town-Mouse",
        "Little Red Riding Hood",
        "The Story of an Hour",
        "Girl",
        "Old Man at the Bridge",
        "Popular Mechanics",
        "A Conversation from the Third Floor",
        "The Dinner Party"
    )

    private val storyAuthors = arrayOf(
        "Flora Annie Steel",
        "Aesop",
        "Beatrix Potter",
        "Charles Perrault",
        "Kate Chopin",
        "Jamaica Kincaid",
        "Ernest Hemingway",
        "Raymond Carver",
        "Mohamed El-Bisatie",
        "Mona Gardner"
    )

    private val storyCovers = intArrayOf(
        R.drawable.img_the_bogey_beast,
        R.drawable.img_the_tortoise_and_the_hare,
        R.drawable.img_the_tale_of_johnny_town_mouse,
        R.drawable.img_little_red_riding_hood,
        R.drawable.img_the_story_of_an_hour,
        R.drawable.img_girl,
        R.drawable.img_the_old_man_at_the_bridge,
        R.drawable.img_popular_mechanics,
        R.drawable.img_a_conversation_from_the_third_floor,
        R.drawable.img_the_dinner_party
    )

    private val storyRatings = doubleArrayOf(
        4.3,
        4.7,
        4.2,
        3.9,
        4.6,
        3.7,
        3.8,
        4.9,
        4.3,
        4.0
    )

    val listData: ArrayList<Story>
        get() {
            return ArrayList<Story>().apply {
                for (position in storyNames.indices) {
                    add(Story(
                        position,
                        storyNames[position],
                        storyAuthors[position],
                        storyCovers[position],
                        storyRatings[position]
                    ))
                }
            }
        }
}