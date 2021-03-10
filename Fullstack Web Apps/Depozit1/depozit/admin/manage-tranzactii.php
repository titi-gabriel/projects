<?php include('../partials/menu.php'); ?>

        <!-- Main content start-->
        <div class="main-content">
            <div class="wrapper">
                <h1>Tabel tranzactii:</h1>
                <br/><br/>

                <!-- Buton adaugare tranzactie -->
                <a href="manage-adaugare-tranzactie.php" class="btn-primary"> Adaugare Tranzactie </a>

                <br/><br/>
                <table class="tbl-full">
                <tr>
                    <th>nrcrt</th>
                    <th>Client(ID)</th>
                    <th>Masina(ID)</th>
                    <th>Material(ID)</th>
                    <th>Cantitate</th>
                    <th>Data</th>
                    <th>Butoane</th>
                </tr>

                <?php
                    $sql = "Select * from tranzactie";
                    $stid = oci_parse($c, $sql);
                    oci_execute($stid);
                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                        $id_client = $row['ID_CLIENT'];
                        $id = $row['ID'];
                        $id_masina = $row['ID_MASINA'];
                        $id_material = $row['ID_MATERIAL'];
                        $cantitate = $row['CANTITATE'];
                        $data = $row['DATA'];
                        
                    
                        ?>
                        <tr>
                        <td><?php echo $id ?></td>
                        
                        <td><?php 
                        $sql1 = "Select nume from client where id in (Select id_client from tranzactie where id_client='$id_client')";
                        $stid1 = oci_parse($c, $sql1);
                        oci_execute($stid1);
                        $row1 = oci_fetch_array($stid1);
                        echo $row1[0];
                        echo "(";
                        echo $id_client;
                        echo ")";
                        ?></td>

                        <td><?php 
                        $sql1 = "Select numar_masina from masina where id in (Select id_masina from tranzactie where id_masina='$id_masina')";
                        $stid1 = oci_parse($c, $sql1);
                        oci_execute($stid1);
                        $row1 = oci_fetch_array($stid1);
                        echo $row1[0];
                        echo "(";
                        echo $id_masina;
                        echo ")";
                        ?></td>

                        <td><?php 
                        $sql1 = "Select nume from material where id in (Select id_material from tranzactie where id_material='$id_material')";
                        $stid1 = oci_parse($c, $sql1);
                        oci_execute($stid1);
                        $row1 = oci_fetch_array($stid1);
                        echo $row1[0];
                        echo "(";
                        echo $id_material;
                        echo ")";
                        ?></td>
                        <td><?php echo $cantitate ?></td>
                        <td><?php echo $data ?></td>
                        <td>
                            <a href="update-tranzactie.php?id=<?php echo $id; ?>" class="btn-primary">Update</a>
                            <a href="delete-tranzactie.php?id=<?php echo $id; ?>" class="btn-primary">Delete</a>
                        </td>
                        </tr>

                        

                        <?php
                    }
                ?>


                </table>
            </div>
        </div>
        <!-- Main content end-->

<?php include('../partials/footer.php'); ?>