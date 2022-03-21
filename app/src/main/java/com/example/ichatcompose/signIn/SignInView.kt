package com.example.ichatcompose.signIn

import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.ScrollableState
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import com.example.ichatcompose.R
import com.example.ichatcompose.ui.theme.backgroundColor
import com.example.ichatcompose.ui.theme.greenColor
import com.google.accompanist.insets.ExperimentalAnimatedInsets
import com.google.accompanist.insets.navigationBarsWithImePadding
import com.google.accompanist.insets.rememberImeNestedScrollConnection
import kotlin.math.sign


@Composable
fun SignInView(signInViewModel: SignInViewModel, navController: NavHostController) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .background(color = backgroundColor)
            .fillMaxHeight()
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
    ) {
        LogoImage()
        TextFields(signInViewModel = signInViewModel)
        Box(
            modifier = Modifier
                .height(10.dp)
        )
        Buttons(navController = navController)
    }

}


@Composable
fun LogoImage() {
    val image: Painter = painterResource(id = R.drawable.chat_logo)
    Image(
        painter = image,
        contentDescription = "Logo",
        contentScale = ContentScale.FillWidth,
        modifier = Modifier.padding(20.dp)
    )
}

@Composable
fun TextFields(signInViewModel: SignInViewModel) {
    val email = signInViewModel.email.observeAsState("")
    val password = signInViewModel.password.observeAsState("")
    OutlinedTextField(
        value = email.value,
        onValueChange = {
            signInViewModel.setEmail(it)
        },
        shape = RoundedCornerShape(24.dp),
        label = { Text(text = "Email") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
    )
    OutlinedTextField(
        value = password.value,
        onValueChange = {
            signInViewModel.setPassword(it)
        },
        shape = RoundedCornerShape(24.dp),
        label = { Text(text = "Senha") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )

}

@Composable
fun Buttons(navController: NavHostController) {
    Button(
        onClick = { /*TODO*/ },
        colors = ButtonDefaults.buttonColors(backgroundColor = greenColor)
    ) {
        Text("ENTRAR", style = TextStyle(color = Color.White))
    }
    TextButton(
        onClick = { navController.navigate("sign_up") },
        modifier = Modifier.imePadding()
    ) {
        Text(
            "Não tem conta ainda? Faça seu cadastro",
            style = TextStyle(color = Color.Black)
        )
    }
}



