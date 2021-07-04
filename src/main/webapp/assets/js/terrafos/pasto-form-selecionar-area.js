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
		
		var url = ENDERECO_SITE+'/ilionnet/terrafos/fazendas/'+FAZENDA_ID+'/regioes-dos-pastos';
		$.getJSON(url, function(result){
		    var fazendaPastosNoMapa = result.data;
		    
		    console.log('fazendaPastosNoMapa', fazendaPastosNoMapa);
		    
			var zoom = fazendaPastosNoMapa.zoom || 5;
			var center = fazendaPastosNoMapa.center || { lat: -13.752, lng: -44.604 };
			
			const mapPasto = new google.maps.Map(document.getElementById("map_canvas"), {
				zoom : zoom,
				center : center,
				mapTypeId : "satellite",
			});
		    
			__initPastosNoMap(mapPasto, fazendaPastosNoMapa);
			
			__inicializarPoligono(mapPasto);
		});
		
	});

}

function __initPastosNoMap(map, fazendaPastosNoMapa) {

	if ( ! fazendaPastosNoMapa.pastos || fazendaPastosNoMapa.pastos.length == 0) {
		return;
	}
	
	for(var i=0;i<fazendaPastosNoMapa.pastos.length;i++) {
		var __mapa = fazendaPastosNoMapa.pastos[i];
		
		if( ! __mapa || ! __mapa.coordenadas ) {
			continue;
		}
		
		var primeiroPonto = __mapa.coordenadas[0];
		
		var __coordenadas = [...__mapa.coordenadas];
		console.log('__coordenadas', __coordenadas);
		
		__coordenadas.push( primeiroPonto );
		
		var color = parseInt(PASTO_ID) === __mapa.idPasto ? '#000' : '#fff';
		
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

function __inicializarPoligono(map) {
    var polyOptions = {
		//strokeColor: '#585858',
        //fillColor: '#888888',
		strokeOpacity : 0.5,
        fillOpacity: 0.5,
		strokeWeight: 5,
        editable: true
    };
    // Creates a drawing manager attached to the map that allows the user to draw
    // markers, lines, and shapes.
    drawingManager = new google.maps.drawing.DrawingManager({
        drawingMode: google.maps.drawing.OverlayType.POLYGON,
        markerOptions: {
            draggable: true,
        },
        drawingControl: false,
        polylineOptions: {
            editable: true
        },
        polygonOptions: polyOptions,
        map: map
    });

    google.maps.event.addListener(drawingManager, 'overlaycomplete', function(e) {
        if (e.type != google.maps.drawing.OverlayType.MARKER) {
            // Switch back to non-drawing mode after drawing a shape.
            drawingManager.setDrawingMode(null);

            // Add an event listener that selects the newly-drawn shape when the user
            // mouses down on it.
            var newShape = e.overlay;

            calcularAreaEmM2(e.overlay);

            inserirCoordenadasDaArea(e.overlay)

            newShape.type = e.type;
            google.maps.event.addListener(newShape, 'click', function() {
                setSelection(newShape);
            });
            setSelection(newShape);
        }
    });
    
    google.maps.event.addListener(map, 'zoom_changed', function() {
        var zoom = map.getZoom();
        console.log('zoom: ', zoom);
        var googleMapsJson = JSON.parse( $('#googleMapsJson').val() || '{}' );
        
        googleMapsJson.zoom = zoom;
        
        $('#googleMapsJson').val( JSON.stringify( googleMapsJson ) );
    });

    // Clear the current selection when the drawing mode is changed, or when the
    // map is clicked.
    google.maps.event.addListener(map, 'click', clearSelection);
    google.maps.event.addDomListener(document.getElementById('botaoLimparPontos'), 'click', deleteSelectedShape);
}

function inserirCoordenadasDaArea(overlay) {

    //arrayDeCoordenadasParaArmazenar = overlay.getPath().getArray();

    var i = 0;

    for (i = 0; i < overlay.getPath().getArray().length; i++) {

        arrayDeCoordenadasParaArmazenar.push(overlay.getPath().getArray()[i]);

    }
    
    var coordenadasJson = JSON.stringify(arrayDeCoordenadasParaArmazenar);
    
    console.log('coordenadasJson: '+coordenadasJson);
    
    var googleMapsJson = JSON.parse( $('#googleMapsJson').val() || '{}' );
    
    googleMapsJson.coordenadas = arrayDeCoordenadasParaArmazenar;
    
    $('#googleMapsJson').val( JSON.stringify( googleMapsJson ) );
}

function calcularAreaEmM2(overlay) {
    var areaEmM2 = google.maps.geometry.spherical.computeArea(overlay.getPath().getArray());
    
    console.log('areaEmM2', areaEmM2);
    
    var areaEmHa = parseFloat( areaEmM2/10000 ).toFixed(2);;
    
    $('#areaTotal').val(areaEmHa);
}

(function() {
	initialize();
})();


