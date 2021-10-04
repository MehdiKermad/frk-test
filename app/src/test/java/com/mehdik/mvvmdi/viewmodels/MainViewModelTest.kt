package com.mehdik.mvvmdi.viewmodels

import com.mehdik.mvvmdi.models.Dish
import com.mehdik.mvvmdi.models.PicsDiaporama
import com.mehdik.mvvmdi.models.Restaurant
import com.mehdik.mvvmdi.ui.main.MainViewModel
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.observers.TestObserver
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import java.util.concurrent.TimeUnit

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class MainViewModelTest {

    @Mock
    private lateinit var mainViewModel: MainViewModel
    private lateinit var testRestaurant: Restaurant
    private var dishList = mutableListOf<Dish>()

    @Before
    fun setUp() {

        //mocking viewModel
        mainViewModel = Mockito.mock(MainViewModel::class.java)

        //creating a dump object to mock the API call
        testRestaurant = Restaurant(
            avg_rate = 1.0,
            dishList = listOf(
                Dish(name = "dish1", price = 2),
                Dish(name = "dish2", price = 3),
                Dish(name = "dish3", price = 4)
            ),
            chef_name = "chef_name",
            min_price = 5,
            pics_diaporama = listOf(
                PicsDiaporama(url = "url1", label = "label1"),
                PicsDiaporama(url = "url2", label = "label2"),
                PicsDiaporama(url = "url3", label = null),
            ),
            rate_count = 6,
            speciality = "speciality"
        )

        //mocking the function to act the expected way
        Mockito.`when`(mainViewModel.getRestaurant(1884))
            .thenReturn(Observable.just(testRestaurant).map {
                dishList.clear()
                dishList.addAll(it.dishList)
                it
            })
    }

    @Test
    fun searchProductVMTest() {

        //tests getDishes() 1/2
        assertTrue(dishList.isEmpty())

        val searchProductsObservable = mainViewModel.getRestaurant(1884)
        val testObserver: TestObserver<Restaurant> = TestObserver()

        searchProductsObservable
            .subscribeWith(testObserver)
            .assertNoErrors()
            .await(5, TimeUnit.SECONDS)

        val restaurant = testObserver.values()[0]

        //testing datas first
        Restaurant(
            avg_rate = 1.0,
            dishList = listOf(
                Dish(name = "dish1", price = 2),
                Dish(name = "dish2", price = 3),
                Dish(name = "dish3", price = 4)
            ),
            chef_name = "chef_name",
            min_price = 5,
            pics_diaporama = listOf(
                PicsDiaporama(url = "url1", label = "label1"),
                PicsDiaporama(url = "url2", label = "label2"),
                PicsDiaporama(url = "url3", label = null),
            ),
            rate_count = 6,
            speciality = "speciality"
        )

        assertEquals(testRestaurant.avg_rate, restaurant.avg_rate, 0.0)
        assertEquals(testRestaurant.dishList.size, restaurant.dishList.size)
        assertEquals(testRestaurant.chef_name, restaurant.chef_name)
        assertEquals(testRestaurant.min_price, restaurant.min_price)
        assertEquals(testRestaurant.pics_diaporama.size, restaurant.pics_diaporama.size)
        assertEquals(testRestaurant.rate_count, restaurant.rate_count)
        assertEquals(testRestaurant.speciality, restaurant.speciality)

        //tests dishList
        for (i in testRestaurant.dishList.indices) {
            assertEquals(testRestaurant.dishList[i].name, restaurant.dishList[i].name)
            assertEquals(testRestaurant.dishList[i].price, restaurant.dishList[i].price)
        }

        //tests diaporamaList
        for (i in testRestaurant.pics_diaporama.indices) {
            assertEquals(testRestaurant.pics_diaporama[i].url, restaurant.pics_diaporama[i].url)
            assertEquals(testRestaurant.pics_diaporama[i].label, restaurant.pics_diaporama[i].label)
        }

        //tests getDishes() 2/2
        assertEquals(dishList.size, testRestaurant.dishList.size)
    }
}