import express from 'express';
import bodyParser from 'body-parser';
import cors from 'cors';
import mancare from './routes/MancareRoute.js';
import bauturi from './routes/BauturaRoute.js';


let app = express();

app.use(bodyParser.urlencoded({extended:true}));
app.use(bodyParser.json());
app.use(cors());

app.use('/api', mancare);
app.use('/api', bauturi);

let port = process.env.PORT || 8080;
app.listen(port);
console.log("API is runnig at "+port);