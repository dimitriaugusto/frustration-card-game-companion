package com.dimiaug.frustration.common

import com.dimiaug.frustration.common.domain.interfaces.CommonSingletons
import org.koin.dsl.module

val appModule = module {
    single<CommonSingletons> {
        CommonSingletonsImpl()
    }
}