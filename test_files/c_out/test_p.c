#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//prototipi funzioni
void fun(int v);
void esempio();


char  *conversioneFloat(float number){
  
 char *buf = malloc(10*sizeof(char));
 sprintf (buf, "%f", number);
  return buf;
}
char * conversioneInt(int number){
 char *buf = malloc(10*sizeof(char));
 sprintf (buf, "%d", number);
  return buf;
}char supporto[100];

void fun(int v){
}
void esempio(){
}
int main(){
int intero=0;
char carattere=' ';
float float1=0;
char *stringa="";
bool booleano=false;
esempio();
return 0;
}
