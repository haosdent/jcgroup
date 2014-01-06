CC=gcc
CFLAGS=-fPIC -lc -shared -I/usr/lib/jvm/java-7-oracle/include/ -I/usr/lib/jvm/java-7-oracle/include/linux/
OUTPUT=libThreads.so

SOURCES_DIR=src/main/native
OBJECTS_DIR=target/native/

SOURCES=$(shell find '$(SOURCES_DIR)' -type f -name '*.c')

all: 	
	mkdir -p $(OBJECTS_DIR)
	$(CC) $(CFLAGS) $(SOURCES) -o $(OBJECTS_DIR)$(OUTPUT)

clean:
	rm -rf $(OBJECTS_DIR)
