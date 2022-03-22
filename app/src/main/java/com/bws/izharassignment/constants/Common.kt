package com.bws.izharassignment.constants
import com.bws.izharassignment.response.CaseTimeSerese
import com.bws.izharassignment.response.StateWise
import com.bws.izharassignment.response.TestedData

object Common {
    val arrCases = ArrayList<CaseTimeSerese>()
    val arrDataStateWise = ArrayList<StateWise>()
    val arrTestData = ArrayList<TestedData>()
    var sourceURL = ""
    var pullToRefresh = false
}