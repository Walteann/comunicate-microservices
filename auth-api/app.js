import express from 'express';

import * as db from './src/config/db/initalData.js';
import userRoutes  from './src/modules/user/routes/UserRoutes.js';


const app = express();
const env = process.env;
const PORT = env.PORT || 8080;

db.createInitialData();

app.get('/api/status', (req, res) => {

    return res.status(200).json({
        service: 'Auth-API',
        status: 'up',
        httpStatus: 200
    });

});

app.use(express.json());
app.use(userRoutes);

app.listen(PORT, () => {
    console.log(` Server starterd successfully at port ${PORT}` );
});
