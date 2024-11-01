import java.util.Scanner;
import java.util.ArrayList;

// Kelas abstrak Orang (abstraksi)
// untuk menyembunyikan beberapa detail dan hanya menampilkan informasi yang esensial kepada user 
// dan abstract class tidak  bisa digunakan untuk membuat objek dan harus digunakan melalui subclass
abstract class Orang { 

    protected String nama;
    protected int umur;

    // Konstruktor
    public Orang(String nama, int umur) {
        this.nama = nama;
        this.umur = umur;
    }

    // Metode abstrak
    public abstract void tampilkanInfo();
}

// Kelas Anggota extends Orang (inheritance, encapsulasi)
class Anggota extends Orang {
    private String idAnggota;

    public Anggota(String nama, int umur, String idAnggota) {
        super(nama, umur); //super keyword berfungsi untuk mereferensi class Orang
        this.idAnggota = idAnggota;
    }

    // Metode untuk menampilkan informasi anggota (polimorfisme)
    @Override// digunakan ketika nama method yang ada pada subclass sama dengan nama method yang ada pada superclass
    public void tampilkanInfo() { 
        System.out.println("Nama Anggota: " + nama + ", Umur: " + umur + ", ID Anggota: " + idAnggota);
    }

    public String getIdAnggota() {
        return idAnggota;
    }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }
}

// Kelas Buku dengan metode untuk mengelola buku
class Buku {
    private String judulBuku;
    private String penulis;

    public Buku(String judulBuku, String penulis) {
        this.judulBuku = judulBuku;
        this.penulis = penulis;
    }

    public void tampilkanInfoBuku() {
        System.out.println("Judul Buku: " + judulBuku + ", Penulis: " + penulis);
    }

    public String getJudulBuku() {
        return judulBuku;
    }

    public String getPenulis() {
        return penulis;
    }
}

// Kelas PeminjamanBuku dengan metode untuk peminjaman buku
class PeminjamanBuku {
    private Anggota anggota;
    private Buku buku;

    public PeminjamanBuku(Anggota anggota, Buku buku) {
        this.anggota = anggota;
        this.buku = buku;
    }

    public void pinjamBuku() {
        System.out.println(anggota.getIdAnggota() + " meminjam buku dengan judul: " + buku.getJudulBuku());
    }

    public void kembalikanBuku() {
        System.out.println(anggota.getIdAnggota() + " mengembalikan buku dengan judul: " + buku.getJudulBuku());
    }
}

// Kelas DataPegawai untuk mengelola data pegawai
class DataPegawai {
    private String namaPegawai;
    private String idPegawai;

    public DataPegawai(String namaPegawai, String idPegawai) {
        this.namaPegawai = namaPegawai;
        this.idPegawai = idPegawai;
    }

    public void tampilkanInfoPegawai() {
        System.out.println("Nama Pegawai: " + namaPegawai + ", ID Pegawai: " + idPegawai);
    }

    public String getIdPegawai() {
        return idPegawai;
    }

    public String getNamaPegawai() {
        return namaPegawai;
    }
}

// Kelas utama
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Anggota> anggotaList = new ArrayList<>();
        ArrayList<Buku> bukuList = new ArrayList<>();
        ArrayList<DataPegawai> pegawaiList = new ArrayList<>();

        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Tambah Anggota");
            System.out.println("2. Tambah Buku");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Tambah Pegawai");
            System.out.println("5. Keluar");
            System.out.print("Pilih opsi: ");
            int pilihan = scanner.nextInt();
            scanner.nextLine();  // Konsumsi baris baru

            switch (pilihan) {
                case 1:
                    System.out.print("Masukkan nama anggota: ");
                    String namaAnggota = scanner.nextLine();
                    System.out.print("Masukkan umur anggota: ");
                    int umurAnggota = scanner.nextInt();
                    System.out.print("Masukkan ID anggota: ");
                    String idAnggota = scanner.next();
                    anggotaList.add(new Anggota(namaAnggota, umurAnggota, idAnggota));
                    System.out.println("Anggota berhasil ditambahkan.");
                    break;

                case 2:
                    System.out.print("Masukkan judul buku: ");
                    String judulBuku = scanner.nextLine();
                    System.out.print("Masukkan nama penulis: ");
                    String penulis = scanner.nextLine();
                    bukuList.add(new Buku(judulBuku, penulis));
                    System.out.println("Buku berhasil ditambahkan.");
                    break;

                case 3:
                    if (anggotaList.isEmpty()) {
                        System.out.println("Belum ada anggota. Silakan tambah anggota terlebih dahulu.");
                        break;
                    }
                    if (bukuList.isEmpty()) {
                        System.out.println("Belum ada buku. Silakan tambah buku terlebih dahulu.");
                        break;
                    }

                    System.out.println("Pilih anggota berdasarkan indeks: ");
                    for (int i = 0; i < anggotaList.size(); i++) {
                        System.out.println((i + 1) + ". " + anggotaList.get(i).getIdAnggota());
                    }
                    int indeksAnggota = scanner.nextInt() - 1;

                    if (indeksAnggota < 0 || indeksAnggota >= anggotaList.size()) {
                        System.out.println("Indeks anggota tidak valid.");
                        break;
                    }

                    System.out.println("Pilih buku berdasarkan indeks: ");
                    for (int i = 0; i < bukuList.size(); i++) {
                        System.out.println((i + 1) + ". " + bukuList.get(i).getJudulBuku());
                    }
                    int indeksBuku = scanner.nextInt() - 1;

                    if (indeksBuku < 0 || indeksBuku >= bukuList.size()) {
                        System.out.println("Indeks buku tidak valid.");
                        break;
                    }

                    PeminjamanBuku peminjaman = new PeminjamanBuku(anggotaList.get(indeksAnggota), bukuList.get(indeksBuku));
                    peminjaman.pinjamBuku();
                    break;

                case 4:
                    System.out.print("Masukkan nama pegawai: ");
                    String namaPegawai = scanner.nextLine();
                    System.out.print("Masukkan ID pegawai: ");
                    String idPegawai = scanner.nextLine();
                    pegawaiList.add(new DataPegawai(namaPegawai, idPegawai));
                    System.out.println("Pegawai berhasil ditambahkan.");
                    break;

                case 5:
                    System.out.println("Keluar dari program...");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Pilihan tidak valid. Silakan coba lagi.");
            }
        }
    }
}
