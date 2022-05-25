package com.example.pokedexfinal.DataClasses.Egggroups

data class Egggroup_data(
    val id: Int,
    val name: String,
    val names: List<Name>,
    val pokemon_species: List<PokemonSpecy>
)