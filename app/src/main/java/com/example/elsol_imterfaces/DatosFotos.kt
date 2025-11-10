package com.example.elsol_imterfaces


data class SolImage(val id: Int, val name: String)

val solImages = listOf(
    SolImage(R.drawable.corona_solar, "Corona solar"),
    SolImage(R.drawable.erupcionsolar, "Erupción solar"),
    SolImage(R.drawable.espiculas, "Espículas"),
    SolImage(R.drawable.filamentos, "Filamentos"),
    SolImage(R.drawable.magnetosfera, "Magneto Esfera"),
    SolImage(R.drawable.manchasolar, "Mancha Solar")
)

