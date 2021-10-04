package com.mehdik.mvvmdi

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.mehdik.mvvmdi.entities.DataEntity
import com.mehdik.mvvmdi.entities.RestaurantEntity
import com.mehdik.mvvmdi.utils.ApiService
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import io.reactivex.rxjava3.observers.TestObserver
import junit.framework.Assert.assertNotNull
import junit.framework.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.TimeUnit
import javax.inject.Inject

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class ApiServiceTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var apiService: ApiService

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun getRestaurantTest() {

        //tests a product detail API call
        val restaurantObservable = apiService.getRestaurant(1884)
        val testObserver: TestObserver<RestaurantEntity> = TestObserver()

        restaurantObservable
            .subscribeWith(testObserver)
            .assertNoErrors()
            .await(5, TimeUnit.SECONDS)

        val restaurant = testObserver.values()[0]

        RestaurantEntity(
            data = DataEntity(
                avg_rate = 0.0,
                card_dessert_1 = "",
                card_dessert_2 = "",
                card_dessert_3 = "",
                card_main_1 = "",
                card_main_2 = "",
                card_main_3 = "",
                chef_name = null,
                min_price = 0,
                pics_diaporama = listOf(),
                price_card_dessert_1 = 0,
                price_card_dessert_2 = 0,
                price_card_dessert_3 = 0,
                price_card_main_1 = 0,
                price_card_main_2 = 0,
                price_card_main_3 = 0,
                rate_count = 0,
                speciality = ""
            ), result = 0, result_detail = "", result_msg = ""
        )

        //tests data in RestaurantEntity object
        assertTrue(restaurant.result >= 0)
        assertNotNull(restaurant.result_detail)
        assertNotNull(restaurant.result_msg)

        //tests RestaurantEntity.data
        assertTrue(restaurant.data.avg_rate >= 0)
        assertTrue(restaurant.data.card_dessert_1.isNotBlank())
        assertTrue(restaurant.data.card_dessert_2.isNotBlank())
        assertTrue(restaurant.data.card_dessert_3.isNotBlank())
        assertTrue(restaurant.data.card_main_1.isNotBlank())
        assertTrue(restaurant.data.card_main_2.isNotBlank())
        assertTrue(restaurant.data.card_main_3.isNotBlank())
        //assertNotNull(restaurant.data.chef_name)
        assertTrue(restaurant.data.min_price > 0)
        assertTrue(restaurant.data.price_card_dessert_1 > 0)
        assertTrue(restaurant.data.price_card_dessert_2 > 0)
        assertTrue(restaurant.data.price_card_dessert_3 > 0)
        assertTrue(restaurant.data.price_card_main_1 > 0)
        assertTrue(restaurant.data.price_card_main_2 > 0)
        assertTrue(restaurant.data.price_card_main_3 > 0)
        assertTrue(restaurant.data.rate_count >= 0)
        assertTrue(restaurant.data.speciality.isNotBlank())
        assertTrue(restaurant.data.pics_diaporama.isNotEmpty())

        //testing diaporama images
        for (i in restaurant.data.pics_diaporama.indices) {
            assertTrue(restaurant.data.pics_diaporama[i].`1350x759`.isNotBlank())
            assertTrue(restaurant.data.pics_diaporama[i].`664x374`.isNotBlank())
            assertTrue(restaurant.data.pics_diaporama[i].`612x344`.isNotBlank())
            assertTrue(restaurant.data.pics_diaporama[i].`480x270`.isNotBlank())
            assertTrue(restaurant.data.pics_diaporama[i].`240x135`.isNotBlank())
            assertTrue(restaurant.data.pics_diaporama[i].label.isNotBlank())
        }
    }
}