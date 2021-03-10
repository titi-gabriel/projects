<?php include('../admin/connection.php'); ?>

<?php

    $id = $_GET['id'];

    
    $sql1 = "delete from masina where id_sofer=$id";
    $stid1 = oci_parse($c, $sql1);
    oci_execute($stid1);

    $sql = "delete from sofer where id=$id";
    $stid = oci_parse($c, $sql);
    oci_execute($stid);
    

    echo "Ati sters soferul cu succes. Prin urmare in orice tabela unde era implicat soferul, a fost sters randul care continea soferul."
    

?>