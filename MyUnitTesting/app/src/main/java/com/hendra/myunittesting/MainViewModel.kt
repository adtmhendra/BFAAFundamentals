package com.hendra.myunittesting

class MainViewModel (private val cuboidModel: CuboidModel) {

    fun save(l: Double, w: Double, h: Double) {
        cuboidModel.save(l, w, h)
    }

    fun getVolume(): Double = cuboidModel.getVolume()

    fun getSurfaceArea(): Double = cuboidModel.getSurfaceArea()

    fun getCircumference(): Double = cuboidModel.getCircumference()
}