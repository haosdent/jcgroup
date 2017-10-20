# Makefile for jcgroup

ifndef JAVA_HOME
	JAVA_HOME=$(shell readlink -f /usr/bin/javac | sed "s:bin/javac::")
endif
CC=gcc
CFLAGS=-fPIC -lc -shared -I${JAVA_HOME}/include/ -I${JAVA_HOME}/include/linux/
OUTPUT=libThreads.so

SOURCES_DIR=src/main/native
OBJECTS_DIR=target/native/

SOURCES=$(shell find '$(SOURCES_DIR)' -type f -name '*.c')

all: 	
	mkdir -p $(OBJECTS_DIR)
	$(CC) $(CFLAGS) $(SOURCES) -o $(OBJECTS_DIR)$(OUTPUT)

clean:
	rm -rf $(OBJECTS_DIR)
