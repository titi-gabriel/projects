async function getBauturiHTML(){
    let tableBautura = await renderTableHTMLBautura();
    return addButtonHTMLBautura() + tableBautura + popupHTMLBautura();
}

function addButtonHTMLBautura(){
    return '<br></br> <button type="button" class="btn btn-success" title="Adaugare Bautura" data-toggle="modal" data-target="#NewBautura" style="margin-left:45%;font-family:Segoe UI;">' + 
        '<i class="fas fa-plus-square"></i> &nbsp; Adaugare Bautura </button> <br></br> '       
}

function popupHTMLBautura(){
    return  '<div class="modal" id="NewBautura">' +
    '<div class="modal-dialog modal-md modal-dialog-centered">' +
        '<div class="modal-content">' +

            '<div class="modal-header">' +                
                '<h4 class="modal-title">Bautura</h4><br />' +
                '<button type="button" onclick="clearPopupBautura()" class="close" data-dismiss="modal"><i class="fas fa-times"></i></button>' +
            '</div>' +

            '<div class="modal-body"><br />' +
                '<h5 class="text-primary">Introduceti datele bauturii</h5><br />' +                 
                '<label hidden id="Id"></label>' +

                '<table class="table table-sm border-0">' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Denumire</label></td>'+
                        '<td> <input type="text" name="NumeBautura" id="NumeBautura" class="form-control clearable" /> </td>' +                               
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Categorie</label></td>'+
                        '<td> <input type="text" name="CategorieBautura" id="CategorieBautura" class="form-control clearable" /> </td>' +                               
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Pret(RON)</label></td>' +
                        '<td> <input type="number" name="PretBautura" id="PretBautura" class="form-control clearable" /> </td>' +                    
                    '</tr>' +
                    '<tr>' +
                        '<td> <label class="control-label font-weight-bolder">Cantitate(ml)</label></td>' +
                        '<td> <input type="number" name="CantitateBautura" id="CantitateBautura" class="form-control clearable" /> </td>' +                             
                   '</tr>' + 
                '</table>' +                  
            '</div>' +       

            '<div class="modal-footer">' +
                '<button type="button" onclick="clearPopupBautura()" class="btn btn-danger" data-dismiss="modal"> <i class="fas fa-times"></i> &nbsp; Cancel </button>' +
                '<button type="button" class="btn btn-success" onClick="createOrEditBautura(event)"> <i class="fas fa-save"></i> &nbsp; Save</button>' +
            '</div>' +

        '</div>' +
    '</div>' +
'</div> '  
}

async function renderTableHTMLBautura()
{
    let data = await get(bauturaLink);
    if (data.hasErrors)
    {
        alert(data.message);
        return;
    }

    let table = '<table id="bautura" class="table table-hover table-succes table-striped table-bordered table-dark" style="width:80%; margin-left:auto; margin-right:auto"><thead><tr><th hidden>BauturaId</th><th>Denumire</th><th>Categorie</th><th>Pret(RON)</th><th>Cantitate(ml)</th></tr></thead>' +
                '<tbody>';

   for(let item of data)
       table += '<tr><td hidden>'+item.id+'</td><td>'+item.nume+'</td><td>'+item.categorie+'</td><td>'+item.pret+'</td><td>'+item.cantitate+'</td><td style="width:11%;font-family:Segoe UI;"><button onClick="getElemDataBautura(event, '+item.id+')" class="btn btn-primary"><i class="fas fa-pen-alt"></i> &nbsp; Update</button></td><td style="width:10%;font-family:Segoe UI;"><button onClick="deleteElemBautura(event, '+item.id+')" class="btn btn-danger"><i class="fas fa-backspace"></i> &nbsp; Delete</button></td></tr>'
   
   table += '</tbody></table>'

   return table;

}

async function createOrEditBautura(e) {
    
    e.preventDefault();
    e.stopPropagation();
    
    let bauturaNume = document.getElementById('NumeBautura').value;
    let bauturaCategorie = document.getElementById('CategorieBautura').value;
    let bauturaPret = document.getElementById('PretBautura').value;
    let bauturaCantitate = document.getElementById('CantitateBautura').value;
    let bauturaId = document.getElementById("Id").innerHTML;
    
    
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    //!!!!!!!!TOATE VALIDARILE SUNT IN BACKEND!!!!!!!!!!!
    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    
    
    if (!bauturaId) {
        let error = await post(bauturaLink, { nume: bauturaNume, categorie: bauturaCategorie, pret: bauturaPret, cantitate: bauturaCantitate });
        if (error.hasErrors) {
            alert(error.message);
            return;
        }
        clearPopupBautura();
    }
    else {
        let error = await put(bauturaLink, bauturaId, { id: bauturaId, nume: bauturaNume, categorie: bauturaCategorie, pret: bauturaPret, cantitate: bauturaCantitate  })
        if (error.hasErrors) {
            alert(error.message);
            return;
        }
        clearPopupBautura();
    }
 
    loadContent();
    $('#NewBautura').modal('hide');
}

function clearPopupBautura() {
    document.getElementById("NumeBautura").value = "";
    document.getElementById("CategorieBautura").value = "";
    document.getElementById("PretBautura").value = "";
    document.getElementById("CantitateBautura").value = "";
    document.getElementById("Id").innerHTML = "";

    let inputCollection = document.getElementsByClassName("modal");
    for (let item of inputCollection)
        item.classList.remove("x");
}

async function getElemDataBautura(e, id)
{
    
    e.preventDefault();
    e.stopPropagation();
    let updateBautura = await get(bauturaLink, id);
    
    if(updateBautura.hasErrors)
    {
        alert(updateBautura.message);
        return;
    }
    
    document.getElementById("NumeBautura").value= updateBautura.nume;
    document.getElementById("CategorieBautura").value=updateBautura.categorie;
    document.getElementById("PretBautura").value=updateBautura.pret;
    document.getElementById("CantitateBautura").value= updateBautura.cantitate;
    document.getElementById("Id").innerHTML= updateBautura.id;
    
    $("#NewBautura").modal('show');
}

async function deleteElemBautura(e, id)
{
    e.preventDefault();
    e.stopPropagation();
    let res = await remove(bauturaLink, id);
    if(res.hasErrors)
    {
        alert(res.message);
        return;
    }

    loadContent();
}