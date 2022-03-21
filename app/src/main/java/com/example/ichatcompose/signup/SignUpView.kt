import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.provider.MediaStore
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import com.example.ichatcompose.R
import com.example.ichatcompose.signup.SignUpViewModel
import com.example.ichatcompose.ui.theme.backgroundColor
import com.example.ichatcompose.ui.theme.greenColor


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SignUpView(signUpViewModel: SignUpViewModel,register: ActivityResultLauncher<Intent>) {
    val bottomSheetScaffoldState = rememberBottomSheetScaffoldState(
        bottomSheetState = BottomSheetState(BottomSheetValue.Collapsed)
    )
    val coroutineScope = rememberCoroutineScope()
    BottomSheetScaffold(
        scaffoldState = bottomSheetScaffoldState,
        sheetContent = {
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(200.dp)
            ) {
                Text(text = "CONTEUDO BOTTOM SHEET")
            }
        }, sheetPeekHeight = 0.dp
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {

            profilePhoto(signUpViewModel, register = register)
            TextFields(signUpViewModel = signUpViewModel)
            Box(
                modifier = Modifier
                    .height(10.dp)
            )
            val loading = signUpViewModel.isLoading.observeAsState(initial = false)
            val error = signUpViewModel.errorText.observeAsState("")
            if (error.value.isNotEmpty()) Toast.makeText(
                LocalContext.current,
                error.value,
                Toast.LENGTH_LONG
            ).show()
            if (loading.value) CircularProgressIndicator()
            Buttons(signUpViewModel = signUpViewModel)


        }

    }
}





@Composable
fun profilePhoto(signUpViewModel: SignUpViewModel,register: ActivityResultLauncher<Intent>) {
    val profilePhoto = signUpViewModel.profilePhoto.observeAsState(initial = Uri.EMPTY)
    Box(modifier = Modifier
        .clip(CircleShape)
        .clickable {
            var gallery = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.INTERNAL_CONTENT_URI
            )

            register.launch(gallery)

        }) {
        if (profilePhoto.value.toString().isEmpty()) {
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_add_a_photo_24),
                contentDescription = "Profile Photo",
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .clip(
                        CircleShape
                    )
                    .background(greenColor)
                    .padding(40.dp)
            )
        } else {
            Image(
                painter = painterResource(id = R.drawable.chat_logo),
                contentDescription = "Logo",
                contentScale = ContentScale.FillWidth,
                modifier = Modifier
                    .padding(20.dp)
                    .border(width = 3.dp, color = Color.Gray)
            )
        }
    }


}

@Composable
fun TextFields(signUpViewModel: SignUpViewModel) {
    val email = signUpViewModel.email.observeAsState(initial = "")
    val password = signUpViewModel.password.observeAsState(initial = "")
    val name = signUpViewModel.name.observeAsState(initial = "")

    OutlinedTextField(
        value = name.value,
        onValueChange = {
            signUpViewModel.setName(it)
        },
        shape = RoundedCornerShape(24.dp),
        label = { Text("Name") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
    Box(
        modifier = Modifier
            .height(10.dp)
    )

    OutlinedTextField(
        value = email.value,
        onValueChange = {
            signUpViewModel.setEmail(it)
        },
        shape = RoundedCornerShape(24.dp),
        label = { Text("Email") },
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
            signUpViewModel.setPassword(it)
        },
        shape = RoundedCornerShape(24.dp),
        label = { Text("Senha") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp)
    )
}

@Composable
fun Buttons(signUpViewModel: SignUpViewModel) {
    Button(
        onClick = { signUpViewModel.signUp() },
        colors = ButtonDefaults.buttonColors(backgroundColor = greenColor),
        modifier = Modifier.imePadding()
    ) {
        Text(
            "CADASTRAR", style = TextStyle(color = Color.White)

        )
    }
}