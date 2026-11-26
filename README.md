# Proyek 2 - Sistem Rekomendasi Pengambilan Mata Kuliah

Program Java yang menampilkan daftar mata kuliah yang bisa diambil di semester berikutnya
beserta alasan untuk mata kuliah yang belum bisa diambil.

## Anggota Kelompok
| No | Nama                      | NIM             |
|----|---------------------------|-----------------|
| 1  |  Senja Cahyo Tirta        | 255150200111016 |
| 2  |  Naufal Mohammad Adhi     | 255150207111018 |
| 3  |  Phaksi Giring Pamungkas  | 255150207111034 |

## Struktur Repository
```
src/       source code (Main, CourseGraph, MyList, MyMap, Course)
data/      matakuliah.csv, prasyarat.csv, mhs_01..03.txt
docs/      gambar graf prasyarat
Laporan_Proyek2_ASD.pdf
```

## Cara Menjalankan
```bash
javac -d out src/*.java
java -cp out Main data/matakuliah.csv data/prasyarat.csv data/mhs_01.txt
```

## Struktur Data (buatan sendiri)
- `MyList<T>`: array dinamis
- `MyMap<K,V>`: hash table (separate chaining)
- `CourseGraph`: graf berarah dengan adjacency list untuk relasi prasyarat

Detail struktur data dan algoritma ada di `Laporan_Proyek2_ASD.pdf`.
