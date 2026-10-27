package com.seminarioAndroid.cinecane.UI.composables

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.request.colorSpace
import com.seminarioAndroid.cinecane.R

@Composable
fun HandleError(
    mensaje: String,
    onRetry: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .border(
                width = 4.dp,
                color = colorResource(R.color.secondaryColor),
                shape = RoundedCornerShape(8.dp)
            )
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {

        Icon(
            painter = painterResource(id = R.drawable.connection_error),
            contentDescription = "Error de conexión",
            tint = colorResource(R.color.textColor),
            modifier = Modifier.size(48.dp)
        )

        Text(
            text = stringResource(R.string.errorTitle),
            color = colorResource(R.color.textColor),
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

        Text(
            text = mensaje,
            color = colorResource(R.color.textColor),
            fontSize = 16.sp
        )

        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.secondaryColor),
                contentColor = colorResource(R.color.primaryColor),
                disabledContainerColor = colorResource(R.color.buttonColor))
        ){
            Text(text = stringResource(R.string.retry))
        }
    }
}
