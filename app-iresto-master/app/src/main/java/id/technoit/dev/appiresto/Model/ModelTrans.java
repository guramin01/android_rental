package id.technoit.dev.appiresto.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelTrans {
    @SerializedName("id_transaksi")
    @Expose
    private String KD;

    @SerializedName("nama")
    @Expose
    private String nama;

    @SerializedName("total_harga")
    @Expose
    private String harga;

//    @SerializedName("gambar")
//    @Expose
//    private String gambar_mbl;

    public static final String id_transaksi1 = "KD";
    public static final String nama_mahasiswa = "KD";
    public static final String jenis_mahasiswa = "KD";
    public static final String gambar = "KD";

    public ModelTrans(String KD, String nama, String harga) {
        this.KD = KD;
        this.nama = nama;
        this.harga= harga;
//        this.gambar_mbl = gambar_mbl;
    }

    /**
     *
     * @return
     *     The idMobil
     */
    public String getKD() {
        return KD;
    }

    /**
     *
     * @param KD
     *     The ID_MOBIL
     */
    public void setKD(String KD) {
        this.KD= KD;
    }

    /**
     *
     * @return
     *     The nama
     */
    public String getNama() {
        return nama;
    }

    /**
     *
     * @param nama
     *     The Nama
     */
    public void setNama(String nama) {
        this.nama = nama;
    }

    /**
     *
     * @return
     *     The jenis
     */
    public String getHarga() {
        return harga;
    }

    /**
     *
     * @param harga
     *     The Jenis
     */
    public void setHarga(String harga) {
        this.harga= harga;
    }
    /**
     *
     * @return
     *     The Gambar
     */
//    public String getGambar_mbl() {
//        return gambar_mbl;
//    }
//
//    /**
//     *
//     * @param gambar_mbl
//     *     The Jenis
//     */
//    public void setGambar_mbl(String gambar_mbl) {
//        this.gambar_mbl = gambar_mbl;
//    }


}

