#!/bin/bash

function prepare {
	export JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
}

function make_c {
	cd src/main/c/ || exit 1
	LD_LIBRARY_PATH=../resources/
	gcc -c -Wall -Werror -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux"\
		-o stp_Native.o stp_Native.c 
	gcc -shared stp_Native.o -o libstpnative.so -Wl,--no-as-needed -lstp
	cp libstpnative.so ../resources/
	cd ../../../
}

function make_java {
	cd src/main/java/ || exit 1
	javac Main.java
	cd ../../../
}

function run {
	cd src/main/java || exit 1
	printf 'RUNNING...\n\n'
	java -cp . -Djava.library.path=../resources/ Main
	cd ../../../
}

set -x

prepare
make_c
make_java
run




