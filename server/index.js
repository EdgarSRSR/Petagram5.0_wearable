/*

heroku open
heroku logs --tail

npm install --save --save-exact

git add .

git commit -m "Petagram5.0"

git push heroku master

heroku open android

Finally, check that everything is working:
$ heroku open 
*/

//Post
//https://gentle-hollows-16128.herokuapp.com


var express = require('express');
var app = express();

app.set('port', (process.env.PORT || 5000));

var bodyParser = require("body-parser");
app.use(bodyParser.json()) //soporte para codificar Json
app.use(bodyParser.urlencoded({extended: true}));// soporte para decodificar las urls

var firebase = require("firebase");

firebase.initializeApp({
	serviceAccount: "Petagram50-9a2dd22aa712.json",
	databaseURL: "https://petagram50.firebaseio.com/"
});

app.use(express.static(__dirname + '/public'));

// views is directory for all template files
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

app.get('/android', function(request, response) {
  response.render('pages/index');
});

//Post
//https://gentle-hollows-16128.herokuapp.com/token-device
//token
var registroUsuario = "token-device";
app.post("/" + registroUsuario, function(request, response){
	var idDispositivo = request.body.token;
	var idUsuario = request.body.userID;


	var db = firebase.database();
	var tokenDevices = db.ref(registroUsuario).push();
	tokenDevices.set({
		idDispositivo : idDispositivo,
		idUsuario: idUsuario
	});

	var path = tokenDevices.toString();
	var pathSplit = path.split(registroUsuario + "/");
	var idAutoGenerado = pathSplit[1];

	var respuesta = generarRespuestaAToken(db, idDispositivo, idUsuario);
	response.setHeader("Content-Type", "application/json");

	response.send(JSON.stringify(respuesta));
});

function generarRespuestaAToken(db, tokenDevice,usuarioInstagram){
	var respuesta = {};
	var usuario = "";
	var ref = db.ref("token-device");
	ref.on("child-added", function(snapshot, prevChildKey){
		usuario = snapshot.val();
		respuesta = {
			InstagramID: usuarioInstagram,
			token: tokenDevice
		};
	})

	return respuesta;
}

app.listen(app.get('port'), function() {
  console.log('Node app is running on port', app.get('port'));
});
