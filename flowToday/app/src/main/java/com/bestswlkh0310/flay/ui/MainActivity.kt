package com.bestswlkh0310.flay.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.bestswlkh0310.flay.ui.theme.FlayTheme
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FlayTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val context = LocalContext.current
                    var account by remember { mutableStateOf(GoogleSignIn.getLastSignedInAccount(context)) }
                    var isLkh by remember { mutableStateOf((account?.email.toString() == "hhhello0507@gmail.com")) }
                    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).build()
                    val mGoogleSignInClient = LocalContext.current.let { GoogleSignIn.getClient(it, gso) }

                    if (!isLkh) {
                        mGoogleSignInClient.signOut()
                    }

                    if (account != null && isLkh) {
                        FlayApp()
                    } else {
                        OnBoardScreen() { text, it ->
                            isLkh = it
                            Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
                            account = GoogleSignIn.getLastSignedInAccount(context)
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DayPreview() {
    FlayTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FlayApp()
        }
    }
}


@Preview(showBackground = true)
@Composable
fun NightPreview() {
    FlayTheme(darkTheme = true) {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            FlayApp()
        }
    }
}