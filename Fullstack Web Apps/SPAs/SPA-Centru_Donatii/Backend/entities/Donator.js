import db from '../dbConfig.js';
import Sequelize from 'sequelize';

const Donator = db.define("Donator",
{
    id:
    {
        type: Sequelize.INTEGER,
        primaryKey: true,
        autoIncrement: true,
        allowNull: false
    },

    nume:{
        type: Sequelize.STRING,
        allowNull: false
    },
    
    prenume:{
        type: Sequelize.STRING,
        allowNull: false
    },

    cnp:{
        type: Sequelize.STRING,
        allowNull: false
    },
    grupa_sg:{
        type: Sequelize.STRING,
        allowNull: false
    },
    rh:{
        type: Sequelize.STRING,
        allowNull: false
    },
    telefon:{
        type: Sequelize.STRING,
        allowNull: true
    }
})

export default Donator;