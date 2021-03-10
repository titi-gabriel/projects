import Bautura from '../entities/Bautura.js';

function validateBautura(bautura){
    if(!bautura || Object.entries(bautura).length === 0)
        return { hasErrors: true, message: "Nu ati introdus datele bauturii."};

    if(!bautura.nume)
        return { hasErrors: true, message: "Nu ati introdus numele bauturii."};

    if(!bautura.pret)
        return { hasErrors: true, message: "Nu ati introdus pretul bauturii."};
    
    if(!bautura.cantitate)
        return { hasErrors: true, message: "Nu ati introdus cantitatea bauturii."};

    if(!bautura.categorie)
        return { hasErrors: true, message: "Nu ati introdus categoria din care face parte bautura."};

    let doarCifre = /^[0-9]+$/;
    if(!doarCifre.test(bautura.pret))
    {
        return  { hasErrors: true, message: "Pretul poate contine doar cifre."};
    }
    if(!doarCifre.test(bautura.cantitate))
    {
        return  { hasErrors: true, message: "Cantitatea poate contine doar cifre."};
    }

    return {hasErrors: false, message: ""};
}

async function getBauturi(){
    return await Bautura.findAll();
}

async function getBauturaById(id)
{
    return await Bautura.findByPk(id);
}

async function createBautura(bautura)
{
    let error = validateBautura(bautura);
    if(error.hasErrors)
        return error;
   
    return await Bautura.create(bautura);
}

async function updateBautura(id, bautura)
{
    if(parseInt(id) !== parseInt(bautura.id))
        return {hasErrors: true, message: "Entity id diff"};
    
    let updateEntity = await getBauturaById(id);

    if(!updateEntity)
        return {hasErrors: true, message: "Nu este nicio bautura cu acest id"};

    let error = validateBautura(bautura);
    if(error.hasErrors)
        return error;

    return await updateEntity.update(bautura);
}

async function deleteBautura(id)
{
    let deleteEntity = await getBauturaById(id);

    if(!deleteEntity)
        return {hasErrors: true, message: "Nu este nicio bautura cu acest id"};
    
    return await deleteEntity.destroy();
}

export { validateBautura,deleteBautura,createBautura,getBauturi,getBauturaById,updateBautura}