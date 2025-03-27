TUGAS BESAR PBO

Task Player adalah aplikasi web yang menggabungkan manajemen tugas dengan gamifikasi untuk meningkatkan motivasi pengguna. Pengguna dapat membuat, memantau, dan menyelesaikan tugas. Aplikasi ini menyediakan sistem poin untuk memotivasi pengguna, serta dashboard untuk melacak kemajuan dan statistik tugas yang telah diselesaikan. 
Task Player bertujuan untuk membantu pengguna menjadi lebih produktif dan termotivasi dalam menyelesaikan tugas sehari-hari.


Arsitektur & Alur Kerja

PhpMyAdmin Database MySQL: Menyimpan data pengguna, tugas, statistik, dan gamifikasi. Relasi antar tabel menggunakan foreign key untuk menjaga konsistensi data.
Back-End (Java): Menggunakan Java Springboot untuk menyediakan REST API bagi operasi CRUD (Create, Read, Update, Delete) yang berkaitan dengan tugas dan pengguna.
Front-End (HTML, CSS): Menggunakan HTML untuk menampilkan data dinamis dari server dan CSS untuk tampilan responsif. Setiap halaman akan berfungsi untuk menampilkan data dari backend dan memungkinkan pengguna berinteraksi dengan aplikasi (seperti mendaftar, login, dan menambahkan tugas).


FITUR UTAMA
1.  Manajemen Akun
Fitur manajemen akun memungkinkan pengguna untuk mengelola profil dan akses mereka ke aplikasi:
Registrasi & Login: Pengguna dapat mendaftar dan membuat akun dengan informasi dasar seperti nama pengguna, email, dan kata sandi. Setelah terdaftar, mereka bisa masuk ke sistem untuk mengakses fungsionalitas aplikasi.
2.  Manajemen Tugas
Manajemen tugas berfokus pada pembuatan, pemantauan, dan penyelesaian tugas yang harus diselesaikan oleh user:
Pembuatan Tugas: Pengguna dapat membuat tugas baru yang dapat berupa Habit, Daily, atau ToDo’s. Tugas dapat diatur dengan tanggal jatuh tempo dan status (misalnya, Pending, Completed, atau Overdue).
Pemantauan Status Tugas: Pengguna dapat melacak, dan melihat status tugas mereka (misalnya Completed, Pending).
Penyelesaian Tugas: Tugas yang sudah selesai akan dihapus dari daftar tugas.
3. Gamifikasi (Shop)
Gamifikasi bertujuan untuk meningkatkan motivasi dengan memberikan poin setiap pengguna menyelesaikan tugas, yang dapat ditukar dengan karakter di shop:
Pemberian Poin: Pengguna mendapatkan poin setiap kali mereka menyelesaikan tugas. Poin ini dihitung dan ditambahkan ke akun pengguna melalui fitur Gamifikasi.
Pengurangan Poin: Jika melakukan pembelian di shop akan ada deduksi point sebesar harga item yang dibeli.
4. Pelaporan Aktivitas
Fitur pelaporan memungkinkan pengguna untuk melihat dan melacak kemajuan tugas serta statistik pengguna:
Dashboard Pengguna: Setiap pengguna memiliki dashboard pribadi yang menampilkan data pengguna diantaranya Username, jumlah tugas yang selesai, tugas yang masih pending, jumlah poin yang telah terkumpul dan juga jumlah item yang telah dibeli di shop.
