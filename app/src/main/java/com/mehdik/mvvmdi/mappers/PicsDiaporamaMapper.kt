package com.mehdik.mvvmdi.mappers

import com.mehdik.mvvmdi.entities.PicsDiaporamaEntity
import com.mehdik.mvvmdi.models.PicsDiaporama

//transforms object received from API in a model for our purpose
object PicsDiaporamaMapper : EntityMapper<PicsDiaporamaEntity, PicsDiaporama> {

    //we keep only the largest img to display
    override fun mapFromEntity(entity: PicsDiaporamaEntity): PicsDiaporama {
        return PicsDiaporama(
            url = entity.`1350x759`, label = entity.label
        )
    }

    fun fromEntityList(initial: List<PicsDiaporamaEntity>): List<PicsDiaporama> {
        return initial.map { mapFromEntity(it) }
    }
}