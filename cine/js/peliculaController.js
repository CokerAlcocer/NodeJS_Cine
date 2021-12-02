const url = 'http://localhost:8080/server_war_exploded';

const findAllMovies = async() => {
    await $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: url + '/pelicula'
    }).done(res => {
        let listPeliculas = res;
        let table = $('#table1');

        table.append(
            "<tr class='bg-dark text-white'>"+
                "<th scope='col'>Id</th>"+
                "<th scope='col'>Título</th>"+
                "<th scope='col'>Rating</th>"+
                "<th scope='col'>Categoria</th>"+
                "<th scope='col'>Detalles</th>"+
                "<th scope='col'>Editar</th>"+
                "<th scope='col'>Eliminar</th>"+
            "</tr>"
        );

        for(let i = 0; i < listPeliculas.length; i++){
            table.append(
                "<tr>"+
                    "<td>"+ listPeliculas[i].id +"</td>"+
                    "<td>"+ listPeliculas[i].titulo +"</td>"+
                    "<td>"+ listPeliculas[i].rating +"</td>"+
                    "<td>"+ listPeliculas[i].categoria +"</td>"+
                    "<td><button class='btn btn-primary' data-target='#info' data-toggle='modal' onclick='getInfo("+ listPeliculas[i].id +")'><span class='fas fa-info-circle'></span></button></td>"+
                    "<td><button class='btn btn-warning' data-target='#update' data-toggle='modal' onclick='getInfoUpdate("+ listPeliculas[i].id +")'><span class='fas fa-edit'></span></button></td>"+
                    "<td><button class='btn btn-danger' data-target='#remove' data-toggle='modal' onclick='removeById("+ listPeliculas[i].id +")'><span class='fas fa-trash'></span></button></td>"+
                "</tr>"
            );
        }
    });
}

findAllMovies();

const findById = async(id) => {
    return await $.ajax({
        type: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: url + '/pelicula/' + id
    }).done(res => res);
}

const getInfo = async(id) => {
    let pelicula = await findById(id);
    console.log(pelicula);
    document.getElementById('i_pel_estado').innerHTML=pelicula.estado? "Activo": "Inactivo";
    document.getElementById('i_pel_rating').innerHTML=pelicula.rating;
    document.getElementById('i_pel_categoria').innerHTML=pelicula.categoria;
    document.getElementById('i_pel_descripcion').innerHTML=pelicula.descripcion;
    document.getElementById('i_pel_sinopsis').innerHTML=pelicula.sinopsis;
}

const getInfoUpdate = async(id) => {
    let pelicula = await findById(id);
    document.getElementById('u_pel_id').value=pelicula.id;
    document.getElementById('u_pel_titulo').value=pelicula.titulo;
    document.getElementById('u_pel_rating').value=pelicula.rating;
    document.getElementById('u_pel_categoria').value=pelicula.categoria;
    document.getElementById('u_pel_descripcion').value=pelicula.descripcion;
    document.getElementById('u_pel_sinopsis').value=pelicula.sinopsis;
}

const removeById = async(id) => {
    let pelicula = await findById(id);
    document.getElementById('r_pel_id').value=pelicula.id;
}

const create = async() => {
    let pelicula = new Object();
    let today = new Date();
    let m = today.getMonth() + 1 < 10? "0"+(today.getMonth() + 1) : today.getMonth() + 1;
    let d = today.getDate() + 1 < 10? "0"+(today.getDate() + 1) : today.getDate() + 1;

    pelicula.titulo = document.getElementById('c_pel_titulo').value;
    pelicula.rating = document.getElementById('c_pel_rating').value;
    pelicula.categoria = document.getElementById('c_pel_categoria').value;
    pelicula.descripcion = document.getElementById('c_pel_descripcion').value;
    pelicula.sinopsis = document.getElementById('c_pel_sinopsis').value;
    pelicula.estado = 1;
    pelicula.registered = today.getFullYear()+'-'+m+'-'+d;

    await $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: url + '/pelicula/save',
        data: pelicula
    }).done(res => {
        console.log(res);
    });
}

const update = async() => {
    let pelicula = new Object();
    let today = new Date();
    let m = today.getMonth() + 1 < 10? "0"+(today.getMonth() + 1) : today.getMonth() + 1;
    let d = today.getDate() + 1 < 10? "0"+(today.getDate() + 1) : today.getDate() + 1;

    let id = document.getElementById('u_pel_id').value;
    pelicula.id = id;
    pelicula.titulo = document.getElementById('u_pel_titulo').value;
    pelicula.rating = document.getElementById('u_pel_rating').value;
    pelicula.categoria = document.getElementById('u_pel_categoria').value;
    pelicula.descripcion = document.getElementById('u_pel_descripcion').value;
    pelicula.sinopsis = document.getElementById('u_pel_sinopsis').value;
    pelicula.updated = today.getFullYear()+'-'+m+'-'+d;
    console.log(pelicula);

    await $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: url + '/pelicula/save/' + id,
        data: pelicula
    }).done(res => {
        console.log(res);
    });
}

const remove = async() => {
    let id = document.getElementById('r_pel_id').value;

    await $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: url + '/pelicula/delete/' + id
    }).done(res => {
        console.log(res);
    });
}