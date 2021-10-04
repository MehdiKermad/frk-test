package com.mehdik.mvvmdi.mappers

import com.mehdik.mvvmdi.entities.DataEntity
import com.mehdik.mvvmdi.entities.PicsDiaporamaEntity
import com.mehdik.mvvmdi.entities.RestaurantEntity
import org.junit.Assert.assertEquals
import org.junit.Test

class RestaurantMapperTest {

    @Test
    fun mapFromEntityTest() {
        val entity = RestaurantEntity(
            data = DataEntity(
                avg_rate = 1.0,
                card_dessert_1 = "card_dessert_1",
                card_dessert_2 = "card_dessert_2",
                card_dessert_3 = "card_dessert_3",
                card_main_1 = "card_main_1",
                card_main_2 = "card_main_2",
                card_main_3 = "card_main_3",
                chef_name = "chef_name",
                min_price = 2,
                pics_diaporama = listOf(
                    PicsDiaporamaEntity(
                        `1350x759` = "url0",
                        `240x135` = "url1",
                        `480x270` = "url2",
                        `612x344` = "url3",
                        `664x374` = "url4",
                        label = "label"
                    )
                ),
                price_card_dessert_1 = 3,
                price_card_dessert_2 = 4,
                price_card_dessert_3 = 5,
                price_card_main_1 = 6,
                price_card_main_2 = 7,
                price_card_main_3 = 8,
                rate_count = 9,
                speciality = "speciality"
            ), result = 10, result_detail = "result_detail", result_msg = "result_msg"
        )
        val model = RestaurantMapper.mapFromEntity(entity)

        assertEquals(model.avg_rate, entity.data.avg_rate, 0.0)
        assertEquals(model.chef_name, entity.data.chef_name)
        assertEquals(model.min_price, entity.data.min_price)
        assertEquals(model.pics_diaporama.size, entity.data.pics_diaporama.size)
        assertEquals(model.rate_count, entity.data.rate_count)
        assertEquals(model.speciality, entity.data.speciality)

        //testing if all dishes are contained in List<Dish>
        val dishes = listOf(
            Pair(entity.data.card_main_1, entity.data.price_card_main_1),
            Pair(entity.data.card_main_2, entity.data.price_card_main_2),
            Pair(entity.data.card_main_3, entity.data.price_card_main_3),
            Pair(entity.data.card_dessert_1, entity.data.price_card_dessert_1),
            Pair(entity.data.card_dessert_2, entity.data.price_card_dessert_2),
            Pair(entity.data.card_dessert_3, entity.data.price_card_dessert_3)
        )

        assertEquals(model.dishList.size, dishes.size)

        for (i in dishes.indices) {
            assertEquals(model.dishList[i].name, dishes[i].first)
            assertEquals(model.dishList[i].price, dishes[i].second)
        }
    }

    @Test
    fun fromEntityListTest() {

        val entitiesList = listOf(
            RestaurantEntity(
                data = DataEntity(
                    avg_rate = 1.0,
                    card_dessert_1 = "card_dessert_1",
                    card_dessert_2 = "card_dessert_2",
                    card_dessert_3 = "card_dessert_3",
                    card_main_1 = "card_main_1",
                    card_main_2 = "card_main_2",
                    card_main_3 = "card_main_3",
                    chef_name = "chef_name",
                    min_price = 2,
                    pics_diaporama = listOf(
                        PicsDiaporamaEntity(
                            `1350x759` = "url0",
                            `240x135` = "url1",
                            `480x270` = "url2",
                            `612x344` = "url3",
                            `664x374` = "url4",
                            label = "label"
                        )
                    ),
                    price_card_dessert_1 = 3,
                    price_card_dessert_2 = 4,
                    price_card_dessert_3 = 5,
                    price_card_main_1 = 6,
                    price_card_main_2 = 7,
                    price_card_main_3 = 8,
                    rate_count = 9,
                    speciality = "speciality"
                ), result = 10, result_detail = "result_detail", result_msg = "result_msg"
            ),
            RestaurantEntity(
                data = DataEntity(
                    avg_rate = 1.0,
                    card_dessert_1 = "card_dessert_1",
                    card_dessert_2 = "card_dessert_2",
                    card_dessert_3 = "card_dessert_3",
                    card_main_1 = "card_main_1",
                    card_main_2 = "card_main_2",
                    card_main_3 = "card_main_3",
                    chef_name = "chef_name",
                    min_price = 2,
                    pics_diaporama = listOf(
                        PicsDiaporamaEntity(
                            `1350x759` = "url0",
                            `240x135` = "url1",
                            `480x270` = "url2",
                            `612x344` = "url3",
                            `664x374` = "url4",
                            label = "label"
                        )
                    ),
                    price_card_dessert_1 = 3,
                    price_card_dessert_2 = 4,
                    price_card_dessert_3 = 5,
                    price_card_main_1 = 6,
                    price_card_main_2 = 7,
                    price_card_main_3 = 8,
                    rate_count = 9,
                    speciality = "speciality"
                ), result = 10, result_detail = "result_detail", result_msg = "result_msg"
            ),
            RestaurantEntity(
                data = DataEntity(
                    avg_rate = 1.0,
                    card_dessert_1 = "card_dessert_1",
                    card_dessert_2 = "card_dessert_2",
                    card_dessert_3 = "card_dessert_3",
                    card_main_1 = "card_main_1",
                    card_main_2 = "card_main_2",
                    card_main_3 = "card_main_3",
                    chef_name = "chef_name",
                    min_price = 2,
                    pics_diaporama = listOf(
                        PicsDiaporamaEntity(
                            `1350x759` = "url0",
                            `240x135` = "url1",
                            `480x270` = "url2",
                            `612x344` = "url3",
                            `664x374` = "url4",
                            label = "label"
                        )
                    ),
                    price_card_dessert_1 = 3,
                    price_card_dessert_2 = 4,
                    price_card_dessert_3 = 5,
                    price_card_main_1 = 6,
                    price_card_main_2 = 7,
                    price_card_main_3 = 8,
                    rate_count = 9,
                    speciality = "speciality"
                ), result = 10, result_detail = "result_detail", result_msg = "result_msg"
            )
        )

        val modelsList = RestaurantMapper.fromEntityList(entitiesList)

        assertEquals(entitiesList.size, modelsList.size)

        for (i in modelsList.indices) {
            assertEquals(entitiesList[i].data.avg_rate, modelsList[i].avg_rate, 0.0)
            assertEquals(entitiesList[i].data.chef_name, modelsList[i].chef_name)
            assertEquals(entitiesList[i].data.min_price, modelsList[i].min_price)
            assertEquals(
                entitiesList[i].data.pics_diaporama.size,
                modelsList[i].pics_diaporama.size
            )
            assertEquals(entitiesList[i].data.rate_count, modelsList[i].rate_count)
            assertEquals(entitiesList[i].data.speciality, modelsList[i].speciality)
        }
    }
}