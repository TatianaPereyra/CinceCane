package com.seminarioAndroid.cinecane.UI.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seminarioAndroid.cinecane.R

//Font utilizada para el titulo
val Agbalumo = FontFamily(
    Font(R.font.agbalumoregular)
)

// Encabezado
@Composable
fun EnCANEzado(){

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .statusBarsPadding().
            padding(20.dp)
    ) {
        Image( painter = painterResource(R.drawable.logo_cinecane),
            contentDescription = "Logo de CineCane",
            modifier = Modifier.size(100.dp))

        Text(text = stringResource(R.string.app_name_first),
            color = colorResource(R.color.textColor),
            fontSize = 30.sp,
            fontFamily = Agbalumo
        )

        Text(text = stringResource(R.string.app_name_second),
            color = colorResource(R.color.secondaryColor),
            fontSize = 30.sp,
            fontFamily = Agbalumo
        )
    }

}