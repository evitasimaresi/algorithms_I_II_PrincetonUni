# make MAIN=HelloGoodbye ARGS="Name1 Name2"

JC = javac
JV = java
CLASSPATH = bin
SRC = src
BIN = bin
all: compile run

compile:
	$(JC) -d $(BIN) $(SRC)/$(MAIN).java

run: compile
	$(JV) -cp $(CLASSPATH) $(MAIN) $(ARGS)

clean:
	rm -f $(BIN)/*.class
