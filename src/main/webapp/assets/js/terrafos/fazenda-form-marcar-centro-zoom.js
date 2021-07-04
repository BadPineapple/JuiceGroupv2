var drawingManager;
var selectedShape;
arrayDeCoordenadasParaArmazenar = new Array();
cidadeHidden = "";
areaEmM2 = 0;

function clearSelection() {
    if (selectedShape) {
        selectedShape.setEditable(false);
        selectedShape = null;
        $('#areaTotal').val("");
        inicializarPoligono();
    }
}

function setSelection(shape) {
    clearSelection();
    selectedShape = shape;
    shape.setEditable(true);
}

function deleteSelectedShape() {
    if (selectedShape) {
        selectedShape.setMap(null);
        arrayDeCoordenadasParaArmazenar = new Array();
        $('#areaTotal').val('');
    }
}

function novoLocal() {
    geocoder.geocode({ 'address': $('#cidade').data('nome') + ', Brasil', 'region': 'BR' }, function(results, status) {
        if (status == google.maps.GeocoderStatus.OK) {
            if (results[0]) {
                var latitude = results[0].geometry.location.lat();
                var longitude = results[0].geometry.location.lng();
                var location = new google.maps.LatLng(latitude, longitude);
                map.setCenter(location);
                map.setZoom(14);
            }
        }
    });
}

function initialize() {
	console.log('google maps initialize()');
	
	$(document).ready(function() {
		
		__consultarPastos(function(fazendaPastosNoMapa) {
			
			console.log('fazendaPastosNoMapa', fazendaPastosNoMapa);
		    
			var zoom = fazendaPastosNoMapa.zoom || 5;
			var center = fazendaPastosNoMapa.center || { lat: -13.752, lng: -44.604 };
			
			const map = new google.maps.Map(document.getElementById("map_canvas"), {
				zoom : zoom,
				center : center,
				mapTypeId : "satellite",
			});
			
			__initMarcarCentro(map, center);
			
			google.maps.event.addListener(map, 'zoom_changed', function() {
		        var zoom = map.getZoom();
		        console.log('zoom: ', zoom);
		        
		        var googleMapsJson = JSON.parse( $('#googleMapsJson').val() || '{}' );
		        googleMapsJson.zoom = zoom;
		        console.log('googleMapsJson', googleMapsJson);
		        $('#googleMapsJson').val( JSON.stringify( googleMapsJson ) );
		        
		    });
		    
			__initPastosNoMap(map, fazendaPastosNoMapa);
			
		});
		
		
		
	});

}

function __consultarPastos(callback) {
	
	if( ! FAZENDA_ID ) {
		callback({});
		return;
	}
	
	var url = ENDERECO_SITE+'/ilionnet/terrafos/fazendas/'+FAZENDA_ID+'/regioes-dos-pastos';
	$.getJSON(url, function(result){
	    
		var fazendaPastosNoMapa = result.data;
	    
	    callback(fazendaPastosNoMapa);
	    
	});
	
}

function __initMarcarCentro(map, center) {
	
	// Create the initial InfoWindow.
  let infoWindow = new google.maps.InfoWindow({
    content: "Marque no mapa o centro da fazenda!",
    position: center,
  });
  infoWindow.open(map);
  // Configure the click listener.
  map.addListener("click", (mapsMouseEvent) => {
    // Close the current InfoWindow.
    infoWindow.close();
    // Create a new InfoWindow.
    infoWindow = new google.maps.InfoWindow({
      position: mapsMouseEvent.latLng,
    });
    infoWindow.setContent('Aqui é o centro da fazenda.'
      //JSON.stringify(mapsMouseEvent.latLng.toJSON(), null, 2)
    );
    infoWindow.open(map);
    
    var googleMapsJson = JSON.parse( $('#googleMapsJson').val() || '{}' );
    googleMapsJson.center = mapsMouseEvent.latLng.toJSON();
    console.log('googleMapsJson', googleMapsJson);
    $('#googleMapsJson').val( JSON.stringify( googleMapsJson ) );
  });
	
}

function __initPastosNoMap(map, fazendaPastosNoMapa) {

	if ( ! fazendaPastosNoMapa.pastos || fazendaPastosNoMapa.pastos.length == 0) {
		return;
	}
	
	for(var i=0;i<fazendaPastosNoMapa.pastos.length;i++) {
		var __mapa = fazendaPastosNoMapa.pastos[i];
		
		if( ! __mapa ) {
			continue;
		}
		
		var primeiroPonto = __mapa.coordenadas[0];
		
		var __coordenadas = [...__mapa.coordenadas];
		console.log('__coordenadas', __coordenadas);
		
		__coordenadas.push( primeiroPonto );
		
		var color = '#fff';
		
		const pastoPolygon = new google.maps.Polygon({
			paths: __coordenadas,
			strokeColor: color,
	        fillColor: color,
			strokeOpacity : 0.5,
	        fillOpacity: 0.5,
			strokeWeight: 5,
		});
		pastoPolygon.setMap(map);
		
	}
	
	
}

(function() {
	initialize();
})();


