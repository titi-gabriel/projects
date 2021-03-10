import express from 'express';
import bodyParser from 'body-parser';
import cors from 'cors';
import donatori from './routes/DonatorRoute.js';

let app = express();

app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());
app.use(cors());

app.use('/api', donatori);

let port = process.env.PORT || 8008;
app.listen(port);
console.log("API is runnig at "+port);