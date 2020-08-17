package com.hendra.myunittesting

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mockito.*

class MainViewModelTest {
    private lateinit var mainViewModel: MainViewModel
    private lateinit var cuboidModel: CuboidModel

    private val dummyLength: Double = 10.0
    private val dummyWidth: Double = 5.0
    private val dummyHeight: Double = 5.0

    private val dummyVolume: Double = 250.0
    private val dummySurfaceArea: Double = 40.0
    private val dummyCircumference: Double = 80.0

    @Before
    fun before() {
        cuboidModel = mock(CuboidModel::class.java)
        mainViewModel = MainViewModel(cuboidModel)
    }

    // Unit testing using JUnit
    @Test
    fun getVolume() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyLength, dummyWidth, dummyHeight)
        val volume = mainViewModel.getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
    }

    @Test
    fun getSurfaceArea() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyLength, dummyWidth, dummyHeight)
        val surfaceArea = mainViewModel.getSurfaceArea()
        assertEquals(dummySurfaceArea, surfaceArea, 0.0001)
    }

    // Unit testing using Mockito
    @Test
    fun getCircumference() {
        cuboidModel = CuboidModel()
        mainViewModel = MainViewModel(cuboidModel)
        mainViewModel.save(dummyLength, dummyWidth, dummyHeight)
        val circumFerence = mainViewModel.getCircumference()
        assertEquals(dummyCircumference, circumFerence, 0.0001)
    }

    @Test
    fun testMockVolume() {
        `when`(mainViewModel.getVolume()).thenReturn(dummyVolume)
        val volume = mainViewModel.getVolume()
        verify(cuboidModel).getVolume()
        assertEquals(dummyVolume, volume, 0.0001)
    }

    @Test
    fun testMockSurfaceArea() {
        `when`(mainViewModel.getSurfaceArea()).thenReturn(dummySurfaceArea)
        val surfaceArea = mainViewModel.getSurfaceArea()
        verify(cuboidModel).getSurfaceArea()
        assertEquals(dummySurfaceArea, surfaceArea, 0.0001)
    }

    @Test
    fun testMockCircumference() {
        `when`(mainViewModel.getCircumference()).thenReturn(dummyCircumference)
        val circumReference = mainViewModel.getCircumference()
        verify(cuboidModel).getCircumference()
        assertEquals(dummyCircumference, circumReference, 0.0001)
    }
}