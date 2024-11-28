<?php

$server = "localhost";
$username = "u739425533_liemuntea";
$password = "haliemhr23Mysql";
$database = "u739425533_lab_elektro";

// Buat koneksi ke database
$db = mysqli_connect($server, $username, $password, $database);

// Periksa koneksi
if (!$db) {
    // Jika gagal, tampilkan pesan kesalahan
    die("Koneksi gagal: " . mysqli_connect_error());
}

// Jika berhasil, Anda bisa menambahkan kode di sini untuk interaksi lebih lanjut
echo "Koneksi berhasil";
?>
