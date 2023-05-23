class Salon {
    val namaSalon = "Flying Dutchman Men's Salon"
    var listTreatment : MutableList<Treatment> = mutableListOf()
    val listPilihanTreatment : MutableList<Treatment> = mutableListOf()
    var namaPelanggan = ""


    constructor(listTreatment: MutableList<Treatment>, namaPelanggan:String?){
        if (listTreatment != null) {
            this.listTreatment = listTreatment
        }
        if (namaPelanggan != null){
            this.namaPelanggan = namaPelanggan
        }
        println("Hi ${when{
            (this.namaPelanggan.isNotBlank()) -> "${this.namaPelanggan},"
            this.namaPelanggan.isBlank()    -> "Pelanggan,"
            else -> "SALAH INPUT"
        }
        } Selamat datang di ${this.namaSalon}, Selamat menikmati treatment")
    }


    fun lakukanPembelian(treatment: Treatment){
        this.listPilihanTreatment.add(treatment)
        println("Anda Memilih Treatment ${treatment.namaTreatment} dengan harga Rp.${treatment.hargaTreatment}")
    }

    fun selectDiscount(hargaTreatment : Float) : Float{
        var besarDiscount = 0F
        when{
            hargaTreatment < 100_000 -> besarDiscount = 0.05F
            hargaTreatment >= 100_000 && hargaTreatment < 200_000   -> besarDiscount = 0.10F
            hargaTreatment >= 200_000 && hargaTreatment <= 300_000   -> besarDiscount = 0.15F
            hargaTreatment > 300_000 -> besarDiscount = 0.20F
        }
        return besarDiscount
    }


    fun hitungHargaDiscount(hargaTreatment: Float, besarDiscount: Float) : Float{
        val hargaDiscount = hargaTreatment - (hargaTreatment*besarDiscount)
//        println("${when{
//            (this.namaPelanggan.isNotBlank()) -> "Hai ${this.namaPelanggan}, "
//            this.namaPelanggan.isBlank()    -> "Hai Pelanggan, "
//            else -> "SALAH INPUT"
//        }
//        }Jumlah yang dibayar adalah sebesar Rp.$hargaDiscount")
        return hargaDiscount
    }


    fun hitungTotalPembayaran():Float{
        println()
        println("|----------PERHITUNGAN TOTAL PEMBAYARAN-------------|")
        println("List treatment yang anda pilih : ")
        //print list pemilihan treatment
        printListTreatment(listPilihanTreatment)

        //total semua total pembayaran
        var totalPembayaran = 0F
        for (treatment in this.listPilihanTreatment){
            totalPembayaran += treatment.hargaTreatment
        }

        println("Total pembayaran = Rp.$totalPembayaran")
        val besarDiscount = this.selectDiscount(totalPembayaran)
        println("Anda mendapatkan diskon sebesar ${String.format("%.1f",besarDiscount*100)}%")
        val totalDiskon = hitungHargaDiscount(totalPembayaran, besarDiscount)
        println("Total pembayaran final yang sudah dikurangi diskon = Rp.$totalDiskon")
        return totalDiskon
    }

    fun printDiskon(){
        println(""" 
            Anda akan mendapat diskon jika total transaksi : 
            - dibawah Rp.100.000 , Diskon sebesar 5% 
            - diatas Rp.100.000 , Diskon sebesar 10% 
            - diatas Rp.200.000 , Diskon sebesar 15% 
            - diatas Rp.300.000 , Diskon sebesar 20% """.trimIndent())
        println()
    }

    //Fungsi ini digunakan untuk print list barang yang defaultnya print list barang jualan, dapat digunakan untuk print list pembelian
    fun printListTreatment(tempListTreatment: MutableList<Treatment> = listTreatment){
        val header = listOf("No.", "Nama Treatment", "Harga")

        // Determine column widths
        val numberColumnWidth = 3
        val nameColumnWidth = tempListTreatment.maxByOrNull { it.namaTreatment.length }?.namaTreatment?.length ?: header[1].length

        // Define column widths
        val columnWidths = listOf(
            numberColumnWidth,
            nameColumnWidth,
            13)

        // Print table header
        println("|${"-".repeat(columnWidths.sum() + columnWidths.size + 5)}|")
        println("| ${header.zip(columnWidths).joinToString(" | ") { (title, width) -> title.padEnd(width) }} |")
        println("|${"-".repeat(columnWidths.sum() + columnWidths.size + 5)}|")


        // Print table rows
        for ((index, row) in tempListTreatment.withIndex()) {
            val values = listOf((index + 1).toString(), row.namaTreatment, "Rp.${row.hargaTreatment}")
            println("| ${values.zip(columnWidths).joinToString(" | ") { (value, width) -> value.padEnd(width) }} |")
        }

        // Print table footer
        println("|${"-".repeat(columnWidths.sum() + columnWidths.size + 5)}|")
    }












}