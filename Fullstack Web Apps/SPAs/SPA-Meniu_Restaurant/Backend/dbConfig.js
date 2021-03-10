import Sequelize from 'sequelize';

const db = new Sequelize({
    dialect: 'mssql',
    database: 'Proiect1',
    username: 'sa',
    host: 'localhost',
    port: '64626',
    password: '123123',  
    validateBulkLoadParameters: true,
    define: {
    timestamps: false,
    freezeTableName: true
    }  
})

export default db;