/*

heroku open
heroku logs --tail

npm install --save --save-exact

git add .

git commit -m "Petagram5.0 tarea 2 semana 3"

git push heroku master

heroku open android

Finally, check that everything is working:
$ heroku open 
*/

//Post
//https://gentle-hollows-16128.herokuapp.com


var express = require('express');
var app = express();

var bodyParser = require("body-parser");
app.use(bodyParser.json()) //soporte para codificar Json
app.use(bodyParser.urlencoded({extended: true}));// soporte para decodificar las urls

var firebase = require("firebase");

firebase.initializeApp({
	serviceAccount: "Petagram50-9a2dd22aa712.json",
	databaseURL: "https://petagram50.firebaseio.com/"
});


app.set('port', (process.env.PORT || 5000));

app.use(express.static(__dirname + '/public'));

// views is directory for all template files
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

//GET
//https://gentle-hollows-16128.herokuapp.com/token-device
app.get('/android', function(request, response) {
  response.render('pages/index');
});

//Post
//https://gentle-hollows-16128.herokuapp.com/token-device
//token
var tokenDeviceUri = "registrar-usuario";
app.post("/" + registroUsuario, function(request, response){
	var idDispositivo = request.body.idDispositivo;
	var idUsuarioInstagram = request.body.idUsuarioInstagram;


	var db = firebase.database();
	var tokenDevices = db.ref("registrar-usuario").push();
	
	tokenDevices.set({
		idDispositivo : idDispositivo,
		idUsuarioInstagram: idUsuarioInstagram
	});

	var path = tokenDevices.toString();
	var pathSplit = path.split(tokenDeviceUri + "/");
	var idAutoGenerado = pathSplit[1];

	var respuesta = generarRespuestaAToken(db, idAutogenerado);
	response.setHeader("Content-Type", "application/json");

	response.send(JSON.stringify(respuesta));
});

function generarRespuestaAToken(db, idAutogenerado){
	var respuesta = {};
	var usuario = "";
	var ref = db.ref(tokenDeviceUri);
	ref.on("child-added", function(snapshot, prevChildKey){
		usuario = snapshot.val();
		respuesta = {
			id: idAutogenerado,
			idDispositivo: usuario.idDispositivo,
			idUsuarioInstagram: usuario.idUsuarioInstagram
		};
	})

	return respuesta;
}

app.listen(app.get('port'), function() {
  console.log('Node app is running on port', app.get('port'));
});
