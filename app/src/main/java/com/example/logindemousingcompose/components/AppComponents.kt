package com.example.logindemousingcompose.components


import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.logindemousingcompose.R;
import com.example.logindemousingcompose.ui.theme.GrayColor
import com.example.logindemousingcompose.ui.theme.Secondary
import com.example.logindemousingcompose.ui.theme.TextColor
import com.example.logindemousingcompose.ui.theme.primary

@Composable
fun NormalTextComponent(value : String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontSize = 24.sp,
            fontWeight = FontWeight.Normal
        ),
        color = TextColor,
        textAlign =  TextAlign.Center
    )
}

@Composable
fun HeadingTextComponent(value : String) {
    Text(
        text = value,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        color = TextColor,
        textAlign =  TextAlign.Center
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTextFieldComponent(labelValue: String, painterResource: Painter,onTextSelected: (String) -> Unit,errorStatus : Boolean = false) {
    val textValue = remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        label = { Text(text = labelValue)},
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShapes.small),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primary,
            focusedLabelColor = primary,
            cursorColor = primary
        ),
        keyboardOptions = KeyboardOptions.Default,
        value = textValue.value,
        onValueChange = {
        textValue.value = it
            onTextSelected(it)
    },

        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },

        isError = !errorStatus

    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordTextComponent(labelValue: String, painterResource: Painter,onTextSelected: (String) -> Unit, errorStatus: Boolean = false) {
    val password = remember {
        mutableStateOf("")
    }

    val passwordVisible = remember {
        mutableStateOf(false)
    }

    OutlinedTextField(
        label = { Text(text = labelValue)},
        modifier = Modifier
            .fillMaxWidth()
            .clip(ComponentShapes.small),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = primary,
            focusedLabelColor = primary,
            cursorColor = primary
        ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        value = password.value,
        onValueChange = {
            password.value = it
            onTextSelected(it)
        },

        leadingIcon = {
            Icon(painter = painterResource, contentDescription = "")
        },

        trailingIcon = {
            val iconImage = if(passwordVisible.value) {
                Icons.Filled.Visibility
            } else {
                Icons.Filled.VisibilityOff
            }

            var description = if(passwordVisible.value) {
                stringResource(R.string.hide_password)
            } else {
                stringResource(R.string.show_password)
            }

            IconButton(onClick = {passwordVisible.value = !passwordVisible.value}) {
                Icon(imageVector = iconImage, contentDescription = description)
            }
        },

        visualTransformation = if(passwordVisible.value) VisualTransformation.None else PasswordVisualTransformation(),
        isError = !errorStatus

    )
}

@Composable
fun CheckboxComponent(value : String, onTextSelected: (String) -> Unit) {

    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 10.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {

        val checkedStatus = remember {
            mutableStateOf(false)
        }
        Checkbox(checked = checkedStatus.value, onCheckedChange = {
                checkedStatus.value != checkedStatus.value
        })


        ClickableTextComponent(value = value,onTextSelected)
    }

}

@Composable
fun ClickableTextComponent(value : String, onTextSelected : (String) -> Unit) {
    val initialText = "By continuing you accept our "
    val privacyPolicyText = "Privacy Policy"
    val andText = " And "
    val termsAndConditionText = "Terms and Condition"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = primary)) {
            pushStringAnnotation(tag = privacyPolicyText, annotation = privacyPolicyText)
            append(privacyPolicyText)
        }
        append(andText)
        withStyle(style = SpanStyle(color = primary)) {
            pushStringAnnotation(tag = termsAndConditionText, annotation = termsAndConditionText)
            append(termsAndConditionText)
        }
    }
    ClickableText(text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also {span->
                Log.d("ClickableTextComponent","{$span}")

                if((span.item == privacyPolicyText) || (span.item == termsAndConditionText)) {
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun ButtonComponent(value: String, onButtonClicked : () -> Unit) {
    Button(onClick = {
        onButtonClicked.invoke()
    } ,
        modifier = Modifier
            .fillMaxWidth()
            .height(60.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
        ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(48.dp)
            .background(
                brush = Brush.horizontalGradient(listOf(Secondary, primary)),
                shape = RoundedCornerShape(50.dp)
            ), contentAlignment = Alignment.Center
        ) {
            Text(text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
fun DividerComponent() {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalDivider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )

        Text(modifier = Modifier.padding(8.dp), text = stringResource(R.string.or), fontSize = 18.sp, color = TextColor)

        HorizontalDivider(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            color = GrayColor,
            thickness = 1.dp
        )
    }
}

@Composable
fun ClickableLoginTextComponent(tryingToLogin : Boolean = true,onTextSelected : (String) -> Unit) {
    val initialText = if(tryingToLogin) "Already have an account? " else "Don't have an account yet? "
    val loginText = if(tryingToLogin) "Login" else "Register"

    val annotatedString = buildAnnotatedString {
        append(initialText)
        withStyle(style = SpanStyle(color = primary)) {
            pushStringAnnotation(tag = loginText, annotation = loginText)
            append(loginText)
        }
    }
    ClickableText(
        modifier = Modifier
        .fillMaxWidth()
        .heightIn(min = 40.dp),
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontSize = 21.sp,
            fontWeight = FontWeight.Normal,
            textAlign = TextAlign.Center
        ),
        text = annotatedString, onClick = {offset->
        annotatedString.getStringAnnotations(offset,offset)
            .firstOrNull()?.also {span->
                Log.d("ClickableTextComponent","{$span}")

                if((span.item == loginText)) {
                    onTextSelected(span.item)
                }
            }
    })
}

@Composable
fun UnderLinedTextComponent(value : String) {
    Text(
        text = value,
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = 40.dp),
        style = TextStyle(
            fontStyle = FontStyle.Normal,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal
        ),
        color = colorResource(R.color.gray),
        textAlign =  TextAlign.Center,
        textDecoration = TextDecoration.Underline
    )
}