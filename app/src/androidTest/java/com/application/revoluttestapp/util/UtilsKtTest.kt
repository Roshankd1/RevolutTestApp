package com.application.revoluttestapp.util

import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class UtilsKtTest {

    @Test
    fun toFloat() {
        val stringOne = "0.899"
        val stringTwo = "."
        val stringThree = ""
        val floatV1 = stringOne.toFloat()
        val floatV2 = stringTwo.toFloat()
        val floatV3 = stringThree.toFloat()
        assertEquals("equal", 0.899F, floatV1)
        assertEquals("equal", 0F, floatV2)
        assertEquals("equal", 0F, floatV3)
    }

    @Test
    fun format() {
        val number = 89.90999F
        val number2 = .99F
        val number3 = 0F
        val result: String = (number).format()
        val result2: String = (number2).format()
        val result3: String = (number3).format()
        MatcherAssert.assertThat(result, CoreMatchers.`is`("89.91"))
        MatcherAssert.assertThat(result2, CoreMatchers.`is`("0.99"))
        MatcherAssert.assertThat(result3, CoreMatchers.`is`("0.00"))
    }
}