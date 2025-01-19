package com.example.diablo


import LoginFragment
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.diablo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // İlk kez açıldığında LoginFragment veya RecyclerFragment gösterilecek
        if (savedInstanceState == null) {
            // Burada başarılı giriş yapıldıysa RecyclerFragment gösterilmeli
            val isUserLoggedIn = checkUserLoginStatus()  // Kullanıcının giriş yapıp yapmadığını kontrol et

            if (isUserLoggedIn) {
                showRecyclerFragment()  // Eğer giriş yapılmışsa yemek listesine yönlendir
            } else {
                showLoginFragment()  // Giriş yapılmamışsa login ekranını göster
            }
        }
    }

    // Kullanıcı giriş durumu kontrolü
    private fun checkUserLoginStatus(): Boolean {
        // Kullanıcının giriş yapıp yapmadığını kontrol et
        // Örneğin, SharedPreferences veya bir oturum kontrolü ile bu doğrulama yapılabilir.
        // Şu an için örnek olarak false döndürüyoruz.
        return false
    }

    // Giriş yapılmışsa RecyclerFragment'i göster
    private fun showRecyclerFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RecyclerFragment())  // RecyclerFragment'i gösterme
            .commit()
    }

    // LoginFragment'i göster
    private fun showLoginFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, LoginFragment())  // LoginFragment'i gösterme
            .commit()

    }

    // LoginFragment'inden başarılı giriş sonrası RecyclerFragment'e geçiş yapacak metod

    // Kayıt ekranına geçiş metodu
    fun switchToRegisterFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, RegisterFragment())  // RegisterFragment'i gösterme
            .addToBackStack(null)
            .commit()
    }
}
