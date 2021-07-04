function initPastoNoMap() {

	var googleMapsJson = $('#googleMapsJson').val() || '{}';

	if (!googleMapsJson) {
		return;
	}

	var googleMaps = JSON.parse(googleMapsJson);
	
	var coordenadas = googleMaps.coordenadas;
	
	if (!coordenadas || coordenadas.length == 0) {
		return;
	}
	
	var center = coordenadas[0];
	
	__coordenadas = [...coordenadas];
	
	__coordenadas.push( center );
	
	const mapPasto = new google.maps.Map(document.getElementById("mapa_pasto"), {
		zoom : googleMaps.zoom,
		center : center,
		mapTypeId : "satellite",
	});
	
	const pastoPolygon = new google.maps.Polygon({
		paths: __coordenadas,
		//strokeColor: '#585858',
        //fillColor: '#888888',
		strokeOpacity : 0.5,
        fillOpacity: 0.5,
		strokeWeight: 5,
	});
	pastoPolygon.setMap(mapPasto);
}

(function() {
	initPastoNoMap();
})();