
package id.technoit.dev.appiresto.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelData {

    @SerializedName("id_mobil")
    @Expose
    private String idMobil;

    @SerializedName("nama_mobil")
    @Expose
    private String nama;

    @SerializedName("harga_sewa")
    @Expose
    private String kelas_mhs;

    @SerializedName("gambar")
    @Expose
    private String gambar_mbl;

    public static final String id_mobil = "ID_MOBIL";
    public static final String nama_mahasiswa = "ID_MOBIL";
    public static final String jenis_mahasiswa = "ID_MOBIL";
    public static final String gambar = "ID_MOBIL";

    public ModelData(String id, String nama, String kelas_mhs, String gambar_mbl) {
        this.idMobil = id;
        this.nama = nama;
        this.kelas_mhs = kelas_mhs;
        this.gambar_mbl = gambar_mbl;
    }

    /**
     * 
     * @return
     *     The idMobil
     */
    public String getidMobil() {
        return idMobil;
    }

    /**
     * 
     * @param idMobil
     *     The ID_MOBIL
     */
    public void setidMobil(String idMobil) {
        this.idMobil = idMobil;
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
    public String getKelas_mhs() {
        return kelas_mhs;
    }

    /**
     * 
     * @param kelas_mhs
     *     The Jenis
     */
    public void setKelas_mhs(String kelas_mhs) {
        this.kelas_mhs = kelas_mhs;
    }
    /**
     *
     * @return
     *     The Gambar
     */
    public String getGambar_mbl() {
        return gambar_mbl;
    }

    /**
     *
     * @param gambar_mbl
     *     The Jenis
     */
    public void setGambar_mbl(String gambar_mbl) {
        this.gambar_mbl = gambar_mbl;
    }


}
