const url = 'http://localhost:8080/server_war_exploded';

const findAll = async() => {
    await $.ajax({
        type: 'GET',
        headers: { "Accept": "application/json" },
        url: url + '/categoria'
    }).done(res => {
        let listCategorias = res;
        let table = $('#table2');

        table.append(
            "<tr class='bg-dark text-white'>"+
                "<th scope='col'>Id</th>"+
                "<th scope='col'>Nombre</th>"+
                "<th scope='col'>Editar</th>"+
                "<th scope='col'>Eliminar</th>"+
            "</tr>"
        );

        for(let i = 0; i < listCategorias.length; i++){
            table.append(
                "<tr>"+
                    "<td>"+ listCategorias[i].id +"</td>"+
                    "<td>"+ listCategorias[i].nombre +"</td>"+
                    "<td><button class='btn btn-warning' data-target='#update' data-toggle='modal' onclick='getInfoUpdate("+ listCategorias[i].id +")'><span class='fas fa-edit'></span></button></td>"+
                    "<td><button class='btn btn-danger' data-target='#remove' data-toggle='modal' onclick='removeById("+ listCategorias[i].id +")'><span class='fas fa-trash'></span></button></td>"+
                "</tr>"
            );
        }
    });
}

findAll();

const findById = async(id) => {
    return await $.ajax({
        type: 'GET',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: url + '/categoria/' + id
    }).done(res => res);
}

const getInfoUpdate = async(id) => {
    let categoria = await findById(id);
    document.getElementById('u_cat_id').value=categoria.id;
    document.getElementById('u_cat_nombre').value=categoria.nombre;
}

const removeById = async(id) => {
    let categoria = await findById(id);
    document.getElementById('r_cat_id').value=categoria.id;
}

const create = async() => {
    let categoria = new Object();
    categoria.nombre = document.getElementById('c_cat_nombre').value;

    await $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: url + '/categoria/save',
        data: categoria
    }).done(res => {
        console.log(res);
    });
}

const update = async() => {
    let categoria = new Object();
    let id = document.getElementById('u_cat_id').value;
    categoria.id = id;
    categoria.nombre = document.getElementById('u_cat_nombre').value;

    await $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: url + '/categoria/save/' + id,
        data: categoria
    }).done(res => {
        console.log(res);
    });
}

const remove = async() => {
    let id = document.getElementById('r_cat_id').value;

    await $.ajax({
        type: 'POST',
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/x-www-form-urlencoded"
        },
        url: url + '/categoria/delete/' + id
    }).done(res => {
        console.log(res);
    });
}