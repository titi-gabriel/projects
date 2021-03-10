<?php include('../admin/connection.php'); ?>

<?php

    $id = $_GET['id'];

    $sql2 = "Select id from material where id=$id";
    $stid2 = oci_parse($c, $sql2);
    oci_execute($stid2);
    while (($row = oci_fetch_array($stid2, OCI_BOTH)) != false) {
        $material = $row[0];
        $sql3 = "delete from tranzactie where id_material=$material";
        $stid3 = oci_parse($c, $sql3);
        oci_execute($stid3);
    }
    $sql = "delete from material where id=$id";
    $stid = oci_parse($c, $sql);
    oci_execute($stid);
    

    echo "Ati sters materialul cu succes. Prin urmare in orice tabela unde era implicat materialul, a fost sters randul care continea materialul."

?>