#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>
#include <math.h>
//prototipi funzioni
float sommac(int a,int d,float b,char ** size);
void stampa(char * messaggio);
void esercizio();


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

	int c = 1;
float sommac(int a,int d,float b,char ** size){
	float result;
	result = a + b + c + d;
	if(result > 100){
	char * valore = (char*) malloc(sizeof(char) * 100);
strcpy(valore , "grande");
	*size = valore;
}
else{
	char * valore = (char*) malloc(sizeof(char) * 100);
strcpy(valore , "piccola");
	*size = valore;
}
	return result;
}
void stampa(char * messaggio){
	int a;
	int i;
	for (int x = 4;x <= 1;x++){
		printf("%s \n", "");
}
		printf("%s \n", messaggio);
}
void esercizio(){
	int ans = 0;
	int c = 2;
	int a,x;
	float b;
	char *taglia = (char*) malloc(sizeof(char) * 100) ;
	float risultato;
	a = 1;
b = 2.2;
x = 3;
		printf("%d \n", c);
	risultato = sommac(a,x,b,&taglia);
	stampa("la somma  incrementata  è ");
		printf("%s \n", taglia);
	stampa(" ed è pari a ");
		printf("%f \n", risultato);
		printf("%s \n", "vuoi continuare? (1/si 0/no)");
		scanf("%d",&ans);
	   while (ans == 1){
	printf("inserisci un intero:");
	scanf("%d",&a);
	printf("inserisci un reale:");
	scanf("%f",&b);
	risultato = sommac(a,x,b,&taglia);
	stampa("la somma  incrementata  è ");
		printf("%s \n", taglia);
	stampa(" ed è pari a ");
		printf("%f \n", risultato);
	printf("vuoi continuare? (1/si 0/no):");
	scanf("%d",&ans);
}
		printf("%s \n", "");
		printf("%s \n", "ciao");
}
int main(){
int intero=0;
char carattere=' ';
float float1=0;
char *stringa="";
bool booleano=false;
esercizio();
return 0;
}
