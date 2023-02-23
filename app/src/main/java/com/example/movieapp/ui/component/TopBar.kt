package com.example.movieapp.ui.component

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    leadingIcon: @Composable () -> Unit,
    title: (@Composable () -> Unit)? = null,
    trailingIcon: (@Composable () -> Unit)? = null
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        leadingIcon()
        title?.invoke()
        if(trailingIcon == null){
            Spacer(modifier = Modifier.requiredWidth(width = 26.dp))
        } else{
            trailingIcon()
        }
    }
}