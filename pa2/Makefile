#------------------------------------------------------------------------------
# Makefile for CMPS 101 Programming Assignment 2
#
#       make                   makes Lex
#       make ListClient        makes ListClient
#------------------------------------------------------------------------------

Lex : Lex.o List.o
	gcc -g -o Lex Lex.o List.o

Lex.o : Lex.c List.h
	gcc -g -c -std=c99 -Wall Lex.c

ListTest: ListTest.o List.o
	gcc -g -o ListTest ListTest.o List.o

ListClient: ListClient.o List.o
	gcc -g -o ListClient ListClient.o List.o

ListTest.o : ListTest.c List.h
	gcc -g -c -std=c99 -Wall ListTest.c

ListClient.o : ListClient.c List.h
	gcc -g -c -std=c99 -Wall ListClient.c

List.o : List.c List.h
	gcc -g -c -std=c99 -Wall -g List.c

clean :
	rm -f Lex ListClient Lex.o ListClient.o List.o
