package com.seminarioAndroid.cinecane.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.seminarioAndroid.cinecane.R

//Font utilizada para el titulo
val Agbalumo = FontFamily(
    Font(R.font.agbalumoregular)
)

// Encabezado
@Composable
fun EnCANEzado(
    onSearchClick: (() -> Unit)? = null
) {

    val colorPrimero = colorResource(R.color.textColor)
    val colorSegundo = colorResource(R.color.caneHeader)
    val primero = stringResource(R.string.app_name_first)
    val segundo = stringResource(R.string.app_name_second)

    // Un solo texto con dos colores: "Cine" y "Cane"
    val nombre = buildAnnotatedString {
        withStyle(SpanStyle(color = colorPrimero)) { append(primero) }
        withStyle(SpanStyle(color = colorSegundo)) { append(segundo) }
    }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .statusBarsPadding()
            .padding(horizontal = 20.dp, vertical = 8.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.logo_cinecane),
                contentDescription = null,
                modifier = Modifier.size(72.dp)
            )

            Text(
                text = nombre,
                fontSize = 30.sp,
                fontFamily = Agbalumo
            )
        }

        if (onSearchClick != null) {
            IconButton(onClick = onSearchClick) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = stringResource(R.string.search_title),
                    tint = colorResource(R.color.textColor),
                    modifier = Modifier.size(32.dp)
                )
            }
        }
    }
}
