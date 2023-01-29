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
	int a=i;
	int i = 1;
	for (int x = 4;x <= 1;x++){
		printf("%s \n", "");
}
		printf("%s \n", messaggio);
}
void esercizio(){
	int x = 3;
float b = 2.2;
int a = 1;
	char *ans1 = (char*) malloc(sizeof(char) * 100) ,*taglia = (char*) malloc(sizeof(char) * 100) ;
	char * ans = (char*) malloc(sizeof(char) * 100);
strcpy(ans , "no");
	float risultato=sommac(a,x,b,&taglia);
	stampa("la somma  incrementata  è ");
		printf("%s \n", taglia);
	stampa(" ed è pari a ");
		printf("%f \n", risultato);
		printf("%s \n", "vuoi continuare? (si/no) - inserisci due volte la risposta");
		scanf("%s%s",ans,ans1);
	   while (strcmp(ans, "si") == 0){
	printf("inserisci un intero:");
	scanf("%d",&a);
	printf("inserisci un reale:");
	scanf("%f",&b);
	risultato = sommac(a,x,b,&taglia);
	stampa("la somma  incrementata  è ");
		printf("%s \n", taglia);
	stampa(" ed è pari a ");
		printf("%f \n", risultato);
	printf("vuoi continuare? (si/no):");
	scanf("%s",ans);
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
