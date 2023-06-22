package com.example.testapplicationlokalise;

import android.app.Application;

import com.lokalise.sdk.Lokalise;

import java.util.Locale;

public class CustomApplication
  extends Application {

  @Override
  public void onCreate() {
    super.onCreate();
    Lokalise.init(this, "6fe2b3ff9cec7d9b022f1d4a43a0af66af3f", "58830419647dd56e247df6.06909429");
    // Fetch the latest translations from Lokalise (can be called anywhere)
    Lokalise.updateTranslations();
    Lokalise.setLocale(Locale.ENGLISH.getLanguage());
  }
}
