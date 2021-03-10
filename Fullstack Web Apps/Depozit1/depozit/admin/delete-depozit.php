<?php include('../admin/connection.php'); ?>

<?php

    $id = $_GET['id'];

    $sql2 = "Select id from material where id_depozit=$id";
    $stid2 = oci_parse($c, $sql2);
    oci_execute($stid2);
    while (($row = oci_fetch_array($stid2, OCI_BOTH)) != false) {
        $material = $row[0];
        $sql3 = "delete from tranzactie where id_material=$material";
        $stid3 = oci_parse($c, $sql3);
        oci_execute($stid3);
    }
    $sql = "delete from material where id_depozit=$id";
    $stid = oci_parse($c, $sql);
    oci_execute($stid);
    $sql1 = "delete from depozit where id=$id";
    $stid1 = oci_parse($c, $sql1);
    oci_execute($stid1);

    echo "Ati sters depozitul cu succes. Prin urmare in orice tabela unde era implicat depozitul, a fost sters randul care continea depozitul."

?>