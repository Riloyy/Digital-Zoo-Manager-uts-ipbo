# Digital Zoo Manager

Aplikasi Java Swing untuk mengelola kebun binatang digital. Pengguna dapat membuat
hewan (`Mammal` / `Bird`), menyimpannya dalam memori, dan melihat info + suaranya
di panel log. Dibuat untuk memenuhi tugas UTS IPBO.

## Struktur Proyek

```text
uts/
├── src/
│   ├── Animal.java    # Superclass: name, age, makeSound(), getInfo()
│   ├── Mammal.java    # Subclass Animal: furColor (+ override makeSound/getInfo)
│   ├── Bird.java      # Subclass Animal: canFly (+ override makeSound/getInfo)
│   ├── ZooFrame.java  # JFrame utama: form input, tombol, log, ArrayList<Animal>
│   └── Main.java      # Entry point aplikasi
├── .idea/             # Konfigurasi proyek IntelliJ IDEA
├── uts.iml            # Module file IntelliJ IDEA
└── uts.pdf            # Soal tugas (tidak ikut ter-commit)
```

### Hierarki Kelas

```text
Animal (name, age)
├── Mammal (furColor)   → makeSound(): "The mammal makes a sound."
└── Bird (canFly)       → makeSound(): "The bird chirps."
```

Penyimpanan memakai `ArrayList<Animal>` sehingga satu list dapat menampung
objek `Mammal` maupun `Bird` (polimorfisme). Pemanggilan `getInfo()` dan
`makeSound()` diselesaikan secara dinamis sesuai tipe objek sebenarnya.

## Cara Menjalankan

### Via IntelliJ IDEA (disarankan)

1. Buka IntelliJ IDEA → **Open** → pilih folder proyek ini (`uts/`).
2. Pastikan JDK 17+ sudah dikonfigurasi (Project Structure → SDK).
3. Buka `src/Main.java` → klik **Run**.

### Via terminal (`javac`/`java`, JDK 17+)

```bash
# dari dalam folder uts/
javac -d out src/*.java
java -cp out Main
```

## Cara Memakai

1. Isi **Name** dan **Age**.
2. Pilih **Animal Type**: `Mammal` atau `Bird`.
3. Isi atribut khusus: **Fur Color** (Mammal) atau centang **Can Fly?** (Bird).
4. Klik **Add Animal** → hewan masuk ke daftar dan muncul di **Zoo Log**.

Contoh isi log:

```text
Added a new Mammal! Info: Name: Leo, Age: 5, Fur Color: Golden. Sound: The mammal makes a sound. (Total animals: 1)
Added a new Bird! Info: Name: Tweety, Age: 2, Can Fly: Yes. Sound: The bird chirps. (Total animals: 2)
```

## Validasi Input

- Nama tidak boleh kosong.
- Age harus bilangan bulat ≥ 0.
- Fur Color wajib diisi untuk Mammal.
- Input yang salah menampilkan dialog error (`JOptionPane`) dan hewan tidak ditambahkan.
