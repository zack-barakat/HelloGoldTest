package com.android.hellogold.test.data.repositories

import android.content.Context
import com.android.hellogold.test.data.model.RegisterPayload
import com.android.hellogold.test.data.model.RegisterResponse
import com.android.hellogold.test.data.network.IApiHelper
import com.android.hellogold.test.data.prefs.IPreferencesHelper
import com.android.hellogold.test.di.qualifiers.ApplicationContext
import com.android.hellogold.test.di.scopes.ApplicationScope
import io.reactivex.Observable
import javax.inject.Inject

interface IUserRepository {
    fun registerUser(registerPayload: RegisterPayload): Observable<RegisterResponse>
}

@ApplicationScope
open class UserRepository @Inject constructor(
    @ApplicationContext private val mAppContext: Context,
    private val preferencesHelper: IPreferencesHelper,
    private val apiHelper: IApiHelper
) : IUserRepository {


    override fun registerUser(registerPayload: RegisterPayload): Observable<RegisterResponse> {
        return apiHelper.registerUser(registerPayload)
    }
}