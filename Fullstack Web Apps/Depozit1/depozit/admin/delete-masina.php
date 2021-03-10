<?php include('../admin/connection.php'); ?>

<?php

    $id = $_GET['id'];

    
    

    $sql1 = "delete from tranzactie where id_masina=$id";
    $stid1 = oci_parse($c, $sql1);
    oci_execute($stid1);

    $sql = "delete from masina where id=$id";
    $stid = oci_parse($c, $sql);
    oci_execute($stid);
    

    echo "Ati sters masina cu succes. Prin urmare in orice tabela unde era implicata masina, a fost sters randul care continea masina."

?>