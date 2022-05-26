import express from 'express';

const app = express();
const env = process.env;
const PORT = process.PORT || 8082;


app.get('/api/status', (req, res) => {
    res.status(200).json({
        server: 'Sales API',
        status: 'up',
        httpStatus: 200
    });
});

app.listen(PORT, () => {

    console.log(` Server starterd successfully at port ${PORT}` )

});

