import Donator from '../entities/Donator.js';

function validateDonator(donator){
    if(!donator || Object.entries(donator).length === 0)
        return { hasErrors: true, message: "Introduceti datele."};

    if(!donator.nume)
        return { hasErrors: true, message: "Numele este obligatoriu."};

    if(!donator.prenume)
        return { hasErrors: true, message: "Preumele este obligatoriu."};
    
    if(!donator.cnp)
        return { hasErrors: true, message: "CNP-ul este obligatoriu."};

    let doarCifre = /^[0-9]+$/;
    if(!doarCifre.test(donator.telefon) && donator.telefon)
    {
        return  { hasErrors: true, message: "Telefonul este alcatuit doar din cifre."};
    }

    let doarLitere = /^[A-Za-z]+$/;
    if(!doarLitere.test(donator.nume))
    {
        return  { hasErrors: true, message: "Numele este alcatuit din litere."};
    }
    
    if(!doarLitere.test(donator.prenume))
    {
        return  { hasErrors: true, message: "Prenumele este alcatuit din litere."};
    }

    if(!doarCifre.test(donator.cnp) && donator.cnp.length != 13)
    {
        return  { hasErrors: true, message: "CNP-ul are 13 cifre."};
    }

    if(donator.cnp.length != 13)
    {
        return  { hasErrors: true, message: "CNP-ul are 13 cifre."};
    }

    if(donator.rh!='pozitiv')
    {
        if(donator.rh != 'negativ')
        {
            return  { hasErrors: true, message: "RH-ul poate fi pozitiv sau negativ."};
        }
    }
    

    return {hasErrors: false, message: ""};
}

async function getDonatori(){
    return await Donator.findAll();
}

async function getDonatorById(id)
{
    let donator = await Donator.findByPk(id);
    if(!donator)
        return{hasErrors: true, message: "Nu se gaseste un donator cu acest id."};
    return donator;

}

async function createDonator(donator)
{
    let error = validateDonator(donator);
    if(error.hasErrors)
        return error;
   
    return await Donator.create(donator);
}

async function updateDonator(id, donator)
{
    if(parseInt(id) !== parseInt(donator.id))
        return {hasErrors: true, message: "Entity id diff"};
    
    let updateEntity = await getDonatorById(id);

    if(!updateEntity)
        return {hasErrors: true, message: "Nu se gaseste un donator cu acest id."};

    let error = validateDonator(donator);
    if(error.hasErrors)
        return error;

    return await updateEntity.update(donator);
}

async function deleteDonator(id)
{
    let deleteEntity = await getDonatorById(id);

    if(!deleteEntity)
        return {hasErrors: true, message: "Nu se gaseste un donator cu acest id."};
    
    return await deleteEntity.destroy();
}

export{getDonatori, getDonatorById, createDonator, updateDonator, deleteDonator}