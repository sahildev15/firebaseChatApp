package com.sahil.myapp.ui.components

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun SpaceSmall() = VerticalSpace(8.dp)
@Composable
fun VerticalSpace(height: Dp) {
    Spacer(modifier = Modifier.height(height))
}

@Composable fun SpaceMedium() = VerticalSpace(16.dp)
@Composable fun SpaceLarge() = VerticalSpace(24.dp)