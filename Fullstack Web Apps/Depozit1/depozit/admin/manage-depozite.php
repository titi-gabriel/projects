<?php include('../partials/menu.php'); ?>

        <!-- Main content start-->
        <div class="main-content">
            <div class="wrapper">
                <h1>Tabel depozite:</h1>
                <br/><br/>

                <!-- Buton adaugare depozit -->
                <a href="manage-adaugare-depozit.php" class="btn-primary"> Adaugare Depozit </a>

                <br/><br/>
                <table class="tbl-full">
                <tr>
                    <th>ID</th>
                    <th>Nume Depozit</th>
                    <th>Butoane</th>
                </tr>

                <?php
                    $sql = "Select * from depozit";
                    $stid = oci_parse($c, $sql);
                    oci_execute($stid);
                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                        $nume = $row['NUME'];
                        $id = $row['ID'];
                        
                    
                        ?>
                        <tr>
                        <td><?php echo $id?></td>
                        <td><?php echo $nume?></td>
                        
                        <td>
                            <a href="update-depozit.php?id=<?php echo $id; ?>" class="btn-primary">Update</a>
                            <a href="delete-depozit.php?id=<?php echo $id; ?>" class="btn-primary">Delete</a>
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