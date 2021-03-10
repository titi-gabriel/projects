<?php include('../partials/menu.php'); ?>

        <!-- Main content start-->
        <div class="main-content">
            <div class="wrapper">
                <h1>Tabel soferi:</h1>
                <br/><br/>

                <!-- Buton adaugare sofer -->
                <a href="manage-adaugare-sofer.php" class="btn-primary"> Adaugare Sofer </a>

                <br/><br/>
                <table class="tbl-full">
                <tr>
                    <th>ID</th>
                    <th>Nume</th>
                    <th>CNP</th>
                    <th>Serie Permis</th>
                    <th>Butoane</th>
                </tr>

                <?php
                    $sql = "Select * from sofer";
                    $stid = oci_parse($c, $sql);
                    oci_execute($stid);
                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                        $nume = $row['NUME'];
                        $id = $row['ID'];
                        $cnp = $row['CNP'];
                        $serie = $row['SERIE_PERMIS'];
                        
                    
                        ?>
                        <tr>
                        <td><?php echo $id?></td>
                        <td><?php echo $nume?></td>
                        <td><?php echo $cnp?></td>
                        <td><?php echo $serie?></td>
                        
                        <td>
                            <a href="update-sofer.php?id=<?php echo $id; ?>" class="btn-primary">Update</a>
                            <a href="delete-sofer.php?id=<?php echo $id; ?>" class="btn-primary">Delete</a>
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