package com.example.movieapp.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.movieapp.R
import com.example.movieapp.ui.theme.SurfaceColor

@Composable
fun BackButton(
    onBackPress: () -> Unit,
    buttonSize: Dp = 24.dp
) {
    Card(
        modifier = Modifier.clickable(role = Role.Button) { onBackPress() },
        elevation = 8.dp,
        backgroundColor = if (isSystemInDarkTheme()) SurfaceColor else MaterialTheme.colors.surface,
        shape = RoundedCornerShape(size = 50.dp)
    ) {
        Icon(
            painter = painterResource(id = R.drawable.left_long_solid),
            contentDescription = "",
            tint = MaterialTheme.colors.primary,
            modifier = Modifier
                .padding(all = 8.dp)
                .size(size = buttonSize)
        )
    }
}
