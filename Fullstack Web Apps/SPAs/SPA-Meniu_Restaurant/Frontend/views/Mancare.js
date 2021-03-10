async function getMancareHTML(){
    let tableMancare = await renderTableHTMLMancare();
    return addButtonHTMLMancare() + tableMancare + popupHTMLMancare();
}

function addButtonHTMLMancare(){
    return '<br></br> <button type="button" class="btn btn-success" title="Adaugare Mancare" data-toggle="modal" data-target="#NewMancare" style="margin-left:45%;font-family:Segoe UI;">' + 
        '<i class="fas fa-plus-square"></i> &nbsp; Adaugare Mancare </button> <br></br> '       
}

function popupHTMLMancare(){
    return  '<div class="modal" id="NewMancare">' +
    '<div class="modal-dialog modal-md modal-dialog-centered">' +
        '<div class="modal-content">' +

            '<div class="modal-header">' +                
                '<h4 class="modal-title">Mancare</h4><br />' +
                '<button type="button" onclick="clearPopupMancare()" class="close" data-dismiss="modal"><i class="fas fa-times"></i></button>' +
            '</div>' +

            '<div class="modal-body"><br />' +
                '<h5 class="text-primary">Introduceti datele mancarii</h5><br />' +                 
                '<label hidden id="Id"></label>' +

                '<table class="table table-sm border-0">' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Denumire</label></td>'+
                        '<td> <input type="text" name="NumeMancare" id="NumeMancare" class="form-control clearable" /> </td>' +                               
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Bucatarie</label></td>'+
                        '<td> <input type="text" name="BucatarieMancare" id="BucatarieMancare" class="form-control clearable" /> </td>' +                               
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Pret(RON)</label></td>' +
                        '<td> <input type="number" name="PretMancare" id="PretMancare" class="form-control clearable" /> </td>' +                    
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Cantitate(g)</label></td>' +
                        '<td> <input type="number" name="CantitateMancare" id="CantitateMancare" class="form-control clearable" /> </td>' +                             
                   '</tr>' + 
                '</table>' +                  
            '</div>' +       

            '<div class="modal-footer">' +
                '<button type="button" onclick="clearPopupMancare()" class="btn btn-danger" data-dismiss="modal"> <i class="fas fa-times"></i> &nbsp; Cancel </button>' +
                '<button type="button" class="btn btn-success" onClick="createOrEditMancare(event)"> <i class="fas fa-save"></i> &nbsp; Save</button>' +
            '</div>' +

        '</div>' +
    '</div>' +
'</div> '  
}

async function renderTableHTMLMancare()
{
    let data = await get(mancareLink);
    if (data.hasErrors)
    {
        alert(data.message);
        return;
    }

    let table = '<table id="mancare" class="table table-dark table-hover table-striped table-bordered" style="width:80%; margin-left:auto; margin-right:auto"><thead><tr><th hidden>MancareId</th><th>Denumire</th><th>Bucatarie</th><th>Pret(RON)</th><th>Cantitate(g)</th></tr></thead>' +
                '<tbody>';

   for(let item of data)
       table += '<tr><td hidden>'+item.id+'</td><td>'+item.nume+'</td><td>'+item.bucatarie+'</td><td>'+item.pret+'</td><td>'+item.cantitate+'</td><td style="width:11%;font-family:Segoe UI;"><button onClick="getElemDataMancare(event, '+item.id+')" class="btn btn-primary"><i class="fas fa-pen-alt"></i> &nbsp; Update</button></td><td style="width:10%;font-family:Segoe UI;"><button onClick="deleteElemMancare(event, '+item.id+')" class="btn btn-danger"><i class="fas fa-backspace"></i> &nbsp; Delete</button></td></tr>'
   
   table += '</tbody></table>'

   return table;

}

async function createOrEditMancare(e) {
    
    e.preventDefault();
    e.stopPropagation();
    
    let mancareNume = document.getElementById('NumeMancare').value;
    let mancareBucatarie = document.getElementById('BucatarieMancare').value;
    let mancarePret = document.getElementById('PretMancare').value;
    let mancareCantitate = document.getElementById('CantitateMancare').value;
    let mancareId = document.getElementById("Id").innerHTML;
    
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!TOATE VALIDARILE SUNT IN BACKEND!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    
    if (!mancareId) {
        let error = await post(mancareLink, { nume: mancareNume, bucatarie: mancareBucatarie, pret: mancarePret, cantitate: mancareCantitate });
        if (error.hasErrors) {
            alert(error.message);
            return;
        }
        clearPopupMancare();
    }
    else {
        let error = await put(mancareLink, mancareId, { id: mancareId, nume: mancareNume, bucatarie: mancareBucatarie, pret: mancarePret, cantitate: mancareCantitate  })
        if (error.hasErrors) {
            alert(error.message);
            return;
        }
        clearPopupMancare();
    }
 
    loadContent();
    $('#NewMancare').modal('hide');
}

function clearPopupMancare() {
    document.getElementById("NumeMancare").value = "";
    document.getElementById("BucatarieMancare").value = "";
    document.getElementById("PretMancare").value = "";
    document.getElementById("CantitateMancare").value = "";
    document.getElementById("Id").innerHTML = "";

    let inputCollection = document.getElementsByClassName("modal");
    for (let item of inputCollection)
        item.classList.remove("x");
}

async function getElemDataMancare(e, id)
{
    
    e.preventDefault();
    e.stopPropagation();
    let updateMancare = await get(mancareLink, id);
    
    if(updateMancare.hasErrors)
    {
        alert(updateMancare.message);
        return;
    }
    
    document.getElementById("NumeMancare").value= updateMancare.nume;
    document.getElementById("BucatarieMancare").value=updateMancare.bucatarie;
    document.getElementById("PretMancare").value=updateMancare.pret;
    document.getElementById("CantitateMancare").value= updateMancare.cantitate;
    document.getElementById("Id").innerHTML= updateMancare.id;
    
    $("#NewMancare").modal('show');
}

async function deleteElemMancare(e, id)
{
    e.preventDefault();
    e.stopPropagation();
    let res = await remove(mancareLink, id);
    if(res.hasErrors)
    {
        alert(res.message);
        return;
    }

    loadContent();
}