<?php include('../partials/menu.php'); ?>

        <!-- Main content start-->
        <div class="main-content">
            <div class="wrapper">
                <h1>Tabel masini:</h1>
                <br/><br/>

                <!-- Buton adaugare masina -->
                <a href="manage-adaugare-masina.php" class="btn-primary"> Adaugare Masina </a>

                <br/><br/>
                <table class="tbl-full">
                <tr>
                    <th>ID</th>
                    <th>Numar Masina</th>
                    <th>Capacitate</th>
                    <th>Sofer(ID)</th>
                    <th>Butoane</th>
                </tr>
                <?php
                    $sql = "Select id,numar_masina,capacitate,id_sofer from masina";
                    $stid = oci_parse($c, $sql);
                    oci_execute($stid);
                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                        $nume = $row['NUMAR_MASINA'];
                        $id = $row['ID'];
                        $capacitate = $row['CAPACITATE'];
                        $id_sofer = $row['ID_SOFER'];
                        
                    
                        ?>
                        <tr>
                        <td><?php echo $id ?></td>
                        <td><?php echo $nume?></td>
                        <td><?php echo $capacitate?></td>
                        <td><?php 
                        $sql1 = "Select nume from sofer where id in (Select id_sofer from masina where id_sofer='$id_sofer')";
                        $stid1 = oci_parse($c, $sql1);
                        oci_execute($stid1);
                        $row1 = oci_fetch_array($stid1);
                        echo $row1[0];
                        echo "(";
                        echo $id_sofer;
                        echo ")";
                        ?></td>
                        
                        <td>
                            <a href="update-masina.php?id=<?php echo $id; ?>" class="btn-primary">Update</a>
                            <a href="delete-masina.php?id=<?php echo $id; ?>" class="btn-primary">Delete</a>
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