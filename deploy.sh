#!/bin/bash

CUSTOMER=pcmfarma
WARNAME=ROOT
CLEANCOMMAND='rm -rf /opt/tomcat8/webapps/*'
WARDEST=/opt/tomcat8/webapps
TEMP_DEST=/home/ilion/temp
RESTARTCOMMAND='sudo service tomcat8 restart'

echo "# BACKUP DOS PROPERTIES DE DESENVOLVIMENTO"
cp src/main/resources/application.properties application.properties_dev
cp src/main/resources/jdbc.properties jdbc.properties_dev

echo "# COPIANDO PROPERTIES DE PRODUÇÃO"
cp util/producao/application.properties src/main/resources/application.properties
cp util/producao/jdbc.properties src/main/resources/jdbc.properties


#echo "# EXECUTANDO TESTES"
#mvn test

echo "# CRIANDO WAR"
mvn clean package

echo "# RESTAURANDO PROPERTIES DE DESENVOLVIMENTO"
mv application.properties_dev src/main/resources/application.properties
mv jdbc.properties_dev src/main/resources/jdbc.properties
mv prop.properties_dev src/main/resources/prop.properties


echo "# COPIANDO BUILD"
BUILD_NAME=$CUSTOMER-1.0.0

cp target/$BUILD_NAME.war ./$WARNAME.war

echo "# LIMPANDO LIXO"
rm -rf target

echo "# CRIANDO PASTA TEMPORÁRIA"
ssh $CUSTOMER "mkdir -p ~/temp"

echo "# ENVIANDO ARQUIVO PARA O SERVIDOR"
scp -rv $WARNAME.war $CUSTOMER:$TEMP_DEST

echo "# MATANDO PROCESSOS RELATIVOS AO TOMCAT"
ssh $CUSTOMER "sudo pkill -f tomcat"

echo "# LIMPANDO PASTA WEBAPPS"
ssh $CUSTOMER $CLEANCOMMAND

echo "# MOVENDO WAR PARA A PASTA WEBAPPS"
ssh $CUSTOMER "mv ~/temp/$WARNAME.war /opt/tomcat8/webapps"

echo "# REINICIANDO TOMCAT"
ssh -t $CUSTOMER $RESTARTCOMMAND
