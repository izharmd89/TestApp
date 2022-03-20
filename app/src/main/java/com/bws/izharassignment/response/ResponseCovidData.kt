package com.bws.izharassignment.response


data class CaseTimeSerese(
    val dailyconfirmed: String,
    val dailydeceased: String,
    val dailyrecovered: String,
    val date: String,
    val dateymd: String,
    val totalconfirmed: String,
    val totaldeceased: String,
    val totalrecovered: String
)

data class StateWise(
    val active: String,
    val confirmed: String,
    val deaths: String,
    val deltaconfirmed: String,
    val deltadeaths: String,
    val deltarecovered: String,
    val lastupdatedtime: String,
    val migratedother: String,
    val recovered: String,
    val state: String,
    val statecode: String,
    val statenotes: String
)

data class TestedData(
    val dailyrtpcrsamplescollectedicmrapplication: String,
    val firstdoseadministered: String,
    val frontlineworkersvaccinated1stdose: String,
    val frontlineworkersvaccinated2nddose: String,
    val healthcareworkersvaccinated1stdose: String,
    val healthcareworkersvaccinated2nddose: String,
    val over45years1stdose: String,
    val over45years2nddose: String,
    val over60years1stdose: String,
    val over60years2nddose: String,
    val positivecasesfromsamplesreported: String,
    val registration18_45years: String,
    val registrationabove45years: String,
    val registrationflwhcw: String,
    val registrationonline: String,
    val registrationonspot: String,
    val samplereportedtoday: String,
    val seconddoseadministered: String,
    val source: String,
    val source2: String,
    val source3: String,
    val source4: String,
    val testedasof: String,
    val testsconductedbyprivatelabs: String,
    val to60yearswithco_morbidities1stdose: String,
    val to60yearswithco_morbidities2nddose: String,
    val totaldosesadministered: String,
    val totaldosesavailablewithstates: String,
    val totaldosesavailablewithstatesprivatehospitals: String,
    val totaldosesinpipeline: String,
    val totaldosesprovidedtostatesuts: String,
    val totalindividualsregistered: String,
    val totalindividualstested: String,
    val totalpositivecases: String,
    val totalrtpcrsamplescollectedicmrapplication: String,
    val totalsamplestested: String,
    val totalsessionsconducted: String,
    val totalvaccineconsumptionincludingwastage: String,
    val updatetimestamp: String,
    val years1stdose: String,
    val years2nddose: String
)