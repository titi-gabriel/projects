async function getDonatoriHTML(){
    let table = await renderTableHTML();
    return addButtonHTML() + table + popupHTML();
}

function addButtonHTML(){
    return '<br></br> <button type="button" class="btn btn-primary" title="Donator" data-toggle="modal" data-target="#NewDonator" style="margin-left:47%;font-family:Arial;">' + 
        '<i class="fas fa-comment-medical"></i> &nbsp; Donator </button> <br></br> '       
}
function popupHTML(){
    return  '<div class="modal" id="NewDonator">' +
    '<div class="modal-dialog modal-md modal-dialog-centered">' +
        '<div class="modal-content">' +

            '<div class="modal-header">' +                
                '<h4 class="modal-title">Adaugare Donator</h4><br />' +
                '<button type="button" onclick="clearPopup()" class="close" data-dismiss="modal"><i class="fas fa-times"></i></button>' +
            '</div>' +

            '<div class="modal-body"><br />' +
                '<h5 class="text-primary">Informatii despre donator</h5><br />' +                 
                '<label hidden id="Id"></label>' +

                '<table class="table table-sm thead-dark border-0">' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Nume</label></td>'+
                        '<td> <input type="text" name="NumeDonator" id="NumeDonator" class="form-control clearable" /> </td>' +                               
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Prenume</label></td>' +
                        '<td> <input type="text" name="PrenumeDonator" id="PrenumeDonator" class="form-control clearable"  /> </td> ' +                         
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">CNP</label></td>' +
                        '<td> <input type="text" name="CnpDonator" id="CnpDonator" class="form-control clearable" /> </td>' +                             
                    '</tr>' + 
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Grupa Sanguina</label></td>' +
                        '<td> <input type="text" name="GrupaSgDonator" id="GrupaSgDonator" class="form-control clearable" /> </td>' +                    
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">RH</label></td>' +
                        '<td> <input type="text" name="RhDonator" id="RhDonator" class="form-control clearable" /> </td>' +                    
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Telefon</label></td>' +
                        '<td> <input type="text" name="TelefonDonator" id="TelefonDonator" class="form-control clearable" /> </td>' +                    
                    '</tr>' +
                '</table>' +                  
            '</div>' +       

            '<div class="modal-footer">' +
                '<button type="button" onclick="clearPopup()" class="btn btn-danger" data-dismiss="modal"> <i class="fas fa-times"></i> &nbsp; Cancel </button>' +
                '<button type="button" class="btn btn-success" onClick="createOrEditDonator(event)"> <i class="fas fa-save"></i> &nbsp; Save</button>' +
            '</div>' +

        '</div>' +
    '</div>' +
'</div> '  
}
async function renderTableHTML()
{
    let data = await get(donatorLink);
    if (data.hasErrors)
    {
        alert(data.message);
        return;
    }

    let table = '<table id="donator" class="table table-hover table-danger table-striped table-bordered" style="width:90%; margin-left:auto; margin-right:auto; text-align: center;"><thead><tr><th hidden>DonatorId</th><th>Nume</th><th>Prenume</th><th>Cnp</th><th>Grupa Sanguina</th><th>RH</th><th>Numar de telefon</th></tr></thead>' +
                '<tbody>';

   for(let item of data)
       table += '<tr><td hidden>'+item.id+'</td><td>'+item.nume+'</td><td>'+item.prenume+'</td><td>'+item.cnp+'</td><td>'+item.grupa_sg+'</td><td>'+item.rh+'</td><td>'+item.telefon+'</td><td style="font-family:Arial;"><button onClick="getElemData(event, '+item.id+')" class="btn btn-success"><i class="fas fa-stream"></i> &nbsp; Update</button></td><td style="font-family:Arial;"><button onClick="deleteElem(event, '+item.id+')" class="btn btn-danger"><i class="fas fa-window-close"></i> &nbsp; Delete</button></td></tr>'
   
   table += '</tbody></table>'

   return table;

}
async function createOrEditDonator(e) {
    
    e.preventDefault();
    e.stopPropagation();
    
    let donatorPrenume = document.getElementById('PrenumeDonator').value;
    let donatorNume = document.getElementById('NumeDonator').value;
    let donatorCnp = document.getElementById('CnpDonator').value;
    let donatorGrupaSg = document.getElementById('GrupaSgDonator').value;
    let donatorRh = document.getElementById('RhDonator').value;
    let donatorPhone = document.getElementById('TelefonDonator').value;
    let donatorId = document.getElementById("Id").innerHTML;
    
    if (!donatorId) {
        let error = await post(donatorLink, { prenume: donatorPrenume, nume: donatorNume, cnp: donatorCnp, grupa_sg: donatorGrupaSg, rh:donatorRh ,telefon: donatorPhone });
        if (error.hasErrors) {
            alert(error.message);
            return;
        }
        clearPopup();
    }
    else {
        let error = await put(donatorLink, donatorId, { id: donatorId, prenume: donatorPrenume, nume: donatorNume, cnp: donatorCnp, grupa_sg: donatorGrupaSg, rh:donatorRh ,telefon: donatorPhone })
        if (error.hasErrors) {
            alert(error.message);
            return;
        }
        clearPopup();
    }
 
    loadContent();
    $('#NewDonator').modal('hide');
}

function clearPopup() {
    document.getElementById("Id").innerHTML = "";
    document.getElementById("PrenumeDonator").value = "";
    document.getElementById("NumeDonator").value = "";
    document.getElementById("GrupaSgDonator").innerHTML = "";
    document.getElementById("RhDonator").innerHTML = "";
    document.getElementById("TelefonDonator").value = "";
    document.getElementById("CnpDonator").value = "";

    let inputCollection = document.getElementsByClassName("modal");
    for (let item of inputCollection)
        item.classList.remove("x");
}

async function getElemData(e, id)
{
    
    e.preventDefault();
    e.stopPropagation();
    let updateDonator = await get(donatorLink, id);
    
    if(updateDonator.hasErrors)
    {
        alert(updateDonator.message);
        return;
    }
    document.getElementById("Id").innerHTML= updateDonator.id;
    document.getElementById("PrenumeDonator").value = updateDonator.prenume;
    document.getElementById("NumeDonator").value= updateDonator.nume;
    document.getElementById("GrupaSgDonator").value = updateDonator.grupa_sg;
    document.getElementById("RhDonator").value = updateDonator.rh;
    document.getElementById("TelefonDonator").value=updateDonator.telefon;
    document.getElementById("CnpDonator").value= updateDonator.cnp;
    
    $("#NewDonator").modal('show');
}

async function deleteElem(e, id)
{
    e.preventDefault();
    e.stopPropagation();
    let res = await remove(donatorLink, id);
    if(res.hasErrors)
    {
        alert(res.message);
        return;
    }

    loadContent();
}
