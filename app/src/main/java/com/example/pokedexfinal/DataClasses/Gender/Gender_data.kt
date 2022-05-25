package com.example.pokedexfinal.DataClasses.Gender

data class Gender_data(
    val id: Int,
    val name: String,
    val pokemon_species_details: List<PokemonSpeciesDetail>,
    val required_for_evolution: List<RequiredForEvolution>
)