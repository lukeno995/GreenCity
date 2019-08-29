package com.example.greencity.activity

import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.TextView
import com.example.greencity.ConnectionDBUtil
import com.example.greencity.NewDBUtil
import com.example.greencity.R
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import org.bson.Document


class MainActivity : AppCompatActivity() {

    private var textSignIn: TextView? = null
    private var btnLogin:Button? = null
    private var emailAccedi: EditText? = null
    private var passwordAccedi: EditText? = null
    private var layoutProgress: ConstraintLayout? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val client = Stitch.initializeDefaultAppClient("stitchapp-oeziy")
        client.auth.loginWithCredential(AnonymousCredential())
        val collection = ConnectionDBUtil.db

        this.emailAccedi = findViewById(R.id.email_user)
        this.passwordAccedi = findViewById(R.id.signin_password)
        this.textSignIn = findViewById(R.id.sign_in)
        this.btnLogin = findViewById(R.id.login_btn)
        this.layoutProgress = findViewById(R.id.wrapper_progress_circular_signin)
        this.progressBar = findViewById(R.id.progress_circular_signin)

        textSignIn?.setOnClickListener {
            val dbUtil = NewDBUtil()
            dbUtil.getListaRegioni(applicationContext, layoutProgress, progressBar)
        }

        btnLogin?.setOnClickListener {
            val iLogin = Intent(this, SplashGreenCity::class.java)
            val users = mutableListOf<Document>()
            //check if exists user on click registrati
            collection
                .find()
                .into(users)
                .addOnSuccessListener {
                    users.map {
                        users.forEach {
                            // Extract only the 'email' field
                            Log.i("LUCA2", it.getString("email").toString())
                            if(emailAccedi?.text.toString().trim()== it.getString("email")){
                                Log.i("Utente  registrato",emailAccedi?.text.toString())
                                startActivity(iLogin)
                            }else{
                                Log.i("Utente non registrato",emailAccedi?.text.toString())
                            }
                        }
                    }
                    // of each document
                }
            // More code here
                }
        }
}
