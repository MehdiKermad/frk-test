package com.mehdik.mvvmdi.mappers

interface EntityMapper<Entity, Model> {
    fun mapFromEntity(entity: Entity): Model
}