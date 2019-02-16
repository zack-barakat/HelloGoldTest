package com.android.hellogold.test.data

import android.content.Context
import com.android.hellogold.test.data.repositories.IPriceRepository
import com.android.hellogold.test.data.repositories.IUserRepository
import com.android.hellogold.test.di.qualifiers.ApplicationContext
import com.android.hellogold.test.di.scopes.ApplicationScope
import javax.inject.Inject

interface IDataManager {
    @ApplicationContext
    fun getApplicationContext(): Context

    fun getAppErrorHelper(): IAppErrorHelper

    fun getUserRepository(): IUserRepository

    fun getPriceRepository(): IPriceRepository
}

@ApplicationScope
class DataManager @Inject
constructor(
    @ApplicationContext val mApplicationContext: Context,
    private val appErrorHelper: IAppErrorHelper,
    private val userRepository: IUserRepository,
    private val priceRepository: IPriceRepository
) :
    IDataManager {

    override fun getApplicationContext(): Context {
        return mApplicationContext
    }

    override fun getAppErrorHelper(): IAppErrorHelper {
        return appErrorHelper
    }

    override fun getUserRepository(): IUserRepository {
        return userRepository
    }

    override fun getPriceRepository(): IPriceRepository {
        return priceRepository
    }
}
