data class Treatment(var namaTreatment: String, var deskripsiTreatment: String, var hargaTreatment: Int)

fun insertNama () : String{
    print("Masukan Nama Anda : ")
    val nama = readln()
//    println("Halo $nama, selamat datang di toko ini")
    return nama
}

fun printMenu(){
    println("""
        Apakah anda yakin dengan transaksi Anda? 
        Pilihan : 
        1. Anda Ingin Menambahkan Treatment
        2. Anda Ingin Mengulang Pemilihan Treatment
        3. Anda Ingin Membatalkan Transaksi Anda dan Keluar
        4. Anda Ingin Melanjutkan Transaksi dan Melakukan Pembayaran
    """.trimIndent())
}
fun main(args: Array<String>) {
    println("Memaasukan treatment")
    //Ceritanya treatment2nya dulu ke salon
    val treatment1 = Treatment("Potong Rambut", "", 50_000)
    val treatment2 = Treatment("Warnai Rambut", "", 35_000)
    val treatment3 = Treatment("Potong Kumis dan Jenggot", "", 25_000)
    val treatment4 = Treatment("Perawatan Wajah Kepala", "", 50_000)
    val treatment5 = Treatment("Gentlemen Grooming & Hairspa", "", 80_000)
    val treatment6 = Treatment("Grooming + Hair Tattoo ", "", 130_000)


    val listTreatment : MutableList<Treatment> = mutableListOf(treatment1,treatment2,treatment3, treatment4, treatment5, treatment6)
    println("")

    println("---------------------APLIKASI MEN'S SALON----------------------")
    println("------------------BY CIKAL, JAE, KEI, & BAGAS -----------------")

    //Instantiate Objek salon dari class Salon
    val namaPelanggan = insertNama()
//    print("Masukan Nomor Telpon : ")
//    val nomorTelpon = readln()!!.toInt()

    val salon = Salon(listTreatment, namaPelanggan)


    println("---------------------PILIHAN TREATMENT----------------------")
    salon.printListTreatment()
    salon.printDiskon()

    //Ini cara yg jumlah treatmentnya di pilih dari awal
//    print("Masukan jumlah treatment yang ingin dibeli : ")
//    val jumlahTreatment = readln().toInt()
//
//    for (value in 1..jumlahTreatment){
//        var pilihanTreatment : Int
//        do {
//            print("Masukan pilihan treatment ke-$value (Pilih : 1-${salon.listTreatment.size}): ")
//            pilihanTreatment = readln().toInt()
//            if (pilihanTreatment !in 1..salon.listTreatment.size){
//                println("Pilihan anda salah, silahkan ulagi lagi")
//            }
//        }while (pilihanTreatment !in 1..salon.listTreatment.size)
//        salon.lakukanPembelian(salon.listTreatment[pilihanTreatment-1]) //pilihan barang -1 karena index dari 0.
//    }


    //Cara yang treatmentnya ada pilihan ditambah atau tidak
    var treatmentCounter : Int = 1
    do {
        var pilihanTreatment : Int
        do {
            print("Masukan pilihan treatment ke-$treatmentCounter (Pilih : 1-${salon.listTreatment.size}): ")
            pilihanTreatment = readln().toInt()
            if (pilihanTreatment !in 1..salon.listTreatment.size){
                println("Pilihan anda salah, silahkan ulagi lagi")
            }
        }while (pilihanTreatment !in 1..salon.listTreatment.size)
        salon.lakukanPembelian(salon.listTreatment[pilihanTreatment-1]) //pilihan barang -1 karena index dari 0.


        treatmentCounter++
        println()
        println("---------------PILIHAN TRANSAKSI SEMENTARA------------------")
        salon.printListTreatment(salon.listPilihanTreatment)
        printMenu()
        //Salah input disini belum dikerjain
        print("Masukan Pilihan (1-4) : ")
        val pilihanMenu : Int = readln().toInt()
        when(pilihanMenu){
            1 -> continue // Menambahkan Treatment
            2 -> {//Mengulang Pemilihan Treatment
                println("Anda Memilih Untuk Mengulang Pilihan Treatment")
                salon.listPilihanTreatment.clear()
                treatmentCounter = 1
                continue
            }
            3 -> {//Membatalkan Transaksi
                println("Anda Memilih Untuk Membatalkan Transaksi")
                salon.listPilihanTreatment.clear()
                break
            }

            4 -> {//Melanjutkan Transaksi
                println("Anda Memilih Untuk Melanjutkan Transaksi")
                val totalPembayaran = salon.hitungTotalPembayaran()
                print("Masukan Uang Pembayaran : Rp.") //Salah Input disini belum dikerjain
                val inputPembayaran = readln().toInt()
                println("Anda Memasukan uang sebesar Rp.$inputPembayaran")
                val kembalian = inputPembayaran - totalPembayaran
                println("Kembalian yang didapat : Rp.$inputPembayaran - Rp.$totalPembayaran = Rp.$kembalian")
                break
            }
        }
        println()
    }while (true)

//    val totalPembayaran = salon.hitungTotalPembayaran()
//    println("Total Pembayaran yang harus dilakukan = Rp.$totalPembayaran")
    println("Terima Kasih telah mengunjungi ${salon.namaSalon}")

}