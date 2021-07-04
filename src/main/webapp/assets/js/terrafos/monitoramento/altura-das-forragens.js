/* var pasto1 = [{"lat":-16.68064266629585,"lng":-49.256339977185064},{"lat":-16.673653932751638,"lng":-49.26397890845948},{"lat":-16.67036502860998,"lng":-49.25762743751221},{"lat":-16.67077614471961,"lng":-49.253078411022955}];
var pasto2 = [{"lat":-16.682369255238747,"lng":-49.257284114758306},{"lat":-16.691084180460415,"lng":-49.26191897193604},{"lat":-16.68582238634648,"lng":-49.247070262829595}];
var pasto3 = [{"lat":-16.67718944163916,"lng":-49.27616686622315},{"lat":-16.68253369146775,"lng":-49.275308559338384},{"lat":-16.686315685698442,"lng":-49.272046993176275},{"lat":-16.68623346922818,"lng":-49.26698298255616},{"lat":-16.681875945703194,"lng":-49.25814242164307}];

var pastos = [pasto1, pasto2, pasto3]; */

function initPastosNoMap(fazendaPastosNoMapa) {

	if ( ! fazendaPastosNoMapa.pastos || fazendaPastosNoMapa.pastos.length == 0) {
		return;
	}
	
	var zoom = fazendaPastosNoMapa.zoom;
	var center = fazendaPastosNoMapa.center;
	
	const map = new google.maps.Map(document.getElementById("mapa_pasto"), {
		zoom : zoom,
		center : center,
		mapTypeId : "satellite",
	});
	
	for(var i=0;i<fazendaPastosNoMapa.pastos.length;i++) {
		var pastoMapaVH = fazendaPastosNoMapa.pastos[i];
		
		if( ! pastoMapaVH ) {
			continue;
		}
		
		var primeiroPonto = pastoMapaVH.coordenadas[0];
		
		var __coordenadas = [...pastoMapaVH.coordenadas];
		console.log('__coordenadas', __coordenadas);
		
		__coordenadas.push( primeiroPonto );
		
		const pastoPolygon = new google.maps.Polygon({
			paths: __coordenadas,
			strokeColor: pastoMapaVH.cor,
	        fillColor: pastoMapaVH.cor,
			strokeOpacity : 0.5,
	        fillOpacity: 0.5,
			strokeWeight: 5,
		});
		pastoPolygon.setMap(map);
		
		let infoContent = `Nome: ${pastoMapaVH.nome}`;
		infoContent += `<br/>Área total: ${pastoMapaVH.areaTotal} Ha`;
		infoContent += `<br/>Total de animais: ${pastoMapaVH.totalDeAnimais}`;
		infoContent += `<br/>Peso Médio: ${pastoMapaVH.pesoMedio} Kg`;
		infoContent += `<br/>Categoria de animais / Qtd.:`;
		
		pastoMapaVH.categoriaAnimaisQtd.forEach((c) => {
			infoContent += `<br/>${c.nome}: ${c.total}`;
		});
		
		let infowindow = new google.maps.InfoWindow({
			content: infoContent,
			position: primeiroPonto,
		});
		
		pastoPolygon.addListener('mouseover', function() {
		    infowindow.open(map, this);
		});

		// assuming you also want to hide the infowindow when user mouses-out
		pastoPolygon.addListener('mouseout', function() {
		    infowindow.close();
		});
		
		$('#conteudo').append('<pre>Pasto: '+pastoMapaVH.nome+', medições: '+JSON.stringify(pastoMapaVH.alturaDasForragens)+'</pre>');
		
	}
	
	
}

/* (function() {
	initPastoNoMap();
})(); */


$(document).ready(function() {
	
	var url = ENDERECO_SITE+'/ilionnet/terrafos/monitoramento/altura-das-forragens';
	$.getJSON(url, function(result){
	    var fazendaPastosNoMapa = result.data;
	    
	    console.log('fazendaPastosNoMapa', fazendaPastosNoMapa);
	    
	    initPastosNoMap(fazendaPastosNoMapa);
	});
	
});