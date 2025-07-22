package com.sahil.myapp.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.*
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.foundation.shape.RoundedCornerShape

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun CommonTextField(
    modifier: Modifier = Modifier,
    hint: String,
    value: String,
    onValueChange: (String) -> Unit,
    label: String? = null,
    isPassword: Boolean = false,
    maxLength: Int? = null,
    isRequired: Boolean = false,
    showValidationError: Boolean = false,
    prefixIcon: @Composable (() -> Unit)? = null,
    suffixIcon: @Composable (() -> Unit)? = null,
    keyboardType: KeyboardType = KeyboardType.Text,
    enabled: Boolean = true,
) {
    val isError = showValidationError && isRequired && value.trim().isEmpty()
    val keyboardController = LocalSoftwareKeyboardController.current

    Column(modifier = modifier.padding(vertical = 4.dp)) {
        OutlinedTextField(
            value = value,
            onValueChange = {
                val newValue = if (maxLength != null) it.take(maxLength) else it
                onValueChange(newValue.replaceFirstChar { c -> c.uppercase() })
            },
            label = {
                if (label != null) {
                    Text(
                        text = label,
                        color = if (isError) Color.Red else Color.Gray,
                        fontSize = 12.sp
                    )
                }
            },
            placeholder = {
                Text(
                    text = hint,
                    style = TextStyle(fontSize = 13.sp, color = Color.Gray)
                )
            },
            textStyle = TextStyle(fontSize = 13.sp),
            singleLine = !isPassword,
            enabled = enabled,
            isError = isError,
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = keyboardType,
                imeAction = ImeAction.Done
            ),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            leadingIcon = prefixIcon,
            trailingIcon = suffixIcon,
            shape = RoundedCornerShape(12.dp), // Rounded corners
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White,
                disabledContainerColor = Color.LightGray,
                errorContainerColor = Color.White,
                focusedIndicatorColor = if (isError) Color.Red else Color.Gray,
                unfocusedIndicatorColor = Color.Gray,
                disabledIndicatorColor = Color.LightGray,
                errorIndicatorColor = Color.Red
            ),
            modifier = Modifier
                .fillMaxWidth()
                .background(Color.Transparent)
        )

        if (isError) {
            Text(
                text = "${label ?: "This field"} is required",
                color = Color.Red,
                fontSize = 11.sp,
                modifier = Modifier.padding(start = 8.dp, top = 2.dp)
            )
        }
    }
}
