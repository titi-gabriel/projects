<?php include('../admin/connection.php'); ?>

<?php

    $id = $_GET['id'];

    $sql = "delete from tranzactie where id=$id";
    $stid = oci_parse($c, $sql);
    oci_execute($stid);
    

    echo "Ati sters tranzactia cu succes."

?>