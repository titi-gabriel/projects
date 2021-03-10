<?php include('../partials/menu.php'); ?>

        <!-- Main content start-->
        <div class="main-content">
            <div class="wrapper">
                <h1>Tabel materiale:</h1>
                <br/><br/>

                <!-- Buton adaugare material -->
                <a href="manage-adaugare-material.php" class="btn-primary"> Adaugare Material </a>

                <br/><br/>
                <table class="tbl-full">
                <tr>
                    <th>ID</th>
                    <th>Nume</th>
                    <th>Pret</th>
                    <th>Cantitate</th>
                    <th>Depozit(ID)</th>
                    <th>Butoane</th>
                </tr>

                <?php
                    $sql = "Select id,id_depozit,nume,pret,cantitate from material";
                    $stid = oci_parse($c, $sql);
                    oci_execute($stid);
                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                        $nume = $row['NUME'];
                        $id = $row['ID'];
                        $id_depozit = $row['ID_DEPOZIT'];
                        $cantitate = $row['CANTITATE'];
                        $pret = $row['PRET'];
                        
                    
                        ?>
                        <tr>
                        <td><?php echo $id ?></td>
                        <td><?php echo $nume?></td>
                        <td><?php echo $pret?></td>
                        <td><?php echo $cantitate?></td>
                        <td><?php 
                        $sql1 = "Select nume from depozit where id in (Select id_depozit from material where id_depozit='$id_depozit')";
                        $stid1 = oci_parse($c, $sql1);
                        oci_execute($stid1);
                        $row1 = oci_fetch_array($stid1);
                        echo $row1[0];
                        echo "(";echo $id_depozit;echo ")";
                        ?></td>
                        
                        <td>
                            <a href="update-material.php?id=<?php echo $id; ?>" class="btn-primary">Update</a>
                            <a href="delete-material.php?id=<?php echo $id; ?>" class="btn-primary">Delete</a>
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