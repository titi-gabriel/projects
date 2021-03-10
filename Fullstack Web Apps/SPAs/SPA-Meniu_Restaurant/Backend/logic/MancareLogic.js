import Mancare from '../entities/Mancare.js';

function validateMancare(mancare){
    if(!mancare || Object.entries(mancare).length === 0)
        return { hasErrors: true, message: "Nu ati introdus datele mancarii."};

    if(!mancare.nume)
        return { hasErrors: true, message: "Nu ati introdus numele mancarii."};

    if(!mancare.pret)
        return { hasErrors: true, message: "Nu ati introdus pretul mancarii."};
    
    if(!mancare.cantitate)
        return { hasErrors: true, message: "Nu ati introdus cantitatea mancarii."};

    if(!mancare.bucatarie)
        return { hasErrors: true, message: "Nu ati introdus bucataria de provenienta a mancarii."};

    let doarCifre = /^[0-9]+$/;
    if(!doarCifre.test(mancare.pret))
    {
        return  { hasErrors: true, message: "Pretul poate contine doar cifre."};
    }
    if(!doarCifre.test(mancare.cantitate))
    {
        return  { hasErrors: true, message: "Cantitatea poate contine doar cifre."};
    }

    return {hasErrors: false, message: ""};
}

async function getMancare(){
    return await Mancare.findAll();
}

async function getMancareById(id)
{
    return await Mancare.findByPk(id);
}

async function createMancare(mancare)
{
    let error = validateMancare(mancare);
    if(error.hasErrors)
        return error;
   
    return await Mancare.create(mancare);
}

async function updateMancare(id, mancare)
{
    if(parseInt(id) !== parseInt(mancare.id))
        return {hasErrors: true, message: "Entity id diff"};
    
    let updateEntity = await getMancareById(id);

    if(!updateEntity)
        return {hasErrors: true, message: "Nu este niciun fel de mancare cu acest id"};

    let error = validateMancare(mancare);
    if(error.hasErrors)
        return error;

    return await updateEntity.update(mancare);
}

async function deleteMancare(id)
{
    let deleteEntity = await getMancareById(id);

    if(!deleteEntity)
        return {hasErrors: true, message: "Nu este niciun fel de mancare cu acest id"};
    
    return await deleteEntity.destroy();
}

export { validateMancare,deleteMancare,createMancare,getMancare,getMancareById,updateMancare}