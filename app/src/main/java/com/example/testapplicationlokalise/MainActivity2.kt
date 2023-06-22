package com.example.testapplicationlokalise

import android.content.Context
import android.content.res.Configuration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.annotation.StringRes
import com.lokalise.sdk.Lokalise
import com.lokalise.sdk.LokaliseContextWrapper
import com.lokalise.sdk.LokaliseResources
import java.util.Locale

class MainActivity2 : AppCompatActivity() {

    private lateinit var title: TextView
    private lateinit var changeLocale: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = findViewById(R.id.title)
        changeLocale = findViewById(R.id.change_locale)
        title.setText(R.string.hello)
        changeLocale.setOnClickListener(View.OnClickListener {
            title.setText(getStringByLocale(this, Locale("nl"), R.string.hello))
        })
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(LokaliseContextWrapper.wrap(newBase!!));
    }

    // With this method, I want to get a string in the language I need,
    // despite the fact that ENG is specified in Lokalise,
    // I want to get what is specified in the locale variable (in this case, NL)
    fun getStringByLocale(
        context: Context,
        locale: Locale, // NL
        @StringRes stringRes: Int,
        vararg formatArgs: Any
    ): String {
        Lokalise.setLocale(locale.language)
        val test1 = context.resources.getString(
            stringRes,
            *formatArgs
        ) // I want to get "Hallo, dit is een test-app. NL", I get "Hello, this is a test app. ENG"

        Lokalise.setLocale(locale.language)
        val lokaliseResources = LokaliseResources(context)
        val test2 = lokaliseResources.getString(
            stringRes,
            *formatArgs
        ) // I want to get "Hallo, dit is een test-app. NL", I get "Hello, this is a test app. ENG"

        Lokalise.setLocale(locale.language)
        val configuration = Configuration(context.resources.configuration)
        configuration.setLocale(locale)
        val test3 = context.createConfigurationContext(configuration).resources.getString(
            stringRes,
            *formatArgs
        ) // I want to get "Hallo, dit is een test-app. NL", I get "Hello, this is a test app. ENG"

        return test3;
    }
}