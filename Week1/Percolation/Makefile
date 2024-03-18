# make MAIN=HelloGoodbye ARGS="Name1 Name2"

JC = javac
JV = java
CLASSPATH = bin:lib/algs4.jar
SRC = src
BIN = bin
MAIN = PercolationVisualizer
ARGS = input20.txt

all: compile run

compile:
	$(JC) -d $(BIN) -cp $(CLASSPATH) $(SRC)/Percolation.java $(SRC)/$(MAIN).java

run: compile
	$(JV) -cp $(CLASSPATH) $(MAIN) $(ARGS)

clean:
	rm -f $(BIN)/*.class
