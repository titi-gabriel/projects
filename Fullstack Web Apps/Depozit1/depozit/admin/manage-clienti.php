<?php include('../partials/menu.php'); ?>

        <!-- Main content start-->
        <div class="main-content">
            <div class="wrapper">
                <h1>Tabel clienti:</h1>
                <br/><br/>

                <!-- Buton adaugare client -->
                <a href="manage-adaugare-client.php" class="btn-primary"> Adaugare Client </a>

                <br/><br/>
                <table class="tbl-full">
                <tr>
                    <th>ID</th>
                    <th>Nume</th>
                    <th>Telefon</th>
                    <th>Adresa</th>
                    <th>Butoane</th>
                </tr>

                <?php
                    $sql = "Select * from client";
                    $stid = oci_parse($c, $sql);
                    oci_execute($stid);
                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                        $nume_client = $row['NUME'];
                        $id_client = $row['ID'];
                        $telefon_client = $row['TELEFON'];
                        $adresa_client = $row['ADRESA']
                    
                        ?>
                        <tr>
                        <td><?php echo $id_client?></td>
                        <td><?php echo $nume_client?></td>
                        <td><?php echo $telefon_client?></td>
                        <td><?php echo $adresa_client?></td>
                        <td>
                            <a href="update-client.php?id=<?php echo $id_client; ?>" class="btn-primary">Update</a>
                            <a href="delete-client.php?id=<?php echo $id_client; ?>" class="btn-primary">Delete</a>
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