package com.android.hellogold.test.data.prefs

import android.content.Context
import android.content.SharedPreferences
import com.android.hellogold.test.di.qualifiers.ApplicationContext
import com.android.hellogold.test.di.scopes.ApplicationScope
import javax.inject.Inject

interface IPreferencesHelper {

    fun getEmail(): String?

    fun setEmail(email: String?)

    fun getApiToken(): String?

    fun setApiToken(token: String?)

    fun getApiKey(): String?

    fun setApiKey(apiKey: String?)

    fun setUserLoggedIn(loggedIn: Boolean)

    fun isUserLoggedIn(): Boolean
}

@ApplicationScope
class AppPreferencesHelper @Inject
constructor(@ApplicationContext context: Context) : IPreferencesHelper {

    companion object {
        private const val PREF_FILE_NAME = "HelloGold"
        private const val PREF_KEY_EMAIL = "KEY_EMAIL"
        private const val PREF_KEY_API_TOKEN = "KEY_API_TOKEN"
        private const val PREF_KEY_API_KEY = "KEY_API_KEY"
        private const val PREF_KEY_IS_LOGGED_IN = "KEY_IS_LOGGED_IN"
    }

    private val mSharedPreferences: SharedPreferences
    private val mPreferenceEditors
        get() = mSharedPreferences.edit()

    init {
        mSharedPreferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE)
    }

    override fun getEmail(): String? {
        return mSharedPreferences.getString(PREF_KEY_EMAIL, null)
    }

    override fun setEmail(email: String?) {
        mPreferenceEditors.putString(PREF_KEY_EMAIL, email).apply()
    }

    override fun getApiToken(): String? {
        return mSharedPreferences.getString(PREF_KEY_API_TOKEN, null)
    }

    override fun setApiToken(token: String?) {
        mPreferenceEditors.putString(PREF_KEY_API_TOKEN, token).apply()
    }

    override fun getApiKey(): String? {
        return mSharedPreferences.getString(PREF_KEY_API_KEY, null)
    }

    override fun setApiKey(apiKey: String?) {
        mPreferenceEditors.putString(PREF_KEY_API_KEY, apiKey).apply()
    }

    override fun isUserLoggedIn(): Boolean {
        return mSharedPreferences.getBoolean(PREF_KEY_IS_LOGGED_IN, false)
    }

    override fun setUserLoggedIn(loggedIn: Boolean) {
        mPreferenceEditors.putBoolean(PREF_KEY_IS_LOGGED_IN, loggedIn).apply()
    }
}
