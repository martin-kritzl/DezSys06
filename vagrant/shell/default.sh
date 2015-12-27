#!/bin/bash

echo "### START default.sh ###"

# cd to working directory

cd /home/vagrant

# install oracle-java8

echo "deb http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee /etc/apt/sources.list.d/webupd8team-java.list
echo "deb-src http://ppa.launchpad.net/webupd8team/java/ubuntu trusty main" | tee -a /etc/apt/sources.list.d/webupd8team-java.list
apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv-keys EEA14886
apt-get update

echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | sudo /usr/bin/debconf-set-selections
apt-get install -y oracle-java8-installer

# Download Apache ActiveMQ

wget http://www.eu.apache.org/dist/activemq/5.13.0/apache-activemq-5.13.0-bin.tar.gz
tar zxvf apache-activemq-5.13.0-bin.tar.gz

# run Apache ActiveMQ

cd apache-activemq-5.13.0
bin/activemq start

echo "### END default.sh ###"
