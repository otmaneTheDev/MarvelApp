package com.example.utils

import org.junit.Assert
import org.junit.Test

class UtilsKtTest {

    @Test
    fun testBuildImageURL() {
        val expected = "imageUrl/portrait_xlarge.png"
        val result = buildImageUrl("imageUrl", "png")

        Assert.assertEquals(expected, result)
    }
}