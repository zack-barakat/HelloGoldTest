package com.android.hellogold.test.data.repositories

import android.content.Context
import com.android.hellogold.test.data.model.SpotPriceResponse
import com.android.hellogold.test.data.network.IApiHelper
import com.android.hellogold.test.data.prefs.IPreferencesHelper
import com.android.hellogold.test.di.qualifiers.ApplicationContext
import com.android.hellogold.test.di.scopes.ApplicationScope
import io.reactivex.Observable
import javax.inject.Inject

interface IPriceRepository {
    fun getSpotPrice(): Observable<SpotPriceResponse>
}

@ApplicationScope
open class PriceRepository @Inject constructor(
    @ApplicationContext private val mAppContext: Context,
    private val preferencesHelper: IPreferencesHelper,
    private val apiHelper: IApiHelper
) : IPriceRepository {

    override fun getSpotPrice(): Observable<SpotPriceResponse> {
        return apiHelper.spotPrice
    }
}