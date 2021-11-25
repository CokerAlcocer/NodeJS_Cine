const exp = require('express');
const router = exp.Router();

router.get('/', async(req, res) => {
    console.log('Servidor en ejecición');
});

module.exports = router;