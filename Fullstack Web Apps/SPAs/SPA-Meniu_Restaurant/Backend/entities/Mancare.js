import db from '../dbConfig.js';
import Sequelize from 'sequelize';

const Mancare = db.define("Mancare",
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

    bucatarie:{
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



export default Mancare;