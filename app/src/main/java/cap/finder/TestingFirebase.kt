package cap.finder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cap.finder.ui.theme.FinderTheme
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.random.Random


fun addRandomUserToDb(db: FirebaseFirestore){
	val interests = listOf("dogs", "cats", "beer", "sport")
	val user = hashMapOf(
		"user$rand" to interests
	)

	db.collection("tempUsers")
		.add(user)
		.addOnCompleteListener { result ->
			if (result.isSuccessful) {
				println("user added to db")
			} else {
				println("user not added to db")
			}
		}
}

fun registerRandomUser(auth: FirebaseAuth){
	auth.createUserWithEmailAndPassword("user$rand@user.com", "pass1234")
		.addOnCompleteListener { result ->
			if (result.isSuccessful) {
				println("registered user!")
			} else {
				println("registration failed")
			}
		}
}


@Composable
fun TestFinderApp(auth: FirebaseAuth, db: FirebaseFirestore) {
	FinderTheme {
		Surface(
			modifier = Modifier.fillMaxSize(),
			color = MaterialTheme.colors.background
		) {
			Column {
				Button(onClick = { registerRandomUser(auth) }) {
					Text(text = "Register new user")
				}
				Button(onClick = { addRandomUserToDb(db) }) {
					Text(text = "Add new user to firestore")
				}
			}
		}
	}
}


val rand = Random.nextInt(0,9999)