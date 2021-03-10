<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class="wrapper">

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Numar Masina: </td>
                        <td><input type="text" name="Numar" placeholder="ex. IS-02-TLQ"></td>
                        <td>Capacitate: </td>
                        <td><input type="text" name="Capacitate" placeholder="ex. 4.5"></td>
                        <td>Sofer: </td>
                        <td>
                            <select name="Sofer">
                                <?php
                                    $sql = "Select s.id, s.nume
                                    from sofer s, (select s.id from sofer s
                                    minus
                                    select m.id_sofer from masina m) m
                                    where s.id = m.id";
                                    $stid = oci_parse($c, $sql);
                                    oci_execute($stid);
                                    while (($row = oci_fetch_array($stid, OCI_BOTH)) != false) {
                                        $nume_sofer = $row['NUME'];
                                        echo "<option value='$nume_sofer'>$nume_sofer</option>";
                                    }
                                ?>
                            </select>
                        </td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="submit" name="submit" value="Adaugare Masina" class="btn-primary">
                    </td>
                </tr>
            </table>

        </form>

    </div>
</div>

<?php include('../partials/footer.php'); ?>

<?php 
    //Procesare date din casute si salvare in baza de date
    //verificare buton apasat
    if(isset($_POST['submit']))
    {
       $Numar = $_POST['Numar'] ;
       $Capacitate = $_POST['Capacitate'] ;
       $Sofer = $_POST['Sofer'] ;
       //sql query
       
       $sql = "INSERT INTO masina (numar_masina,capacitate,id_sofer) VALUES ('${Numar}','${Capacitate}',(SELECT id FROM sofer WHERE nume='$Sofer'))";
       $stid = oci_parse($c, $sql);
       oci_execute($stid);

    }
?>