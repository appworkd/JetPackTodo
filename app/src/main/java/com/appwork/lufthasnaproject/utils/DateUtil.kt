package com.appwork.lufthasnaproject.utils

import java.text.SimpleDateFormat
import java.util.*

object DateUtil {
    const val HH_MM_A = "hh:mm a" //The output will be -: 10:37 am

    const val H_MM_A = "h:mm a" //Output will be -: 10:37 am

    const val YYYY_MM_DD = "yyyy-MM-dd" //The output will be -: 2018-12-05

    const val DD_MMMM_YYYY = "dd-MMMM-yyyy" //The output will be -: 05-December-2018

    const val DD_MMMM_YYYY_ = "dd MMMM yyyy"  //The output will be -: 05 December 2018
    const val DD_MMM_YYYY_ = "dd MMM yyyy"  //The output will be -: 05 Dec 2018


    const val DD_MMMM_YYYY_ZZZZ = "dd MMMM yyyy zzzz" //The output will be -: 05 December 2018 UTC

    const val EEE_MMM_D_YY = "EEE, MMM d, ''yy" //The output will be -: Wed, Dec 5, '18

    const val YYYY_MM_DD_HH_MM_SS =
        "yyyy - MM - dd HH : mm : ss" //The Output will be -: 2018-12-05 10:37:43

    const val H_MM_A_DD_MMMM_YYYY =
        "h:mm a dd MMMM yyyy" //The output will be -: 10:37 am 05 December 2018

    const val K_MM_A_Z = "K:mm a, z" //The output will be-: 10:37 am, UTC

    const val HH_O_A_ZZZZ = " hh 'o''clock' a, zzzz" //The output will be -: 10 o'clock am, UTC

    const val YYYY_MM_DD_T_HH_MM_SS_SSS_Z =
        "yyyy - MM - dd'T'HH:mm:ss.SSS'Z'"//The output will be -: 2018-12-05T10:37:43.937Z

    const val E_DD_MMM_YYYY_HH_MM_SS_Z =
        "E, dd MMM yyyy HH:mm:ss z"//The output will be -: Wed, 05 Dec 2018 10:37:43 UTC

    const val YYYY_MM_DD_G_HH_MM_SS_Z =
        "yyyy.MM.dd G 'at' HH : mm : ss z"//The output will be -: 2018.12.05 AD at 10:37:43 UTC

    const val YYYY_MMMMM_DD_GGG_HH_MM_AAA =
        "yyyyy.MMMMM.dd GGG hh:mm aaa"//The output will be -: 02018.D.05 AD 10:37 am

    const val EEE_D_MMM_YYYY_HH_MM_SS_Z =
        "EEE, d MMM yyyy HH:mm:ss Z" //The output will be -: Wed, 5 Dec 2018 10:37:43 +0000

    const val YYYY_MM_DD_T_HH_MM_SS_SSSZ =
        "yyyy-MM-dd'T'HH:mm:ss.SSSZ"//The output will be -: 2018-12-05T10:37:43.946+0000
    const val YYYY_MM_DD_T_HH_MM_SS_SSSXXX =
        "yyyy-MM-dd'T'HH:mm:ss.SSSXXX" //The output will be -: 2018-12-05T10:37:43.949Z
    const val DD_MMM_YYYY = "dd-MMM-yyyy" //The output will be -: 05-Dec-2018

    fun getCurrentDate(pattern: String): String {
        val sdf = SimpleDateFormat(pattern, Locale.getDefault())
        sdf.timeZone = TimeZone.getTimeZone("UTC")
        return sdf.format(Calendar.getInstance().time)
    }

    fun getCurrentTimeInMillis(): Long {
        return Calendar.getInstance().timeInMillis
    }
}