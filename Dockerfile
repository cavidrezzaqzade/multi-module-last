FROM ubuntu:latest
RUN apt-get -y update
RUN apt-get install -y tzdata
RUN apt-get -y install apache2
RUN apt-get install -y maven
RUN apt-get install -y vim
ADD . /var/mia_grps_dev
RUN cd /var/mia_grps_dev && mvn clean install
EXPOSE 80
EXPOSE 8023
EXPOSE 8024
EXPOSE 8026
EXPOSE 8033
EXPOSE 8057
CMD apachectl -D FOREGROUND