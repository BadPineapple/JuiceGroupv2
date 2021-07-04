#!/bin/bash

function _build {
	echo "building: $1"
}

function build {
	echo "efetuando backup dos properties de desenvolvimento"
	cp src/main/resources/application.properties application.properties_dev
	cp src/main/resources/jdbc.properties jdbc.properties_dev
	
	echo "copiando properties de produção"
	cp util/producao/application.properties src/main/resources/application.properties
	cp util/producao/jdbc.properties src/main/resources/jdbc.properties
	
	echo "criando war..."
	mvn clean package -Dmaven.test.skip=true
	
	echo "restaurando backup dos properties de desenvolvimento"
	mv application.properties_dev src/main/resources/application.properties
	mv jdbc.properties_dev src/main/resources/jdbc.properties
	
	BUILD_FOLDER="build"
	
	if [ ! -d "$BUILD_FOLDER" ]; then
	    echo "criando diretorio $BUILD_FOLDER"
	    mkdir $BUILD_FOLDER
	fi
	
	echo "excluindo builds antigos"
	rm $BUILD_FOLDER/* -r
	
	echo "copiando build"
	BUILD_NAME="$1-1.0.0"
	
	cp target/vitazure-1.0.0.war $BUILD_FOLDER/$BUILD_NAME.war
	cp -r target/vitazure-1.0.0 $BUILD_FOLDER/$BUILD_NAME
	
	echo "limpando e recompilando para desenvolvimento"
	mvn clean compile
	
	echo "fim"
}


CLIENTE="vitazure"

if [ "$CLIENTE" = "" ]; then
    echo "necessário passar o nome do cliente"
else
	build $CLIENTE
fi
