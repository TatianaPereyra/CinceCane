package com.seminarioAndroid.cinecane.app

import java.util.Locale

object DeviceLocale {

    //Obtengo el idioma del dispositivo, si no lo encuentra por defecto es ingles
    fun languageTag(): String =
        Locale.getDefault().toLanguageTag().ifBlank { "en-US" }

}
