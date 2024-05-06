package com.example.loginpage



import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.loginpage.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var mainBinding: ActivityMainBinding
    lateinit var SharedPreferences: SharedPreferences

    var email:String? = null
    var password:String? =null
    var rememberMe: Boolean? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()


        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        var view = mainBinding.root
        setContentView(view)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        mainBinding.Register.setOnClickListener {
            SharedPreferences = this.getSharedPreferences("uservalues", MODE_PRIVATE)

            email = mainBinding.Email.text.toString()
            password = mainBinding.Password.text.toString()
            rememberMe = mainBinding.RememberMe.isChecked

            var editor = SharedPreferences.edit()
            editor.putString("email",email)
            editor.putString("password",password)
            editor.putBoolean("remember",rememberMe!!)

            editor.apply()
            Toast.makeText(applicationContext,"Data Saved",Toast.LENGTH_LONG
            ).show()

        }
        mainBinding.Login.setOnClickListener {
            email = SharedPreferences.getString("email","")
            password = SharedPreferences.getString("password","")
            if(email.toString()==mainBinding.Email.text.toString() && password.toString()==mainBinding.Password.text.toString()){
                var intent = Intent(this@MainActivity,RecyclerView::class.java)
                startActivity(intent)

            }
        }
    }

    override fun onPause() {
        SharedPreferences = this.getSharedPreferences("uservalues", MODE_PRIVATE)

        email = mainBinding.Email.text.toString()
        password = mainBinding.Password.text.toString()
        rememberMe = mainBinding.RememberMe.isChecked

        var editor = SharedPreferences.edit()
        editor.putString("email",email)
        editor.putString("password",password)
        editor.putBoolean("remember",rememberMe!!)

        editor.apply()
        Toast.makeText(applicationContext,"Data Saved",Toast.LENGTH_LONG
        ).show()
        super.onPause()
    }

    override fun onResume() {
        SharedPreferences = this.getSharedPreferences("uservalues", MODE_PRIVATE)
        email = SharedPreferences.getString("email","")
        password = SharedPreferences.getString("password","")
        rememberMe = SharedPreferences.getBoolean("remember",false)
        mainBinding.Email.setText(email)
        mainBinding.Password.setText(password)
        mainBinding.RememberMe.isChecked = rememberMe!!

        super.onResume()
    }
}