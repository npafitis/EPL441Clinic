FROM ubuntu:16.04

RUN apt-get update && apt-get install software-properties-common -y && apt-get install -y libmcrypt-dev \
   mysql-client libmagickwand-dev --no-install-recommends \
