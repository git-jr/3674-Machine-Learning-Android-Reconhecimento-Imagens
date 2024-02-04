package com.br.alura.galeria.data


class ImageCategoryRepository {

    fun getAvailableGroups(): List<ImageCategory> {
        return listOf(
            peoplesGroup,
            placesGroup,
            foodsGroup,
            vehiclesGroup,
            animalsGroup,
            musicGroup
        )
    }


    private val peoplesGroup = ImageCategory(
        name = "Pessoas",
        keyWords = listOf(
            "Person",
            "Dude",
            "Laugh",
            "Superhero",
            "Bride",
            "Model",
            "Rodeo",
            "Army",
            "Selfie",
            "Groom",
            "Supervillain",
            "Joker",
            "Hair",
            "Beard",
            "Skin",
            "Smile",
            "Moustache",
            "Skateboarder"
        )
    )

    private val placesGroup = ImageCategory(
        name = "Lugares",
        keyWords = listOf(
            "Event",
            "Park",
            "Factory",
            "Stadium",
            "Rafting",
            "Bridge",
            "Tower",
            "Cave",
            "Skyline",
            "Shipwreck",
            "Pier",
            "Community",
            "Church",
            "Temple",
            "Castle",
            "Aurora",
            "Canyon",
            "Farm",
            "Beach",
            "Museum",
            "Mountain",
            "Garden",
            "Building",
            "Cathedral"
        )
    )

    private val foodsGroup = ImageCategory(
        name = "Comidas",
        keyWords = listOf(
            "Food",
            "Vegetable",
            "Cheeseburger",
            "Couscous",
            "Fast food",
            "Pho",
            "Sushi",
            "Cake",
            "Bread",
            "Cappuccino",
            "Sand",
            "Juice",
            "Cuisine",
            "Fruit"
        )
    )


    private val vehiclesGroup = ImageCategory(
        name = "Veículos",
        keyWords = listOf(
            "Vehicle",
            "Car",
            "Wheel",
            "Bumper",
            "Windshield",
            "Tractor",
            "Bus",
            "Sled",
            "Clipper",
            "Motorcycle",
            "Airliner",
            "Sailboat",
            "Rickshaw",
            "Canoe",
            "Rocket",
            "Bicycle"
        )
    )

    private val animalsGroup = ImageCategory(
        name = "Animais",
        keyWords = listOf(
            "Cat",
            "Cattle",
            "Dog",
            "Gerbil",
            "Shetland sheepdog",
            "Dalmatian",
            "Penguin",
            "Basset hound",
            "Seal",
            "Sphynx",
            "Turtle",
            "Crocodile",
            "Butterfly",
            "Bull",
            "Horse",
            "Duck",
            "Bird",
        )
    )

    private val musicGroup = ImageCategory(
        name = "Música",
        keyWords = listOf(
            "Concert",
            "Prom",
            "Pop music",
            "Musical instrument",
            "Musical",
            "Musician",
            "Song"
        )
    )
}