const exp = require('express');
const router = exp.Router();
const pool = require('../database');
const { database } = require('../keys');

router.get('/', async(req, res) => {
    let listPeliculas = await pool.query('SELECT * FROM pelicula');

    if(listPeliculas.length > 0){
        res.json({
            status: 200,
            message: "Se encontraron los registros",
            listPeliculas: listPeliculas
        });
    }else{
        res.json({
            status: 404,
            message: "No se encontraron registros"
        });
    }
});

router.get('/:id', async(req, res) => {
    let { id } = req.params;
    let pelicula = await pool.query('SELECT * FROM pelicula WHERE id = ?', [id]);

    if(pelicula.length > 0){
        res.json({
            status: 200,
            message: "Se encontro el registro",
            pelicula: pelicula
        });
    }else{
        res.json({
            status: 404,
            message: "No se encontro el registro"
        });
    }
});

router.post('/create', async(req, res) => {
    let { titulo, descripcion, sinopsis, rating, categoria } = req.body;
    
    if(rating >= 0 && rating <= 10){
        let today = new Date();

        let d = today.getDate() > 10? "0"+today.getDate() : today.getDate();
        let m = today.getMonth()+1 > 10? "0"+(today.getMonth()+1) : today.getMonth()+1;
        let y = today.getFullYear();
        let registered = y+'-'+m+'-'+d;
        let estado = 1;
        
        let pelicula = { titulo, descripcion, sinopsis, rating, registered, estado, categoria };

        await pool.query('INSERT INTO pelicula SET ?', [pelicula]);

        res.json({
            status: 200,
            message: "Se registro correctamente"
        });
    }else{
        res.json({
            status: 400,
            message: "El rating de la pelicula debe estar entre 1 y 10"
        });
    }
});

router.post('/update/:id', async(req, res) => {
    let { id } = req.params; 
    let { titulo, descripcion, sinopsis, rating, categoria } = req.body;
    let today = new Date();

    let d = today.getDate() > 10? "0"+today.getDate() : today.getDate();
    let m = today.getMonth()+1 > 10? "0"+(today.getMonth()+1) : today.getMonth()+1;
    let y = today.getFullYear();
    let updated = y+'-'+m+'-'+d;
    
    let pelicula = { titulo, descripcion, sinopsis, rating, updated, categoria };

    await pool.query('UPDATE pelicula SET ? WHERE id = ?', [pelicula, id]);

    res.json({
        status: 200,
        message: "Se modifico correctamente"
    });
});

router.post('/remove/:id', async(req, res) => {
    let { id } = req.params;

    await pool.query('UPDATE pelicula SET estado = 0 WHERE id = ?', [id]);

    res.json({
        status: 200,
        message: "Se elimino correctamente"
    });
});

module.exports = router;