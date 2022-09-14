package cap.finder

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : ComponentActivity() {
	private lateinit var auth: FirebaseAuth
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		auth = Firebase.auth
		val db = Firebase.firestore
		setContent {
			TestFinderApp(auth, db)
		}
	}

	override fun onStart() {
		super.onStart()
		val currentUser = auth.currentUser
		if (currentUser != null) {
			println("user logged in!")
		} else {
			println("user not logged in")
		}
	}
}
