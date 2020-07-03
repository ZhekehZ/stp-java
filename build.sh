#!/bin/bash

build_dir=
sources=
resources=
csources=

function prepare {
	JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
	build_dir="$(pwd)/build/"
  mkdir -p "$build_dir"

  sources="$(pwd)/src/main/java/org/zhekehz/stpjava/"
  resources="$(pwd)/src/main/resources/"
  csources="$(pwd)/src/main/c/"
}

function generate_headers {
  javac -d "$build_dir" "$sources/Native.java"
  cd "$build_dir"
  javah -o stp_Native.h org.zhekehz.stpjava.Native
  mv stp_Native.h "$csources"
  cd -
}

function make_c {
	gcc -c -Wall -Werror -fPIC -I"$JAVA_HOME/include" -I"$JAVA_HOME/include/linux"\
		-o "$build_dir/stp_Native.o" "$csources/stp_Native.c"
	gcc -shared "$build_dir/stp_Native.o" -o "$build_dir/libstpnative.so" -Wl,--no-as-needed -lstp
	mv "$build_dir/libstpnative.so" "$resources/"
}

function clean {
	rm -rf "$build_dir"
}

# set -x

printf ">> BUILDING C SHARED LIBRARY... "
prepare
generate_headers
make_c
clean
printf ">> DONE."



