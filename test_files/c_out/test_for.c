#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//prototipi funzioni
void test();


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

	int i=30;
	bool  a = true;
	bool  b = false;
	char * c = (char*) malloc(sizeof(char) * 100);
strcpy(c , "true");
	int d = 3;
	float f = 4.8;
void test(){
	for (int i = 8;i <= 7;i++){
		printf("%s \n", "se vuoi fare la somma scrivi 1 ");
}
		printf("%s %d \n", "fuori for ", i);
}
int main(){
int intero=0;
char carattere=' ';
float float1=0;
char *stringa="";
bool booleano=false;
test();
return 0;
}
