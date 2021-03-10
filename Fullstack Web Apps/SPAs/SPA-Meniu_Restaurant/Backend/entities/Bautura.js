import db from '../dbConfig.js';
import Sequelize from 'sequelize';

const Bautura = db.define("Bautura",
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

    categorie:{
        type: Sequelize.STRING,
        allowNull: false
    },

    pret:
    {
        type: Sequelize.INTEGER,
        allowNull: true
    },

    cantitate:
    {
        type: Sequelize.INTEGER,
        allowNull: true
    }
})



export default Bautura;