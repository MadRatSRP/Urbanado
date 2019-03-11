package idd.urbanido.model

data class Jojo(
        val date_instruments: List<DateInstrument>,
        val result_str: String
) {
    data class DateInstrument(
            val avprice: Double,
            val created_at: String,
            val date: String,
            val financial_instrument_id: Int,
            val id: Int,
            val maxprice: Double,
            val minprice: Double,
            val updated_at: String
    )
}