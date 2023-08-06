package com.bestswlkh0310.flay.ui

import android.app.Activity
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ButtonElevation
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bestswlkh0310.flay.R
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

@Composable
fun OnBoardScreen(
    callback: (String, Boolean) -> Unit
) {
    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
        .requestEmail()
        .build()
    val mGoogleSignInClient = LocalContext.current.let { GoogleSignIn.getClient(it, gso) }

    fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            val email = account?.email.toString()
            val familyName = account?.familyName.toString()
            if (email == "hhhello0507@gmail.com") {
                callback("반가워요 이강현님", true)
            } else {
                callback("이강현이 아닙니당!!! 당신의 휴대폰은 30초 이내로 폭파합니다!!", false)
            }
        } catch (e: ApiException){
            Log.w("failed", "signInResult:failed code=" + e.statusCode)
            callback("이강현이 아닙니다!!! 당신의 휴대폰은 30초 이내로 폭파합니다!!", false)
        }
    }

    val startForResult =
        rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            Log.d("TAG", " - OnBoardScreen() called")
            if (result.resultCode == Activity.RESULT_OK) {
                val intent = result.data
                if (result.data != null) {
                    val task: Task<GoogleSignInAccount> =
                        GoogleSignIn.getSignedInAccountFromIntent(intent)
                    handleSignInResult(task)
                }
            }
        }
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = {
                val signInIntent = mGoogleSignInClient.signInIntent
                startForResult.launch(signInIntent)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(start = 16.dp, end = 16.dp),
            shape = RoundedCornerShape(50),
            elevation = ButtonDefaults.buttonElevation(4.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_google_logo),
                contentDescription = "",
                Modifier.size(20.dp)
            )
            FlayText(text = "구글 계정으로 이강현인지 인증하기", modifier = Modifier.padding(6.dp))
        }
    }
}