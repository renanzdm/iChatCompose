import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.example.ichatcompose.R
import com.example.ichatcompose.main.navigation.Router
import com.example.ichatcompose.signup.SignUpViewModel
import com.example.ichatcompose.ui.theme.backgroundColor
import com.example.ichatcompose.ui.theme.greenColor


@Composable
fun SignUpView(signUpViewModel: SignUpViewModel, router: Router<*>) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .background(color = backgroundColor)
                .fillMaxWidth()
                .fillMaxHeight()
                .verticalScroll(rememberScrollState())
        ) {
            profilePhoto(signUpViewModel)
            TextFields(signUpViewModel = signUpViewModel)
            Box(
                modifier = Modifier
                    .height(10.dp)
            )
            val loading = signUpViewModel.isLoading.collectAsState(initial = false)
            val error = signUpViewModel.errorText.collectAsState("")
            if (error.value.isNotEmpty()) Toast.makeText(
                LocalContext.current,
                error.value,
                Toast.LENGTH_LONG
            ).show()
            if (loading.value) CircularProgressIndicator()
            Buttons(signUpViewModel = signUpViewModel)


        }


}


@Composable
fun profilePhoto(signUpViewModel: SignUpViewModel) {
    var myImage = signUpViewModel.profilePhoto.collectAsState(Uri.EMPTY)
    val EMPTY_IMAGE_URI: Uri = Uri.parse("file://dev/null")
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            signUpViewModel.setProfilePhoto(uri ?: EMPTY_IMAGE_URI)
        })
    Box(modifier = Modifier
        .height(200.dp)
        .width(200.dp)
        .clip(CircleShape)
        .clickable {
        launcher.launch("image/*")
        }) {
        if (myImage.value.path?.isNotEmpty() == true) Image(
            painter = rememberImagePainter(myImage.value),
            contentDescription = "Profile Photo",
             contentScale = ContentScale.Crop,
                modifier = Modifier
                .background(greenColor)


        ) else Image(
            painter = painterResource(id = R.drawable.ic_baseline_add_a_photo_24),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxSize()
                .clip(CircleShape)
                .background(greenColor)
                .padding(32.dp)
        )

    }


}

@Composable
fun TextFields(signUpViewModel: SignUpViewModel) {
    val email = signUpViewModel.email.collectAsState(initial = "")
    val password = signUpViewModel.password.collectAsState(initial = "")
    val name = signUpViewModel.name.collectAsState(initial = "")

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