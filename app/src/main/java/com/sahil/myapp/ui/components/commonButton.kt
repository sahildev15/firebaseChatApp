package com.sahil.myapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.*
import com.sahil.myapp.ui.theme.AppColor
import com.sahil.myapp.ui.theme.AppTextStyles



@Composable
fun CommonButton(
    title: String,
    isButtonEnabled: Boolean,
    isLoading: Boolean = false,
    onClick: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val backgroundColor = if (isButtonEnabled) AppColor.mainColor else AppColor.greyTertiaryBox
    val textStyle = if (isButtonEnabled) AppTextStyles.archivo_font16_500_brand else AppTextStyles.archivo_font16_500_white

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp)
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(10.dp)
            )
            .clickable(
                enabled = isButtonEnabled && !isLoading,
                onClick = { onClick?.invoke() }
            ),
        contentAlignment = Alignment.Center
    ) {
        if (isLoading) {
            CircularProgressIndicator(
                color = AppColor.brand,
                strokeWidth = 2.dp,
                modifier = Modifier.size(20.dp)
            )
        } else {
            Text(
                text = title,
                style = textStyle
            )
        }
    }
}
