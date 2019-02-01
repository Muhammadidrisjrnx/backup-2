package com.example.rplrus021.midsemester12rpl

class data {
    private var tanggal: String? = null
    private var description: String? = null
    private var gambar: Int = 0
    private var judul: String? = null

    fun getJudul(): String? {
        return judul
    }

    fun setJudul(judul: String) {
        this.judul = judul
    }

    fun getGambar(): Int {
        return gambar
    }

    fun setGambar(gambar: Int) {
        this.gambar = gambar
    }

    fun getTanggal(): String? {
        return tanggal
    }

    fun setTanggal(tanggal: String) {
        this.tanggal = tanggal
    }

    fun getDescription(): String? {
        return description
    }

    fun setDescription(description: String) {
        this.description = description
    }

}