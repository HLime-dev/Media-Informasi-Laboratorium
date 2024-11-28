<?php
include "../koneksi.php";
$sql = "SELECT * FROM alat4 WHERE id ='".$_POST['id']."'";

$query = mysqli_query($db, $sql);

$data = mysqli_fetch_assoc($query);
echo json_encode(array(
    'data'=>array(
        'name' => $data['name'],
        'kategori' => $data['kategori'],
        'jumlah' => $data['jumlah'],
        'image' => $data['image']
    )

));