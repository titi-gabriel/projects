<?php include('../partials/menu.php'); ?>

<div class="main-content">
    <div class=wrapper>
        <h1>Update Depozit</h1>

        <?php 
            $id = $_GET['id'];

            $sql = "SELECT * from depozit";
            $stid = oci_parse($c, $sql);
            oci_execute($stid);

            $row = oci_fetch_assoc($stid);

            $nume = $row['NUME'];

        ?>

        <form action="" method="POST">

            <table>
                <tr>
                        <td>Nume depozit: </td>
                        <td><input type="text" name="Nume" value="<?php echo $nume ?>"></td>
                </tr>

                <tr>
                    <td colspan="2">
                        <input type="hidden" name="id" value="<?php echo $id; ?>">
                        <input type="submit" name="submit" value="Update Depozit" class="btn-primary">
                    </td>
                </tr>
            </table>

        </form>
    </div>
</div>

<?php
    if(isset($_POST['submit']))
    {
        $id = $_POST['id'];
        $nume = $_POST['Nume'];

        $sql = "UPDATE depozit SET
        NUME = '$nume'
        WHERE ID='$id'";

        $stid = oci_parse($c, $sql);
        oci_execute($stid);
        
    }
?>

<?php include('../partials/footer.php'); ?>