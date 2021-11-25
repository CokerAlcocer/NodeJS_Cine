const exp = require('express');
const router = exp.Router();
const pool = require('../database');

router.get('/', async(req, res) => {
    let listCategorias = await pool.query('SELECT * FROM categoria');

    if(listCategorias.length > 0){
        res.json({
            status: 200,
            message: "Se encontraron los registros",
            listCategorias: listCategorias
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
    let categoria = await pool.query('SELECT * FROM categoria WHERE id = ?', [id]);

    if(categoria.length > 0){
        res.json({
            status: 200,
            message: "Se encontro el registro",
            categoria: categoria
        });
    }else{
        res.json({
            status: 404,
            message: "No se encontro el registro"
        });
    }
});

router.post('/create', async(req, res) => {
    let { nombre } = req.body;
    let categoria = { nombre };

    await pool.query('INSERT INTO categoria SET ?', [categoria]);
    
    res.json({
        status: 200,
        message: "Se registro correctamente"
    });
});

router.post('/update/:id', async(req, res) => {
    let { id } = req.params; 
    let { nombre } = req.body;
    let categoria = { nombre };

    await pool.query('UPDATE categoria SET ? WHERE id = ?', [categoria, id]);

    res.json({
        status: 200,
        message: "Se modifico correctamente"
    });
});

router.post('/remove/:id', async(req, res) => {
    let { id } = req.params;

    await pool.query('UPDATE pelicula SET categoria = 0 WHERE categoria = ?', [id]);
    await pool.query('DELETE FROM categoria WHERE id = ?', [id]);

    res.json({
        status: 200,
        message: "Se elimino correctamente"
    });
});

module.exports = router;