package com.mehdik.mvvmdi.mappers

import com.mehdik.mvvmdi.entities.PicsDiaporamaEntity
import org.junit.Assert.*

import org.junit.Test

class PicsDiaporamaMapperTest {

    @Test
    fun mapFromEntityTest() {

        val entity = PicsDiaporamaEntity(
            `1350x759` = "url0",
            `240x135` = "url1",
            `480x270` = "url2",
            `612x344` = "url3",
            `664x374` = "url4",
            label = "label"
        )
        val model = PicsDiaporamaMapper.mapFromEntity(entity)

        //tests we keep the biggest image size as expected
        assertEquals(entity.`1350x759`, model.url)
        assertEquals(entity.label, model.label)
    }

    @Test
    fun fromEntityListTest() {

        val entitiesList = listOf<PicsDiaporamaEntity>(
            PicsDiaporamaEntity(
                `1350x759` = "url0",
                `240x135` = "url1",
                `480x270` = "url2",
                `612x344` = "url3",
                `664x374` = "url4",
                label = "label0"
            ),
            PicsDiaporamaEntity(
                `1350x759` = "url5",
                `240x135` = "url6",
                `480x270` = "url7",
                `612x344` = "url8",
                `664x374` = "url9",
                label = "labe1"
            ),
            PicsDiaporamaEntity(
                `1350x759` = "url10",
                `240x135` = "url11",
                `480x270` = "url12",
                `612x344` = "url13",
                `664x374` = "url14",
                label = "label2"
            )
        )

        val modelsList = PicsDiaporamaMapper.fromEntityList(entitiesList)

        assertEquals(entitiesList.size, modelsList.size)

        for (i in modelsList.indices) {
            assertEquals(entitiesList[i].`1350x759`, modelsList[i].url)
            assertEquals(entitiesList[i].label, modelsList[i].label)
        }
    }
}